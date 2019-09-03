package com.railgo.domain;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommJoinDTO {
	private int comm_code; // 댓글코드
	private String sns_code; // 통합코드
	private String mem_code; // 멤버코드
	private int origin_code; // 원댓글 넘버
	private String content; // 댓글 내용
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
	private Date regDate; // 댓글 작성일자
	private String name; // 멤버 이름
	private String gender; // 멤버 성별
	private String profile; // 멤버 프로필
	
	private ArrayList<CommJoinDTO> rereList; // 대댓글
}
