package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommVO {
	private int comm_code; // 댓글코드
	private String code; // 숙/식/관/공/행/SNS 통합코드
	private int mem_code; // 회원코드
	private int originCode; // 대댓글의 원 회원코드
	private Date regDate; // 등록날짜
}
