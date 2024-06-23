package com.atguigu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/23
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {


    OrderSettingDao orderSettingDao;

    public OrderSettingServiceImpl(OrderSettingDao orderSettingDao) {
        this.orderSettingDao = orderSettingDao;
    }

    @Override
    public void batchRes(List<OrderSetting> args) {
        orderSettingDao.batchAdd(args);
    }

    @Override
    public List<Map<String, Object>> getOrderSetting(Integer year, Integer month) {

        // 日期处理，获得这个月的开始和结束，并格式化为字符串
        String s = year + "-" + month + "-" + 1;
        Date date = DateUtil.parseDate(s);
        Date start = DateUtil.beginOfMonth(date);
        Date end = DateUtil.endOfMonth(date);
//        String sStart = DateUtil.formatDate(start);
//        String sEnd = DateUtil.formatDate(end);
        List<OrderSetting> list = orderSettingDao.queryOrderSettingByYearAndMonth(start, end);
        List<Map<String, Object>> maps = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map<String, Object> map = BeanUtil.beanToMap(orderSetting);
            Date orderDate = orderSetting.getOrderDate();
            // 提取日期的 day 部分
            map.put("date", DateUtil.dayOfMonth(orderDate));
            maps.add(map);
        }
        return maps;
    }

    @Override
    public void updateOrderSetting(OrderSetting orderSetting) {
        // 1. 将日期格式化为字符串 yyyy-MM-dd 目的是去掉时间
        String s = DateUtil.formatDate(orderSetting.getOrderDate());
        // 2. 将日期字符串解析为日期对象
        Date date = DateUtil.parse(s, "yyyy-MM-dd");
        orderSetting.setOrderDate(date);
        orderSettingDao.updateOrderSettingNumberByOrderDate(orderSetting);
    }
}
