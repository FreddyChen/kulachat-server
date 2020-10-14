package com.freddy.kulachat.core.controller;

import com.freddy.kulachat.core.auth.annotation.AuthTokenAnnotation;
import com.freddy.kulachat.core.config.NetworkConfig;
import com.freddy.kulachat.core.net.RetResponse;
import com.freddy.kulachat.core.net.RetResult;
import com.freddy.kulachat.core.storage.AliyunAssumeRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author FreddyChen
 * @name
 * @date 2020/10/13 15:11
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@RestController
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);
    private final AliyunAssumeRole aliyunAssumeRole;

    public ConfigController() {
        aliyunAssumeRole = new AliyunAssumeRole();
    }

    @AuthTokenAnnotation
    @RequestMapping(value = NetworkConfig.FUNC_CONFIG_GET_OSS_CREDENTIALS, method = RequestMethod.POST)
    public RetResult getOSSCredentials() {
        Map<String, Object> result = aliyunAssumeRole.getOSSCredentials();
        logger.debug("getOSSCredentials() result = " + result);
        boolean succeed = (boolean) result.get(AliyunAssumeRole.KEY_SUCCEED);
        if (!succeed)
            return RetResponse.onFailed((int) result.get(AliyunAssumeRole.KEY_ERROR_CODE), (String) result.get(AliyunAssumeRole.KEY_ERROR_MSG));

        return RetResponse.onSucceed(result.get(AliyunAssumeRole.KEY_CREDENTIALS));
    }
}
