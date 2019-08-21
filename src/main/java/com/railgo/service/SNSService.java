package com.railgo.service;

import java.util.ArrayList;
import java.util.List;

import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;

public interface SNSService {
	public void register(SNSVO sns); // 글쓰기
	public List<SNSJoinDTO> getList(); // 글 전체 불러오기
	public SNSJoinDTO content(String sns_code); // 글 상세보기
	public void remove(String sns_code); // 글 삭제
	public boolean modify(SNSVO sns); // 글 수정
	
	ArrayList<TripImageVO> findSNSImg(String sns_code); // SNS코드로 이미지 조회
	void insertSNSImg(TripImageVO vo); // SNS 이미지 저장
}
