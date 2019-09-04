package com.railgo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlannerBookmarkVO {
	private String mem_code; // 회원코드
	private String plan_code; // 플래너코드
}
