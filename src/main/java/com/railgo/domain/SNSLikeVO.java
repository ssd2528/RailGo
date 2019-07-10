package com.railgo.domain;

import lombok.Data;

@Data
public class SNSLikeVO {
	private String sns_code; //숙/식/관/공/행/SNS 통합코드
	private int mem_code; //회원코드
}
