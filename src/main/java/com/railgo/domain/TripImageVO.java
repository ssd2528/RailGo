package com.railgo.domain;

import lombok.Data;

@Data
public class TripImageVO {
	/*
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
	*/
	
	private String uuid;
	private String sns_code; // 숙,식,관,공,행,SNS 통합코드
	private String imagePath; // 이미지 경로
	private String filename;
	
}
