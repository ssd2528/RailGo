package com.railgo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/planner/*")
@AllArgsConstructor
public class PlannerController {
	@GetMapping("/plan")
	public String plan() {
		return "planner/plan";
	}

	@RequestMapping(value="/nailerSchedule", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String test4() {
		  BufferedReader br = null;
		  try {
			  String urlStr = "http://api.visitkorea.or.kr/"
			  		+ "openapi/service/rest/KorService/areaBasedList"
			  		+ "?serviceKey=5J1arCmxYhNIW8f4XlwopZ1O6GyDwUvcAFiUcxYHXROD95kIiO7pfYTye2eDqw551CuepQ1D3goC3BHHQptHCQ%3D%3D"
			  		+ "&pageNo=1&numOfRows=1&MobileApp=AppTest&MobileOS=ETC&arrange=A&contentTypeId=15&areaCode=4&sigunguCode=4&listYN=Y"
			  		+ "&_type=json";
			  URL url = new URL(urlStr);
			  HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			  urlConnection.setRequestMethod("GET");
			  br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			  String line = "";
			  line = br.readLine();
			  System.out.println(line);
	
			return line;
		  }catch(Exception e) {			 
			  System.out.println("error : "+e.getMessage());
			  return null;
		  }
	}
}
