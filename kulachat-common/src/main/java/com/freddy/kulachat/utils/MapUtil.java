package com.freddy.kulachat.utils;

import java.util.Map;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/23 17:58
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class MapUtil {

    public static boolean isEmpty(Map params) {
        return params == null || params.isEmpty();
    }

    public static boolean isNotEmpty(Map params) {
        return !isEmpty(params);
    }
}
