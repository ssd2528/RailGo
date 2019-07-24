package com.railgo.mapper;

import java.util.List;

import com.railgo.domain.SNSVO;

public interface SNSMapper {
	
	public List<SNSVO> getList();
	public void register(SNSVO sns);
}
