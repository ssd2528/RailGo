package com.railgo.mapper;

import java.util.ArrayList;

import com.railgo.domain.ReviewJoinDTO;
import com.railgo.domain.ReviewVO;

public interface ReviewMapper {
	ArrayList<ReviewJoinDTO> findAllReview(int contentid);
	void insertReview(ReviewVO vo);
	void deleteReview(String r_code);
}
