package com.railgo.service;

import java.util.List; 

import com.railgo.domain.SNSVO;

public interface SNSService {
	public void register(SNSVO sns);
	public List<SNSVO> getList();
	public void remove(String sns_code);
	public boolean modify(SNSVO sns);
}
