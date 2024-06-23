package com.atguigu.service;

import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/26
 */
public interface OrderService {
    Integer orderSetMeal(Map<String, String> queryMap);

    Map<String, Object> getOrderSuccessData(Integer id);
}
