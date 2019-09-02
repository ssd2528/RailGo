package com.railgo.controller;

import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.railgo.domain.FollowingVO;
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
	
	//타임라인 페이지
	@GetMapping("/timeline")
	public ModelAndView timeline(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
	
		mv.setViewName("/member/timeline");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		log.info("timeline mem_code "+member.getMem_code());
		//FollowingVO follow = new FollowingVO();
		//follow.setMem_code(member.getMem_code());
		//follow.setFollowing(mem_code);
		MemberVO[] list = memberService.selRecomMem();
		
		for(MemberVO str: list) {
			log.info("str "+str);
		}
		
		mv.addObject("recomMember",memberService.selRecomMem2(member.getMem_code()));
		mv.addObject("recomMemberAdd",memberService.selRecomMemAdd2(member.getMem_code()));
		mv.addObject("selFollowing",memberService.selFollowing(member.getMem_code()));
		mv.addObject("selFollower",memberService.selFollower(member.getMem_code()));
		mv.addObject("selFollower",memberService.selFollower(member.getMem_code()));
		//mv.addObject("selFollowExist",memberService.selFollowExist(follow));
		//mv.addObject("otherFollower",memberService.selFollower());
		
		log.info("timeline ModelAndView "+mv);
		
		return mv;
	}
	
	//스케쥴 페이지
	@GetMapping("/schedule")
	public ModelAndView schedule(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/member/schedule");
		MemberVO member = (MemberVO)session.getAttribute("member");
		log.info("schedule mem_code "+member.getMem_code());
		
		mv.addObject("selFollowing",memberService.selFollowing(member.getMem_code()));
		mv.addObject("selFollower",memberService.selFollower(member.getMem_code()));
		
		
		
		return mv;
	}
	
	//다른 유저 정보 보기
	@RequestMapping(value="/other_user_info" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ModelAndView userinfo(@RequestParam("mem_code") String mem_code, Model model,HttpServletRequest request) {
		
		model.addAttribute("userInfo",memberService.selMember(mem_code));
		model.addAttribute("userInfoAdd",memberService.selMemadd(mem_code));
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		FollowingVO follow = new FollowingVO();
		log.info("member "+member);
		
		if(member != null) {
			follow.setMem_code(member.getMem_code());
			follow.setFollowing(mem_code);
			mv.addObject("selFollowExist",memberService.selFollowExist(follow));
			mv.addObject("recomMember",memberService.selRecomMem2(member.getMem_code()));
			mv.addObject("recomMemberAdd",memberService.selRecomMemAdd2(member.getMem_code()));
		}else if(member == null){
			mv.addObject("recomMember",memberService.selRecomMem());
			mv.addObject("recomMemberAdd",memberService.selRecomMemAdd());
		}		
	
		log.info(follow);
		mv.setViewName("/member/other_user_info");
		mv.addObject("selFollowing",memberService.selFollowing(mem_code));
		mv.addObject("selFollower",memberService.selFollower(mem_code));
		log.info(mv);
		
		return mv;
	}
	
	@RequestMapping(value="/following" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void following(FollowingVO following, RedirectAttributes rttr){
		//log.info("mem_code :"+mem_code);
		log.info("following :"+following);
		memberService.following(following);
	}
	
	@RequestMapping(value="/unfollow" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void unfollow(FollowingVO following, RedirectAttributes rttr){
		//log.info("mem_code :"+mem_code);
		log.info("following :"+following);
		memberService.unFollow(following);
	}
	
	@RequestMapping(value="/followExist" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void followExist(FollowingVO following, RedirectAttributes rttr){
		//log.info("mem_code :"+mem_code);
		log.info("following :"+following);
		log.info(memberService.selFollowExist(following));
		memberService.selFollowExist(following);
		
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
		
		String uploadFolder = "C:\\Upload\\temp";
		
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
				member.setBackImage(multipartFile.getOriginalFilename());
			}
			if (member.getProfile()==null){
				member.setProfile(multipartFile.getOriginalFilename());	
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
			
			File file = new File("C:\\Upload\\temp\\"+fileName);
			//File file = new File(fileName);
			
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