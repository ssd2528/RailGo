package com.railgo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;
import com.railgo.service.MemberService;
import com.railgo.service.PlannerService;
import com.railgo.service.PlannerServiceImpl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.railgo.service.APIService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/planner/*")
@AllArgsConstructor
public class PlannerController {
	@Autowired
	private PlannerService plannerService;
	@Autowired
	private APIService apiService;
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

	// [위치기반] 카테고리 클릭 시, 클릭된 카테고리에 대한 리스트 목록 50개 뿌리기 
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
					+ "?serviceKey=" + key + "&numOfRows=50" + "&pageNo=1&MobileOS=ETC&MobileApp=RailGo" + "&arrange=B"
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
	
	@RequestMapping(value="/searchKeyword", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String searchKeyword(@RequestParam("areaName") String areaName, @RequestParam("keyword") String keyword) throws Exception {
		String areaCode = apiService.findAreaCode(areaName); // areaName을 받아서 areaCode 받는 부분
		keyword = URLEncoder.encode(keyword, "UTF-8");
		String urlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?"
				+ "ServiceKey=5J1arCmxYhNIW8f4XlwopZ1O6GyDwUvcAFiUcxYHXROD95kIiO7pfYTye2eDqw551CuepQ1D3goC3BHHQptHCQ%3D%3D"
				+ "&keyword="+keyword+areaCode+"&listYN=Y&MobileOS=ETC&MobileApp=RailGo&arrange=B&numOfRows=50&pageNo=1&_type=json";
		System.out.println("## url : " + urlStr);
		
		String responseStr=null; 
		responseStr = apiService.getResponseStr(urlStr);
		return responseStr;
	}

	@RequestMapping(value="/plan/saveAndClose")
	@ResponseBody
	public String saveAndClose(@RequestBody PlannerJsonDTO dto) {
		PlannerJsonDTO pjd = dto;
		log.info("##saveAndClose : "+pjd);
		PlannerVO planner = pjd.getPlanner();
		ArrayList<PlannerDateVO> plannerDateArr = pjd.getPlannerDate();
		ArrayList<PlannerScheduleVO> plannerScheduleArr = pjd.getPlannerSchedule();
		log.info("####planner : "+planner);
		log.info("####plannerDateArr : "+plannerDateArr);
		log.info("####plannerScheduleArr : "+plannerScheduleArr);
		plannerService.insertPlanner(planner);
		for(PlannerDateVO vo : plannerDateArr) {
			plannerService.insertPlannerDate(vo);
		}
		if(plannerScheduleArr != null) {
			for(PlannerScheduleVO vo : plannerScheduleArr) {
				plannerService.insertPlannerSchedule(vo);
			}
		}
		return "success";
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
