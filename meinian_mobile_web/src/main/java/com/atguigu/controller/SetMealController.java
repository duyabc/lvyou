package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.SetMeal;
import com.atguigu.service.SetMealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/26
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {

  @Reference SetMealService setMealService;

  @PostMapping("getSetmeal")
  public Result getSetmeal() {
    List<SetMeal> setMealList = setMealService.getAllSetMeal();
    return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMealList);
  }

  @PostMapping("findById")
  public Result findById(Integer id) {
    SetMeal setMeal = null;
    try {
      setMeal = setMealService.findById(id);
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
    }
    return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMeal);
  }

  @GetMapping("getSetMealDetail")
  public Result getSetMealDetail(Integer id) {
    SetMeal setMeal = null;
    try {
      setMeal = setMealService.getSetMealDetail(id);
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
    }
    return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMeal);
  }
}
