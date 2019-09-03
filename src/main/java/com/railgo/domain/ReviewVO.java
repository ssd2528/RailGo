package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	private String r_code; // 리뷰 코드
	private int contentid; // 통합 코드(숙,식,관,공,행,SNS 통합코드)
	private String mem_code; // 회원 코드
	private int rating; // 평점
	private String review; // 리뷰글
	private Date regDate; // 리뷰 등록날짜
}
