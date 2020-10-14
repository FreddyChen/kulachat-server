package com.freddy.kulachat.core.controller;

import com.freddy.kulachat.core.auth.RequestUser;
import com.freddy.kulachat.core.auth.annotation.AuthTokenAnnotation;
import com.freddy.kulachat.core.auth.annotation.RequestUserAnnotation;
import com.freddy.kulachat.core.auth.utils.JWTUtil;
import com.freddy.kulachat.core.config.NetworkConfig;
import com.freddy.kulachat.core.entity.User;
import com.freddy.kulachat.core.net.RetCode;
import com.freddy.kulachat.core.net.RetResponse;
import com.freddy.kulachat.core.net.RetResult;
import com.freddy.kulachat.core.service.RedisTemplateService;
import com.freddy.kulachat.core.service.UserService;
import com.freddy.kulachat.utils.MapUtil;
import com.freddy.kulachat.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/22 20:20
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Autowired
    private RedisTemplateService redisTemplateService;
    private static final String DEFAULT_VERIFICATION_CODE = "000000";

    @RequestMapping(value = NetworkConfig.FUNC_USER_GET_VERIFY_CODE, method = RequestMethod.POST)
    public RetResult getVerifyCode(@RequestBody Map<String, Object> params) {
        logger.debug("getVerifyCode() params = " + params);
        return RetResponse.onSucceed();
    }

    @RequestMapping(value = NetworkConfig.FUNC_USER_LOGIN, method = RequestMethod.POST)
    public RetResult login(@RequestBody Map<String, Object> params) {
        logger.debug("login() params = " + params);
        if (MapUtil.isEmpty(params)) {
            return RetResponse.onFailed(RetCode.PARAMS_IS_EMPTY);
        }

        String phone = (String) params.get(NetworkConfig.PARAM_USER_PHONE);
        if (StringUtil.isEmpty(phone)) {
            return RetResponse.onFailed(RetCode.PHONE_IS_EMPTY);
        }
        if (!StringUtil.isPhone(phone)) {
            return RetResponse.onFailed(RetCode.ILLEGAL_PHONE);
        }

        String code = (String) params.get(NetworkConfig.PARAM_USER_VERIFY_CODE);
        if (StringUtil.isEmpty(code)) {
            return RetResponse.onFailed(RetCode.VERIFICATION_CODE_IS_EMPTY);
        }

        if (code.length() != 6 || !DEFAULT_VERIFICATION_CODE.equals(code)) {
            return RetResponse.onFailed(RetCode.VERIFICATION_CODE_IS_INCORRECT);
        }

        User user = userService.queryUserByPhone(phone);
        if (user == null) {// 如果不存在，直接注册
            user = new User();
            user.setPhone(phone);
            userService.register(user);
        }

        Long userId = user.getUserId();
        // 生成token
        String token = JWTUtil.sign(user.getUserId(), user.getPhone());
        if (StringUtil.isEmpty(token)) {
            return RetResponse.onFailed(RetCode.TOKEN_IS_EMPTY);
        }

        // token存到redis
        redisTemplateService.set(NetworkConfig.PARAM_USER_TOKEN + userId, token, JWTUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
        Map<String, Object> result = new HashMap<>(2);
        result.put(NetworkConfig.PARAM_USER, user);
        result.put(NetworkConfig.PARAM_USER_TOKEN, token);

        return RetResponse.onSucceed(result);
    }

    @AuthTokenAnnotation
    @RequestMapping(value = "/test.action", method = RequestMethod.POST)
    public RetResult test() {
        return RetResponse.onSucceed();
    }

    @AuthTokenAnnotation
    @RequestMapping(value = NetworkConfig.FUNC_USER_COMPLETE_INFO, method = RequestMethod.POST)
    public RetResult completeInfo(@RequestUserAnnotation RequestUser requestUser, @RequestBody Map<String, Object> params) {
        logger.debug("completeInfo() requestUser = " + requestUser + "\tparams = " + params);
//        User user = userService.queryUserByUserId(requestUser.getUserId());
//        if(user == null) {
//            return RetResponse.onFailed();
//        }

        User user = new User();
        user.setUserId(requestUser.getUserId());

        String nickname = (String) params.get(NetworkConfig.PARAM_USER_NICKNAME);
        if(StringUtil.isNotEmpty(nickname)) {
            user.setNickname(nickname);
        }

        String avatar = (String) params.get(NetworkConfig.PARAM_USER_AVATAR);
        if(StringUtil.isNotEmpty(avatar)) {
            user.setAvatar(avatar);
        }

        int gender = (int) params.get(NetworkConfig.PARAM_USER_GENDER);
        if(gender != User.GENDER_UNKNOWN) {
            user.setGender(gender);
        }

        String birthday = (String) params.get(NetworkConfig.PARAM_USER_BIRTHDAY);
        if(StringUtil.isNotEmpty(birthday)) {
            user.setBirthday(birthday);
        }

        String province = (String) params.get(NetworkConfig.PARAM_USER_PROVINCE);
        if(StringUtil.isNotEmpty(province)) {
            user.setProvince(province);
        }

        String city = (String) params.get(NetworkConfig.PARAM_USER_CITY);
        if(StringUtil.isNotEmpty(city)) {
            user.setCity(city);
        }

        String area = (String) params.get(NetworkConfig.PARAM_USER_AREA);
        if(StringUtil.isNotEmpty(area)) {
            user.setArea(area);
        }

        String signature = (String) params.get(NetworkConfig.PARAM_USER_SIGNATURE);
        if(StringUtil.isNotEmpty(signature)) {
            user.setSignature(signature);
        }

        userService.completeInfo(user);
        return RetResponse.onSucceed();
    }
}
