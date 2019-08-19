package com.railgo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/planner/*")
@AllArgsConstructor
public class PlannerController {

	@RequestMapping("/plan")
	public ModelAndView plan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String ticket = request.getParameter("tickets");
		String startday = request.getParameter("startday");
		System.out.println(ticket + ", " + startday);
		mv.setViewName("planner/plan");
		mv.addObject("ticket", ticket);
		mv.addObject("startday", startday);
		return mv;
	}

	// 카테고리 클릭 시, 클릭된 카테고리에 대한 리스트 목록 50개 뿌리기 
	@RequestMapping(value = "/plan/dataForTheme", produces = "text/html;charset=UTF-8;application/json", method = RequestMethod.POST)
	@ResponseBody
	public String dataForTheme(@RequestBody Map<String, String> param) {
		Map<String, String> themeAndPosition = param;
		String xpos = String.format("%.6f", Double.parseDouble(themeAndPosition.get("xpos")));
		String ypos = String.format("%.6f", Double.parseDouble(themeAndPosition.get("ypos")));
		String theme = themeAndPosition.get("theme");
		System.out.println(xpos + ", " + ypos);
		System.out.println("dataForTheme method : " + theme);
		int themeCode = 0;

		int[] contentTypeId = { 12, 14, 15, 28, 38, 25, 32, 39 }; // 32 - 숙 39 - 식 / 나머지 관광
		if (theme.equals("accom")) {
			themeCode = contentTypeId[6];
		} else if (theme.equals("food")) {
			themeCode = contentTypeId[7];
		} else {
			themeCode = contentTypeId[0];
		}
		BufferedReader br = null;
		try {
			String key = "5J1arCmxYhNIW8f4XlwopZ1O6GyDwUvcAFiUcxYHXROD95kIiO7pfYTye2eDqw551CuepQ1D3goC3BHHQptHCQ%3D%3D";
			String urlStr = "http://api.visitkorea.or.kr/" + "openapi/service/rest/KorService/" + "locationBasedList"
					+ "?serviceKey=" + key + "&numOfRows=50" + "&pageNo=1&MobileOS=ETC&MobileApp=AppTest" + "&arrange=B"
					+ "&contentTypeId=" + themeCode + "&mapX=" + xpos + "&mapY=" + ypos + "&radius=5000" + "&listYN=Y"
					+ "&_type=json";
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			String line = "";
			line = br.readLine();
			System.out.println(line);
			return line;
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
	}

	
	@RequestMapping(value = "/map/locData", produces = "text/html;charset=UTF-8;application/json", method = RequestMethod.POST)
	@ResponseBody
	public String test5(@RequestBody Map<String, String> json) {
		Map<String, String> m = json;
		String xpos = String.format("%.6f", Double.parseDouble(m.get("xpos")));
		String ypos = String.format("%.6f", Double.parseDouble(m.get("ypos")));
		System.out.println(xpos + " , " + ypos);
		int[] contentTypeId = { 12, 14, 15, 28, 38, 25, 32, 39 };
		BufferedReader br = null;
		try {
			String key = "5J1arCmxYhNIW8f4XlwopZ1O6GyDwUvcAFiUcxYHXROD95kIiO7pfYTye2eDqw551CuepQ1D3goC3BHHQptHCQ%3D%3D";
			String urlStr = "http://api.visitkorea.or.kr/" + "openapi/service/rest/KorService/" + "locationBasedList"
					+ "?serviceKey=" + key + "&numOfRows=50" + "&pageNo=1&MobileOS=ETC&MobileApp=AppTest" + "&arrange=B"
					// + "&contentTypeId=" + contentTypeId[6]
					+ "&mapX=" + xpos + "&mapY=" + ypos + "&radius=5000" + "&listYN=Y" + "&_type=json";
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			String line = "";
			line = br.readLine();
			System.out.println(line);
			return line;
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/nailerSchedule", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String test4() {
		BufferedReader br = null;
		try {
			String urlStr = "http://api.visitkorea.or.kr/" + "openapi/service/rest/KorService/areaBasedList"
					+ "?serviceKey=5J1arCmxYhNIW8f4XlwopZ1O6GyDwUvcAFiUcxYHXROD95kIiO7pfYTye2eDqw551CuepQ1D3goC3BHHQptHCQ%3D%3D"
					+ "&pageNo=1&numOfRows=1&MobileApp=AppTest&MobileOS=ETC&arrange=A&contentTypeId=15&areaCode=4&sigunguCode=4&listYN=Y"
					+ "&_type=json";
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			String line = "";
			line = br.readLine();
			System.out.println(line);

			return line;
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
	}
}
