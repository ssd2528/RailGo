package com.railgo.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.railgo.service.InfoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/info/*")
@AllArgsConstructor
public class InfoController {

	@Inject
	InfoService infoservice;
	
	
	
	
	/*
	 * @RequestMapping(value = "/{city}") public String
	 * cityInfo(@PathVariable("city") String city, Model model) { String serviceKey
	 * =
	 * "9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D";
	 * int areaCode = infoservice.areaCode(city); String urlFood =
	 * "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="
	 * + serviceKey; urlFood +=
	 * "&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&arrange=P&contentTypeId=39&areaCode="
	 * + areaCode; urlFood += "&_type=json"; model.addAttribute("urlFood", urlFood);
	 * return "info/info"; }
	 */
	 
	
	
	
	 
	
	@GetMapping("/detail")
	public String detail() {
		return "info/detail";
	}
	@GetMapping("/category")
	public String category() {
		return "info/category";
	}
}
