package com.freddy.kulachat.core.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author FreddyChen
 * @name Token验证注解，需要验证token的接口加上此注解即可
 * @date 2020/09/25 11:20
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthTokenAnnotation {
    boolean required() default true;
}
