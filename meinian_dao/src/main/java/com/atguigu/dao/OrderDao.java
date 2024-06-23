package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/26
 */
public interface OrderDao {
    Order findByCondition(Map<String, Object> mapCondition);

    void insert(Order order);

    Map<String, Object> findById(Integer id);
}
