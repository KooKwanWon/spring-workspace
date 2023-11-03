package com.kh.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.test.dao.MemberDAO;
import com.kh.test.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	
	@Override
	public Member loginMember(Member member) {
		return dao.loginMember(member);
	}
}
