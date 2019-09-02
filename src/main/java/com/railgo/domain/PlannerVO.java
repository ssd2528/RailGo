package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PlannerVO {
	private String plan_code;	// 여행 계획 코드
	private String mem_code;	// 플래너 제작한 회원 코드
	private String subject;		// 플래너 제목 
	private String hash_tag;	// 여행 테마 -> 계획을 완료 했을때만 부여
	/*
	private int plan_code; // 플래너 코드
	private Date depDate; // 출발 날짜
	private Date arrDate; // 도착 날짜
	private String tripMoment; // 여행 시기
	private Date regDate; // 계획 등록날짜
	private String name; // 계획 이름
	*/
}
