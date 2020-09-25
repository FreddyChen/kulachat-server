package com.freddy.kulachat.core.dao;

import com.freddy.kulachat.core.entity.User;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/22 20:21
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public interface UserDao {

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    User queryUserByPhone(String phone);

    /**
     * 注册用户
     * @param user
     */
    void register(User user);
}
