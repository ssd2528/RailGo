package com.railgo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.railgo.domain.CommJoinDTO;
import com.railgo.domain.CommVO;
import com.railgo.domain.SNSJoinDTO;
import com.railgo.domain.SNSLikeVO;
import com.railgo.domain.SNSVO;
import com.railgo.domain.TripImageVO;
import com.railgo.mapper.CommMapper;
import com.railgo.mapper.SNSLikeMapper;
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
	private CommMapper commMapper;
	private SNSLikeMapper snsLikeMapper;
	
	@Override
	public void register(SNSVO sns) {
		log.info("register.." + sns);
		snsMapper.register(sns);
	}
	
	@Override
	public void remove(String sns_code) {
		snsMapper.delete(sns_code);
	}
	
	@Override
	public boolean modify(SNSVO sns) {
		return snsMapper.update(sns) == 1;
	}
	
	@Override
	public SNSVO modifyContent(String sns_code) {
		return snsMapper.modifyContent(sns_code);
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
	
	@Override
	public int commCount(String sns_code) {
		return commMapper.commCount(sns_code);
	}
	
	@Override 
	public ArrayList<CommJoinDTO> getCommList(String sns_code) {
		return commMapper.getCommList(sns_code);
	}

	@Override
	public void commDelete(int comm_code) {
		commMapper.commDelete(comm_code);
	}
	
	@Override
	public void commRegister(CommVO vo) {
		commMapper.commRegister(vo);
	}
	
	@Override
	public CommJoinDTO commCheck() {
		return commMapper.commCheck();
	}
	
	@Override
	public int snsLikeCount(String sns_code) {
		return snsLikeMapper.snsLikeCount(sns_code);
	}
	
	@Override
	public void snsLikePlus(SNSLikeVO vo) {
		snsLikeMapper.snsLikePlus(vo);
	}
	
	@Override
	public void snsLikeMinus(SNSLikeVO vo) {
		snsLikeMapper.snsLikeMinus(vo);
	}
	
	@Override
	public boolean snsLikeCheck(SNSLikeVO vo){
		int check = snsLikeMapper.snsLikeCheck(vo);
		boolean result = false;
		if(check==0) {
			result = false;
		}else if(check==1){
			result = true;
		}
		return result;
	}
	
	@Override
	public ArrayList<CommJoinDTO> getRereList(int comm_code) {
		return commMapper.getRereList(comm_code);
	}
	
}
