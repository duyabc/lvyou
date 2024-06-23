package com.atguigu.dao;

import com.atguigu.pojo.SetMeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/22
 */
public interface SetMealDao {
  Page<SetMeal> findSetMealByQueryString(String queryString);

  void addSetMeal(SetMeal setMeal);

  void setSetMealAndTravelGroup(
      @Param("setmeal_id") Integer id, @Param("travelgroupIds") List<Integer> travelgroupIds);

  void delSetMealById(Integer id);

  void delSetMealAndTravelGroups(Integer id);

  List<Integer> getSelectedTravelGroupIds();

  SetMeal findSetMealById(Integer id);

  List<Integer> getSelectedTravelGroupIds(Integer id);

  void updateSetMeal(SetMeal setMeal);

  List<SetMeal> getAllSetMealList();

  SetMeal findById(Integer id);

  SetMeal getSetMealDetail(Integer id);

  List<Map<String, Object>> getSetmealReport();
}
