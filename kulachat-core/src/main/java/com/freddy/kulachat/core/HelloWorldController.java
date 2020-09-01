package com.freddy.kulachat.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/01 22:14
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Welcome to KulaChat server.";
    }
}
