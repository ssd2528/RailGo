package com.railgo.controller;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.railgo.domain.InfoItemDTO;
import com.railgo.domain.CategoryVO;
import com.railgo.service.InfoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
//@Log4j
@RequestMapping("/info")
@AllArgsConstructor
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	@GetMapping("/info")
	public String info() {
		return "info/info";
	}
	
	// Info
	@GetMapping(value="/{areaName}", produces = "application/json; charset=utf-8")
	public ModelAndView infoAreaName(@PathVariable("areaName") String areaName) throws Exception {
		
		System.out.println("-------------------------------------- infoAreaName() --------------------------------------");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/info");
		mv.addObject("areaName", areaName);
		
		/*** TourAPI를 이용해 JSON데이터 추출하는 부분 ***/
		String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray = null;
		String areaCode = infoService.findAreaCode(areaName); // areaName을 받아서 areaCode 받는 부분
		
		String areaURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "ServiceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
				+ "&listYN=Y&MobileOS=ETC&MobileApp=RailGo"
				+ "&contentTypeId=12&cat1=B03&cat1=B03&arrange=B&numOfRows=1" + areaCode + "&_type=json";
		System.out.println("## areaURL : " + areaURL);
		responseStr = infoService.getResponseStr(areaURL);
		itemsObject = infoService.getItemsObject(responseStr);
		int contentId = ((JsonObject) (itemsObject.get("item"))).get("contentid").getAsInt();
		String areaOverviewURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
				+ "&MobileOS=ETC&MobileApp=RailGo&contentId="+contentId+"&overviewYN=Y&_type=json";
		responseStr = infoService.getResponseStr(areaOverviewURL);
		itemsObject = infoService.getItemsObject(responseStr);
		String overview = infoService.getOverview(itemsObject);
		String overviews[] = overview.split("<br");
		mv.addObject("overview", overviews[0]);
		System.out.println("## overview : " + overviews[0] + "\n");
		
		
		
		String foodURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
				+ "&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&contentTypeId=39" // 음식점
				+ areaCode + "&_type=json";
		responseStr = infoService.getResponseStr(foodURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemsArray = infoService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> foodList = makeDTOList(itemsArray);
		mv.addObject("foodList", foodList);
		
		String accommURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
				+ "&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&contentTypeId=32" // 숙박
				+ areaCode + "&_type=json";
		responseStr = infoService.getResponseStr(accommURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemsArray = infoService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> accomList = makeDTOList(itemsArray);		
		mv.addObject("accomList", accomList);
		
		return mv;
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
		System.out.println("## contentTypeId:"+contentTypeId+" / cat1:"+cat1+" / cat2:"+cat2+" / cat3:"+cat3);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category_list");
		mv.addObject("category", category);		mv.addObject("areaName", areaName);		mv.addObject("contentTypeId", contentTypeId);
		mv.addObject("cat1", cat1);		mv.addObject("cat2", cat2);		mv.addObject("cat3", cat3);
		
		if(cat1==null) cat1="";		if(cat2==null) cat2="";		if(cat3==null) cat3="";
		if(arrange==null) arrange="C";		mv.addObject("arrange", arrange);
		if(pageNo==null) pageNo="1";		mv.addObject("currentPage", pageNo);
		
		String areaCode = infoService.findAreaCode(areaName); 
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
				+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=" + arrange + "&contentTypeId="+ contentTypeId + areaCode 
				+ "&cat1="+cat1+"&cat2="+cat2+"&cat3="+cat3
				+ "&pageNo=1&numOfRows=9999&_type=json";
		System.out.println("## url : "  + url);
		String responseStr = infoService.getResponseStr(url);
		
		int totalCount = infoService.getTotalCount(responseStr); mv.addObject("totalCount", totalCount); 
		if(totalCount==0) return mv;
		
		JsonObject itemsObject = infoService.getItemsObject(responseStr);
		JsonArray itemsArray = infoService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> resultList = makeDTOList(itemsArray);
		mv.addObject("list", resultList);
		return mv;
	}
	
	// [숙박,관광명소,맛집] Category No Filter 
	@GetMapping(value= {"/{category}/{areaName}", "/{category}/{areaName}/{currentPage}"}, produces = "application/json; charset=utf-8")
	public ModelAndView category(
			@PathVariable("category") String category, @PathVariable("areaName") String areaName, 
			@PathVariable Optional<Integer> currentPage, @RequestParam(value="arrange", required=false) String arrange) throws Exception {
		
		System.out.println("-------------------------------------- category() --------------------------------------");
		System.out.println("## category : " + category + " / areaName : " + areaName + " / arrange : " + arrange) ;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category"); 
		mv.addObject("category", category);		mv.addObject("areaName", areaName);
		
		// pageNo 
		int pageNo=1;
		if(currentPage.isPresent()) pageNo = currentPage.get();	mv.addObject("currentPage", pageNo);
		pageNo = category.equals("hotplace") ? 1 : pageNo; 
		
		// arrange 
		if(arrange==null) arrange = "C"; mv.addObject("arrange", arrange);		

		String areaCode = infoService.findAreaCode(areaName); 
		ArrayList<Integer> categoryList = infoService.findContentTypeId(category);
		int numOfRows = category.equals("hotplace") ? 9999 : 10;
		
		int totalCount=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray=null;
		JsonArray resultArray=new JsonArray();
		
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=C" + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo +"&numOfRows=" + numOfRows + "&_type=json";
			System.out.println("## url : " + url);
			responseStr = infoService.getResponseStr(url);
			totalCount += infoService.getTotalCount(responseStr);
			itemsObject = infoService.getItemsObject(responseStr);
			itemsArray = infoService.makeItemsArray(itemsObject);
			System.out.println("## itemsArray : " + itemsArray + "\n\n");
			resultArray.addAll(itemsArray);
		}
		
		mv.addObject("totalCount", totalCount); 
		System.out.println("## resultArray : " + resultArray);
		ArrayList<InfoItemDTO> resultList = makeDTOList(resultArray);
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
		System.out.println("## category : " + category + " / areaName : " + areaName + " / arrange : " + arrange);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category_list");
		mv.addObject("category", category);		mv.addObject("areaName", areaName);		mv.addObject("arrange", arrange);
		
		// pageNo 
		int pageNo=1;
		if(currentPage.isPresent()) pageNo = currentPage.get();
		mv.addObject("currentPage", pageNo);
		pageNo = category.equals("hotplace") ? 1 : pageNo; 
		
		String areaCode = infoService.findAreaCode(areaName); 
		ArrayList<Integer> categoryList = infoService.findContentTypeId(category);
		int numOfRows = category.equals("hotplace") ? 9999 : 10;
		
		int totalCount=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray=null;
		JsonArray resultArray=new JsonArray();
		
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange="+ arrange + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&_type=json";
			//System.out.println("## url : " + url);
			responseStr = infoService.getResponseStr(url);
			totalCount += infoService.getTotalCount(responseStr);
			itemsObject = infoService.getItemsObject(responseStr);
			itemsArray = infoService.makeItemsArray(itemsObject);
			System.out.println("## itemsArray : " + itemsArray + "\n\n");
			resultArray.addAll(itemsArray);
		}
		mv.addObject("totalCount", totalCount); 
		System.out.println("## resultArray : " + resultArray);
		ArrayList<InfoItemDTO> resultList = makeDTOList(resultArray);
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
		
		System.out.println("## category : " + category + " / areaName : " + areaName + " / cat3 : " + cat3 + " / arrange : " + arrange);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/category_list");
		mv.addObject("category", category);		mv.addObject("areaName", areaName);		
		mv.addObject("cat3", cat3);		
		
		String cat1 = cat3.substring(0, 3); 	String cat2 = cat3.substring(0, 5);
		System.out.println("## cat1 : " + cat1 + " / cat2 : " + cat2);
		
		// pageNo 
		int pageNo=1;
		if(currentPage.isPresent()) pageNo = currentPage.get();
		System.out.println("## pageNo : " + pageNo);
		mv.addObject("currentPage", pageNo);
		
		// arrange 
		if(arrange==null) arrange = "C"; // 초기값은 최신순(C)
		mv.addObject("arrange", arrange);		
		
		String areaCode = infoService.findAreaCode(areaName); 
		ArrayList<Integer> categoryList = infoService.findContentTypeId(category);
		int numOfRows = category.equals("hotplace") ? 9999 : 10;
		
		int totalCount=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray=null;
		JsonArray resultArray=new JsonArray();
		
		// totalCount먼저 구하기
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=" + arrange + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo +"&numOfRows=" + numOfRows 
					+ "&cat1=" + cat1 + "&cat2=" + cat2 + "&cat3=" + cat3 +"&_type=json";
			System.out.println("## url : " + url);
			responseStr = infoService.getResponseStr(url);
			totalCount += infoService.getTotalCount(responseStr);
		}
		mv.addObject("totalCount", totalCount); 
		if(totalCount==0) return mv; // 조회수가 0개이면 바로 return;
		
		for(int contentTypeId : categoryList) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
					+ "&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=" + arrange + "&contentTypeId="+ contentTypeId + areaCode 
					+ "&pageNo=" + pageNo +"&numOfRows=" + numOfRows 
					+ "&cat1=" + cat1 + "&cat2=" + cat2 + "&cat3=" + cat3 +"&_type=json";
			System.out.println("## url : " + url);
			responseStr = infoService.getResponseStr(url);
			//totalCount += infoService.getTotalCount(responseStr);
			itemsObject = infoService.getItemsObject(responseStr);
			
			if(totalCount == 1) {
				resultArray.add(itemsObject.get("item"));
			}else {
				itemsArray = infoService.makeItemsArray(itemsObject);
				System.out.println("## itemsArray : " + itemsArray + "\n\n");
				resultArray.addAll(itemsArray);
			}
		}

		System.out.println("## resultArray : " + resultArray);
		ArrayList<InfoItemDTO> resultList = makeDTOList(resultArray);
		mv.addObject("list", resultList);
		
		return mv;
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "info/detail";
	}
	@GetMapping("/category")
	public String category() {
		return "info/category";
	}
	
	
	/*** makeDTOList(JsonArray jsonArray) : cat1,2,3 코드를 catName으로 바꿔서 다시 가공하는 일반 메소드  ***/
	public ArrayList<InfoItemDTO> makeDTOList(JsonArray jsonArray) throws Exception {
		ArrayList<InfoItemDTO> list = new ArrayList<InfoItemDTO>();
		System.out.println("## jsonArray size : " + jsonArray.size());
		int contentId=0; String url=null; String responseStr=null; JsonObject itemsObject=null; String overview=null;
		for(int i=0; i<jsonArray.size(); i++) {
			JsonObject itemObject = (JsonObject)jsonArray.get(i); // 각 itemObject 추출
			System.out.println("## itemObject : " + itemObject);
			InfoItemDTO dto = new Gson().fromJson(itemObject, InfoItemDTO.class); // 각 itemObject를 AreaBasedItemDTO에 담기  ( Json => Object )
			System.out.println("## cat코드변경 전 AreaBasedItemDTO : " + dto);
			if(dto.getCat3() == null || dto.getCat1()=="B03") continue;
			CategoryVO vo = infoService.findCatNameByCat3(itemObject.get("cat3").getAsString());  // cat3를 통해 itemObject의 CatName 추출
			dto.setCat1(vo.getCat1Name());		dto.setCat2(vo.getCat2Name());		dto.setCat3(vo.getCat3Name());  // cat코드를 catName으로 변경
			//System.out.println("## cat코드 이름으로 변경 후 AreaBasedItemDTO : " + dto);
			
			// overview 추출 후 dto.setOverview()
			/*contentId = infoService.getContentId(itemObject); 
			url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=9PvcwqzNy2cTGrRteXXTc00BL0lttnj1uPLlqfRlqVwARkgZGSRy84gdjfY54ZbqVRvas8fYlxL3Q1dxDjmLZw%3D%3D"
				+ "&MobileOS=ETC&MobileApp=RailGo&contentId="+contentId+"&overviewYN=Y&_type=json";
			responseStr = infoService.getResponseStr(url);
			itemsObject = infoService.getItemsObject(responseStr);
			overview = infoService.getOverview(itemsObject);
			dto.setOverview(overview); */
			
			list.add(dto);
		}
		return list;
	}
}
