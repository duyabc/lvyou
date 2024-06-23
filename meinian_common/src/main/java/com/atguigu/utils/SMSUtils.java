package com.atguigu.utils;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/** 山东鼎信（第三方短信发送） */
public class SMSUtils {

  /**
   * 发送短信
   *
   * @param code 验证码
   * @param mobile 手机号
   */
  public static void sendShortMessage(String code, String mobile) {
    String host = "http://dingxin.market.alicloudapi.com";
    String path = "/dx/sendSms";
    String method = "POST";
    String appcode = "db7df8ccb15e4bf98072981ee5f56678";
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Authorization", "APPCODE " + appcode);
    Map<String, String> querys = new HashMap<String, String>();
    querys.put("mobile", mobile);
    querys.put("param", "code:" + code);
    querys.put("tpl_id", "TP1711063");
    Map<String, String> bodys = new HashMap<String, String>();

    try {

      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
      //      System.out.println(response.toString());
      // 获取response的body
      // System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
