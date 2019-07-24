package com.railgo.controller;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.railgo.domain.AreaBasedItemDTO;
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
	
	@GetMapping(value="/{areaName}", produces = "application/json; charset=utf-8")
	public ModelAndView infoCity(@PathVariable("areaName") String areaName) throws Exception {
		System.out.println("## info areaName : " + areaName);
		
		ModelAndView mv = new ModelAndView();
		
		/*** TourAPI를 이용해 JSON데이터 추출하는 부분 ***/
		String areaCode = infoService.findAreaCode(areaName); // areaName을 받아서 areaCode 받는 부분
		String baseURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=fyNSqF%2BlKTGtgYOP3I8bswAw6c1fuJyBOE1LFcOCaFEPicsqeDZMkQ7ORZAS6gqYmnH41plvBWTKUtVhXGaj4g%3D%3D"
				+ "&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&listYN=Y"
				+ "&arrange=P" // (A=제목순, B=조회순, C=수정일순, D=생성일순, P=이미지만 있는 조회순)
				+ "&contentTypeId=39" // 음식점
				+ areaCode 
				+ "&_type=json";
		URI uri = new URI(baseURL); 
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String responseStr = restTemplate.getForObject(uri, String.class); 
		//System.out.println(responseStr);
		
		/*** JsonParser를 이용해 필요한 JsonObject(item)를 추출해서 다시 JsonArray로 가공 ***/
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(responseStr);
		JsonObject dataObject = (JsonObject)((JsonObject)((JsonObject)jsonObject.get("response")).get("body")).get("items");
		//System.out.println(dataObject);
		JsonArray jsonArray = (JsonArray) dataObject.get("item"); //System.out.println(jsonArray.toString());
		
		/*** cat1,2,3 코드를 catName으로 바꿔서 다시 가공 ***/
		ArrayList<AreaBasedItemDTO> foodList = new ArrayList<AreaBasedItemDTO>();
		for(int i=0; i<jsonArray.size(); i++) {
			JsonObject itemObject = (JsonObject)jsonArray.get(i); // 각 itemObject 추출
			// System.out.println("itemObject : " + itemObject);
			
			AreaBasedItemDTO dto = new Gson().fromJson(itemObject, AreaBasedItemDTO.class); // 각 itemObject를 AreaBasedItemDTO에 담기  ( Json => Object )
			System.out.println("## cat코드변경 전 AreaBasedItemDTO : " + dto);
			CategoryVO vo = infoService.findCatNameByCat3(itemObject.get("cat3").getAsString());  // cat3를 통해 itemObject의 CatName 추출
			dto.setCat1(vo.getCat1Name());		dto.setCat2(vo.getCat2Name());		dto.setCat3(vo.getCat3Name());  // cat코드를 catName으로 변경
			System.out.println("## cat코드 이름으로 변경 후 AreaBasedItemDTO : " + dto);
			
			foodList.add(dto);
		}
		
		mv.setViewName("info/info");
		mv.addObject("areaName", areaName);
		mv.addObject("foodList", foodList);
		
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
}
