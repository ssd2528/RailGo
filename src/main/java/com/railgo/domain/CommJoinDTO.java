package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommJoinDTO {
	private int comm_code; // 댓글코드
	private String code; // 통합코드
	private int mem_code; // 멤버코드
	private int origin_code; // 원댓글 넘버
	private Date regDate; // 댓글 작성일자
	private String name; // 멤버 이름
	private String gender; // 멤버 성별
	private String profile; // 멤버 프로필
}
