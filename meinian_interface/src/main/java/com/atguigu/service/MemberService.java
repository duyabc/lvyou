package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.List;
import java.util.Map;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/27
 */
public interface MemberService {
    void registerUser(Member telephone);

    Member findUserByPhone(String telephone);

    Map<String, List> getMemberReport();

}
