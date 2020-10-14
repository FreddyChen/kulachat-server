package com.freddy.kulachat.core.interceptor;

import com.freddy.kulachat.core.auth.RequestUser;
import com.freddy.kulachat.core.auth.annotation.AuthTokenAnnotation;
import com.freddy.kulachat.core.auth.utils.JWTUtil;
import com.freddy.kulachat.core.config.NetworkConfig;
import com.freddy.kulachat.core.net.RetCode;
import com.freddy.kulachat.core.net.RetResponse;
import com.freddy.kulachat.core.service.RedisTemplateService;
import com.freddy.kulachat.utils.JsonMapper;
import com.freddy.kulachat.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author FreddyChen
 * @name 自定义的请求拦截器，所有接口请求都会进行拦截
 * @date 2020/09/24 14:49
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding(NetworkConfig.CHARACTER_ENCODING);
        response.setContentType(NetworkConfig.CONTENT_TYPE);

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 如果不需要token验证，直接放行
        // 如果需要token验证，则流程如下：
        // 1.判断请求头的token是否为空，如果为空，返回失败（token为空），否则执行2
        // 2.从请求头的token里取出userId，如果userId为空，返回失败（用户不存在），否则执行3
        // 3.根据userId在redis查询token，若redis不存在此token，返回失败（token过期），否则执行4
        // 4.通过JWT验证token，验证不通过，返回失败（token无效），否则执行5
        // 5.验证通过，对token进行续期，也就是更新redis里token的过期时间，返回成功
        String token = request.getHeader(NetworkConfig.PARAM_USER_TOKEN);
        AuthTokenAnnotation annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthTokenAnnotation.class);
        if(annotation != null && annotation.required()) {
            if(StringUtil.isEmpty(token)) {
                response.getWriter().println(JsonMapper.toJSONString(RetResponse.onFailed(RetCode.TOKEN_IS_EMPTY)));
                return false;
            }

            try {
                Long userId = JWTUtil.getUserId(token);
                if(userId == null) {
                    response.getWriter().println(JsonMapper.toJSONString(RetResponse.onFailed(RetCode.USER_DOES_NOT_EXIST)));
                    return false;
                }

                String redisTokenKey = NetworkConfig.PARAM_USER_TOKEN + userId;
                String redisToken = redisTemplateService.get(redisTokenKey, String.class);
                if(StringUtil.isEmpty(redisToken)) {
                    response.getWriter().println(JsonMapper.toJSONString(RetResponse.onFailed(RetCode.TOKEN_EXPIRED)));
                    return false;
                }

                boolean verified = JWTUtil.verify(token);
                if(!verified) {
                    response.getWriter().println(JsonMapper.toJSONString(RetResponse.onFailed(RetCode.TOKEN_INVALID)));
                    return false;
                }

                RequestUser requestUser = new RequestUser(userId, token);
                request.getSession().setAttribute(RequestUser.KEY_REQUEST_USER, requestUser);
                // token续期
                redisTemplateService.expire(redisTokenKey, JWTUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
                return true;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
