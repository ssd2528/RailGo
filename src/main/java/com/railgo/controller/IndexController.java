package com.railgo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.railgo.domain.MemberVO;
import com.railgo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {
	
	MemberService memberService;
	
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		if(member != null) {
			mv.addObject("recomMember",memberService.selRecomMem2(member.getMem_code()));
			mv.addObject("recomMemberAdd",memberService.selRecomMemAdd2(member.getMem_code()));
		}else if(member == null){
			mv.addObject("recomMember",memberService.selRecomMem());
			mv.addObject("recomMemberAdd",memberService.selRecomMemAdd());
		}		
		
		return mv;
	}

	/*public ModelAndView recommend() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("recomMember",memberService.selRecomMem());
		mv.addObject("recomMemberAdd",memberService.selRecomMemAdd());
		log.info(mv);
		
		return mv;
	}*/
	
	@RequestMapping("/planner")
	public String plannerList() {
		return "planner/list";
	}
	
	@GetMapping("/pwd_setting")
	public String pwd_setting() {
		return "pwd_setting";
	}
	
	@GetMapping("/error500")
	public String error500() {
		return "error500";
	}
	
	@GetMapping("/error404")
	public String error404() {
		return "error404";
	}
}
