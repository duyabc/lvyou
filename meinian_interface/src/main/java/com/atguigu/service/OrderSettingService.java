package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/23
 */
public interface OrderSettingService {
    void batchRes(List<OrderSetting> args);

    List<Map<String, Object>> getOrderSetting(Integer year, Integer month);

    void updateOrderSetting(OrderSetting orderSetting);
}
