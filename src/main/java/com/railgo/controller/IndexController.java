package com.railgo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.railgo.domain.MemberVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSLikeVO;
import com.railgo.domain.TripImageVO;
import com.railgo.service.MemberService;
import com.railgo.service.PlannerService;
import com.railgo.service.SNSService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	@Autowired
	private MemberService memberService;
	@Autowired
	private SNSService snsService;
	@Autowired
	private PlannerService plannerService;
	
	private InfoController infoController;
	
	
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		/* SNS 추천 이용자 조회 */
		if(member != null) {
			mv.addObject("recomMember",memberService.selRecomMem2(member.getMem_code()));
			mv.addObject("recomMemberAdd",memberService.selRecomMemAdd2(member.getMem_code()));
		}else if(member == null){
			mv.addObject("recomMember",memberService.selRecomMem());
			mv.addObject("recomMemberAdd",memberService.selRecomMemAdd());
		}	
		
		/* 컨셉 추천 */
		ArrayList<PlannerJsonDTO> plannerScheduleJsonList = null;
		Gson gson = new GsonBuilder().create();
		// 추천1. 나홀로 떠나는 여행 
		plannerScheduleJsonList = plannerService.PlanScheduleListByTheme("theme-solo");o
		Collections.shuffle(plannerScheduleJsonList);
		String plannerListBySolo = gson.toJson(plannerScheduleJsonList);
		mv.addObject("plannerListBySolo", plannerListBySolo);
		// 추천2. 먹방
		plannerScheduleJsonList = plannerService.PlanScheduleListByTheme("theme-eating");
		Collections.shuffle(plannerScheduleJsonList);
		String plannerListByEating = gson.toJson(plannerScheduleJsonList);
		mv.addObject("plannerListByEating", plannerListByEating);
		
		/* SNS 게시물 목록 조회 */
		List<SNSJoinDTO> getList = snsService.getList();
		for(SNSJoinDTO snsJoinDTO : getList) {
			String sns_code = snsJoinDTO.getSns_code();
			ArrayList<TripImageVO> snsImgList = snsService.findSNSImg(sns_code);	snsJoinDTO.setImgList(snsImgList); // SNS 이미지 
			int commCount = snsService.commCount(sns_code);							snsJoinDTO.setCommCount(commCount); // 댓글 갯수
			int snsLikeCount = snsService.snsLikeCount(sns_code);					snsJoinDTO.setSnsLikeCount(snsLikeCount); //좋아요 갯수
			// 좋아요 체크
			if(member!=null) { // 멤버세션이 null이면 발동X
				MemberVO memVO = (MemberVO) member;
				String mem_code = memVO.getMem_code();
				SNSLikeVO snsLikeVO = new SNSLikeVO(sns_code, mem_code);
				boolean snsLikeCheck = snsService.snsLikeCheck(snsLikeVO);
				snsJoinDTO.setSnsLikeCheck(snsLikeCheck);
			}
		}
		mv.addObject("sns", getList);
		
		
		return mv;
	}
	@RequestMapping("planner")
	public String planner() {
		return "planner/list";
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
