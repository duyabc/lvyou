package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/28
 * @desc 根据用户id查询用户
 */
public interface UserDao {
    User findUserByUserName(String username);
}
