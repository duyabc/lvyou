package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.atguigu.dao.TravelItemDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/20
 */
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService {

    TravelItemDao travelItemDao;

    public TravelItemServiceImpl(TravelItemDao travelItemDao) {
        this.travelItemDao = travelItemDao;
    }

    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<TravelItem> page = travelItemDao.findPage(queryPageBean.getQueryString());

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        // 在删除自由行之前，先判断自由行的id，在中间表中是否存在数据
        Long count = travelItemDao.countByTravelItemId(id);
        // 中间表如果有数据，不要往后面执行，直接抛出异常
        if (count > 0) {
            throw new RuntimeException("不允许删除");
        }
        travelItemDao.deleteById(id);
    }

    @Override
    public TravelItem getById(Integer id) {
        return travelItemDao.getById(id);
    }

    @Override
    public void update(TravelItem travelItem) {
        travelItemDao.updateById(travelItem);
    }

    @Override
    public List<TravelItem> findAllTravelItems() {
        return travelItemDao.findAll();
    }
}
