package com.railgo.service;

import java.util.ArrayList;
import java.util.List;

import com.railgo.domain.CommJoinDTO;
import com.railgo.domain.CommVO;
import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSLikeVO;
import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;

public interface SNSService {
	public void register(SNSVO sns); // 글쓰기
	public List<SNSJoinDTO> getList(); // 글 전체 불러오기
	public SNSJoinDTO content(String sns_code); // 글 상세보기
	public void remove(String sns_code); // 글 삭제
	public boolean modify(SNSVO sns); // 글 수정
	public SNSVO modifyContent(String sns_code);
	
	public ArrayList<TripImageVO> findSNSImg(String sns_code); // SNS코드로 이미지 조회
	public void insertSNSImg(TripImageVO vo); // SNS 이미지 저장
	
	public int commCount(String sns_code); // 댓글 갯수
	public ArrayList<CommJoinDTO> getCommList(String sns_code); // 댓글 리스트
	public void commDelete(int comm_code);
	public void commRegister(CommVO vo); // 댓글 달기
	public CommJoinDTO commCheck(); // 댓글 작성 후 ajax로 댓글 뿌리기
	
	public int snsLikeCount(String sns_code); // 좋아요 갯수
	public void snsLikePlus(SNSLikeVO vo); // 좋아요 +1
	public void snsLikeMinus(SNSLikeVO vo); // 좋아요 -1
	public boolean snsLikeCheck(SNSLikeVO vo); // 좋아요 했는지 안했는지 체크
	
	public ArrayList<CommJoinDTO> getRereList(int comm_code); // 대댓글 리스트
}
