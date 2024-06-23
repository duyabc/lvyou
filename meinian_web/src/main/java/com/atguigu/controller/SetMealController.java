package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.SetMeal;
import com.atguigu.service.SetMealService;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/22
 * @desc 套餐游控制器
 */
@RestController
@RequestMapping("/setMeal")
public class SetMealController {

    @Reference
    SetMealService setMealService;

    @Autowired
    JedisPool jedisPool;

    @GetMapping("findPage")
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageResult pageResult = null;
        try {
            pageResult = setMealService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败，返回空的结果集，避免前台报找不到属性错误
            pageResult = new PageResult();
        }
        return pageResult;
    }

    @RequestMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile file) {
        String fileName = null;
        try {
            fileName = QiniuUtils.uploadFile(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        // TODO fixBUG 用户上传完图片，未上传表单期间，导致图片被垃圾图片定时清理任务清理掉
        // 图片上传完成后，将文件名添加到redis中
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);

        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);

    }

    @PostMapping("add")
    public Result add(@RequestBody SetMeal setMeal, @RequestParam("travelgroupIds") List<Integer> travelgroupIds) {
        try {
            setMealService.add(setMeal, travelgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        // 表单上传成功后，将图片名称添加到redis中
        if (setMeal.getImg() != null) {
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setMeal.getImg());
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @DeleteMapping("delete")
    public Result delete(Integer id) {
        try {
            setMealService.delete(id);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELTE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

    @GetMapping("getSelectedTravelGroupIds")
    public Result getSelectedTravelGroupIds(Integer id) {
        List<Integer> list = null;
        try {
            list = setMealService.getSelectedTravelGroupIds(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, list);
    }

    @GetMapping("find")
    public Result find(Integer id) {
        SetMeal setMeal = null;
        try {
            setMeal = setMealService.find(id);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMeal);
    }


    @PutMapping("edit")
    public Result edit(@RequestBody SetMeal setMeal, @RequestParam("travelgroupIds") List<Integer> travelGroupIds) {
        try {
            setMealService.updateSetMeal(setMeal, travelGroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }
}
