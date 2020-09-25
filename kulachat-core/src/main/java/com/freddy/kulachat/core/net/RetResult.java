package com.freddy.kulachat.core.net;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/22 20:13
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class RetResult<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public RetResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult setData(T data) {
        this.data = data;
        return this;
    }
}
