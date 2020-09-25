package com.freddy.kulachat.core;

import com.freddy.kulachat.core.entity.User;
import com.freddy.kulachat.core.service.RedisTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/24 16:23
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateServiceTest {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @Test
    public void test() {
        redisTemplateService.set("age", 19, 10000, TimeUnit.MILLISECONDS);
    }
}
