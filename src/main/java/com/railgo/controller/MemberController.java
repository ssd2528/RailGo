package com.railgo.controller;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.railgo.domain.CommJoinDTO;
import com.railgo.domain.CommVO;
import com.railgo.domain.FollowingVO;
import com.google.gson.Gson;
import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSLikeVO;
import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;
import com.railgo.service.MemberService;
import com.railgo.service.PlannerService;
import com.railgo.service.SNSService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
	
	@Setter(onMethod_=@Autowired)
	MemberService memberService;
	@Setter(onMethod_=@Autowired)
	PlannerService plannerService;
	LoginController loginController;
	private SNSService snsService;

	//타임라인 페이지
	@GetMapping("/timeline")
	public ModelAndView timeline(HttpSession session, RedirectAttributes rttr) {
		
		ModelAndView mv = new ModelAndView();
		MemberVO member = (MemberVO)session.getAttribute("member");
		Object obj = session.getAttribute("member");
		
		mv.setViewName("/member/timeline");
		
		if(member==null) { 
			mv.setViewName("redirect:/");
			rttr.addFlashAttribute("msg", "정상적인 경로를 통해 다시 접근해 주세요.");
			return mv;
		}
		
		List<SNSJoinDTO> getList = snsService.getListMem(member.getMem_code());
		
		for(SNSJoinDTO snsJoinDTO : getList) {
			String sns_code = snsJoinDTO.getSns_code();
			
			/* SNS 이미지 */
			ArrayList<TripImageVO> snsImgList = snsService.findSNSImg(sns_code);
			snsJoinDTO.setImgList(snsImgList);
			
			/* 댓글 갯수 */
			int commCount = snsService.commCount(sns_code);
			snsJoinDTO.setCommCount(commCount);
			
			/* 좋아요 갯수 */
			int snsLikeCount = snsService.snsLikeCount(sns_code);
			snsJoinDTO.setSnsLikeCount(snsLikeCount);
			
			/* 좋아요 체크 */
			if(obj!=null) { // 멤버세션이 null이면 발동X
				MemberVO memVO = (MemberVO) obj;
				String mem_code = memVO.getMem_code();
				SNSLikeVO snsLikeVO = new SNSLikeVO(sns_code, mem_code);
				boolean snsLikeCheck = snsService.snsLikeCheck(snsLikeVO);
				snsJoinDTO.setSnsLikeCheck(snsLikeCheck);
			}
		}
	
		//FollowingVO follow = new FollowingVO();
		//follow.setMem_code(member.getMem_code());
		//follow.setFollowing(mem_code);
		MemberVO[] list = memberService.selRecomMem();
		
		for(MemberVO MemberVO: list) {
			mv.addObject("otherFollower",memberService.selFollower(MemberVO.getMem_code()));
			
		}
		
		mv.addObject("recomMember",memberService.selRecomMem2(member.getMem_code()));
		mv.addObject("recomMemberAdd",memberService.selRecomMemAdd2(member.getMem_code()));
		mv.addObject("selFollowing",memberService.selFollowing(member.getMem_code()));
		mv.addObject("selFollower",memberService.selFollower(member.getMem_code()));
		mv.addObject("getSnsCount",memberService.getSnsCount(member.getMem_code()));
		//mv.addObject("selFollower",memberService.selFollower(member.getMem_code()));
		//mv.addObject("selFollowExist",memberService.selFollowExist(follow));
		//mv.addObject("otherFollower",memberService.selFollower());
		mv.addObject("sns", getList);
		
		log.info("timeline ModelAndView "+mv);
		
		return mv;
	}
	
	//스케쥴 페이지
	@GetMapping("/schedule")
	public ModelAndView schedule(HttpSession session, RedirectAttributes rttr) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/member/schedule");
		MemberVO member = (MemberVO)session.getAttribute("member");
		if(member==null) { 
			mv.setViewName("redirect:/");
			rttr.addFlashAttribute("msg", "정상적인 경로를 통해 다시 접근해 주세요.");
			return mv;
		}

		mv.addObject("selFollowing",memberService.selFollowing(member.getMem_code()));
		mv.addObject("selFollower",memberService.selFollower(member.getMem_code()));
		mv.addObject("getSnsCount",memberService.getSnsCount(member.getMem_code()));
		
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
		Object obj = session.getAttribute("member");
		FollowingVO follow = new FollowingVO();
		
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
		
		List<SNSJoinDTO> getList = snsService.getListMem(mem_code);
		
		for(SNSJoinDTO snsJoinDTO : getList) {
			String sns_code = snsJoinDTO.getSns_code();
			
			/* SNS 이미지 */
			ArrayList<TripImageVO> snsImgList = snsService.findSNSImg(sns_code);
			snsJoinDTO.setImgList(snsImgList);
			
			/* 댓글 갯수 */
			int commCount = snsService.commCount(sns_code);
			snsJoinDTO.setCommCount(commCount);
			
			/* 좋아요 갯수 */
			int snsLikeCount = snsService.snsLikeCount(sns_code);
			snsJoinDTO.setSnsLikeCount(snsLikeCount);
			
			/* 좋아요 체크 */
			if(obj!=null) { // 멤버세션이 null이면 발동X
				MemberVO memVO = (MemberVO) obj;
				String mem_code2 = memVO.getMem_code();
				SNSLikeVO snsLikeVO = new SNSLikeVO(sns_code, mem_code);
				boolean snsLikeCheck = snsService.snsLikeCheck(snsLikeVO);
				snsJoinDTO.setSnsLikeCheck(snsLikeCheck);
			}
		}	
	
		mv.setViewName("/member/other_user_info");
		mv.addObject("selFollowing",memberService.selFollowing(mem_code));
		mv.addObject("selFollower",memberService.selFollower(mem_code));
		mv.addObject("getSnsCount",memberService.getSnsCount(mem_code));
		mv.addObject("sns", getList);
		
		return mv;
	}
	
	//팔로우
	@RequestMapping(value="/following" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void following(FollowingVO following, RedirectAttributes rttr){
		log.info("## following :"+following);
		memberService.following(following);
	}
	
	//언팔로우
	@RequestMapping(value="/unfollow" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void unfollow(FollowingVO following, RedirectAttributes rttr){
		log.info("## unfollow :"+following);
		memberService.unFollow(following);
	}
	
	//팔로우 했는지 확인
	@RequestMapping(value="/followExist" , method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void followExist(FollowingVO following, RedirectAttributes rttr){
		log.info("## followExist :"+following);
		memberService.selFollowExist(following);
		
	}
	
	@RequestMapping(value="schedule/getScheduleList", produces = "text/html;charset=UTF-8;application/json", method = RequestMethod.POST)
	@ResponseBody
	public String getScheduleList(@RequestBody Map<String,String> json) {
		Map<String, String> map = json;
		System.out.println("schedule/getScheduleList init mem_code: " + map.get("mem_code"));
		String mem_code = map.get("mem_code");
		int start = Integer.parseInt(map.get("start"));
		int end = Integer.parseInt(map.get("end"));
		ArrayList<PlannerJsonDTO> plannerScheduleJsonList = plannerService.PlanScheduleList(mem_code,"schedule",start,end);
		if(plannerScheduleJsonList == null || plannerScheduleJsonList.size() == 0) {
			return "null";
		}else {
			Gson gson = new Gson();
			String plannerScheduleJsonListToJson = gson.toJson(plannerScheduleJsonList);
			System.out.println("ArrayList  :"+plannerScheduleJsonList);
			System.out.println("ArrayList -> Json result :"+plannerScheduleJsonListToJson);
			return plannerScheduleJsonListToJson;
		}
	}
	@RequestMapping(value="schedule/deleteScheduleList", produces = "text/html;charset=UTF-8;application/json", method = RequestMethod.POST)
	@ResponseBody
	public String deleteScheduleList(@RequestBody Map<String, String> json) {
		Map<String,String> map = json;
		String plan_code = map.get("plan_code");
		System.out.println("plan_code : "+plan_code);
		Boolean delCheck = plannerService.deleteScheduleList(plan_code);
		if(delCheck)	return "s";
		else	return "f";
	}
	//추가정보 수정
	@GetMapping("/updateMemadd")
	public String updateMemadd(MemberAddVO member,HttpSession session, RedirectAttributes rttr) {
		System.out.println("-------------------updateMemadd()--------------------");
		System.out.println("## member : " + member);
		memberService.updateMemadd(member);
		session.setAttribute("memadd", member);
		System.out.println("## member : " +member);
		
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
				member.setBackImage(multipartFile.getOriginalFilename());
			}
			if (member.getProfile()==null){
				member.setProfile(multipartFile.getOriginalFilename());	
			}
			
			memberService.updateMemImage(member);
			MemberAddVO memadd = memberService.selMemadd(member.getMem_code());
			session.setAttribute("memadd", memadd);
			
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
		log.info("## filename: " + fileName);
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
		
	// 글 삭제
	@PostMapping("/remove")
	public String remove(String sns_code) {
		log.info("remove..." + sns_code);
		if(sns_code == null) return "redirect:/member/timeline";
		snsService.remove(sns_code);
		return "redirect:/member/timeline";
	}
	
	// 수정할 글 불러오기
	@PostMapping("/modifyContent")
	@ResponseBody
	public ResponseEntity<SNSVO> modifyContent(@RequestParam(value="sns_code") String sns_code){
		log.info("## sns_code: " + sns_code);
		SNSVO vo = snsService.modifyContent(sns_code);
		ResponseEntity<SNSVO> result = new ResponseEntity<>(vo, HttpStatus.OK);
		
		return result;
	}
	
	// 게시글 수정
	@PostMapping("/modify")
	@ResponseBody
	public ResponseEntity<String> modify(String sns_code, String mem_code, String content) {
		SNSVO vo = new SNSVO(sns_code, mem_code, content, null);
		snsService.modify(vo);
		
		ResponseEntity<String> result = new ResponseEntity<>("good", HttpStatus.OK);
		return result;
	}
	
	/* 게시글 상세보기 */
	@RequestMapping("/content")
	public ModelAndView content(String sns_code, HttpSession session) {
		Object obj = session.getAttribute("member");
		log.info("## content sns_code: " + sns_code);
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("member/content");
	  
		// SNS 내용 가져오기
		SNSJoinDTO content = snsService.content(sns_code); 
		mv.addObject("content", content);
		
		// 이미지리스트 가져오기
		ArrayList<TripImageVO> snsImgList = snsService.findSNSImg(sns_code);
		mv.addObject("imgList", snsImgList);
		
		// 댓글&대댓글 가져오기
		ArrayList<CommJoinDTO> commList = snsService.getCommList(sns_code);
		
		for(CommJoinDTO commJoinDTO : commList) {
			int origin_code = commJoinDTO.getComm_code();
			ArrayList<CommJoinDTO> rereList = snsService.getRereList(origin_code);
			commJoinDTO.setRereList(rereList);
		}
		mv.addObject("commList", commList);
		
		// 좋아요 갯수
		int snsLikeCount = snsService.snsLikeCount(sns_code);
		mv.addObject("likeCount", snsLikeCount);
		
		// 좋아요 체크
		if(obj!=null) { // 멤버세션이 null이면 발동X
			MemberVO memVO = (MemberVO) obj;
			String mem_code = memVO.getMem_code();
			SNSLikeVO snsLikeVO = new SNSLikeVO(sns_code, mem_code);
			boolean snsLikeCheck = snsService.snsLikeCheck(snsLikeVO);
			mv.addObject("likeCheck", snsLikeCheck);
			log.info(snsLikeCheck);
		}
		
	  	return mv;
		 
	}
	
	/* 이미지 업로드 */
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<String> upload(MultipartFile[] uploadFile) {
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();
		
		// 이미지 저장 폴더 생성
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {
			TripImageVO imgvo = new TripImageVO();
			log.info("## Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("## Upload File Size: " + multipartFile.getSize());
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			imgvo.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				imgvo.setUuid(uuid.toString());
				imgvo.setImagePath(uploadFolderPath);
				
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 800, 500);
				thumbnail.close();
				
				snsService.insertSNSImg(imgvo);
			} catch (Exception e) {
				log.info("## upload Exception: " + e);
			}
		}
		ResponseEntity<String> result = new ResponseEntity<>("성공", HttpStatus.OK);
		return result;
	}
	
	/* 댓글 달기 */
	@PostMapping("/insertReply")
	@ResponseBody
	public ResponseEntity<CommJoinDTO> insertReply(String content, String mem_code, String sns_code, int comm_code){
		log.info("## comm_code : " + comm_code);
		ResponseEntity<CommJoinDTO> result = null;
		
		CommVO vo = new CommVO(-1, sns_code, mem_code, comm_code, content, null); 
		snsService.commRegister(vo);
	  
		CommJoinDTO commCheck = snsService.commCheck();
		result = new ResponseEntity<CommJoinDTO>(commCheck, HttpStatus.OK);
		
		return result;
	}
	
	/* 댓글 삭제 */
	@PostMapping(value="/commDelete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<String> commDelete(int comm_code){
		snsService.commDelete(comm_code);
		ResponseEntity<String> result = null;
		result = new ResponseEntity<String>("성공", HttpStatus.OK);
		
		return result;
	}
	
	/* 좋아요 클릭 */
	@PostMapping(value="/snsLike", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<String> snsLike(String sns_code, String mem_code, String check){
		log.info("##### snsLike: " + sns_code + mem_code + check);
		SNSLikeVO vo = new SNSLikeVO(sns_code, mem_code);
		
		if(check.equals("plus")) {
			snsService.snsLikePlus(vo);
		}else if(check.equals("minus")) {
			snsService.snsLikeMinus(vo);
		}
		ResponseEntity<String> result = new ResponseEntity<>("성공", HttpStatus.OK);
		return result;
	}
	
	/* 날짜별로 이미지 저장 폴더 생성해주는 메소드 */
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
}