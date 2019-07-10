package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private int mem_code; // 회원코드
	private String email; // 이메일
	private String name; // 이름
	private String pwd; // 비밀번호
	private Date regDate; // 가입날짜
	private String gender; // 성별
}
