package com.railgo.domain;

import lombok.Data;

@Data
public class CADVO {
	private int s_code; // 소분류 코드
	private int l_code; // 대분류 코드
	private String city; // 시도 이름 
	private int m_code; // 중분류 코드
	private String town; // 시군구 코드
	private String townShip; // 읍면동 이름
}
