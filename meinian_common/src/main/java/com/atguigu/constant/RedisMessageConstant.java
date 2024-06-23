package com.atguigu.constant;

/** @author MagicKong */
public class RedisMessageConstant {
  /** 用于缓存旅游预约时发送的验证码 */
  public static final String SEND_TYPE_ORDER = ":001";
  /** 用于缓存手机号快速登录时发送的验证码 */
  public static final String SEND_TYPE_LOGIN = ":002";
  /** 用于缓存找回密码时发送的验证码 */
  public static final String SEND_TYPE_GET_PWD = ":003";
}
