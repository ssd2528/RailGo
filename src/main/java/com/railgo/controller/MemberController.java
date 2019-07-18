package com.railgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/")
@AllArgsConstructor
public class MemberController {

	@GetMapping("/timeline")
	public String timeline() {
		return "member/timeline";
	}
	
	@GetMapping("/schedule")
	public String schedule() {
		return "member/schedule";
	}
}
