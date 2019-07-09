package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TrafficVO {
	private String code; // 숙,식,관,공,행,SNS 통합코드
	private int s_code; // 소분류 코드
	private String t_category; // 교통 카테고리
	private String name; // 이름
	private int price; // 가격
	private Date time; // 소요시간
	private String info; // 정보
}
