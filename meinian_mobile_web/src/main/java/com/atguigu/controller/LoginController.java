package com.atguigu.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import com.atguigu.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/27
 */
@RestController
public class LoginController {

  @Reference MemberService memberService;

  @Autowired JedisPool jedisPool;

  /**
   * 发送短信验证码
   *
   * @param telephone 手机号
   * @return Result
   */
  @RequestMapping("/validateCode/send4Login")
  public Result sendLoginCode(String telephone) {
    try {
      // 1. 生成验证码
      String code = RandomUtil.randomNumbers(4);
      // 2. 发短信
      SMSUtils.sendShortMessage(code, telephone);
      // 3. 将验证码缓存到redis中,5分钟过期
      jedisPool.getResource().setex(telephone + RedisMessageConstant.SEND_TYPE_LOGIN, 5 * 60, code);
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
    }
    return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
  }

  /**
   * 登陆
   *
   * @param map 登陆参数
   * @param response 添加 cookie
   * @return Result
   */
  @PostMapping("/login/check")
  public Result login(@RequestBody Map<String, String> map, HttpServletResponse response) {
    // 1. 获取提交参数
    String telephone = map.get("telephone");
    String validateCode = map.get("validateCode");
    // 2. 验证码校验
    String code = jedisPool.getResource().get(telephone + RedisMessageConstant.SEND_TYPE_LOGIN);
    if (StrUtil.equals(validateCode, code)) {
      // 3. 登陆成功
      // 删除缓存中的验证码
      jedisPool.getResource().del(telephone + RedisMessageConstant.SEND_TYPE_LOGIN);
      // 设置 cookie
      Cookie token = new Cookie("token", IdUtil.fastSimpleUUID());
      // 作用域
      token.setPath("/");
      // 有效期一个月
      token.setMaxAge(24 * 60 * 60 * 30);
      response.addCookie(token);
      // 4. 查询会员是否注册
      Member member = memberService.findUserByPhone(telephone);
      if (ObjectUtil.isNull(member)) {
        // 5. 如果会员未注册，则自动注册会员
        Member newMember = new Member();
        newMember.setPhoneNumber(telephone);
        memberService.registerUser(newMember);
      }
      return new Result(true, MessageConstant.LOGIN_SUCCESS);
    } else {
      // 验证码错误
      return new Result(false, MessageConstant.VALIDATECODE_ERROR);
    }
  }
}
