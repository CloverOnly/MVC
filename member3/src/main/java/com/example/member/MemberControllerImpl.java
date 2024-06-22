package com.example.member;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MemberVO meberVO;
    
    
    @Override
    @GetMapping("/member/listMembers.do")
    public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List membersList = memberService.listMembers();
        ModelAndView mav = new ModelAndView("/member/listMembers");

        mav.addObject("membersList", membersList);
        return mav;
    }

    @Override
    @PostMapping("/member/addMember.do")
    public ModelAndView addMember(@ModelAttribute("member") @Valid MemberVO member,
            BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                if (error.isBindingFailure()) {
                    mav.addObject("valid_" + error.getField(), "형식이 올바르지 않습니다");
                } else {
                    mav.addObject("valid_" + error.getField(), error.getDefaultMessage());
                }
            }

            mav.setViewName("/member/memberForm"); // 유효성 검사 에러 발생 시 다시 입력 폼으로 이동
            return mav;
        }

        // 유효성 검사를 통과한 경우, 회원 추가 로직 실행
        int res = memberService.addMember(member);
        mav.setViewName("redirect:/member/listMembers.do");
        return mav;
    }

    @Override
    @GetMapping("/member/removeMember.do")
    public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        memberService.removeMember(id);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }

    @Override
    @PostMapping("/member/login.do")
    public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        MemberVO memberVO = memberService.login(member);
        if (memberVO != null) {
            // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("member", memberVO);
            session.setAttribute("isLogOn", true);
            // 세션 유효 시간 설정 (예: 30분)
            session.setMaxInactiveInterval(1800); // 초 단위, 30분

            String action = (String) session.getAttribute("action");
            session.removeAttribute("action");
            if (action != null) {
                mav.setViewName("redirect:" + action);
            } else {
                mav.setViewName("redirect:/member/listMembers.do");
            }
        } else {
            // 로그인 실패
            rAttr.addFlashAttribute("result", "아이디 또는 비밀번호가 일치하지 않습니다.");
            mav.setViewName("redirect:/member/loginForm.do");
        }
        return mav;
    }

    @Override
    @GetMapping("/member/logout.do")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("member");
        session.setAttribute("isLogOn", false);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/member/listMembers.do");
        return mav;
    }

   
    @GetMapping("/member/modMember.do")
    public ModelAndView showModMemberForm(HttpSession session, HttpServletRequest request) {
    	try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
    	MemberVO member = (MemberVO) session.getAttribute("member");
        ModelAndView mav = new ModelAndView("/member/modMember");
        mav.addObject("member", member);
        return mav;
    }
    
    @PostMapping("/member/modMember.do")
    public ModelAndView modMember(@ModelAttribute("member") MemberVO member, BindingResult result,
                                  HttpSession session) throws Exception {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("/member/modMember");
            mav.addObject("member", member);
            return mav;
        }

        memberService.updateMember(member);
        session.setAttribute("member", member);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }


    @GetMapping("/member/*Form.do")
    private ModelAndView form(@RequestParam(value = "result", required = false) String result,
            @RequestParam(value = "action", required = false) String action, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        HttpSession session = request.getSession();
        session.setAttribute("action", action);

        ModelAndView mav = new ModelAndView();
        mav.addObject("result", result);
        mav.setViewName(viewName);
        return mav;
    }

    @GetMapping({ "/", "/main.do" })
    private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        String viewName = (String) request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        return mav;
    }

	

}
