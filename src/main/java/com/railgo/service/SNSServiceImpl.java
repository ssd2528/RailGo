package com.railgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railgo.domain.SNSVO;
import com.railgo.mapper.SNSMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class SNSServiceImpl implements SNSService {
	@Setter(onMethod_ = @Autowired)
	private SNSMapper snsMapper;
	
	@Override
	public void register(SNSVO sns) {
		log.info("register.." + sns);
		snsMapper.register(sns);
	}
}
