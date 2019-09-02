package com.railgo.domain;

import lombok.Data;

@Data
public class FollowingVO {
	private String mem_code; // 회원코드
	private String following; // 팔로잉 회원코드
}
