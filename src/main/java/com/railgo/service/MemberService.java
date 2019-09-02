package com.railgo.service;

import com.railgo.domain.FollowingVO;
import com.railgo.domain.MemberAddVO;
import com.railgo.domain.MemberVO;

public interface MemberService {
	int checkEmail(String email); // 이메일 중복 확인
	void signup(MemberVO member) throws Exception; // 회원가입
	int findOne(MemberVO member); // 회원이 존재하는 지 확인 
	void autoSignup(MemberVO member); // 메일보내지 않고 회원 insert
	
	MemberVO signin(MemberVO vo); // 로그인 
	MemberVO selMember(String mem_code); // 회원 정보 불러오기
	MemberAddVO selMemadd(MemberVO member); // 회원 추가정보 불러오기
	MemberAddVO selMemadd(String mem_code); 
	MemberVO[] selRecomMem2(String mem_code); // 추천유저 정보 불러오기
	MemberVO[] selRecomMem(); 
	MemberAddVO[] selRecomMemAdd2(String mem_code); // 추천유저 추가정보 불러오기
	MemberAddVO[] selRecomMemAdd(); 
	void following(FollowingVO following); // 팔로우
	void unFollow(FollowingVO following); // 언팔로우
	int selFollowing(String mem_code); // 팔로우 불러오기
	int selFollower(String mem_code); // 팔로워 불러오기
	int selFollower2(String[] member);
	int selFollowExist(FollowingVO following); // 팔로우 했는지 확인
	void updateMemadd(MemberAddVO member); // 추가정보 업데이트
	void updateMemImage(MemberAddVO member); // 사진 업데이트
	
	void sendEmailByPwd(String email) throws Exception;
	void updatePwd(String email, String pwd);
}
