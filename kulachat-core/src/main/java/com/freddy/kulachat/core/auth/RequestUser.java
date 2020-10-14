package com.freddy.kulachat.core.auth;

/**
 * @author FreddyChen
 * @name
 * @date 2020/10/12 20:29
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class RequestUser {

    private Long userId;
    private String token;

    public static final String KEY_REQUEST_USER = "key_request_user";

    public RequestUser(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RequestUser{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
