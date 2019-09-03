package com.railgo.service;

import java.util.HashMap;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.railgo.domain.FollowingVO;
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
	@Autowired
	private PasswordEncoder passwordEncoder;	

	@Override
	public int checkEmail(String email) { // 이메일 중복 체크
		return memberMapper.checkEmail(email);
	}
	
	@Override
	public boolean signup(MemberVO member){
		//String authKey = new TempKey().getKey(50, false); // 임의의 authKey 생성
		//log.info("## authKey : " + authKey);
		//member.setAuthKey(authKey);
		//memberMapper.updateAuthKey(member); // 인증키 DB에 저장
		
		// 메일 작성 관련
		try {
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
			memberMapper.signup(member); // 회원가입
			return true;
		}catch(Exception e) {
			System.out.println("## 에러 발생!! : " + e);
			return false;
		}
	}
	
	@Override
	public int findOne(MemberVO member) {
		return memberMapper.findOne(member);
	}
	
	@Override 
	public void autoSignup(MemberVO member) {
		memberMapper.signup(member);
	}
	
	@Override
	public MemberVO signin(MemberVO member) {
		return memberMapper.signin(member);
	}
	
	@Override 
	public void sendEmailByPwd(String email) throws Exception {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		uuid = uuid.substring(0, 10);
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[RailGo] 비밀번호 재설정 관련 메일");
		sendMail.setText(new StringBuffer().append("<h1></h1>")
						.append("<p>이 메일은 비밀번호 재설정을 위해 발송된 메일입니다.</p>")
						.append("<p>비밀번호 재설정을 위한 인증번호는 '" +uuid+"' 이며, 아래 링크로 들어가 인증번호와 새로운 비밀번호를 입력해주세요.</p><br>")
						
						.append("<form name='f' method='post' action='http://localhost:8080/settingPwd'>")
						.append("<input type='hidden' name='email' value='"+email+"'>")
						.append("<input type='hidden' name='uuid' value='"+uuid+"'>")
						.append("<input type='submit' value='비밀번호 재설정하러 가기'>")
						.append("</form>")
						//.append("<a href='http://localhost:8080' target='_blenk'>비밀번호 재설정 링크</a>")
						.toString()
				);
		sendMail.setFrom("leeghrbs13@gmail.com", "내일고");
		sendMail.setTo(email);
		sendMail.send();
		
		/*
		String encPwd = passwordEncoder.encode(uuid);
		System.out.println("## 원래 비밀번호: " + uuid + " / 암호화된 비밀번호 : " + encPwd);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);	map.put("pwd", encPwd);
		System.out.println("## email : " + email + ", uuid : " + uuid);
		memberMapper.updatePwd(map);
		*/
	}
	
	@Override
	public void updatePwd(String email, String pwd) {
		String encPwd = passwordEncoder.encode(pwd);
		System.out.println("## 원래 비밀번호: " + pwd + " / 암호화된 비밀번호 : " + encPwd);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);	map.put("pwd", encPwd);
		System.out.println("## email : " + email + ", pwd : " + pwd);
		memberMapper.updatePwd(map);
	}
	@Override
	public MemberVO selMember(String mem_code) {
		return memberMapper.selMember(mem_code);
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
	public MemberVO[] selRecomMem2(String mem_code) {
		return memberMapper.selRecomMem2(mem_code);
	}
	
	@Override
	public MemberVO[] selRecomMem() {
		return memberMapper.selRecomMem();
	}
	
	@Override
	public MemberAddVO[] selRecomMemAdd2(String mem_code) {
		return memberMapper.selRecomMemAdd2(mem_code);
	}
	
	@Override
	public MemberAddVO[] selRecomMemAdd() {
		return memberMapper.selRecomMemAdd();
	}
	
	@Override
	public void following(FollowingVO following) {
		 memberMapper.following(following);
	}
	
	@Override
	public void unFollow(FollowingVO following) {
		 memberMapper.unFollow(following);
	}
	
	@Override
	public int selFollowing(String mem_code) {
		 return memberMapper.selFollowing(mem_code);
	}
	
	@Override
	public int selFollower(String mem_code) {
		 return memberMapper.selFollower(mem_code);
	}
	
	@Override
	public int selFollower2(String[] member) {
		 return memberMapper.selFollower2(member);
	}
	
	@Override
	public int selFollowExist(FollowingVO following) {
		 return memberMapper.selFollowExist(following);
	}
	
	@Override
	public void updateMemadd(MemberAddVO member) {
		memberMapper.updateMemadd(member);
	}
	
	@Override
	public void updateMemImage(MemberAddVO member) {
		memberMapper.updateMemImage(member);
	}
	@Override
	public String getMemberName(String mem_code) {
		return memberMapper.getMemberName(mem_code);
	}

}
