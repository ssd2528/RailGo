package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PlannerVO {
	private String plan_code;	// 여행 계획 코드
	private String mem_code;	// 플래너 제작한 회원 코드
	private String subject;		// 플래너 제목 
	private String hash_tag;	// 여행 테마 -> 계획을 완료 했을때만 부여
}