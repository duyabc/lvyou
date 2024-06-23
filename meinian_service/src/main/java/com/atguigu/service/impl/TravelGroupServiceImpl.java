package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/21
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    TravelGroupDao travelGroupDao;

    public TravelGroupServiceImpl(TravelGroupDao travelGroupDao) {
        this.travelGroupDao = travelGroupDao;
    }

    @Override
    public void add(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {
        // map转对象
        TravelGroup travelGroup = new TravelGroup();
        BeanUtils.populate(travelGroup, (Map) map.get("form"));
        // 封装 travelItems
        List<Integer> travelItems = (List<Integer>) map.get("travelItems");

        // 将 travelGroup 添加到数据库, 设置mybatis使用自动生成的id
        travelGroupDao.addTravelGroup(travelGroup);

        // 自由行长度大于0
        if (travelItems.size() > 0) {
            // 将与跟团游关联的自由行添加到数据库
            travelGroupDao.addTravelItems(travelGroup.getId(), travelItems);
        }

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<TravelGroup> travelGroups = travelGroupDao.findPage(queryPageBean.getQueryString());
        return new PageResult(travelGroups.getTotal(), travelGroups.getResult());
    }

    /**
     * 删除跟团游 t_travelgroup t_travelgroup_travelitem
     *
     * @param id
     */
    @Override
    public void removeTravelGroup(Integer id) {
        // 从 t_travelgroup 表中删除跟团游
        travelGroupDao.removeById(id);

        // 删除与自由行的表关系
        travelGroupDao.removeTravelitems(id);
    }

    @Override
    public Map<String, Object> findTravelGroupDetail(Integer id) {
        // 查询基本信息
        TravelGroup travelGroup = travelGroupDao.findTravelGroupById(id);
        // 查询关联的自由行id
        List<Integer> travelItemIdList = travelGroupDao.findTravelItemIdsAboutTravelGroupById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("formData", travelGroup);
        resultMap.put("travelItemIds", travelItemIdList);
        return resultMap;
    }

    @Override
    public void edit(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {
        // 更新基本信息
        TravelGroup travelGroup = new TravelGroup();
        BeanUtils.populate(travelGroup, (Map<String, Object>) map.get("form"));
        travelGroupDao.update(travelGroup);
        // 更新关联自由行信息
        List<Integer> ids = (List<Integer>) map.get("travelItems");
        // 先删除所有关系
        travelGroupDao.removeTravelitems(travelGroup.getId());
        // 再增加新关系
        if (ids.size() > 0) {
            travelGroupDao.addTravelItems(travelGroup.getId(), ids);
        }

    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAllTravelGroup();
    }
}
