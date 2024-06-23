package com.atguigu.security;

import com.alibaba.fastjson.JSON;
import com.atguigu.entity.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/28
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    // AJAX请求,使用response发送403
    if (isAjaxRequest(request)) {
      Result result = new Result(false, "无权访问", "403");
      String json = JSON.toJSONString(result);
      response.getWriter().print(json);
    } else { // 同步请求处理
      request.getRequestDispatcher("/error/403.html").forward(request, response);
    }
  }

  /**
   * 判断是否为异步请求
   * @param request
   * @return
   */
  public static boolean isAjaxRequest(HttpServletRequest request) {
    return request.getHeader("accept").contains("application/json")
            || (request.getHeader("X-Requested-With") != null
            && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")));
  }
}
