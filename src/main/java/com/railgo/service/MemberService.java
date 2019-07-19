package com.railgo.service;

import com.railgo.domain.MemberVO;

public interface MemberService {
	int checkEmail(String email); // 이메일 중복 확인
	void signup(MemberVO member) throws Exception; // 회원가입
	MemberVO signin(MemberVO vo); // 로그인 
}
