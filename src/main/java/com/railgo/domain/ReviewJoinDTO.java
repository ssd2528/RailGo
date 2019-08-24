package com.railgo.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReviewJoinDTO {
	private String r_code; // 리뷰 코드
	private int contentid; // 통합 코드
	private String mem_code; // 회원 코드
	private int rating; // 평점
	private String review; // 리뷰글
	private Date regDate; // 리뷰 등록날짜
	private String name; // 멤버 이름
	private String gender; // 멤버 성별
	private String profile; // 멤버 프로필사진
	
	private List<TripImageVO> imgList; // 리뷰 사진
}
