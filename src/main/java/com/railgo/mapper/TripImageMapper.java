package com.railgo.mapper;

import java.util.List;

import com.railgo.domain.TripImageVO;

public interface TripImageMapper {
	public void insert(TripImageVO vo);
	public List<TripImageVO> findBySNS_CODE(String sns_code);
}
