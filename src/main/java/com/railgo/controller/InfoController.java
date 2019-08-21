package com.railgo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.railgo.domain.InfoItemDTO;
import com.railgo.domain.MemberVO;
import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;
import com.railgo.domain.TripImageVO;
import com.railgo.domain.CategoryVO;
import com.railgo.service.InfoService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/info")
@AllArgsConstructor
public class InfoController {

	@Autowired
	private InfoService infoService;

	@GetMapping("/info")
	public String info() {
		return "info/info";
	}

	@GetMapping(value = "/{areaName}", produces = "application/json; charset=utf-8")
	public ModelAndView infoCity(@PathVariable("areaName") String areaName) throws Exception {
		System.out.println("## info areaName : " + areaName);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/info");
		mv.addObject("areaName", areaName);

		/*** TourAPI를 이용해 JSON데이터 추출하는 부분 ***/
		String responseStr = null;
		JsonObject itemsObject = null;
		JsonArray itemsArray = null;
		String areaCode = infoService.findAreaCode(areaName); // areaName을 받아서 areaCode 받는 부분

		String foodURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=fyNSqF%2BlKTGtgYOP3I8bswAw6c1fuJyBOE1LFcOCaFEPicsqeDZMkQ7ORZAS6gqYmnH41plvBWTKUtVhXGaj4g%3D%3D"
				+ "&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P" + "&contentTypeId=39" // 음식점
				+ areaCode + "&_type=json";
		responseStr = infoService.getResponseStr(foodURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemsArray = infoService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> foodList = makeDTOList(itemsArray);
		mv.addObject("foodList", foodList);

		String accommURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey=fyNSqF%2BlKTGtgYOP3I8bswAw6c1fuJyBOE1LFcOCaFEPicsqeDZMkQ7ORZAS6gqYmnH41plvBWTKUtVhXGaj4g%3D%3D"
				+ "&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&listYN=Y&arrange=P" + "&contentTypeId=32" // 숙박
				+ areaCode + "&_type=json";
		responseStr = infoService.getResponseStr(accommURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemsArray = infoService.makeItemsArray(itemsObject);
		ArrayList<InfoItemDTO> accomList = makeDTOList(itemsArray);
		mv.addObject("accomList", accomList);

		return mv;
	}

	@PostMapping(value = "/detail/{title}", produces = "text/plain; charset=utf-8")
	public ModelAndView detail(@PathVariable("title") String title, @RequestParam("contentid") int contentid,
			@RequestParam("contenttypeid") int contenttypeid, @RequestParam("mapx") String mapx,
			@RequestParam("mapy") String mapy, @RequestParam("areaName") String areaName) throws Exception {
		System.out.println("## info title : " + title);
		System.out.println("## info contentid : " + contentid);
		System.out.println("## info contenttypeid : " + contenttypeid);
		System.out.println("## info mapx : " + mapx);
		System.out.println("## info mapy : " + mapy);
		System.out.println("## info areaName : " + areaName);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info/detail");
		mv.addObject("areaName", areaName);

		/*** TourAPI를 이용해 JSON데이터 추출하는 부분 ***/
		String responseStr = null;
		JsonObject itemsObject = null;
		JsonArray itemsArray = null;
		JsonObject itemObject = null;
		ArrayList<InfoItemDTO> list = new ArrayList<InfoItemDTO>();

		String baseURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=aITyOzpmSgMBVPzkEURdo%2F2EuYMTaPQaYNejkRIo3VLfO6RR0DQ2Wt1z32pqbLPq5WcBWkvJvdYei259ze6XvA%3D%3D"
				+ "&MobileOS=ETC&MobileApp=RailGo&listYN=Y" + "&contentId=" + contentid // 컨텐트id 매칭
				+ "&contentTypeId=" + contenttypeid // 컨텐트타입 매칭
				+ "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y" + "&_type=json";
		responseStr = infoService.getResponseStr(baseURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemObject = (JsonObject) itemsObject.get("item");

		InfoItemDTO dto = new Gson().fromJson(itemObject, InfoItemDTO.class); // itemObject를 AreaBasedItemDTO에 담기 ( Json
																				// => Object )
		// System.out.println("## cat코드변경 전 AreaBasedItemDTO : " + dto);
		CategoryVO vo = infoService.findCatNameByCat3(itemObject.get("cat3").getAsString()); // cat3를 통해 itemObject의
																								// CatName 추출
		dto.setCat1(vo.getCat1Name());
		dto.setCat2(vo.getCat2Name());
		dto.setCat3(vo.getCat3Name()); // cat코드를 catName으로 변경
		// System.out.println("## cat코드 이름으로 변경 후 AreaBasedItemDTO : " + dto);
		mv.addObject("detail", dto);

		String detailURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "serviceKey=aITyOzpmSgMBVPzkEURdo%2F2EuYMTaPQaYNejkRIo3VLfO6RR0DQ2Wt1z32pqbLPq5WcBWkvJvdYei259ze6XvA%3D%3D"
				+ "&numOfRows=10&MobileOS=ETC&MobileApp=RailGo" + "&contentId=" + contentid + "&contentTypeId="
				+ contenttypeid + "&_type=json";
		responseStr = infoService.getResponseStr(detailURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemObject = (JsonObject) itemsObject.get("item");
		if (contenttypeid == 39) {
			if(itemObject.get("opentimefood")!=null) {
				mv.addObject("opentime", itemObject.get("opentimefood").getAsString());
			}else {
				mv.addObject("opentime", null);
			}
		} else if (contenttypeid == 32) {
			mv.addObject("chkintime", itemObject.get("checkintime").getAsString());
			mv.addObject("chkouttime", itemObject.get("checkouttime").getAsString());
		}

		String imageURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "serviceKey=aITyOzpmSgMBVPzkEURdo%2F2EuYMTaPQaYNejkRIo3VLfO6RR0DQ2Wt1z32pqbLPq5WcBWkvJvdYei259ze6XvA%3D%3D"
				+ "&numOfRows=2&MobileOS=ETC&MobileApp=RailGo" + "&contentId=" + contentid + "&imageYN=Y"
				+ "&_type=json";
		responseStr = infoService.getResponseStr(imageURL);
		int total = infoService.getTotalCount(responseStr);
		if (total == 0) {
			mv.addObject("img1", null);
			mv.addObject("img2", null);
		} else if (total == 1) {
			itemsObject = infoService.getItemsObject(responseStr);
			itemObject = (JsonObject) itemsObject.get("item");
			mv.addObject("img1", itemObject.get("originimgurl").getAsString());
			mv.addObject("img2", null);
		} else {
			itemsObject = infoService.getItemsObject(responseStr);
			itemsArray = infoService.makeItemsArray(itemsObject);
			mv.addObject("img1", ((JsonObject) itemsArray.get(0)).get("originimgurl").getAsString());
			mv.addObject("img2", ((JsonObject) itemsArray.get(1)).get("originimgurl").getAsString());
		}

		String locURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?"
				+ "serviceKey=aITyOzpmSgMBVPzkEURdo%2F2EuYMTaPQaYNejkRIo3VLfO6RR0DQ2Wt1z32pqbLPq5WcBWkvJvdYei259ze6XvA%3D%3D"
				+ "&numOfRows=3&MobileOS=ETC&MobileApp=RailGo&arrange=E" + "&contentTypeId=" + contenttypeid + "&mapX="
				+ mapx + "&mapY=" + mapy + "&radius=100000000&listYN=Y" + "&_type=json";
		responseStr = infoService.getResponseStr(locURL);
		itemsObject = infoService.getItemsObject(responseStr);
		itemsArray = infoService.makeItemsArray(itemsObject);
		for (int i = 1; i < itemsArray.size(); i++) { // 거리순이라 0번째는 자기 자신이기 때문에 1부터 추출
			JsonObject locObject = (JsonObject) itemsArray.get(i); // 각 locObject 추출
			InfoItemDTO infoDto = new Gson().fromJson(locObject, InfoItemDTO.class); // 각 locObject를 infoItemDTO에 담기 (
																						// Json => Object )
			CategoryVO catVo = infoService.findCatNameByCat3(locObject.get("cat3").getAsString()); // cat3를 통해
																									// locObject의
																									// CatName 추출
			infoDto.setCat1(catVo.getCat1Name());
			infoDto.setCat2(catVo.getCat2Name());
			infoDto.setCat3(catVo.getCat3Name()); // cat코드를 catName으로 변경
			list.add(infoDto);
		}
		mv.addObject("locList", list);

		ArrayList<ReviewJoinDTO> reList = null;
		reList = infoService.findAllReview(contentid);
		for(ReviewJoinDTO reviewJoinDTO : reList) {
			String r_code = reviewJoinDTO.getR_code();
			ArrayList<TripImageVO> reviewImgList =  infoService.findReviewImg(r_code);
			if(reviewImgList!=null) {
				reviewJoinDTO.setImgList(reviewImgList);
			}
		}
		log.info("## reList: " + reList);
		mv.addObject("reList", reList);
		
		
		return mv;
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
				if (checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				infoService.insertReviewImg(imgvo);
			} catch (Exception e) {
				log.info("## upload Exception: " + e);
			}
		}
	}
	
	@PostMapping("/insertReview")
	public void insertReview(ReviewVO vo) {
		log.info("## ReviewVO: " + vo);
		infoService.insertReview(vo);
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
	 
	/* 리뷰 삭제 */
	@PostMapping("/deleteReview")
	public String remove(@RequestBody Map<String,String> r_code) {
		Map<String,String> code = new HashMap<String,String>();
		code = r_code;
		log.info("## delete: " + code.get("r_code"));
		if(r_code == null) return null;
		infoService.deleteReview(code.get("r_code"));
		return "성공";
	}
	
	@GetMapping("/category")
	public String category() {
		return "info/category";
	}

	/***
	 * makeDTOList(JsonArray jsonArray) : cat1,2,3 코드를 catName으로 바꿔서 다시 가공하는 일반 메소드
	 ***/
	public ArrayList<InfoItemDTO> makeDTOList(JsonArray jsonArray) throws Exception {
		ArrayList<InfoItemDTO> list = new ArrayList<InfoItemDTO>();

		int contentId = 0;
		String url = null;
		String responseStr = null;
		JsonObject itemsObject = null;
		String overview = null;
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject itemObject = (JsonObject) jsonArray.get(i); // 각 itemObject 추출
			// System.out.println("## itemObject : " + itemObject);
			InfoItemDTO dto = new Gson().fromJson(itemObject, InfoItemDTO.class); // 각 itemObject를 AreaBasedItemDTO에 담기
																					// ( Json => Object )
			// System.out.println("## cat코드변경 전 AreaBasedItemDTO : " + dto);
			CategoryVO vo = infoService.findCatNameByCat3(itemObject.get("cat3").getAsString()); // cat3를 통해 itemObject의
																									// CatName 추출
			dto.setCat1(vo.getCat1Name());
			dto.setCat2(vo.getCat2Name());
			dto.setCat3(vo.getCat3Name()); // cat코드를 catName으로 변경
			// System.out.println("## cat코드 이름으로 변경 후 AreaBasedItemDTO : " + dto);

			// overview 추출 후 dto.setOverview()
			contentId = infoService.getContentId(itemObject);
			url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
					+ "serviceKey=aITyOzpmSgMBVPzkEURdo%2F2EuYMTaPQaYNejkRIo3VLfO6RR0DQ2Wt1z32pqbLPq5WcBWkvJvdYei259ze6XvA%3D%3D"
					+ "&MobileOS=ETC&MobileApp=RailGo&contentId=" + contentId + "&overviewYN=Y&_type=json";
			responseStr = infoService.getResponseStr(url);
			itemsObject = infoService.getItemsObject(responseStr);
			overview = infoService.getOverview(itemsObject);
			dto.setOverview(overview);

			list.add(dto);
		}
		return list;
	}

	/* 날짜별로 이미지 저장 폴더 생성해주는 메소드 */
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	/* 이미지 파일을 판단해주는 메소드 */
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");

		} catch (IOException ie) {
			log.info("## checkImageType Exception: " + ie);
		}
		return false;
	}
}
