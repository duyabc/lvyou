package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.SetMeal;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/22
 */
public interface SetMealService {
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(SetMeal setMeal, List<Integer> travelgroupIds);

    void delete(Integer id);

    List<Integer> getSelectedTravelGroupIds(Integer id);

    SetMeal find(Integer id);

    void updateSetMeal(SetMeal setMeal, List<Integer> travelGroupIds);

    List<SetMeal> getAllSetMeal();

    SetMeal findById(Integer id);

    SetMeal getSetMealDetail(Integer id);

    Map<String, List<Map<String, Object>>> getSetmealReport();

}
