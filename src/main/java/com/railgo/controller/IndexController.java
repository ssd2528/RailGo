package com.railgo.controller;

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
}
