package com.railgo.service;

import java.util.List;

import com.railgo.domain.ARTVO;

public interface InfoService {
	List<ARTVO> cityListFood(String city);
	
	List<ARTVO> townListFood(String town);
	
	int areaCode(String city);
}
