package com.railgo.mapper;

import java.util.List;

import com.railgo.domain.ARTVO;

public interface ARTMapper {
	public List<ARTVO> cityListFood(String city); // 대분류코드로 맛집 리스트 SELECT
	
	public List<ARTVO> townListFood(String town); // 중분류코드로 맛집 리스트 SELECT
}
