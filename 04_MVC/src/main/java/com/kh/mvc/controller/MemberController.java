package com.kh.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mvc.model.service.MemberService;
import com.kh.mvc.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@RequestMapping("search")
	public String search() {
		return "search";
	}
	
	@RequestMapping("find")
	public String find(String keyword, Model model) {
		System.out.println(keyword);
		
		// 서비스 - 비즈니스 로직 처리!!
		//	--> list 값! 데이터 바인딩 -> Model!
		List<Member> list = service.findMember(keyword);
		
		if(list.isEmpty()) return "find_fail";
		
		model.addAttribute("list", list);
		return "find_ok"; // 실패시  "find_fail"
	}
	
	@RequestMapping("register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("signUp")
	public String signUp(Member member) {
		System.out.println(member);
		try {
			service.registerMember(member);
		} catch (Exception e) {
			System.out.println("회원가입 실패!!");
			return "register_fail";
		}
		
		return "redirect:/";	// index.jsp
	}
	
	// login - 페이지 이동
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	// signIn - 비즈니스 로직 포함 : 파라미터 값 -> HttpServletRequest request
	@RequestMapping("signIn")
	public String signIn(HttpServletRequest request) {
		String id  = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);
		
		HttpSession session = request.getSession();
		
		if(service.login(member) != null) {
			member = service.login(member);
			session.setAttribute("vo", member);
			return "redirect:/";
		}
		return "login_fail";
			
	}
	
	// allMember - 비즈니스 로직 포함, 데이터 바인딩 - Model
	//	--> return "find_ok";
	
	@RequestMapping("allMember")
	public String allMember(Model model) {
		

		
		List<Member> list = service.showAllMember();
		model.addAttribute("list", list);
		
		return "find_ok";
	}
	
	// logout - 로그아웃 기능!
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		System.out.println(session.getAttribute("vo"));
		if(session.getAttribute("vo") != null) {
			session.invalidate();;
		}
		return "redirect:/";
	}
	
	// update - 페이지 이동
	@RequestMapping("update")
	public String update() {
		return "update";
	}
	
	
	// updateMember - 비즈니스 로직 포함 -> 파리미터 request 필요
	@RequestMapping("updateMember")
	public String updateMember(HttpServletRequest request) {
		
		
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr  = request.getParameter("addr");
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("vo");
		member.setPwd(pwd);
		member.setName(name);
		member.setAddr(addr);
		
		service.updateMember(member);
		
		return "redirect:/";
	}
}
