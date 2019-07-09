package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class DetailPlannerVO {
	private int plan_code; // 플래너코드
	private Date startTime; // 시작시간
	private Date finishTime; // 종료시간 
	private int code; // 숙,식,관,공,행,SNS 통합코드
	private String memo;  // 세부메모
	private int price;  // 가격(이용금액)
}
