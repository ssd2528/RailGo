package com.railgo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;
import com.railgo.service.SNSService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;


@Controller
@Log4j
@RequestMapping("/sns")
@AllArgsConstructor
public class SNSController {
	private SNSService snsService;

	@GetMapping("/sns")
	public void snsList(Model model) {
		log.info("sns");
		model.addAttribute("sns", snsService.getList());
	}

	@PostMapping("/sns")
	public String register(SNSVO sns, RedirectAttributes rttr) {
		log.info("register: " + sns);
		snsService.register(sns);
		rttr.addFlashAttribute("result", sns.getSns_code());
		return "redirect:/sns/sns";
	}
	
	@PostMapping("/remove")
	public String remove(String sns_code) {
		log.info("remove..." + sns_code);
		if(sns_code == null) return "redirect:/sns/sns";
		snsService.remove(sns_code);
		return "redirect:/sns/sns";
	}
	@PostMapping("/modify")
	public String modify(String sns_code, String mod_content, String mem_code, RedirectAttributes rttr) {
		log.info("modify..." + sns_code);
		log.info("mod_content" + mod_content);
		log.info("mem_code" + mem_code);
		SNSVO sns = new SNSVO("", sns_code, mem_code, mod_content, null);
		if(snsService.modify(sns)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/sns/sns";
	}
}