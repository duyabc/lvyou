package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.SetMealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.SetMeal;
import com.atguigu.service.SetMealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/22
 */
@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

  SetMealDao setMealDao;

  public SetMealServiceImpl(SetMealDao setMealDao) {
    this.setMealDao = setMealDao;
  }

  @Override
  public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
    PageHelper.startPage(currentPage, pageSize);
    Page<SetMeal> page = setMealDao.findSetMealByQueryString(queryString);
    return new PageResult(page.getTotal(), page.getResult());
  }

  @Override
  public void add(SetMeal setMeal, List<Integer> travelgroupIds) {
    setMealDao.addSetMeal(setMeal);
    if (travelgroupIds != null && travelgroupIds.size() > 0) {
      setMealDao.setSetMealAndTravelGroup(setMeal.getId(), travelgroupIds);
    }
  }

  @Override
  public void delete(Integer id) {
    // 关系表依赖于主表，存在外键关系, 所以注意删除顺序，先删除子表，再删除父表

    // 从关系表中删除
    setMealDao.delSetMealAndTravelGroups(id);

    // 从主表中删除
    setMealDao.delSetMealById(id);
  }

  @Override
  public List<Integer> getSelectedTravelGroupIds(Integer id) {
    return setMealDao.getSelectedTravelGroupIds(id);
  }

  @Override
  public SetMeal find(Integer id) {
    return setMealDao.findSetMealById(id);
  }

  @Override
  public void updateSetMeal(SetMeal setMeal, List<Integer> travelGroupIds) {
    // 更新父表
    setMealDao.updateSetMeal(setMeal);
    // 更新中间表
    setMealDao.delSetMealAndTravelGroups(setMeal.getId());
    if (travelGroupIds != null && travelGroupIds.size() > 0) {
      setMealDao.setSetMealAndTravelGroup(setMeal.getId(), travelGroupIds);
    }
  }

  @Override
  public List<SetMeal> getAllSetMeal() {
    return setMealDao.getAllSetMealList();
  }

  @Override
  public SetMeal findById(Integer id) {
    return setMealDao.findById(id);
  }

  @Override
  public SetMeal getSetMealDetail(Integer id) {
    return setMealDao.getSetMealDetail(id);
  }

  @Override
  public Map<String, List<Map<String, Object>>> getSetmealReport() {
    Map<String, List<Map<String, Object>>> map = new HashMap<>(1);
    map.put("setmealCount", setMealDao.getSetmealReport());
    return map;
  }
}
