package com.railgo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.railgo.domain.InfoItemDTO;
import com.railgo.domain.MemberVO;
import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;
import com.railgo.domain.TripImageVO;
import com.railgo.domain.CategoryVO;
import com.railgo.domain.DetailInfoDTO;
import com.railgo.service.APIService;
import com.railgo.service.InfoService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
//@Log4j
@RequestMapping("/info")
@AllArgsConstructor
public class InfoController {

	@Autowired
	private APIService apiService;
	@Autowired
	private InfoService infoService;
	
	// [InfoAreaName]
	@GetMapping(value="/{areaName}", produces = "application/json; charset=utf-8")
	public ModelAndView infoAreaName(@PathVariable("areaName") String areaName) throws Exception {
		System.out.println("-------------------------------------- infoAreaName() --------------------------------------");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/info");
		mv.addObject("areaName", areaName);
		
		String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray = null;
		String areaCode = apiService.findAreaCode(areaName); // areaName을 받아서 areaCode 받는 부분
		
		// 지역 정보 조회
		String areaURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "ServiceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&listYN=Y&MobileOS=ETC&MobileApp=RailGo"
				+ "&contentTypeId=12&cat1=B03&cat1=B03&arrange=B&numOfRows=1" + areaCode + "&_type=json";
		responseStr = apiService.getResponseStr(areaURL);
		itemsObject = apiService.getItemsObject(responseStr);
		int contentId = ((JsonObject) (itemsObject.get("item"))).get("contentid").getAsInt();
		String areaOverviewURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&MobileOS=ETC&MobileApp=RailGo&contentId="+contentId+"&overviewYN=Y&_type=json";
		responseStr = apiService.getResponseStr(areaOverviewURL);
		itemsObject = apiService.getItemsObject(responseStr);
		String overview = apiService.getOverview(itemsObject);
		String overviews[] = overview.split("<br");
		mv.addObject("overview", overviews[0]);
		//System.out.println("## overview : " + overviews[0] + "\n");
		
		// 지역에 따른 맛집 추천
		String foodURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=12&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&contentTypeId=39" // 음식점
				+ areaCode + "&_type=json";
		responseStr = apiService.getResponseStr(foodURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> foodList = makeInfoItemDTOList(itemsArray, "N");
		Collections.shuffle(foodList); mv.addObject("foodList", foodList);
		
		// 지역에 따른 코스 추천
		String courseURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=12&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&contentTypeId=25" // 추천 코스
				+ areaCode + "&_type=json";
		responseStr = apiService.getResponseStr(courseURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> courseList = makeInfoItemDTOList(itemsArray, "Y");
		for(InfoItemDTO course : courseList) {
			course.setCourseSubNames(getSubNamesByCourse(course));
		}
		Collections.shuffle(courseList); mv.addObject("courseList", courseList);
		
		// 지역에 따른 숙박 추천
		String accommURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=12&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&contentTypeId=32" // 숙박
				+ areaCode + "&_type=json";
		responseStr = apiService.getResponseStr(accommURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> accomList = makeInfoItemDTOList(itemsArray, "N");	
		Collections.shuffle(accomList); mv.addObject("accomList", accomList);
		
		return mv;
	}
	
	// [일일코스 정보 조회]
	@PostMapping(value="/courseDetail")
	public ModelAndView courseDetail(@RequestParam("areaName") String areaName, 
			@RequestParam("title") String title, @RequestParam("overview") String overview, 
			@RequestParam("firstimage") String firstimage, @RequestParam("contentId") String contentId) throws Exception {
		System.out.println("-------------------------------------- courseDetail() --------------------------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/one_course_info");
		mv.addObject("areaName", areaName); mv.addObject("title", title); mv.addObject("overview", overview); mv.addObject("firstimage", firstimage); 
		
		String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray = null;
		// 소개정보 조회
		String detailIntro_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "ServiceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&MobileApp=RailGo&MobileOS=ETC&introYN=Y"
				+ "&contentTypeId=25&contentId="+contentId+"&_type=json";
		responseStr = apiService.getResponseStr(detailIntro_URL);
		itemsObject = apiService.getItemsObject(responseStr);
		String distance = ((JsonObject)itemsObject.get("item")).get("distance").getAsString(); // 코스 총 거리
		String taketime = ((JsonObject)itemsObject.get("item")).get("taketime").getAsString(); // 코스 총 소요시간
		mv.addObject("distance", distance);	mv.addObject("taketime", taketime);
		
		// 일일 코스에 대한 코스들 정보 조회
		String detailInfo_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?"
				+ "ServiceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y"
				+ "&contentTypeId=25&contentId="+contentId+"&_type=json";
		responseStr = apiService.getResponseStr(detailInfo_URL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<DetailInfoDTO> detailInfoList = makeDetailInfoDTOList(itemsArray);
		System.out.println("## detailInfoList : " + detailInfoList);
		mv.addObject("list", detailInfoList);
		
		
		String areaCode = apiService.findAreaCode(areaName); 
		String courseURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=4&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&contentTypeId=25" // 추천 코스
				+ areaCode + "&_type=json";
		responseStr = apiService.getResponseStr(courseURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> courseList = makeInfoItemDTOList(itemsArray, "Y");
		Collections.shuffle(courseList); mv.addObject("recommedList", courseList);
		
		return mv;
	}
	
	// [키워드를 통해 컨텐츠 찾기]
	@PostMapping(value="/search/{keyword}")
	public void findCategoryListByKeyword(@PathVariable("keyword") String keyword, @RequestParam("areaName") String areaName) throws Exception {
		String areaCode = apiService.findAreaCode(areaName); 
		String accomKeyword_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?"
				+ "ServiceKey=인증키&keyword=%EA%B4%91%EC%A3%BC"
				+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=C"
				+ areaCode+"&listYN=Y&numOfRows=10&pageNo=1";
		String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray = null;
		responseStr = apiService.getResponseStr(accomKeyword_URL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> courseList = makeInfoItemDTOList(itemsArray, "Y");
	} 
	
	
	
	// [관광명소] CheckBox - FindCat1List
	@ResponseBody
	@GetMapping(value="/find/cat1", params = {"contentTypeId"}, produces = "application/json; charset=utf-8")
	public ArrayList<CategoryVO>  findCat1List(@RequestParam("contentTypeId") int contentTypeId) {
		System.out.println("-------------------------------------- findCat1List() --------------------------------------");
		ArrayList<CategoryVO> cat1List = infoService.findCat1List(contentTypeId);
		return cat1List;
	}
	// [관광명소] CheckBox - FindCat2List
	@ResponseBody
	@GetMapping(value="/find/cat2", params = {"contentTypeId", "cat1"}, produces = "application/json; charset=utf-8")
	public ArrayList<CategoryVO>  findCat2List(@RequestParam("contentTypeId") int contentTypeId, @RequestParam("cat1") String cat1) {
		System.out.println("-------------------------------------- findCat2List() --------------------------------------");
		ArrayList<CategoryVO> cat2List = infoService.findCat2List(contentTypeId, cat1);
		return cat2List;
	}
	// [관광명소] CheckBox - FindCat3List
	@ResponseBody
	@GetMapping(value="/find/cat3", params = {"contentTypeId", "cat2"}, produces = "application/json; charset=utf-8")
	public ArrayList<CategoryVO>  findCat3List(@RequestParam("contentTypeId") int contentTypeId, @RequestParam("cat2") String cat2) {
		System.out.println("-------------------------------------- findCat3List() --------------------------------------");
		ArrayList<CategoryVO> cat3List = infoService.findCat3List(cat2);
		return cat3List;
	}	
	// [관광명소] Filtering List
	@GetMapping(value="/filter/{category}/{areaName}", produces = "application/json; charset=utf-8")
	public ModelAndView filterHotplace(@PathVariable("category") String category, @PathVariable("areaName") String areaName, 
			@RequestParam("contentTypeId") int contentTypeId,
			@RequestParam(value="cat1", required=false) String cat1, @RequestParam(value="cat2", required=false) String cat2, 
			@RequestParam(value="cat3", required=false) String cat3, 
			@RequestParam(value="arrange", required=false) String arrange, @RequestParam(value="pageNo", required=false) String pageNo) throws Exception {
		
		System.out.println("-------------------------------------- filterHotplace() --------------------------------------");
		//System.out.println("## contentTypeId:"+contentTypeId+" / cat1:"+cat1+" / cat2:"+cat2+" / cat3:"+cat3);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category_list");
		mv.addObject("category", category);		mv.addObject("areaName", areaName);		mv.addObject("contentTypeId", contentTypeId);
		mv.addObject("cat1", cat1);		mv.addObject("cat2", cat2);		mv.addObject("cat3", cat3);
		
		if(cat1==null) cat1="";		if(cat2==null) cat2="";		if(cat3==null) cat3="";
		if(arrange==null) arrange="C";		mv.addObject("arrange", arrange);
		if(pageNo==null) pageNo="1";		mv.addObject("currentPage", pageNo);
		
		String areaCode = apiService.findAreaCode(areaName); 
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=" + arrange + "&contentTypeId="+ contentTypeId + areaCode 
				+ "&cat1="+cat1+"&cat2="+cat2+"&cat3="+cat3
				+ "&pageNo=1&numOfRows=9999&_type=json";
		//System.out.println("## url : "  + url);
		String responseStr = apiService.getResponseStr(url);
		
		int totalCount = apiService.getTotalCount(responseStr); mv.addObject("totalCount", totalCount); 
		if(totalCount==0) return mv;
		
		JsonObject itemsObject = apiService.getItemsObject(responseStr);
		JsonArray itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> resultList = makeInfoItemDTOList(itemsArray, "Y");
		mv.addObject("list", resultList);
		return mv;
	}
	
	// [숙박,관광명소,맛집] Category No Filter 
	@GetMapping(value= {"/{category}/{areaName}", "/{category}/{areaName}/{currentPage}"}, produces = "application/json; charset=utf-8")
	public ModelAndView category(
			@PathVariable("category") String category, @PathVariable("areaName") String areaName, 
			@PathVariable Optional<Integer> currentPage, @RequestParam(value="arrange", required=false) String arrange) throws Exception {
		
		System.out.println("-------------------------------------- category() --------------------------------------");
		//System.out.println("## category : " + category + " / areaName : " + areaName + " / arrange : " + arrange) ;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category"); 
		mv.addObject("category", category);		mv.addObject("areaName", areaName);
		
		// pageNo 
		int pageNo=1;
		if(currentPage.isPresent()) pageNo = currentPage.get();	mv.addObject("currentPage", pageNo);
		pageNo = category.equals("hotplace") ? 1 : pageNo; 
		
		// arrange 
		if(arrange==null) arrange = "C"; mv.addObject("arrange", arrange);		

		String areaCode = apiService.findAreaCode(areaName); 
		ArrayList<Integer> categoryList = infoService.findContentTypeId(category);
		int numOfRows = category.equals("hotplace") ? 9999 : 10;
		
		int totalCount=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray=null;
		JsonArray resultArray=new JsonArray();
		
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=C" + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo +"&numOfRows=" + numOfRows + "&_type=json";
			//System.out.println("## url : " + url);
			responseStr = apiService.getResponseStr(url);
			totalCount += apiService.getTotalCount(responseStr);
			itemsObject = apiService.getItemsObject(responseStr);
			itemsArray = apiService.makeItemsArray(itemsObject);
			//System.out.println("## itemsArray : " + itemsArray + "\n\n");
			resultArray.addAll(itemsArray);
		}
		
		mv.addObject("totalCount", totalCount); 
		//System.out.println("## resultArray : " + resultArray);
		ArrayList<InfoItemDTO> resultList = makeInfoItemDTOList(resultArray, "Y");
		mv.addObject("list", resultList);
		
		ArrayList<CategoryVO> cat3List = null;
		if(category.equals("accom")) {
			cat3List = infoService.findCat3ListByContentType("숙박");
			mv.addObject("cat3List", cat3List);
		} else if(category.equals("food")) {
			cat3List = infoService.findCat3ListByContentType("음식");
			mv.addObject("cat3List", cat3List);
		}
		else if(category.equals("hotplace")) {
			ArrayList<CategoryVO> contentTypeList = infoService.findContentTypeList(categoryList);
			mv.addObject("contentTypeList", contentTypeList);
		}
		
		return mv;
	}
	
	
	// [숙박,관광명소,맛집] Category No Filter - Arrange
	@GetMapping(value= {"/{category}/{areaName}", "/{category}/{areaName}/{currentPage}"}, params = {"arrange"}, produces = "application/json; charset=utf-8")
	public ModelAndView arrangeCategory(
			@PathVariable("category") String category, @PathVariable("areaName") String areaName, 
			@PathVariable Optional<Integer> currentPage, @RequestParam("arrange") String arrange) throws Exception {
		
		System.out.println("-------------------------------------- arrangeCategory() --------------------------------");
		//System.out.println("## category : " + category + " / areaName : " + areaName + " / arrange : " + arrange);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category_list");
		mv.addObject("category", category);		mv.addObject("areaName", areaName);		mv.addObject("arrange", arrange);
		
		// pageNo 
		int pageNo=1;
		if(currentPage.isPresent()) pageNo = currentPage.get();
		mv.addObject("currentPage", pageNo);
		pageNo = category.equals("hotplace") ? 1 : pageNo; 
		
		String areaCode = apiService.findAreaCode(areaName); 
		ArrayList<Integer> categoryList = infoService.findContentTypeId(category);
		int numOfRows = category.equals("hotplace") ? 9999 : 10;
		
		int totalCount=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray=null;
		JsonArray resultArray=new JsonArray();
		
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange="+ arrange + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&_type=json";
			//System.out.println("## url : " + url);
			responseStr = apiService.getResponseStr(url);
			totalCount += apiService.getTotalCount(responseStr);
			itemsObject = apiService.getItemsObject(responseStr);
			itemsArray = apiService.makeItemsArray(itemsObject);
			//System.out.println("## itemsArray : " + itemsArray + "\n\n");
			resultArray.addAll(itemsArray);
		}
		mv.addObject("totalCount", totalCount); 
		//System.out.println("## resultArray : " + resultArray);
		ArrayList<InfoItemDTO> resultList = makeInfoItemDTOList(resultArray, "Y");
		mv.addObject("list", resultList);
		
		return mv;
	}
	
	
	// [숙박,맛집] Category CheckBox Filter
	@RequestMapping(value= {"/filter/{category}/{areaName}/{cat3}", "/filter/{category}/{areaName}/{cat3}/{currentPage}"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf-8")
	@ResponseBody
	public ModelAndView filterCategory(
			@PathVariable("category") String category, @PathVariable("areaName") String areaName, 
			@PathVariable("cat3") String cat3, @PathVariable Optional<Integer> currentPage,
			@RequestParam(value="arrange", required=false) String arrange) throws Exception {
		
		System.out.println("-------------------------------------- filterCategory() --------------------------------");
		
		//System.out.println("## category : " + category + " / areaName : " + areaName + " / cat3 : " + cat3 + " / arrange : " + arrange);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category_list");
		mv.addObject("category", category);		mv.addObject("areaName", areaName);		
		mv.addObject("cat3", cat3);		
		
		String cat1 = cat3.substring(0, 3); 	String cat2 = cat3.substring(0, 5);
		//System.out.println("## cat1 : " + cat1 + " / cat2 : " + cat2);
		
		// pageNo 
		int pageNo=1;
		if(currentPage.isPresent()) pageNo = currentPage.get();
		//System.out.println("## pageNo : " + pageNo);
		mv.addObject("currentPage", pageNo);
		
		// arrange 
		if(arrange==null) arrange = "C"; // 초기값은 최신순(C)
		mv.addObject("arrange", arrange);		
		
		String areaCode = apiService.findAreaCode(areaName); 
		ArrayList<Integer> categoryList = infoService.findContentTypeId(category);
		int numOfRows = category.equals("hotplace") ? 9999 : 10;
		
		int totalCount=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray=null;
		JsonArray resultArray=new JsonArray();
		
		// totalCount먼저 구하기
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=" + arrange + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo +"&numOfRows=" + numOfRows 
					+ "&cat1=" + cat1 + "&cat2=" + cat2 + "&cat3=" + cat3 +"&_type=json";
			//System.out.println("## url : " + url);
			responseStr = apiService.getResponseStr(url);
			totalCount += apiService.getTotalCount(responseStr);
		}
		mv.addObject("totalCount", totalCount); 
		if(totalCount==0) return mv; // 조회수가 0개이면 바로 return;
		
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=" + arrange + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo +"&numOfRows=" + numOfRows 
					+ "&cat1=" + cat1 + "&cat2=" + cat2 + "&cat3=" + cat3 +"&_type=json";
			//System.out.println("## url : " + url);
			responseStr = apiService.getResponseStr(url);
			//totalCount += infoService.getTotalCount(responseStr);
			itemsObject = apiService.getItemsObject(responseStr);
			
			if(totalCount == 1) {
				resultArray.add(itemsObject.get("item"));
			}else {
				itemsArray = apiService.makeItemsArray(itemsObject);
				//System.out.println("## itemsArray : " + itemsArray + "\n\n");
				resultArray.addAll(itemsArray);
			}
		}

		//System.out.println("## resultArray : " + resultArray);
		ArrayList<InfoItemDTO> resultList = makeInfoItemDTOList(resultArray, "Y");
		mv.addObject("list", resultList);
		
		return mv;
	}
	
	@PostMapping(value = "/detailInfo")
	public ModelAndView detail(@RequestParam("areaName") String areaName, @RequestParam("contentid") int contentid) throws Exception {
		System.out.println("## info contentid : " + contentid);
		String responseStr = null; JsonObject itemsObject = null; JsonArray itemsArray = null; JsonObject itemObject = null;
		ArrayList<InfoItemDTO> list = new ArrayList<InfoItemDTO>();

		// [공통정보 조회]
		String baseURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&MobileOS=ETC&MobileApp=RailGo&listYN=Y" + "&contentId=" + contentid // 컨텐트id 매칭
				+ "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y" + "&_type=json";
		responseStr = apiService.getResponseStr(baseURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemObject = (JsonObject) itemsObject.get("item");
		InfoItemDTO dto = new Gson().fromJson(itemObject, InfoItemDTO.class); 
		System.out.println("## dto : " + dto);
		String category = "";
		if(dto.getContenttypeid()==32) {
			category="숙박";
		}else if(dto.getContenttypeid()==12 || dto.getContenttypeid()==14 || dto.getContenttypeid()==15 || dto.getContenttypeid()==28 || dto.getContenttypeid()==38) {
			category="관광명소";
		}else if(dto.getContenttypeid()==39) {
			category="음식점";
		}
		
		return this.detail(dto.getTitle(), dto.getContentid(), dto.getContenttypeid(), dto.getMapx(), dto.getMapy(), areaName, category);
	}
	
	
	// Detail
	@PostMapping(value = "/detail/{title}", produces = "text/plain; charset=utf-8")
	public ModelAndView detail(@PathVariable("title") String title, @RequestParam("contentid") int contentid,
			@RequestParam("contenttypeid") int contenttypeid, @RequestParam("mapx") String mapx,
			@RequestParam("mapy") String mapy, @RequestParam("areaName") String areaName, @RequestParam("category") String category) throws Exception {
		
		  System.out.println("## info title : " + title);
		  System.out.println("## info contentid : " + contentid);
		  System.out.println("## info contenttypeid : " + contenttypeid);
		  System.out.println("## info mapx : " + mapx);
		  System.out.println("## info mapy : " + mapy);
		  System.out.println("## info areaName : " + areaName);
		  System.out.println("## info category : " + category);
		 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/detail");
		mv.addObject("areaName", areaName);		mv.addObject("category", category);

		String responseStr = null; JsonObject itemsObject = null; JsonArray itemsArray = null; JsonObject itemObject = null;
		ArrayList<InfoItemDTO> list = new ArrayList<InfoItemDTO>();

		// [공통정보 조회]
		String baseURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&MobileOS=ETC&MobileApp=RailGo&listYN=Y" + "&contentId=" + contentid // 컨텐트id 매칭
				+ "&contentTypeId=" + contenttypeid // 컨텐트타입 매칭
				+ "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y" + "&_type=json";
		responseStr = apiService.getResponseStr(baseURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemObject = (JsonObject) itemsObject.get("item");
		InfoItemDTO dto = new Gson().fromJson(itemObject, InfoItemDTO.class); 
		CategoryVO vo = infoService.findCatNameByCat3(itemObject.get("cat3").getAsString()); 
		dto.setCat1(vo.getCat1Name());
		dto.setCat2(vo.getCat2Name());
		dto.setCat3(vo.getCat3Name()); // cat코드를 catName으로 변경
		mv.addObject("detail", dto);

		// [소개정보 조회] 
		String detailURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=10&MobileOS=ETC&MobileApp=RailGo" + "&contentId=" + contentid + "&contentTypeId="
				+ contenttypeid + "&_type=json";
		responseStr = apiService.getResponseStr(detailURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemObject = (JsonObject) itemsObject.get("item");
		if (contenttypeid == 39) { // 카테고리가 '맛집'인 경우에는 '오픈시간' 추가
			if(itemObject.get("opentimefood")!=null) mv.addObject("opentime", itemObject.get("opentimefood").getAsString());
			else  mv.addObject("opentime", null);
		} else if (contenttypeid == 32) { // 카테고리가 '숙박'인 경우에는 '체크인/아웃  시간' 추가
			mv.addObject("chkintime", itemObject.get("checkintime").getAsString());
			mv.addObject("chkouttime", itemObject.get("checkouttime").getAsString());
		}

		// [이미지정보 조회]
		String imageURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=2&MobileOS=ETC&MobileApp=RailGo" + "&contentId=" + contentid + "&imageYN=Y" + "&_type=json";
		responseStr = apiService.getResponseStr(imageURL);
		int total = apiService.getTotalCount(responseStr);
		if (total == 0) {
			mv.addObject("img1", null);
			mv.addObject("img2", null);
		} else if (total == 1) {
			itemsObject = apiService.getItemsObject(responseStr);
			itemObject = (JsonObject) itemsObject.get("item");
			mv.addObject("img1", itemObject.get("originimgurl").getAsString());
			mv.addObject("img2", null);
		} else {
			itemsObject = apiService.getItemsObject(responseStr);
			itemsArray = apiService.makeItemsArray(itemsObject);
			mv.addObject("img1", ((JsonObject) itemsArray.get(0)).get("originimgurl").getAsString());
			mv.addObject("img2", ((JsonObject) itemsArray.get(1)).get("originimgurl").getAsString());
		}

		// [근처 컨텐츠 정보 조회]
		String locURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?"
				+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&numOfRows=3&MobileOS=ETC&MobileApp=RailGo&arrange=E" + "&contentTypeId=" + contenttypeid + "&mapX="
				+ mapx + "&mapY=" + mapy + "&radius=100000000&listYN=Y" + "&_type=json";
		responseStr = apiService.getResponseStr(locURL);
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		for (int i = 1; i < itemsArray.size(); i++) { // 거리순이라 0번째는 자기 자신이기 때문에 1부터 추출
			JsonObject locObject = (JsonObject) itemsArray.get(i); // 각 locObject 추출
			InfoItemDTO infoDto = new Gson().fromJson(locObject, InfoItemDTO.class); 
			//System.out.println("## 근처 컨텐츠 : " + infoDto);
			if(! locObject.get("cat1").getAsString().equals("B03")) {
				CategoryVO catVo = infoService.findCatNameByCat3(locObject.get("cat3").getAsString()); 
				infoDto.setCat1(catVo.getCat1Name());
				infoDto.setCat2(catVo.getCat2Name());
				infoDto.setCat3(catVo.getCat3Name()); // cat코드를 catName으로 변경
				list.add(infoDto);
			}
		}
		mv.addObject("locList", list);

		// [리뷰 목록 조회]
		ArrayList<ReviewJoinDTO> reList = infoService.findAllReview(contentid);
		int ratingTotal = 0; // 총 평점
		for(ReviewJoinDTO reviewJoinDTO : reList) {
			String r_code = reviewJoinDTO.getR_code();
			ratingTotal += reviewJoinDTO.getRating();
			ArrayList<TripImageVO> reviewImgList =  infoService.findReviewImg(r_code);
			if(reviewImgList!=null) {
				reviewJoinDTO.setImgList(reviewImgList);
			}
		}
		//log.info("## reList: " + reList);
		float ratingAvg = ratingTotal/(float)reList.size();
		//log.info("## ratingAvg: " + ratingAvg + " / ratingTotal : " + ratingTotal + " / reList.size() : " + reList.size());
		mv.addObject("ratingAvg" , ratingAvg);
		mv.addObject("reList", reList);
		return mv;
	}
	
	// 이미지 업로드 
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void upload(MultipartFile[] uploadFile) {
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();
		
		// 이미지 저장 폴더 생성
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {
			TripImageVO imgvo = new TripImageVO();
			//log.info("## Upload File Name: " + multipartFile.getOriginalFilename());
			//log.info("## Upload File Size: " + multipartFile.getSize());
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			imgvo.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				imgvo.setUuid(uuid.toString());
				imgvo.setImagePath(uploadFolderPath);
				if (checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				infoService.insertReviewImg(imgvo);
			} catch (Exception e) {
				//log.info("## upload Exception: " + e);
			}
		}
	}
	
	// 리뷰 작성 
	@PostMapping("/insertReview")
	public void insertReview(ReviewVO vo) {
		//log.info("## ReviewVO: " + vo);
		infoService.insertReview(vo);
	}
	// 업로드 된 이미지를 썸네일로 보여주는 부분 
	@GetMapping("/display")	  
	@ResponseBody 
	public ResponseEntity<byte[]> getFile(String fileName){ 
	File file = new File("C:\\upload\\" + fileName); 
	ResponseEntity<byte[]> result = null;
		try { 
			HttpHeaders header = new HttpHeaders(); 
			header.add("Content-Type", Files.probeContentType(file.toPath())); 
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch(IOException ie) { 
			//log.info("## display exception: " + ie); 
		} 
		return result; 
	}
	 
	// 리뷰 삭제 
	@PostMapping("/deleteReview")
	public String remove(@RequestBody Map<String,String> r_code) {
		Map<String,String> code = new HashMap<String,String>();
		code = r_code;
		//log.info("## delete: " + code.get("r_code"));
		if(r_code == null) return null;
		infoService.deleteReview(code.get("r_code"));
		return "성공";
	}
	
	
	
	/*** makeInfoItemDTOList(JsonArray jsonArray) : cat1,2,3 코드를 catName으로 바꿔서 다시 가공하는 일반 메소드  ***/
	public ArrayList<InfoItemDTO> makeInfoItemDTOList(JsonArray jsonArray, String checkOverview) throws Exception {
		ArrayList<InfoItemDTO> list = new ArrayList<InfoItemDTO>();
		//System.out.println("## jsonArray size : " + jsonArray.size());
		int contentId=0; String url=null; String responseStr=null; JsonObject itemsObject=null; String overview=null;
		for(int i=0; i<jsonArray.size(); i++) {
			JsonObject itemObject = (JsonObject)jsonArray.get(i); // 각 itemObject 추출
			//System.out.println("## itemObject : " + itemObject);
			InfoItemDTO dto = new Gson().fromJson(itemObject, InfoItemDTO.class); // 각 itemObject를 AreaBasedItemDTO에 담기  ( Json => Object )
			//System.out.println("## cat코드변경 전 AreaBasedItemDTO : " + dto);
			if(dto.getCat3() == null || dto.getCat1()=="B03") continue;
			CategoryVO vo = infoService.findCatNameByCat3(itemObject.get("cat3").getAsString());  // cat3를 통해 itemObject의 CatName 추출
			if(vo!=null) {
				dto.setCat1(vo.getCat1Name());		dto.setCat2(vo.getCat2Name());		dto.setCat3(vo.getCat3Name());  // cat코드를 catName으로 변경
				if(checkOverview.equals("Y")) {
					// overview 추출 후 dto.setOverview()
					contentId = apiService.getContentId(itemObject); 
					url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
						+ "serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
						+ "&MobileOS=ETC&MobileApp=RailGo&contentId="+contentId+"&overviewYN=Y&_type=json";
					responseStr = apiService.getResponseStr(url);
					itemsObject = apiService.getItemsObject(responseStr);
					overview = apiService.getOverview(itemsObject);
					dto.setOverview(overview); 
				}
				
				list.add(dto);
			}
		}
		return list;
	}
	
	/*** makeDetailInfoDTOList ***/
	public ArrayList<DetailInfoDTO> makeDetailInfoDTOList(JsonArray jsonArray) {
		ArrayList<DetailInfoDTO> list = new ArrayList<DetailInfoDTO>();
		for(int i=0; i<jsonArray.size(); i++) {
			JsonObject itemObject = (JsonObject)jsonArray.get(i);
			DetailInfoDTO dto = new Gson().fromJson(itemObject, DetailInfoDTO.class);
			list.add(dto);
		}
		return list;
	}
	
	/*** 각 코스에 대한 코스 정보 가공해주는 메소드 (코스1-코스2-코스3..) ***/
	public String getSubNamesByCourse(InfoItemDTO course) throws Exception {
		int contentId = course.getContentid();
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?"
				+ "ServiceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&contentTypeId=25&contentId="+contentId+"&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&listYN=Y&_type=json";
		String responseStr = apiService.getResponseStr(url);
		JsonObject itemsObject = apiService.getItemsObject(responseStr);
		JsonArray itemsArray = apiService.makeItemsArray(itemsObject);
		
		String subNames = ""; JsonObject itemObject=null;
		for(int i=0; i<itemsArray.size(); i++) {
			itemObject = (JsonObject)itemsArray.get(i);
			if(i==itemsArray.size()-1) subNames += itemObject.get("subname").getAsString();
			else subNames += itemObject.get("subname").getAsString() + " → ";
		}
		//System.out.println("## 코스 정보 : " + subNames);
		return subNames;
	}

	/*** 날짜별로 이미지 저장 폴더 생성해주는 메소드 ***/
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}

	/*** 이미지 파일을 판단해주는 메소드 ***/
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException ie) {
			//log.info("## checkImageType Exception: " + ie);
		}
		return false;
	}
}
