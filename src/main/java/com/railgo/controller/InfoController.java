package com.railgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/info")
@AllArgsConstructor
public class InfoController {
	@GetMapping("/info")
	public String info() {
		return "info/info";
	}
	@GetMapping("/detail")
	public String detail() {
		return "info/detail";
	}
	@GetMapping("/category")
	public String category() {
		return "info/category";
	}
}
