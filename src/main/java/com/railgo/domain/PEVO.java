package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PEVO {
	private String pe_code; // 숙,식,관,공,행,SNS 통합코드
	private int s_code; // 소분류 코드
	private String name; // 공,행 이름 
	private String describe; // 공,행 정보 설명
	private String price; // 가격정보
	private Date startTime; // 시작날짜 
	private Date finishTime; // 종료날짜
	private String tripMoment; // 여행시기
	private Date regDate; // 등록날짜
	private String theme; // 테마
	private int avgRating; // 평균평점
	private String contactNumber; // 연락처
}
