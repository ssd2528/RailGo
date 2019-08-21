package com.railgo.mapper;

import java.util.ArrayList;

import com.railgo.domain.CategoryVO;

public interface CategoryMapper {
	CategoryVO findCatNameByCat3(String cat3);
	ArrayList<CategoryVO> findCat3List(String contentTypeName);
	String findContentTypeName(int contentTypeId);
}