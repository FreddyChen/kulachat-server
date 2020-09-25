package com.freddy.kulachat.core.net;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/22 20:13
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class RetResponse {

    public static <T> RetResult<T> onSucceed() {
        return RetResponse.onSucceed(null);
    }

    public static <T> RetResult<T> onSucceed(T data) {
        RetResult<T> result = new RetResult<>();
        result.setCode(RetCode.SUCCEED.getCode()).setMsg(RetCode.SUCCEED.getMsg());
        if(data != null) {
            result.setData(data);
        }

        return result;
    }

    public static RetResult onFailed() {
        return RetResponse.onFailed(RetCode.FAILED.getCode(), RetCode.FAILED.getMsg());
    }

    public static RetResult onFailed(int errCode) {
        return RetResponse.onFailed(errCode, RetCode.FAILED.getMsg());
    }

    public static RetResult onFailed(String errMsg) {
        return RetResponse.onFailed(RetCode.FAILED.getCode(), errMsg);
    }

    public static RetResult onFailed(RetCode retCode) {
        if(retCode == null) {
            return RetResponse.onFailed();
        }
        return RetResponse.onFailed(retCode.getCode(), retCode.getMsg());
    }

    public static RetResult onFailed(int errCode, String errMsg) {
        RetResult result = new RetResult();
        return result.setCode(errCode).setMsg(errMsg);
    }
}
