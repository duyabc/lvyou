import com.atguigu.utils.QiniuUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/21
 * @desc 七牛云文件上传下载测试
 */
public class QiNiuTest {

    public static final String AK = "D8RRoEcf-uvYzEs0c5CbRuRhoiYDtp_oQ1VUcCQv";
    public static final String SK = "ghQcEywDLOyi6vUYRteRnW9srbZFeG4_BWI7XYTn";
    public static final String BUCKET = "atguigumeinian-kongdecheng";

    /**
     * 测试本地文件上传
     */
    @Test
    public void testUpload() {
        // 构造一个带指定Region对象的配置类
        Configuration cfg = new Configuration(Region.region2());//华南
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = AK;
        String secretKey = SK;
        String bucket = BUCKET;
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png，可支持中文
        String localFilePath = "/Users/codemao/Pictures/redundancy-before.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            // 文件名
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * 测试删除文件
     */
    @Test
    public void testDeleteFile() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        String accessKey = AK;
        String secretKey = SK;
        String bucket = BUCKET;
        String key = "FoU-lRiJDc_-b05A4S4n1YxMOWJ1";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     *
     */
    @Test
    public void test() {
        String s = QiniuUtils.uploadFile("/Users/codemao/Pictures/redundancy-after.png");
        System.out.println("s = " + s);
    }

    /**
     *
     */
    @Test
    public void test2() {
        QiniuUtils.deleteFile("FplIeWtpFd_ZDLC8in0i8EkWxFhe");
    }

    /**
     *
     */
    @Test
    public void test3() {
        File file = new File("/Users/codemao/Pictures/网购量体指南表.png");
        //init array with file length
        byte[] bytesArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytesArray); //read file into bytes[]
            QiniuUtils.uploadFile(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

