package com.example.member;

import java.util.List;

import jakarta.validation.Valid;

public interface MemberService {
    List<MemberVO> listMembers() throws Exception;
    int addMember(MemberVO member) throws Exception;
    int removeMember(String id) throws Exception;
    MemberVO login(MemberVO memberVO) throws Exception;
    int modMember(MemberVO memberVO) throws Exception;
    int modifyMember(MemberVO member);
    void updateMember(@Valid MemberVO member) throws Exception;
}
