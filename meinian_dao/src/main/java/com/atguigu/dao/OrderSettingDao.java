package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/23
 */
public interface OrderSettingDao {
    void batchAdd(@Param("list") List<OrderSetting> orderSettings);

    List<OrderSetting> queryOrderSettingByYearAndMonth(@Param("start") Date start, @Param("end") Date end);

    void updateOrderSettingNumberByOrderDate(OrderSetting orderSetting);

    OrderSetting findByOrderDate(String orderDate);

    void incrementReservationByOrderDate(String orderDate);
}
