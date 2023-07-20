package com.example.demo.member.mapper;

import com.example.demo.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Member getMember(@Param("id") String id);
    void saveMember(Member member);
    void updateMember(Member member);
    void deleteMember(@Param("id") String id);
}
