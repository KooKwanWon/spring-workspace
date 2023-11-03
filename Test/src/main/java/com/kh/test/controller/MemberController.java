package com.kh.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.service.MemberServiceImpl;
import com.kh.test.vo.Member;



@Controller
public class MemberController {
	
	@Autowired
	private MemberServiceImpl service;
	
	@RequestMapping("login")
	public String memberLogin(Member member, HttpSession session) {
		
		Member target = service.loginMember(member);
		
		if(target != null) {
			
			session.setAttribute("loginMember", target);
			
			return "redirect:/";
		}
		
		return "errorPage";
	}
	
}
