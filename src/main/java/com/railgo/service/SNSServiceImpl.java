package com.railgo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;
import com.railgo.mapper.SNSMapper;
import com.railgo.mapper.TripImageMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class SNSServiceImpl implements SNSService {
	@Setter(onMethod_ = @Autowired)
	private SNSMapper snsMapper;
	private TripImageMapper imgMapper;
	
	/*
	@Setter(onMethod_ = @Autowired)
	private TripImageMapper tripimageMapper;
	*/
	
	@Override
	public void register(SNSVO sns) {
		log.info("register.." + sns);
		snsMapper.register(sns);
	}
	
	/*
	@Transactional
	@Override
	public void register(SNSVO sns) {
		log.info("register.." + sns);
	
		if(sns.getAttachList() == null || sns.getAttachList().size() <= 0) {
			return;
		}
		sns.getAttachList().forEach(attach -> {
			attach.setSns_code(sns.getSns_code());
			tripimageMapper.insert(attach);
		});
		snsMapper.register(sns);
	}
	*/
	
	@Override
	public void remove(String sns_code) {
		snsMapper.delete(sns_code);
	}
	
	@Override
	public boolean modify(SNSVO sns) {
		return snsMapper.update(sns) == 1;
	}
	
	@Override
	public List<SNSJoinDTO> getList() {
		return snsMapper.getList();
	}
	
	@Override
	public SNSJoinDTO content(String sns_code) {
		return snsMapper.content(sns_code);
	}
	
	@Override
	public ArrayList<TripImageVO> findSNSImg(String sns_code){
		return imgMapper.findSNSImg(sns_code);
	}
	
	@Override
	public void insertSNSImg(TripImageVO vo) {
		imgMapper.insertSNS(vo);
	}
}
