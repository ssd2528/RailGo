package com.railgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;
import com.railgo.mapper.MailUtils;
import com.railgo.mapper.MemberMapper;
import com.railgo.mapper.TempKey;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public int checkEmail(String email) { // 이메일 중복 체크
		return memberMapper.checkEmail(email);
	}
	
	@Override
	@Transactional
	public void signup(MemberVO member) throws Exception {
		memberMapper.signup(member); // 회원가입
		//String authKey = new TempKey().getKey(50, false); // 임의의 authKey 생성
		//log.info("## authKey : " + authKey);
		//member.setAuthKey(authKey);
		//memberMapper.updateAuthKey(member); // 인증키 DB에 저장
		
		// 메일 작성 관련
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[RailGo] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
						.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
						.append("<a href='http://localhost:8080/emailConfirm?email=")
						.append(member.getEmail())
						//.append("&authKey=")
						//.append(authKey)
						.append("' target='_blenk'>이메일 인증 확인</a>")
						.toString()
				);
		sendMail.setFrom("leeghrbs13@gmail.com", "내일고");
		sendMail.setTo(member.getEmail());
		sendMail.send();
	}
	
	@Override
	public MemberVO signin(MemberVO member) {
		return memberMapper.signin(member);
	}

	@Override
	public MemberAddVO selMemadd(MemberVO member) {
		return memberMapper.selMemadd(member);
	}
	
	@Override
	public MemberAddVO selMemadd(String mem_code) {
		return memberMapper.selMemadd(mem_code);
	}
	
	@Override
	public void updateMemadd(MemberAddVO member) {
		memberMapper.updateMemadd(member);
	}
	
	@Override
	public void updateMemImage(MemberAddVO member) {
		memberMapper.updateMemImage(member);
	}

}
