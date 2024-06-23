package com.atguigu.dao;

import com.atguigu.pojo.Member;

/**
 * @Author 孔德成
 * @Slogan 致敬大师，致敬未来的你
 * @date 2021/7/26
 */
public interface MemberDao {
    Member findMemberByPhone(String phoneNumber);

    void insert(Member queryMember);

    int countMemberByRegTime(String regTime);
}
