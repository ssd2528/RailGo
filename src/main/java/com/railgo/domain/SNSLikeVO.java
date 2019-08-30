package com.railgo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SNSLikeVO {
	private String sns_code; // 통합코드
	private String mem_code; // 회원코드
}