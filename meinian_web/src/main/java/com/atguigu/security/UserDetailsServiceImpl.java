package com.atguigu.security;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/28
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Reference UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findUserByUserName(username);
    if (ObjectUtil.isNull(user)) {
      return null;
    }
    // 查询用户所拥有的权限
    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    Set<Role> roles = user.getRoles();
    for (Role role : roles) {
      Set<Permission> permissions = role.getPermissions();
      for (Permission permission : permissions) {
        grantedAuthorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
      }
    }

    // 返回认证用户
    return new org.springframework.security.core.userdetails.User(
        username, user.getPassword(), grantedAuthorities);
  }
}
