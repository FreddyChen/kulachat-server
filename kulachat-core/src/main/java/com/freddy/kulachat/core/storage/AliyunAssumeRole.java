package com.freddy.kulachat.core.storage;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.freddy.kulachat.core.net.RetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author FreddyChen
 * @name
 * @date 2020/10/13 14:43
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class AliyunAssumeRole {

    private static final String REGION_ID = "cn-shenzhen";
    private static final String ACCESS_KEY_ID = "LTAI4G84PL5gxeCUWwQq3TPX";
    private static final String ACCESS_KEY_SECRET = "fEoIxxk4HI7eNTcysLcE4h2mBMUGMY";
    private static final String ROLE_ARN = "acs:ram::1813380640232954:role/kulachatram";
    private static final String ROLE_SESSION_NAME = "kulachatappsession";
    private static final long EXPIRE_TIME = 2 * 60 * 1000L;// 过期时间

    public static final String KEY_SUCCEED = "succeed";
    public static final String KEY_ERROR_CODE = "errCode";
    public static final String KEY_ERROR_MSG = "errMsg";
    public static final String KEY_CREDENTIALS = "credentials";

    public Map<String, Object> getOSSCredentials() {
        Map<String, Object> result = new HashMap<>();
        try {
            DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            IAcsClient client = new DefaultAcsClient(profile);
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysRegionId(REGION_ID);
            request.setRoleArn(ROLE_ARN);
            request.setRoleSessionName(ROLE_SESSION_NAME);
            request.setDurationSeconds(EXPIRE_TIME);
            AssumeRoleResponse response = client.getAcsResponse(request);
            AssumeRoleResponse.Credentials credentials = response.getCredentials();
            if (credentials == null) {
                result.put(KEY_SUCCEED, false);
                result.put(KEY_ERROR_CODE, RetCode.GET_OSS_CREDENTIALS_FAILED.getCode());
                result.put(KEY_ERROR_MSG, "credentials is null");
            }else {
                result.put(KEY_SUCCEED, true);
                result.put(KEY_CREDENTIALS, credentials);
            }
            return result;
        } catch (ClientException e) {
            result.put(KEY_SUCCEED, false);
            result.put(KEY_ERROR_CODE, e.getErrCode());
            result.put(KEY_ERROR_MSG, e.getErrMsg());
        }

        return result;
    }

    public static void main(String[] args) {
        AliyunAssumeRole aliyunAssumeRole = new AliyunAssumeRole();
        Map<String, Object> result = aliyunAssumeRole.getOSSCredentials();
        System.out.println("result = " + result);
    }
}
