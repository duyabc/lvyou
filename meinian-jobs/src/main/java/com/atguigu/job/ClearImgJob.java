package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/23
 * @desc 定时清理垃圾图片
 * 垃圾图片定义：无法访问的图片
 * 1. 数据库中存在，七牛云中不存在
 * 2. 七牛云中存在，数据库中不存在
 */
public class ClearImgJob {


    JedisPool jedisPool;

    public ClearImgJob(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void clearImg() {
        // 求差集
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        // 从七牛云中删除差级中的所有图片
        for (String s : sdiff) {
            QiniuUtils.deleteFile(s);
        }
        // 清空图片缓存
        jedisPool.getResource().del(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

    }
}
