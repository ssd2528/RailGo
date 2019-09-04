package com.railgo.mapper;

import java.util.HashMap;

import com.railgo.domain.FollowingVO;
import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;

public interface MemberMapper {
	int checkEmail(String email);
	void signup(MemberVO member);
	int findOne(MemberVO member);	
	MemberVO signin(MemberVO member);
	
	MemberVO selMember(String mem_code);
	MemberAddVO selMemadd(MemberVO member);
	MemberAddVO selMemadd(String mem_code);
	MemberVO[] selRecomMem2(String mem_code);
	MemberVO[] selRecomMem();
	MemberAddVO[] selRecomMemAdd2(String mem_code);
	MemberAddVO[] selRecomMemAdd();
	void following(FollowingVO following);
	void unFollow(FollowingVO following);
	int selFollowing(String mem_code);
	int selFollower(String mem_code);
	int selFollower2(String[] member);
	int selFollowExist(FollowingVO following);
	int getSnsCount(String mem_code);
	void updateMemadd(MemberAddVO member);
	void updateMemImage(MemberAddVO member);
	
	void updatePwd(HashMap<String, Object> map);
	String getMemberName(String mem_code);
}
