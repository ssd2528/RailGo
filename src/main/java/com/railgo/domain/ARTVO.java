package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ARTVO {
	private String art_code; // 숙,식,관,공,행,SNS 통합코드
	private int s_code; // 소분류
	private String address; // 상세주소
	private String name; // 이름
	private String price; // 가격 정보 
	private String explain; // 설명
	private String usingHour; // 이용시간
	private String category; // 카테고리 정보
	private int avgRating; // 평균 평점
	private String theme; // 테마 정보 
	private String tripMoment; // 여행 시기
	private Date regDate; // 등록 날짜
	private String contactNumber; // 연락처 
}
