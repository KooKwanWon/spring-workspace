package com.kh.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mvc.model.service.MemberService;
import com.kh.mvc.model.vo.Member;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	MemberService memberservice = new MemberService();
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public void login(Member member) {
		memberservice.loadUserByUsername(member.getId());
	}

}

