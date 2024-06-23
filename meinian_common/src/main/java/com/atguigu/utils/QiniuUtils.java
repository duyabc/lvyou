package com.atguigu.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/21
 * @desc 七牛云工具类
 */
public class QiniuUtils {
    public static final String ACCESS_KEY = "D8RRoEcf-uvYzEs0c5CbRuRhoiYDtp_oQ1VUcCQv";
    public static final String SECRET_KEY = "ghQcEywDLOyi6vUYRteRnW9srbZFeG4_BWI7XYTn";
    public static final String BUCKET = "atguigumeinian-kongdecheng";


    /**
     * 上传本地文件
     *
     * @param localFilePath 路径
     * @return 文件唯一标识符
     */
    public static String uploadFile(String localFilePath) {
        // 构造一个带指定Region对象的配置类
        Configuration cfg = new Configuration(Region.region2());//华南
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        DefaultPutRet putRet = null;
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            // 文件名
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }


    /**
     * 字节数组上传
     * 可以支持将内存中的字节数组上传到空间中。
     */
    public static String uploadFile(byte[] uploadBytes) {
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        DefaultPutRet putRet = null;
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param key 文件标识符
     */
    public static void deleteFile(String key) {
        Configuration cfg = new Configuration(Region.region2());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(BUCKET, key);
            System.out.println("删除图片：" + key + " 成功");
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.out.println("删除图片：" + key + " 失败");
        }
    }
}
