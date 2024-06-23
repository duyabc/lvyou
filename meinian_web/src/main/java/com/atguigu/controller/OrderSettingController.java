package com.atguigu.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/23
 */
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    OrderSettingService orderSettingService;


    @RequestMapping("upload")
    public Result uploadExcel(MultipartFile excelFile) {
        try {
            // 使用 huTool 工具包完成excel的读取
            ExcelReader reader = ExcelUtil.getReader(excelFile.getInputStream());
            List<OrderSetting> list = reader.readAll(OrderSetting.class);
            orderSettingService.batchRes(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }

    @GetMapping("getOrderSetting")
    public Result getOrderSetting(@RequestParam("currentYear") Integer year, @RequestParam("currentMonth") Integer month) {
        List<Map<String, Object>> data = null;
        try {
            data = orderSettingService.getOrderSetting(year, month);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, data);
    }

    @PutMapping("updateOrderSetting")
    public Result updateOrderSetting(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.updateOrderSetting(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }

}
