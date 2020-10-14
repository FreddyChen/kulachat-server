package com.freddy.kulachat.core.auth;

import com.freddy.kulachat.core.auth.annotation.RequestUserAnnotation;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


/**
 * @author FreddyChen
 * @name
 * @date 2020/10/12 20:35
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class RequestUserHandlerMethodArgReslover implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(RequestUserAnnotation.class) != null && parameter.getParameterType() == RequestUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return request.getSession().getAttribute(RequestUser.KEY_REQUEST_USER);
    }
}
