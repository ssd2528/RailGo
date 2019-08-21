package com.railgo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;
import com.railgo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
	
	@Setter(onMethod_=@Autowired)
	MemberService memberService;
	LoginController loginController;
	
	@GetMapping("/timeline")
	public String timeline() {
		
		return "member/timeline";
	}
	
	@GetMapping("/schedule")
	public String schedule() {
		return "member/schedule";
	}
	//추가정보 수정
	@GetMapping("/updateMemadd")
	public String updateMemadd(MemberAddVO member,HttpSession session, RedirectAttributes rttr) {
		
		System.out.println(member);
		memberService.updateMemadd(member);
		session.setAttribute("memadd", member);
		System.out.println(member);
		
		return "redirect:/member/timeline";
	}
	
	//프로필, 배경사진 수정
	@PostMapping("/updateMemImage")
	//@RequestMapping(value="/uploadAjaxAction" , method = {RequestMethod.GET, RequestMethod.POST})
	public String updateMemImage(MultipartFile[] uploadFile, MemberAddVO member, HttpSession session) {
		
		
		log.info("update ajax post........");
		
		String uploadFolder = "C:\\upload\\temp";
		
		for (MultipartFile multipartFile : uploadFile) {
			
			log.info("------------------------------------");
			log.info("Upload File Name: " +multipartFile.getOriginalFilename());
			log.info("Upload File Size: " +multipartFile.getSize());
			log.info("Upload Name: " +multipartFile.getName());
			//log.info("Upload Name: " +uploadFile.toString());
			//log.info(uploadFolder.toString()+"\\"+multipartFile.getOriginalFilename());
			
			
			String uploadFileName = multipartFile.getOriginalFilename();
			String path = uploadFolder.toString()+"\\"+multipartFile.getOriginalFilename();
			//String path = "<spring:url value='/picture/"+multipartFile.getOriginalFilename()+"'/>";
			//String path = "<c:url value='/picture/"+multipartFile.getOriginalFilename()+"'/>";
			log.info(path);
			
			if(member.getBackImage()==null) {
				member.setBackImage(path);
			}
			if (member.getProfile()==null){
				member.setProfile(path);	
			}
			
			log.info(member);
			memberService.updateMemImage(member);
			MemberAddVO memadd = memberService.selMemadd(member.getMem_code());
			session.setAttribute("memadd", memadd);
			log.info(member);
			log.info(memadd);
			
			// IE has file each
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +1);
			log.info("only file name: " +uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);			
				
			try {
				multipartFile.transferTo(saveFile);
				
			}catch (Exception e) {
				log.error(e.getMessage());
			}//end catch
			
		}//end for
		return "redirect:/member/timeline";
	}
	//로컬 사진 불러오기
	@GetMapping("/display")
		@ResponseBody
		public ResponseEntity <byte[]> getFile(String fileName){
			
			log.info("filename: " + fileName);
			
			//File file = new File("C:\\Upload\\temp\\"+fileName);
			File file = new File(fileName);
			
			ResponseEntity<byte[]> result = null;
			
			try {
				HttpHeaders header = new HttpHeaders();
				
				header.add("Content-Type", Files.probeContentType(file.toPath()));
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			}catch(IOException e) {
				e.printStackTrace();
			}
			return result;
		}
}