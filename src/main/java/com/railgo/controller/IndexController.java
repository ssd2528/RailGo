package com.railgo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

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
