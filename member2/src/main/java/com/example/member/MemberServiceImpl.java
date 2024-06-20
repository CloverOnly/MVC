package com.example.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public List<MemberVO> listMembers() throws Exception {
        return memberDAO.selectAllMemberList();
    }

    @Override
    public int addMember(MemberVO member) throws Exception {
        return memberDAO.insertMember(member);
    }

    @Override
    public int removeMember(String id) throws Exception {
        return memberDAO.deleteMember(id);
    }

    @Override
    public MemberVO login(MemberVO memberVO) throws Exception {
        return memberDAO.loginById(memberVO);
    }

    @Override
    public int modMember(MemberVO memberVO) throws Exception {
        return memberDAO.updateMember(memberVO);
    }

    @Override
    public int modifyMember(MemberVO member) {
        // 예정된 메서드로 구현되지 않음
        return 0;
    }

    @Override
    public void updateMember(@Valid MemberVO member) throws Exception {
        memberDAO.updateMember(member);
    }
}
