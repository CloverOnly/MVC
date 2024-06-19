package com.example.member;

import java.util.List;

import com.example.member.MemberVO;

public interface MemberService {
	public List listMembers() throws Exception;
	public int addMember(MemberVO memberVO)throws Exception;
	public int removeMember(String id) throws Exception;
	public MemberVO login(MemberVO memberVO)throws Exception;
}
