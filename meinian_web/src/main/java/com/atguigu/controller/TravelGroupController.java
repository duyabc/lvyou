package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/21
 */
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference
    TravelGroupService travelGroupService;

    @PostMapping("add")
    public Result add(@RequestBody Map<String, Object> map) {
        try {
            travelGroupService.add(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }

        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }

    @GetMapping("findPage")
    public PageResult findPage(QueryPageBean queryPageBean) {
        return travelGroupService.findPage(queryPageBean);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam Integer id) {
        try {
            travelGroupService.removeTravelGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
    }

    @GetMapping("findById")
    public Result findTravelGroupDetail(@RequestParam Integer id) {
        Map<String, Object> resultMap = null;
        try {
            resultMap = travelGroupService.findTravelGroupDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_TRAVELGROUP_DETAIL_FAIL);
        }
        return new Result(true, MessageConstant.GET_TRAVELGROUP_DETAIL_SUCCESS, resultMap);

    }

    @PutMapping("edit")
    public Result edit(@RequestBody Map<String, Object> map) {
        try {
            travelGroupService.edit(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }

        return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    @GetMapping("findAll")
    public Result findAll() {
        List<TravelGroup> list = null;
        try {
            list = travelGroupService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS, list);
    }


}
