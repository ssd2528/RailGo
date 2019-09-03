package com.railgo.mapper;

import java.util.ArrayList;

import com.railgo.domain.TripImageVO;

public interface TripImageMapper {
	public void insertReview(TripImageVO vo);
	public ArrayList<TripImageVO> findReviewImg(String r_code);
	public void insertSNS(TripImageVO vo);
	public ArrayList<TripImageVO> findSNSImg(String sns_code);
}
