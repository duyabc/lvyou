package com.atguigu.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/27
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

  MemberDao memberDao;

  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  /**
   * 用户注册
   *
   * @param member 会员
   */
  @Override
  public void registerUser(Member member) {
    memberDao.insert(member);
  }

  @Override
  public Member findUserByPhone(String telephone) {
    return memberDao.findMemberByPhone(telephone);
  }

  @Override
  public Map<String, List> getMemberReport() {
    // 1. 生成 x轴 一年日期(年-月)数据
    ArrayList<String> months = new ArrayList<>();
    // 今天
    DateTime date = DateUtil.date();
    for (int i = 12; i >= 1; i--) {
      months.add(DateUtil.format(DateUtil.offsetMonth(date, -i), "yyyy-MM"));
    }
    // 2. 根据日期查询 y轴 会员注册数量
    ArrayList<Integer> memberCount = new ArrayList<>();
    // 循环查询 TODO sql 优化
    for (String s : months) {
      Integer count = memberDao.countMemberByRegTime(s);
      memberCount.add(count);
    }
    Map<String, List> map = new HashMap<>(2);
    map.put("months", months);
    map.put("memberCount", memberCount);
    return map;
  }
}
