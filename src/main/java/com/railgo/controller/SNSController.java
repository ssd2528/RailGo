package com.railgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.railgo.domain.SNSVO;
import com.railgo.service.SNSService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/sns")
@AllArgsConstructor
public class SNSController {
	private SNSService snsService;
	
	@GetMapping("/sns")
	public String sns() {
		return "sns/sns";
	}
	@GetMapping("/sns_modal")
	public void register() {
		
	}
	@PostMapping("/sns")
	public String register(SNSVO sns, RedirectAttributes rttr) {
		log.info("register: " + sns);
		snsService.register(sns);
		rttr.addFlashAttribute("result", sns.getSns_code());
		return "redirect:/sns/sns";
	}
}
