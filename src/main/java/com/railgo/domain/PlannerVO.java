package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PlannerVO {
	private int plan_code; // 플래너 코드
	private Date depDate; // 출발 날짜
	private Date arrDate; // 도착 날짜
	private String tripMoment; // 여행 시기
	private Date regDate; // 계획 등록날짜
	private String name; // 계획 이름
}
