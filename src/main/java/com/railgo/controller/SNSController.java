package com.railgo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.railgo.domain.CommJoinDTO;
import com.railgo.domain.CommVO;
import com.railgo.domain.MemberVO;
import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSLikeVO;
import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;
import com.railgo.service.MemberService;
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
	private MemberService memberService;

	@GetMapping("/sns")
	public void snsList(Model model, HttpSession session) {
		Object obj = session.getAttribute("member");
		
		List<SNSJoinDTO> getList = snsService.getList();
		
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
		
			MemberVO member = (MemberVO)session.getAttribute("member");
			
			if(member != null) {
				model.addAttribute("recomMember",memberService.selRecomMem2(member.getMem_code()));
				model.addAttribute("recomMemberAdd",memberService.selRecomMemAdd2(member.getMem_code()));
			}else if(member == null){
				model.addAttribute("recomMember",memberService.selRecomMem());
				model.addAttribute("recomMemberAdd",memberService.selRecomMemAdd());
			}		
		}
		model.addAttribute("sns", getList);
	}
	
	// 글쓰기
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> register(String mem_code, String content) {
		SNSVO vo = new SNSVO(null, mem_code, content, null);
		snsService.register(vo);
		
		ResponseEntity<String> result = new ResponseEntity<>("good", HttpStatus.OK);
		return result;
	}
	
	// 글 삭제
	@PostMapping("/remove")
	public String remove(String sns_code) {
		log.info("remove..." + sns_code);
		if(sns_code == null) return "redirect:/sns/sns";
		snsService.remove(sns_code);
		return "redirect:/sns/sns";
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
		mv.setViewName("sns/content");
	  
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
	
	/* 업로드 된 이미지를 썸네일로 보여주는 부분 */
	@GetMapping("/display")	  
	@ResponseBody 
	public ResponseEntity<byte[]> getFile(String fileName){ 
	File file = new File("C:\\upload\\" + fileName); 
	ResponseEntity<byte[]> result = null;
		  
		try { 
			HttpHeaders header = new HttpHeaders(); 
			header.add("Content-Type", Files.probeContentType(file.toPath())); 
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch(IOException ie) { 
			log.info("## display exception: " + ie); 
		} 
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