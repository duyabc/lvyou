package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/21
 */
public interface TravelGroupService {
    void add(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException;

    PageResult findPage(QueryPageBean queryPageBean);

    void removeTravelGroup(Integer id);

    Map<String, Object> findTravelGroupDetail(Integer id);

    void edit(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException;

    List<TravelGroup> findAll();

}
