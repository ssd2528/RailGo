package com.railgo.mapper;

import com.railgo.domain.SNSLikeVO;

public interface SNSLikeMapper {
	int snsLikeCount(String sns_code); // 좋아요 갯수 
	void snsLikePlus(SNSLikeVO vo); // 좋아요 +1
	void snsLikeMinus(SNSLikeVO vo); // 좋아요 -1
	
	int snsLikeCheck(SNSLikeVO vo); // 좋아요 체크
}
