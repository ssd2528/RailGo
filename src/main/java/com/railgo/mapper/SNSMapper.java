package com.railgo.mapper;

import java.util.List;

import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSVO;

public interface SNSMapper {
	
	public void register(SNSVO sns); // 글쓰기
	public List<SNSJoinDTO> getList(); // 글 전체 불러오기
	public SNSJoinDTO content(String sns_code); // 글 상세보기
	public int delete(String sns_code); // 글 삭제
	public int update(SNSVO sns); // 글 수정
}
