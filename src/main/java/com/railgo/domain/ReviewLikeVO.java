package com.railgo.domain;

import lombok.Data;

@Data
public class ReviewLikeVO {
	private String code; // 숙/식/관/공/행/SNS 통합코드
	private int mem_code; // 회원코드
	private int memLike_code; // 좋아요누른회원코드
}
