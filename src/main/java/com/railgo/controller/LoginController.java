package com.railgo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;
import com.railgo.mapper.MailUtils;
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
	
	@Setter(onMethod_=@Autowired)
	MemberService memberService;
	@Setter(onMethod_=@Autowired)
	PasswordEncoder passwordEncoder;
	
	
	// 이메일 중복 체크
	@PostMapping("/checkEmail")
	public void checkEmail(@RequestParam("email")String email,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		log.info("## 이메일 중복 체크 : " + email);
		int check = memberService.checkEmail(email);
		PrintWriter out = response.getWriter();
		if(check==1)  out.print("false");
		else out.print("true");
	}
	// 회원가입
	@PostMapping("/signup")
	public String signup(MemberVO member, RedirectAttributes rttr) throws Exception {
		log.info("## 회원가입 : " + member);
		
		String encPwd = passwordEncoder.encode(member.getPwd());
		log.info("## 원래 비밀번호: " + member.getPwd() + " / 암호화된 비밀번호 : " + encPwd);
		member.setPwd(encPwd);
		
		memberService.signup(member); // 회원가입
		rttr.addFlashAttribute("authMsg", "가입시 사용한 이메일로 인증해주세요.");
		return "redirect:/";
	}
	// 이메일 인증 확인 
	@GetMapping("/emailConfirm")
	public String emailConfirm(MemberVO member, RedirectAttributes rttr, Model model) throws  Exception {
		log.info("## "+ member.getEmail() + " : auth confirmed");
		rttr.addFlashAttribute("authMsg", "회원가입이 완료되었습니다. 가입시 사용한 이메일로 로그인해주세요.");
		return "redirect:/";
	}
	
	
	// 로그인
	@PostMapping("/signin")
	@ResponseBody
	public ResponseEntity<String> signin(MemberVO vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("## 로그인 ");
		if(session.getAttribute("member") != null) { // 기존에 member란 세션 값이 존재한다면
			session.removeAttribute("member"); // 기존 세션 값을 제거
		}
		String rawPwd = vo.getPwd();
		MemberVO member = memberService.signin(vo);
		MemberAddVO memadd = memberService.selMemadd(member);
		
		if(member!=null) {
			log.info("## 사용자 확인 ");
			String encPwd = member.getPwd();
			log.info("## 암호화된 비밀번호 : " + encPwd + " / 입력한 비밀번호 : " + rawPwd); 
			if(passwordEncoder.matches(rawPwd, encPwd) && member!=null) {  // 로그인 성공
				log.info("## 로그인 성공!");
				session.setAttribute("member", member);
				session.setAttribute("memadd", memadd);
				if(request.getParameter("loginCookie").equals("true")) { 
					// 로그인 성공 후, 자동로그인이 체크되어 있으면 (쿠키를 사용할 거라면)
					log.info("## 자동로그인 체크되어 있음 : " + request.getParameter("loginCookie"));
					Cookie cookie = new Cookie("loginCookie", session.getId()); // 쿠키를 생성하고 현재 로그인 되어 있을 때 생성되었던 세션의 id를 쿠키에 저장
					cookie.setPath("/"); // 쿠기를 찾을 경로를 컨텍스트 경로로 변경
					cookie.setMaxAge(60*60*24*7); // 단위는 초(sec) 단위이므로 7일로  유효시간 설정
					response.addCookie(cookie); // 쿠키 적용
				}else {
					log.info("## 자동로그인 체크되어 있지 않음");
				}
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}else { // 로그인 실패
				log.info("## 로그인 실패! - 아이디랑 비밀번호 일치 X");
				return new ResponseEntity<String>("do not match", HttpStatus.OK);
			}
		}else { // 로그인 실패
			log.info("## 로그인 실패! - 아이디 존재 X");
			return new ResponseEntity<String>("does not exit", HttpStatus.OK);
		}
	}
	
	
	// 로그아웃
	@GetMapping("/signout")
	public String signout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Object obj = session.getAttribute("member");
		if(obj != null) {
			MemberVO vo = (MemberVO) obj;
			session.removeAttribute("member");
			session.invalidate(); // 세션 전체 날리기
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null){
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
			}
		}
		return "redirect:/";
	}
}
