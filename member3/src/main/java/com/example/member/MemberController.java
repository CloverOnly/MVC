package com.example.member;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface MemberController {
    ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView addMember(@Valid @ModelAttribute("member") MemberVO member,
                           BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
                              HttpServletResponse response) throws Exception;
    ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
                       HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
