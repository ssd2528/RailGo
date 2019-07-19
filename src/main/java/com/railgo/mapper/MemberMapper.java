package com.railgo.mapper;

import com.railgo.domain.MemberVO;

public interface MemberMapper {
	int checkEmail(String email);
	void signup(MemberVO member);
	MemberVO signin(MemberVO member);
}
