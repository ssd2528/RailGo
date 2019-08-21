package com.railgo.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SNSJoinDTO {
	private String sns_code; // 통합코드
	private String mem_code; // 회원코드
	private String content; // 내용
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
	private Date regDate; // 등록날짜
	private String name; // 멤버 이름
	private String gender; // 멤버 성별
	private String profile; // 멤버 프로필 사진
	
	private List<TripImageVO> imgList; // SNS 사진
}
