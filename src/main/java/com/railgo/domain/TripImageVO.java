package com.railgo.domain;

import lombok.Data;

@Data
public class TripImageVO {
	private String uuid; // 고유식별자
	private String code; // 숙,식,관,공,행,SNS 통합코드
	private String imagePath; // 이미지 경로
	private String fileName; // 이미지 이름
}
