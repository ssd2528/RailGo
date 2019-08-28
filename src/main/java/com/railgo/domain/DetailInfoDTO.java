package com.railgo.domain;

import lombok.Data;

@Data
public class DetailInfoDTO {
	private String contentid; // 콘텐츠id
	private String contenttypeid; // 콘텐츠타입id
	private String subcontentid; // 하위 콘텐츠id
	private String subdetailalt; // 코스이미지 설명
	private String subdetailimg; // 코스이미지
	private String subdetailoverview; // 코스개요
	private String subname; // 코스명
	private String subnum; // 반복 일련코드
}
