package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.MemberService;
import com.atguigu.service.SetMealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/28
 * @desc 图表模块
 */
@RestController
public class ReportController {

  @Reference
  MemberService memberService;

  @Reference
  SetMealService setMealService;

  /**
   * 近一年注册会员数量折线图
   *
   * @return
   */
  @GetMapping("/report/getMemberReport")
  public Result getMemberReport() {
    Map<String, List> data = null;
    try {
      data = memberService.getMemberReport();
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
    }
    return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, data);
  }


  @GetMapping("/report/getSetmealReport")
  public Result getSetmealReport() {
    Map<String, List<Map<String, Object>>> data = null;
    try {
      data = setMealService.getSetmealReport();
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
    }
    return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, data);
  }
}
