import com.atguigu.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/26
 * @desc 短信发送测试
 */
public class SMSTest {
    /**
     * 山东鼎信数字科技有限公司 短信 api 测试
     */
    @Test
    public void test(){
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "db7df8ccb15e4bf98072981ee5f56678";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "13304469618");
        querys.put("param", "code:1234");
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
