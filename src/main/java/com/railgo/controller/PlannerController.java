package com.railgo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
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
import com.google.gson.Gson;
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
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="detail", produces = "text/html;charset=UTF-8;application/json")
	@ResponseBody
	public String detailview(@RequestParam("areacode") String areacode, @RequestParam("sigungucode") String sigungucode) {
		System.out.println("areacode : "+areacode+", sigungucode : "+sigungucode);
		String areaName = apiService.findAreaName(Integer.parseInt(areacode), Integer.parseInt(sigungucode));
		System.out.println("areaName : "+areaName);
		return areaName;
	}
	@RequestMapping(value="getOtherUsersScheduleList", produces = "text/html;charset=UTF-8;application/json", method = RequestMethod.POST)
	@ResponseBody
	public String getOtherUsersScheduleList(@RequestBody Map<String,String> json) {
		Map<String, String> map = json;
		ModelAndView mv = new ModelAndView();
		System.out.println("planner/getOtherUsersScheduleList init mem_code: " + map.get("mem_code"));
		String mem_code = map.get("mem_code");
		int start = Integer.parseInt(map.get("start"));
		int end = Integer.parseInt(map.get("end"));
		if(mem_code != null) {
			ArrayList<PlannerJsonDTO> plannerScheduleJsonList = plannerService.PlanScheduleList(mem_code,"plan",start,end);
			if(plannerScheduleJsonList == null || plannerScheduleJsonList.size() == 0) {
				return "null";
			}else {
				int totalCount = plannerScheduleJsonList.size();
				Gson gson = new Gson();
				String plannerScheduleJsonListToJson = gson.toJson(plannerScheduleJsonList);
				System.out.println("ArrayList  :"+plannerScheduleJsonList);
				System.out.println("ArrayList -> Json result :"+plannerScheduleJsonListToJson);
				return plannerScheduleJsonListToJson;
			}
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="getUserNameScheduleList", produces = "text/html;charset=UTF-8;application/json", method = RequestMethod.POST)
	@ResponseBody
	public String getUserNameScheduleList(@RequestBody Map<String,String> json) {
		Map<String, String> map = json;
		System.out.println("planner/getUserNameScheduleList init member name : " + map.get("mem_code"));
		String mem_code = map.get("mem_code");
		String name = memberService.getMemberName(mem_code);
		if(name != null)	return name;
		else	return "fail";
		
	}
	@RequestMapping("/plan")
	public ModelAndView plan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String scheduleitem = request.getParameter("item");
		String ticket = request.getParameter("tickets");
		String startday = request.getParameter("startday");
		String plancode = request.getParameter("plancode");
		System.out.println("item: "+scheduleitem);
		System.out.println(ticket + ", " + startday+", item : "+scheduleitem);
		mv.setViewName("planner/plan");
		mv.addObject("ticket", ticket);
		mv.addObject("startday", startday);
		mv.addObject("plancode",plancode);
		mv.addObject("scheduleitem",scheduleitem);
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
	// 플래너 생성 페이지에서 장소 검색시 키워드를 tour api에 요청해서 해당 자료들을 불러오는 메소드
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
	// 저장&닫기 버튼을 눌렀을때 신규 생성인지 update인지 구별해서 저장하는 메소드
	@RequestMapping(value="/plan/saveAndClose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveAndClose(@RequestBody PlannerJsonDTO dto) {
		PlannerJsonDTO pjd = dto;
		PlannerVO planner = pjd.getPlanner();
	  /*log.info("##saveAndClose : "+pjd);
		log.info("####planner : "+planner); */
		String plan_code = planner.getPlan_code();
		if(plan_code.equals("new")) {
			plannerService.createPlanner(pjd);
		}else {
			// plan code 를 디비에서 일치하는 넘 검사해서 걔네 싹 지우고 지금 가져온 데이터로 다시 insert
			Boolean delCheck = plannerService.deleteScheduleList(plan_code);
			if(delCheck) { plannerService.updatePlanner(pjd);
			}else { return "false"; }
			
		}
		return "save";	
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
	
}
