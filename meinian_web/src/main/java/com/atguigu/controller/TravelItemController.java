package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/20
 */
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    TravelItemService travelItemService;

    @PostMapping("add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")
    public Result add(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.add(travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }

    @GetMapping("findPage")
    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")
    public PageResult findPage(QueryPageBean queryPageBean) {
        return travelItemService.findPage(queryPageBean);
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE')")
    public Result delete(@RequestParam("id") Integer id) {
        // TODO bug id 为null，显示删除成功
        try {
            travelItemService.deleteById(id);

        } catch (RuntimeException e) {
            // 如果该自由行已被多个跟团游关联，则不能直接删除
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_MEMBER_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);
    }

    @GetMapping("getById")
    public Result getById(@RequestParam("id") Integer id) {
        TravelItem travelItem = null;
        try {
            travelItem = travelItemService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, travelItem);
    }

    @PutMapping("update")
    @PreAuthorize("hasAuthority('TRAVELITEM_EDIT')")
    public Result update(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.update(travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    @GetMapping("findAllTravelItems")
    public Result findAllTravelItems() {
        List<TravelItem> list = null;
        try {
            list = travelItemService.findAllTravelItems();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, list);
    }
}
