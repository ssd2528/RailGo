package com.railgo.service;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.railgo.domain.CategoryVO;
import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;
import com.railgo.domain.TripImageVO;

public interface InfoService {
	CategoryVO findCatNameByCat3(String cat3); // cat3을 통해 cat1Name, cat2Name, cat3Name 찾는 메소드
	 
	ArrayList<Integer> findContentTypeId(String category); // category로 contentTypeId 찾는 메소드
		
	ArrayList<CategoryVO> findCat3ListByContentType(String contentTypeName); // contentTypeName으로 cat3, cat3Name 목록 받는 메소드
	ArrayList<CategoryVO> findContentTypeList(ArrayList<Integer> contentTypeList);

	ArrayList<CategoryVO> findCat1List(int contentTypeId); 
	ArrayList<CategoryVO> findCat2List(int contentTypeId, String cat1); 
	ArrayList<CategoryVO> findCat3List(String cat2);
	
	/* 찬우 */
	//ArrayList<String> findContentTypeName(ArrayList<Integer> contentTypeList);
	ArrayList<ReviewJoinDTO> findAllReview(int contentid); // contentid로 리뷰 찾기
	void insertReview(ReviewVO vo); // 리뷰 작성
	ArrayList<TripImageVO> findReviewImg(String code); // 리뷰코드로 이미지 조회
	void insertReviewImg(TripImageVO vo); // 리뷰 이미지 저장
	void deleteReview(String r_code); // 리뷰 삭제
}
