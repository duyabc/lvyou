package com.atguigu.dao;

import com.atguigu.pojo.Role;

import java.util.Set;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/28
 * @desc 根据用户id查询角色集合
 */
public interface RoleDao {

    Set<Role> findRolesByUserId(Integer userId);
}
