package com.railgo.service;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.railgo.domain.CategoryVO;

public interface InfoService {
	CategoryVO findCatNameByCat3(String cat3); // cat3을 통해 cat1Name, cat2Name, cat3Name 추출
	 
	ArrayList<Integer> findContentTypeId(String category); 
		
	ArrayList<CategoryVO> findCat3ListByContentType(String contentTypeName); // contentTypeName으로 cat3, cat3Name 목록 받기
	ArrayList<CategoryVO> findContentTypeList(ArrayList<Integer> contentTypeList);

	ArrayList<CategoryVO> findCat1List(int contentTypeId); 
	ArrayList<CategoryVO> findCat2List(int contentTypeId, String cat1); 
	ArrayList<CategoryVO> findCat3List(String cat2);
}
