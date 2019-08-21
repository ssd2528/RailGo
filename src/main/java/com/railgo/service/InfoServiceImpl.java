package com.railgo.service;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.railgo.domain.CategoryVO;
import com.railgo.domain.MemberVO;
import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;
import com.railgo.domain.TripImageVO;
import com.railgo.mapper.CategoryMapper;
import com.railgo.mapper.ReviewMapper;
import com.railgo.mapper.TripImageMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Log4j
@Service
@AllArgsConstructor
public class InfoServiceImpl implements InfoService {

	@Autowired
	private CategoryMapper categoryMapper;
	private ReviewMapper reviewMapper;
	private TripImageMapper imgMapper;
	
	@Override
	public String findAreaCode(String areaCodeStr) {
		String result="";
		
		if(areaCodeStr.equals("서울")) result="&areaCode=1";
		else if(areaCodeStr.equals("인천")) result="&areaCode=2";
		else if(areaCodeStr.equals("대전")) result="&areaCode=3";
		else if(areaCodeStr.equals("대구")) result="&areaCode=4";
		else if(areaCodeStr.equals("광주")) result="&areaCode=5";
		else if(areaCodeStr.equals("부산")) result="&areaCode=6";
		else if(areaCodeStr.equals("울산")) result="&areaCode=7";
		// 경기도
		else if(areaCodeStr.equals("가평")) result="&areaCode=31&sigunguCode=1";
		else if(areaCodeStr.equals("고양")) result="&areaCode=31&sigunguCode=2";
		else if(areaCodeStr.equals("광명")) result="&areaCode=31&sigunguCode=4";
		else if(areaCodeStr.equals("동두천")) result="&areaCode=31&sigunguCode=10";
		else if(areaCodeStr.equals("수원")) result="&areaCode=31&sigunguCode=13";
		else if(areaCodeStr.equals("안양")) result="&areaCode=31&sigunguCode=17";
		else if(areaCodeStr.equals("양평")) result="&areaCode=31&sigunguCode=19";
		else if(areaCodeStr.equals("평택")) result="&areaCode=31&sigunguCode=28";
		else if(areaCodeStr.equals("화성")) result="&areaCode=31&sigunguCode=31";
		// 강원도
		else if(areaCodeStr.equals("강릉")) result="&areaCode=32&sigunguCode=1";
		else if(areaCodeStr.equals("동해")) result="&areaCode=32&sigunguCode=3";
		else if(areaCodeStr.equals("영월")) result="&areaCode=32&sigunguCode=8";
		else if(areaCodeStr.equals("정선")) result="&areaCode=32&sigunguCode=11";
		else if(areaCodeStr.equals("춘천")) result="&areaCode=32&sigunguCode=13";
		else if(areaCodeStr.equals("태백")) result="&areaCode=32&sigunguCode=14";
		else if(areaCodeStr.equals("평창")) result="&areaCode=32&sigunguCode=15";
		// 충청권
		else if(areaCodeStr.equals("단양")) result="&areaCode=33&sigunguCode=2";
		else if(areaCodeStr.equals("대천")) result="&areaCode=34&sigunguCode=5";
		else if(areaCodeStr.equals("영동")) result="&areaCode=33&sigunguCode=4";
		else if(areaCodeStr.equals("제천")) result="&areaCode=33&sigunguCode=7";
		else if(areaCodeStr.equals("조치원")) result="&areaCode=34&sigunguCode=11";
		else if(areaCodeStr.equals("천안")) result="&areaCode=34&sigunguCode=12";
		else if(areaCodeStr.equals("천안아산")) result="&areaCode=34&sigunguCode=9";
		else if(areaCodeStr.equals("충주")) result="&areaCode=33&sigunguCode=11";
		else if(areaCodeStr.equals("홍성")) result="&areaCode=34&sigunguCode=15";
		// 전라권
		else if(areaCodeStr.equals("곡성")) result="&areaCode=38&sigunguCode=3";
		else if(areaCodeStr.equals("구례구")) result="&areaCode=38&sigunguCode=11";
		else if(areaCodeStr.equals("나주")) result="&areaCode=38&sigunguCode=6";
		else if(areaCodeStr.equals("남원")) result="&areaCode=37&sigunguCode=4";
		else if(areaCodeStr.equals("목포")) result="&areaCode=38&sigunguCode=8";
		else if(areaCodeStr.equals("순천")) result="&areaCode=38&sigunguCode=11";
		else if(areaCodeStr.equals("여수엑스포")) result="&areaCode=38&sigunguCode=13";
		else if(areaCodeStr.equals("익산")) result="&areaCode=37&sigunguCode=9";
		else if(areaCodeStr.equals("전주")) result="&areaCode=37&sigunguCode=12";
		else if(areaCodeStr.equals("정읍")) result="&areaCode=37&sigunguCode=13";
		// 경상권
		else if(areaCodeStr.equals("경주")) result="&areaCode=35&sigunguCode=2";
		else if(areaCodeStr.equals("구미")) result="&areaCode=35&sigunguCode=4";
		else if(areaCodeStr.equals("김천구미")) result="&areaCode=35&sigunguCode=6";
		else if(areaCodeStr.equals("마산")) result="&areaCode=36&sigunguCode=6";
		else if(areaCodeStr.equals("신경주")) result="&areaCode=35&sigunguCode=2";
		else if(areaCodeStr.equals("안동")) result="&areaCode=35&sigunguCode=11";
		else if(areaCodeStr.equals("영주")) result="&areaCode=35&sigunguCode=14";
		else if(areaCodeStr.equals("영천")) result="&areaCode=35&sigunguCode=15";
		else if(areaCodeStr.equals("점촌")) result="&areaCode=35&sigunguCode=7";
		else if(areaCodeStr.equals("진주")) result="&areaCode=36&sigunguCode=13";
		else if(areaCodeStr.equals("춘양")) result="&areaCode=35&sigunguCode=8";
		else if(areaCodeStr.equals("포항")) result="&areaCode=35&sigunguCode=23";
		
		else result="&areaCode=1";
		
		return result;
	}
	
