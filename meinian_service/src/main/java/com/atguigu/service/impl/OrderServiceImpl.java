package com.atguigu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/26
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

  OrderDao orderDao;

  OrderSettingDao orderSettingDao;

  MemberDao memberDao;

  public OrderServiceImpl(OrderDao orderDao, OrderSettingDao orderSettingDao, MemberDao memberDao) {
    this.orderDao = orderDao;
    this.orderSettingDao = orderSettingDao;
    this.memberDao = memberDao;
  }

  @Override
  public Integer orderSetMeal(Map<String, String> queryMap) {
    // 获取信息，套餐ID
    Integer setmealId = Convert.toInt(queryMap.get("setmealId"));
    // 1. 判断当前的日期是否可以预约(根据 orderDate 查询 t_ordersetting , 能查询出来可以预约;查询不出来,不能预约)
    String orderDate = queryMap.get("orderDate");
    OrderSetting orderSetting = orderSettingDao.findByOrderDate(orderDate);
    if (ObjectUtil.isNull(orderSetting)) {
      // 当前日期不可预约
      throw new RuntimeException(MessageConstant.SELECTED_DATE_CANNOT_ORDER);
    }
    // 2. 判断当前日期是否预约已满(判断 reservations（已经预约人数）是否等于number（可以预约人数）)
    if (orderSetting.getReservations() >= orderSetting.getNumber()) {
      // 预约已满
      throw new RuntimeException(MessageConstant.ORDER_FULL);
    }
    // TODO 系统设计缺陷：手机号码查询出来的用户可能非预约用户
    // 3. 判断是否是会员(根据手机号码查询 t_member )
    Member queryMember = BeanUtil.toBean(queryMap, Member.class);
    Member dbMember = memberDao.findMemberByPhone(queryMember.getPhoneNumber());
    Member orderMember = new Member();
    if (ObjectUtil.isNotNull(dbMember)) {
      //   - 判断用户是否为首次预约套餐游，如果非首次，防止重复预约
      //   - 如果是会员(能够查询出来), 防止重复预约(根据member_id,orderDate,setmeal_id查询t_order)
      Map<String, Object> mapCondition = new HashMap<>();
      mapCondition.put("member_id", dbMember.getId());
      mapCondition.put("orderDate", orderDate);
      mapCondition.put("setmeal_id", setmealId);
      Order order = orderDao.findByCondition(mapCondition);
      if (ObjectUtil.isNotNull(order)) {
        // 重复预约
        throw new RuntimeException(MessageConstant.HAS_ORDERED);
      }
      // 属性复制, 使用 orderMember 进行预约
      orderMember = BeanUtil.toBean(dbMember, Member.class);
    } else {
      //   - 如果不是会员(不能够查询出来) ,自动注册为会员(直接向t_member插入一条记录)
      //   主键回填
      memberDao.insert(queryMember);
      orderMember = BeanUtil.toBean(queryMember, Member.class);
    }

    // 4. 进行预约(orderMember)
    //   - 向 t_order 表插入一条记录
    Order order = new Order();
    // 设置订单日期
    order.setOrderDate(DateUtil.parse(orderDate));
    // 设置订单状态，默认未出游
    order.setOrderStatus(Order.ORDERSTATUS_NO);
    // 设置订单类型，默认微信预约
    order.setOrderType(Order.ORDERTYPE_WEIXIN);
    // 设置预约的套餐
    order.setSetmealId(setmealId);
    // 设置订单用户
    order.setMemberId(orderMember.getId());
    // 主键回填
    orderDao.insert(order);
    //   - t_ordersetting 表里面预约的人数 reservations+1
    orderSettingDao.incrementReservationByOrderDate(orderDate);
    return order.getId();
  }

  @Override
  public Map<String, Object> getOrderSuccessData(Integer id) {
    Map<String, Object> map = orderDao.findById(id);
    // 处理日期对象，格式化为字符串
    String orderDate = DateUtil.formatDate(Convert.toDate(map.get("orderDate")));
    map.put("orderDate", orderDate);
    return map;
  }
}
