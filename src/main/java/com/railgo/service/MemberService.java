package com.railgo.service;

import com.railgo.domain.MemberVO;

public interface MemberService {
	int checkEmail(String email); // 이메일 중복 확인
	void signup(MemberVO member) throws Exception; // 회원가입
	int findOne(MemberVO member); // 회원이 존재하는 지 확인 
	void autoSignup(MemberVO member); // 메일보내지 않고 회원 insert
	
	MemberVO signin(MemberVO vo); // 로그인 
}
