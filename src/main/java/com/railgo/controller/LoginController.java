package com.railgo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;
import com.railgo.mapper.MailUtils;
import com.railgo.oauth.KakaoAccessToken;
import com.railgo.oauth.KakaoUserInfo;
import com.railgo.oauth.NaverLoginBO;
import com.railgo.service.MemberService;
import com.sun.mail.iap.Response;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
@AllArgsConstructor
public class LoginController {
	
	private NaverLoginBO naverLoginBO;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;	
	
	// 이메일 중복 체크
	@PostMapping("/checkEmail")
	public void checkEmail(@RequestParam("email")String email,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		System.out.println("## 이메일 중복 체크 : " + email);
		int check = memberService.checkEmail(email);
		PrintWriter out = response.getWriter();
		if(check==1)  out.print("false");
		else out.print("true");
	}
	// 회원가입
	@PostMapping("/signup")
	public ModelAndView signup(MemberVO member, RedirectAttributes rttr, @RequestParam(value="status", required=false) String status) throws Exception {
		System.out.println("## 회원가입 시작 : " + member);
		
		String encPwd = passwordEncoder.encode(member.getPwd());
		System.out.println("## 원래 비밀번호: " + member.getPwd() + " / 암호화된 비밀번호 : " + encPwd);
		member.setPwd(encPwd);
		
		ModelAndView mv = new ModelAndView();
		if(status == null) {
			System.out.println("### 내일고에서 회원가입 ");
			if(memberService.signup(member)) { // 회원가입
				rttr.addFlashAttribute("msg", "가입시 사용한 이메일로 인증해주세요.");
			}else {
				rttr.addFlashAttribute("msg", "Error");
			}
			mv.setViewName("redirect:/");
			
		} else if(status.equals("kakao") || status.equals("naver")) {
			System.out.println("### 소셜에서 회원가입 ");
			int check = memberService.findOne(member);
			if(check==0) memberService.autoSignup(member); 
			mv.setViewName("autosign");
			
			if(status.equals("kakao")) {
				mv.addObject("email", member.getEmail());
				mv.addObject("pwd", "kakao");
			}else if(status.equals("naver")) {
				mv.addObject("email", member.getEmail());
				mv.addObject("pwd", "naver");
			}
			mv.addObject("request", "signin");
		}
		
		return mv;
	}
	// 이메일 인증 확인 
	@GetMapping("/emailConfirm")
	public String emailConfirm(MemberVO member, RedirectAttributes rttr, Model model) throws  Exception {
		log.info("## "+ member.getEmail() + " : auth confirmed");
		rttr.addFlashAttribute("msg", "회원가입이 완료되었습니다. 가입시 사용한 이메일로 로그인해주세요.");
		return "redirect:/";
	}
	
	
	// 로그인
	@PostMapping("/signin")
	@ResponseBody
	public ResponseEntity<String> signin(MemberVO vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("## 로그인 ");
		if(session.getAttribute("member") != null) { // 기존에 member란 세션 값이 존재한다면
			session.removeAttribute("member"); // 기존 세션 값을 제거
		}
		String rawPwd = vo.getPwd();
		MemberVO member = memberService.signin(vo);
		System.out.println("## member : " + member);
		MemberAddVO memadd = memberService.selMemadd(member);
		System.out.println("## memadd : " + memadd);
		
		if(member!=null) {
			System.out.println("## 사용자 확인 ");
			String encPwd = member.getPwd();
			System.out.println("## 암호화된 비밀번호 : " + encPwd + " / 입력한 비밀번호 : " + rawPwd); 
			if(passwordEncoder.matches(rawPwd, encPwd) && member!=null) {  // 로그인 성공
				System.out.println("## 로그인 성공!");
				session.setAttribute("member", member);
				session.setAttribute("memadd", memadd);
				/* ------------------------------ 자동 로그인 부분 ------------------------------ 
				if(request.getParameter("loginCookie").equals("true")) { 
					// 로그인 성공 후, 자동로그인이 체크되어 있으면 (쿠키를 사용할 거라면)
					System.out.println("## 자동로그인 체크되어 있음 : " + request.getParameter("loginCookie"));
					Cookie cookie = new Cookie("loginCookie", session.getId()); // 쿠키를 생성하고 현재 로그인 되어 있을 때 생성되었던 세션의 id를 쿠키에 저장
					cookie.setPath("/"); // 쿠기를 찾을 경로를 컨텍스트 경로로 변경
					cookie.setMaxAge(60*60*24*7); // 단위는 초(sec) 단위이므로 7일로  유효시간 설정
					response.addCookie(cookie); // 쿠키 적용
				}else {
					System.out.println("## 자동로그인 체크되어 있지 않음");
				}
				*/
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}else { // 로그인 실패
				System.out.println("## 로그인 실패! - 아이디랑 비밀번호 일치 X");
				return new ResponseEntity<String>("do not match", HttpStatus.OK);
			}
		}else { // 로그인 실패
			System.out.println("## 로그인 실패! - 아이디 존재 X");
			return new ResponseEntity<String>("does not exit", HttpStatus.OK);
		}
	}
	
	
	// KaKao Signin Callback
	@RequestMapping(value="/kakaoSignin", produces = "application/json; charset=utf-8")
	public ModelAndView kakaoSignin(@RequestParam("code") String code) throws Exception {
		System.out.println("## kakao code : " + code);
		JsonNode jsonToken = KakaoAccessToken.getKakaoAccessToken(code); // JsonNode 트리형태로 토큰을 받아온다.
        JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(jsonToken.get("access_token")); // access_token을 이용해 사용자 정보 요청
        
        System.out.println(userInfo);
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("autosign");
        mv.addObject("email", userInfo.path("id").asText());
        mv.addObject("name", userInfo.path("properties").path("nickname").asText());
        mv.addObject("pwd", "kakao");
        mv.addObject("gender", userInfo.path("kakao_account").path("gender").asText().equals("female") ? "F" : "M");
       
        mv.addObject("status", "kakao");
        mv.addObject("request", "signup");
        
        return mv;
	}
	
	
	// Naver Signin
	@RequestMapping("/naver_url")
	@ResponseBody
	public String getNaverURL(HttpSession session) {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		return naverAuthUrl;
	}
	// Naver Signin Callback
	@RequestMapping(value="/naverSignin", produces="application/text; charset=UTF-8")
	public ModelAndView naverSignin(@RequestParam("code") String code, @RequestParam String state, HttpSession session) throws Exception {
    	OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
    	ObjectNode userInfo = new ObjectMapper().readValue(naverLoginBO.getUserProfile(oauthToken), ObjectNode.class);
		System.out.println("## userInfo : " + userInfo);
    	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("autosign");
		mv.addObject("email", userInfo.path("response").path("id").asText());
        mv.addObject("name", userInfo.path("response").path("nickname").asText());
        mv.addObject("pwd", "naver");
        mv.addObject("gender", userInfo.path("response").path("gender").asText().equals("F") ? "F" : "M");
        //mv.addObject("profile_pic", userInfo.path("response").path("profile_image").asText());
        
        mv.addObject("status", "naver");
        mv.addObject("request", "signup");
		
		return mv;
    }
	
	
	// 이메일 보내기 (+인증번호)
	@PostMapping("/sendUUID")
	@ResponseBody
	public ResponseEntity<String> findPassword(@RequestParam("email") String email) throws Exception{
		int check = memberService.checkEmail(email);
		if(check == 0) { // 회원가입을 하지 않았을 경우
			return new ResponseEntity<String>("does not exit", HttpStatus.OK);
		}else {
			memberService.sendEmailByPwd(email);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}
	
	// 비밀번호 재설정으로 이동
	@PostMapping("/settingPwd")
	public ModelAndView goPwdSetting(@RequestParam("email") String email, @RequestParam("uuid") String uuid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("includes/pwd_setting");
		mv.addObject("email", email);
		mv.addObject("uuid", uuid);
		return mv;
	}
	
	// 비밀번호 재설정
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
		System.out.println(email + ' ' + pwd);
		memberService.updatePwd(email, pwd);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	
	
	
	// 로그아웃
	@GetMapping("/signout")
	public String signout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Object obj = session.getAttribute("member");
		if(obj != null) {
			MemberVO vo = (MemberVO) obj;
			session.removeAttribute("member");
			session.invalidate(); // 세션 전체 날리기
			Cookie loginCookie = WebUtils.getCookie(request, "remember-me");
			if(loginCookie != null){
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
			}
		}
		return "redirect:/";
	}
}
