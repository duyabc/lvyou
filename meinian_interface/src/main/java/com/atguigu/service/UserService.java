package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/28
 */
public interface UserService {
    User findUserByUserName(String username);
}
