package com.railgo.mapper;

import java.util.HashMap;

import com.railgo.domain.MemberVO;

public interface MemberMapper {
	int checkEmail(String email);
	void signup(MemberVO member);
	int findOne(MemberVO member);	
	MemberVO signin(MemberVO member);
	void updatePwd(HashMap<String, Object> map);
}
