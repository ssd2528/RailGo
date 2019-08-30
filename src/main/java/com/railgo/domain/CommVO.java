package com.railgo.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommVO {
	private int comm_code; // 댓글코드
	private String sns_code; // 통합코드
	private String mem_code; // 회원코드
	private int origin_code; // 대댓글의 원댓글코드
	private String content; // 댓글내용
	private Date regDate; // 등록날짜
}
