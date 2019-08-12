package com.railgo.domain;

import lombok.Data;

@Data
public class CategoryVO {
	private int contentTypeId; // 서비스분류 코드
	private String contentTypeName; // 서비스분류 명
	private String cat1; // 대분류 코드
	private String cat1Name; // 대분류 명
	private String cat2; // 중분류 코드
	private String cat2Name; // 중분류 명
	private String cat3; // 소분류 코드 (PK)
	private String cat3Name; // 소분류 명
}
