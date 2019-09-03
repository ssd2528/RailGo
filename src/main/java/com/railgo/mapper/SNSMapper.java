package com.railgo.mapper;

import java.util.List;

import com.railgo.domain.SNSVO;

public interface SNSMapper {
	
	public void register(SNSVO sns);
	public List<SNSVO> getList();
	public int delete(String sns_code);
	public int update(SNSVO sns);
}
