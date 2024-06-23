package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/28
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("getUsername")
  public Result getUsername() {
    User user = null;
    try {
      user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(false, MessageConstant.GET_USERNAME_FAIL);
    }

    return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user);
  }
}
