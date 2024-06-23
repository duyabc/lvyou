package com.atguigu.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.OrderService;
import com.atguigu.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/26
 * @desc 预约模块
 */
@RestController
public class OrderController {

  @Reference OrderService orderService;

  @Autowired JedisPool jedisPool;

  /**
   * 发送验证码
   *
   * @param queryMap json 参数集
   * @return Result
   */
  @PostMapping("/validateCode/send4Order")
  public Result send4Order(@RequestBody Map<String, Object> queryMap) {
    // 1. 获取手机号
    String telephone = Convert.toStr(queryMap.get("telephone"));
    // 2. 生成随机验证码
    String code = RandomUtil.randomNumbers(4);
    // 3. 调用接口发送短信
    try {
      SMSUtils.sendShortMessage(code, telephone);
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
    }
    // 4. 将验证码添加到 redis 中，设置有效期为5分钟
    jedisPool.getResource().setex(telephone + RedisMessageConstant.SEND_TYPE_ORDER, 5 * 60, code);
    return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
  }

  /**
   * 预约套餐游
   *
   * @param queryMap 参数集合
   * @return Result
   */
  @PostMapping("/order/submit")
  public Result submit(@RequestBody Map<String, String> queryMap) {
    Integer orderId = null;
    try {
      // 1. 获取校验信息
      String telephone = queryMap.get("telephone");
      String validateCode = queryMap.get("validateCode");
      // 2. 校验验证码（redis）
      String s = jedisPool.getResource().get(telephone + RedisMessageConstant.SEND_TYPE_ORDER);
      if (StrUtil.equals(s, validateCode)) {
        // 3. 调用预约业务
        orderId = orderService.orderSetMeal(queryMap);
      }

    } catch (RuntimeException e) {
      e.printStackTrace();
      return new Result(false, e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.ORDERSETTING_FAIL);
    }
    return new Result(true, MessageConstant.ORDERSETTING_SUCCESS, orderId);
  }

  /**
   * 预约成功页面数据展示
   *
   * @param id 订单id
   * @return
   */
  @GetMapping("/order/findById")
  public Result orderSuccess(Integer id) {
    Map<String, Object> data = null;
    try {
      data = orderService.getOrderSuccessData(id);
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
    }
    return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, data);
  }
}
