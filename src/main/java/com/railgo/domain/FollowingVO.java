package com.railgo.domain;

import lombok.Data;

@Data
public class FollowingVO {
	private int mem_code; // 회원코드
	private int following; // 팔로잉 회원코드
}
