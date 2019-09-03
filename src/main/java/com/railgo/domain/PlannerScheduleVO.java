package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PlannerScheduleVO {
	private String plan_code;
	private String content_addr;// 세부계획 내용의 주소
	private String content_id;	// 세부계획 내용의 id
	private String content_img;	// 세부계획 내용의 img
	private String content_name;// 세부계획 내용의 이름
	private String content_position;	//세부계획 내용의 위치 (lat lng 좌표)
	private String days;	// 3일권 5일권 7일권을 선택했을때의 day ex) day1 day2 day3일때 day1이면 1 day2면 2 day3이면 3	
	private String areacode;
	private String sigungucode;
}
