package com.railgo.service;

import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;

public interface MemberService {
	int checkEmail(String email); // 이메일 중복 확인
	void signup(MemberVO member) throws Exception; // 회원가입
	MemberVO signin(MemberVO vo); // 로그인 
	MemberAddVO selMemadd(MemberVO member);
	MemberAddVO selMemadd(String mem_code);
	void updateMemadd(MemberAddVO member);
	void updateMemImage(MemberAddVO member);
}
