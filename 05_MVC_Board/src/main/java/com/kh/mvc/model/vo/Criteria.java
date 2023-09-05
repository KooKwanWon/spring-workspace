package com.kh.mvc.model.vo;

import lombok.Data;

// page 와 amount 값을 같이 전달하는 용도
@Data
public class Criteria {
	private int page;	// 페이지 번호
	private int amount;	// 페이지 당 표시해주는 데이터 갯수
	
	public Criteria() {
		this(1, 30);	// 기본 값 지정해서 처리
	}
	
	public Criteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
}
