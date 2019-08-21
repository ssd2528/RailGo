package com.railgo.service;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.railgo.domain.CategoryVO;
import com.railgo.domain.MemberVO;
import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;
import com.railgo.domain.TripImageVO;

public interface InfoService {
	String findAreaCode(String areaCodeStr);
	String getResponseStr(String url) throws Exception;
	JsonObject getItemsObject(String responseStr);
	int getContentId(JsonObject itemObject);
	JsonArray makeItemsArray(JsonObject itemsObject);

	CategoryVO findCatNameByCat3(String cat3); // cat3을 통해 cat1Name, cat2Name, cat3Name 추출
	 
	ArrayList<Integer> findContentTypeId(String category); 
	int getTotalCount(String responseStr);
	
	String getOverview(JsonObject itemsObject);
	
	ArrayList<CategoryVO> findCat3List(String contentTypeName); // contentTypeName으로 cat3, cat3Name 목록 받기
	ArrayList<String> findContentTypeName(ArrayList<Integer> contentTypeList);
	
	ArrayList<ReviewJoinDTO> findAllReview(int contentid); // contentid로 리뷰 찾기
	void insertReview(ReviewVO vo); // 리뷰 작성
	ArrayList<TripImageVO> findReviewImg(String code); // 리뷰코드로 이미지 조회
	void insertReviewImg(TripImageVO vo); // 리뷰 이미지 저장
	void deleteReview(String r_code); // 리뷰 삭제
}
