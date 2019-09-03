package com.railgo.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SNSVO {
	private String name;
	private String sns_code; // 숙/식/관/공/행/SNS통합코드
	private String mem_code; // 회원코드
	private String content; // 내용
	private Date regDate; // 등록날짜
	
	//private List<TripImageVO> attachList;
}
