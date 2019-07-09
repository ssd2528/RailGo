package com.railgo.domain;

import lombok.Data;

@Data
public class MemberAddVO {
	private int mem_code; // 회원코드
	private String address; // 주소
	private String job; // 직업
	private String brith; // 생년월일
	private String interest; // 관심사
	private String phone; // 폰 번호
	private String profile; // 프로필 경로
	private String backImage; // 배경사진 경로
}
