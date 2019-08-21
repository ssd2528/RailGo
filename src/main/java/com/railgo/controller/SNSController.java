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

import com.railgo.domain.SNSJoinDTO;
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
		List<SNSJoinDTO> getList = snsService.getList();
		for(SNSJoinDTO snsJoinDTO : getList) {
			String sns_code = snsJoinDTO.getSns_code();
			ArrayList<TripImageVO> snsImgList = snsService.findSNSImg(sns_code);
			snsJoinDTO.setImgList(snsImgList);
		}
		model.addAttribute("sns", getList);
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
	public String modify(SNSVO vo, RedirectAttributes rttr) {
		if(snsService.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/sns/sns";
	}
	
	@RequestMapping("/content")
	public ResponseEntity<SNSJoinDTO> content(@RequestBody Map<String,String> sns_code, Model model) {
		Map<String,String> code = new HashMap<String,String>();
		code = sns_code;
		log.info("## content: " + code.get("sns_code"));
		SNSJoinDTO snsList = snsService.content(code.get("sns_code"));
		ArrayList<TripImageVO> snsImgList = snsService.findSNSImg(code.get("sns_code"));
		if(snsImgList!=null) {
			snsList.setImgList(snsImgList);
		}
		ResponseEntity<SNSJoinDTO> content = new ResponseEntity<>(snsList, HttpStatus.OK);
		return content;
	}
	
	/* 이미지 업로드 */
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void upload(MultipartFile[] uploadFile) {
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
				snsService.insertSNSImg(imgvo);
			} catch (Exception e) {
				log.info("## upload Exception: " + e);
			}
		}
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
	
	/* 날짜별로 이미지 저장 폴더 생성해주는 메소드 */
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
}