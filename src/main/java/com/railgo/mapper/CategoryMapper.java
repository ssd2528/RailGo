package com.railgo.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.railgo.domain.CategoryVO;

public interface CategoryMapper {
	CategoryVO findCatNameByCat3(String cat3);
	ArrayList<CategoryVO> findCat3ListByContentType(String contentTypeName);
	CategoryVO findContentTypeList(int contentTypeId);
	ArrayList<CategoryVO> findCat1List(int contentTypeId);
	ArrayList<CategoryVO> findCat2List(HashMap<String, Object> map);
	ArrayList<CategoryVO> findCat3List(String cat2);
}
