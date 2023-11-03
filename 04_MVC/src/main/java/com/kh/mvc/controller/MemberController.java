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
		// --> list 값! 데이터 바인딩 -> Model!
		List<Member> list = service.findMember(keyword);

		if (list.isEmpty())
			return "find_fail";

		model.addAttribute("list", list);
		return "find_ok"; // 실패시 "find_fail"
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

		return "redirect:/"; // index.jsp
	}

	// login - 페이지 이동
	@RequestMapping("login")
	public String login() {
		return "login";
	}

	// signIn - 비즈니스 로직 포함 : 파라미터 값 -> HttpServletRequest request


	@RequestMapping("signIn") // 강사님이 한거
	public String signIn(Member vo, /* HttpServletRequest request */ HttpSession session) {
		Member member = service.login(vo);

//		HttpSession session = request.getSession();

		if (member != null) {
			session.setAttribute("vo", member);
		}
		return "login_result";

	}

	// allMember - 비즈니스 로직 포함, 데이터 바인딩 - Model
	// --> return "find_ok";

	@RequestMapping("allMember")
	public String allMember(Model model) {

		List<Member> list = service.showAllMember();
		model.addAttribute("list", list);

		return "find_ok";
	}

	// logout - 로그아웃 기능!



	@RequestMapping("logout") // 강사님이 한거
	public String logout(HttpSession session) {
		if (session.getAttribute("vo") != null) {
			session.invalidate();
			;
		}
		return "redirect:/";
	}

	// update - 페이지 이동
	@RequestMapping("update")
	public String update() {
		return "update";
	}

	// updateMember - 비즈니스 로직 포함 -> 파리미터 request 필요
	


	@RequestMapping("updateMember")	// 강사님
	public String updateMember(Member vo, HttpSession session) {


		service.updateMember(vo);
		
		if(session.getAttribute("vo") != null) {
			session.setAttribute("vo", vo);
		}

		return "redirect:/";
	}
}
