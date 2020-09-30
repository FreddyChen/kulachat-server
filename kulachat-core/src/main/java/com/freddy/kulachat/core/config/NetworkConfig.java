package com.freddy.kulachat.core.config;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/25 11:33
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class NetworkConfig {

    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String CONTENT_TYPE = "application/json;charset=utf-8";

    public static final String PARAM_USER = "user";
    public static final String PARAM_USER_TOKEN = "token";
    public static final String PARAM_USER_ID = "userId";
    public static final String PARAM_USER_PHONE = "phone";
    public static final String PARAM_USER_VERIFY_CODE = "verifyCode";

    public static final String FUNC_USER_GET_VERIFY_CODE = "/user/getVerifyCode.action";
    public static final String FUNC_USER_LOGIN = "/user/login.action";
}
