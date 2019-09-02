package com.railgo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.railgo.domain.CategoryVO;
import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;
import com.railgo.domain.TripImageVO;
import com.railgo.mapper.CategoryMapper;
import com.railgo.mapper.ReviewMapper;
import com.railgo.mapper.TripImageMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class InfoServiceImpl implements InfoService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private TripImageMapper imgMapper;
	
	@Override
	public CategoryVO findCatNameByCat3(String cat3) {
		return categoryMapper.findCatNameByCat3(cat3);
	}

	@Override
	public ArrayList<Integer> findContentTypeId(String category) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(category.equals("accom")) list.add(32); // 숙박
		else if(category.equals("hotplace")) {
			list.add(12); // 관광지
			list.add(14); // 문화시설 
			list.add(15); // 축제,공연,행사
			list.add(28); // 레포츠
			list.add(38); // 쇼핑 
		}
		else if(category.equals("food")) list.add(39); // 음식
		return list;
	}

	@Override
	public ArrayList<CategoryVO> findCat3ListByContentType(String contentTypeName) {
		return categoryMapper.findCat3ListByContentType(contentTypeName);
	}
	@Override
	public ArrayList<CategoryVO> findContentTypeList(ArrayList<Integer> contentTypeList) {
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		for(int contentTypeId : contentTypeList) {
			list.add(categoryMapper.findContentTypeList(contentTypeId));
		}
		return list;
	}
	
	@Override 
	public ArrayList<CategoryVO> findCat1List(int contentTypeId){
		return categoryMapper.findCat1List(contentTypeId);
	}
	@Override 
	public ArrayList<CategoryVO> findCat2List(int contentTypeId, String cat1){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("contentTypeId", contentTypeId);
		map.put("cat1", cat1);
		return categoryMapper.findCat2List(map);
	}
	@Override 
	public ArrayList<CategoryVO> findCat3List(String cat2){
		return categoryMapper.findCat3List(cat2);
	}
	
	
	@Override
	public ArrayList<ReviewJoinDTO> findAllReview(int contentid){
		return reviewMapper.findAllReview(contentid);
	}
	@Override
	public void insertReview(ReviewVO vo) {
		reviewMapper.insertReview(vo);
	}
	@Override
	public ArrayList<TripImageVO> findReviewImg(String code) {
		return imgMapper.findReviewImg(code);
	}
	@Override
	public void insertReviewImg(TripImageVO vo) {
		imgMapper.insertReview(vo);
	}
	@Override
	public void deleteReview(String r_code) {
		reviewMapper.deleteReview(r_code);
	}

}