	@Override 
	public String getResponseStr(String url) throws Exception {
		URI uri = new URI(url); 
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String responseStr = restTemplate.getForObject(uri, String.class); 
		System.out.println("## responseStr : " + responseStr);
		return responseStr;
	}
	
	
	@Override
	public JsonObject getItemsObject(String responseStr) { // JsonParser를 이용해 필요한 JsonObject(item)를 추출해서 다시 JsonArray로 가공
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(responseStr);
		JsonObject itemsObject = (JsonObject)((JsonObject)((JsonObject)jsonObject.get("response")).get("body")).get("items");
		
		System.out.println("## itemsObject : " + itemsObject);
		
		return itemsObject;
	}
	
	@Override
	public int getContentId(JsonObject itemObject) {
		int contentId = itemObject.get("contentid").getAsInt();
		System.out.println("## contentId : " + contentId);
		return contentId;
	}
	
	@Override
	public JsonArray makeItemsArray(JsonObject itemsObject) {
		JsonArray itemsArray = (JsonArray) itemsObject.get("item"); 
		return itemsArray;
	}
	
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
	public int getTotalCount(String responseStr) {
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(responseStr);
		JsonObject bodyObject = (JsonObject)((JsonObject)jsonObject.get("response")).get("body");
		//System.out.println("## bodyObject : " + bodyObject);	
		int totalCount = bodyObject.get("totalCount").getAsInt(); 
		//System.out.println("## categotyCount : " + totalCount);
		return totalCount;
	}
	
	@Override
	public String getOverview(JsonObject itemsObject) {
		String overview = ((JsonObject)itemsObject.get("item")).get("overview").getAsString();
		System.out.println("## overview : " + overview);
		return overview;
	}


	@Override
	public ArrayList<CategoryVO> findCat3List(String contentTypeName) {
		return categoryMapper.findCat3List(contentTypeName);
	}
	
	@Override
	public ArrayList<String> findContentTypeName(ArrayList<Integer> contentTypeList) {
		ArrayList<String> list = new ArrayList<String>();
		for(int contentTypeId : contentTypeList) {
			list.add(categoryMapper.findContentTypeName(contentTypeId));
		}
		return list;
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
