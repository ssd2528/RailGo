package com.railgo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.railgo.domain.InfoItemDTO;
import com.railgo.service.APIService;
import com.railgo.service.InfoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {
	
	@Autowired
	private APIService apiService;
	private InfoController infoController;
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("planner")
	public String planner() {
		return "planner/list";
	}
	@RequestMapping("search")
	public String search() {
		return "info/search";
	}
	
	// [키워드를 통해 컨텐츠 찾기 init]
	@PostMapping(value="/search/{keyword}")
	public ModelAndView searchKeyword(@PathVariable("keyword") String keyword) throws Exception {
		System.out.println("-------------------------------------- searchKeyword() --------------------------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/search");
		
		mv.addObject("keyword", keyword);	
		keyword = URLEncoder.encode(keyword, "UTF-8");
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?"
				+ "ServiceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&listYN=Y&numOfRows=12&pageNo=1&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&keyword="+keyword+"&contentTypeId=12&_type=json";
		String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray = null;
		responseStr = apiService.getResponseStr(url);
		int totalCount = apiService.getTotalCount(responseStr); mv.addObject("totalCount", totalCount); 
		if(totalCount==0) {
			mv.addObject("list", null);
			return mv;
		}
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> categoryList = infoController.makeInfoItemDTOList(itemsArray, "N");
		
		String areaName="";
		for(InfoItemDTO dto : categoryList) {
			System.out.println("## " + dto.getAddr1() + "의  areaCode : " + dto.getAreacode() + " / sigungucode : " + dto.getSigungucode());
			areaName = apiService.findAreaName(dto.getAreacode(), dto.getSigungucode());
			System.out.println("## areaName : " + areaName);
			dto.setAreaName(areaName);
		}
		System.out.println("## categoryList : " + categoryList);
		
		mv.addObject("list", categoryList);
		
		return mv;
	} 
	
	// 필터링을 이용한 키워드 검색 결과 
	@PostMapping(value="/search/filter", produces = "application/json; charset=utf-8")
	public ModelAndView searchKeywordByFiltering(@RequestParam("keyword") String keyword,
			@RequestParam("category") String category, @RequestParam("currentPage") int currentPage) throws Exception {
		System.out.println("-------------------------------------- searchKeywordByFiltering() --------------------------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/search_list");
		
		mv.addObject("keyword", keyword);	mv.addObject("category", category); mv.addObject("currentPage", currentPage);
		
		int contentTypeId=0; String responseStr=null; JsonObject itemsObject=null; JsonArray itemsArray = null;
		if(category.equals("숙박")) contentTypeId=32;
		else if(category.equals("맛집")) contentTypeId=39;
		else if(category.equals("관광명소")) contentTypeId=12;
		
		keyword = URLEncoder.encode(keyword, "UTF-8");
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?"
				+ "ServiceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D"
				+ "&listYN=Y&numOfRows=12&pageNo="+currentPage+"&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P"
				+ "&keyword="+keyword+"&contentTypeId="+contentTypeId+"&_type=json";
		responseStr = apiService.getResponseStr(url);
		int totalCount = apiService.getTotalCount(responseStr); mv.addObject("totalCount", totalCount); 
		if(totalCount==0) {
			mv.addObject("list", null);
			return mv;
		}
		itemsObject = apiService.getItemsObject(responseStr);
		itemsArray = apiService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> categoryList = infoController.makeInfoItemDTOList(itemsArray, "N");
		System.out.println("## categoryList : " + categoryList);
		
		mv.addObject("list", categoryList);
		
		return mv;
	}
}
