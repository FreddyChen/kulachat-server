package com.freddy.kulachat.core.service;

import com.freddy.kulachat.core.dao.UserDao;
import com.freddy.kulachat.core.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/22 20:20
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@Component
public class UserService {

    @Resource
    private UserDao userDao;

    public User queryUserByPhone(String phone) {
        return userDao.queryUserByPhone(phone);
    }

    public User queryUserByUserId(Long userId) {
        return userDao.queryUserByUserId(userId);
    }

    public void register(User user) {
        userDao.register(user);
    }

    public void completeInfo(User user) {
        userDao.completeInfo(user);
    }
}
