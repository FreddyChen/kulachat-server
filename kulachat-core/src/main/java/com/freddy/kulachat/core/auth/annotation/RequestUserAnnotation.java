package com.freddy.kulachat.core.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author FreddyChen
 * @name
 * @date 2020/10/12 20:31
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestUserAnnotation {
}
