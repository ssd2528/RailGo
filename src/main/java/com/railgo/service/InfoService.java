package com.railgo.service;

import com.railgo.domain.CategoryVO;

public interface InfoService {
	String findAreaCode(String areaCodeStr);
	CategoryVO findCatNameByCat3(String cat3); // cat3을 통해 cat1Name, cat2Name, cat3Name 추출
}
