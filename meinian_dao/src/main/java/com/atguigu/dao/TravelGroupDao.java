package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/21
 */
public interface TravelGroupDao {

    void addTravelGroup(TravelGroup travelGroup);

    void addTravelItems(@Param("travelGroupId") Integer id, @Param("travelItemIds") List<Integer> travelItems);

    Page<TravelGroup> findPage(String queryString);

    void removeById(Integer id);

    void removeTravelitems(Integer id);

    TravelGroup findTravelGroupById(Integer id);

    List<Integer> findTravelItemIdsAboutTravelGroupById(Integer id);

    void update(TravelGroup travelGroup);

    List<TravelGroup> findAllTravelGroup();

}
