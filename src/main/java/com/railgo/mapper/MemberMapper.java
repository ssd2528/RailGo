package com.railgo.mapper;

import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;

public interface MemberMapper {
	int checkEmail(String email);
	void signup(MemberVO member);
	int findOne(MemberVO member);	
	MemberVO signin(MemberVO member);
	MemberAddVO selMemadd(MemberVO member);
	MemberAddVO selMemadd(String mem_code);
	void updateMemadd(MemberAddVO member);
	void updateMemImage(MemberAddVO member);
}
