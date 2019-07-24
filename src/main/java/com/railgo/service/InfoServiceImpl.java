package com.railgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railgo.domain.CategoryVO;
import com.railgo.mapper.CategoryMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Log4j
@Service
@AllArgsConstructor
public class InfoServiceImpl implements InfoService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public String findAreaCode(String areaCodeStr) {
		String result="";
		
		if(areaCodeStr.equals("서울")) result="&areaCode=1";
		else if(areaCodeStr.equals("인천")) result="&areaCode=2";
		else if(areaCodeStr.equals("대전")) result="&areaCode=3";
		else if(areaCodeStr.equals("대구")) result="&areaCode=4";
		else if(areaCodeStr.equals("광주")) result="&areaCode=5";
		else if(areaCodeStr.equals("부산")) result="&areaCode=6";
		else if(areaCodeStr.equals("울산")) result="&areaCode=7";
		// 경기도
		else if(areaCodeStr.equals("가평")) result="&areaCode=31&sigunguCode=1";
		else if(areaCodeStr.equals("고양")) result="&areaCode=31&sigunguCode=2";
		else if(areaCodeStr.equals("광명")) result="&areaCode=31&sigunguCode=4";
		else if(areaCodeStr.equals("동두천")) result="&areaCode=31&sigunguCode=10";
		else if(areaCodeStr.equals("수원")) result="&areaCode=31&sigunguCode=13";
		else if(areaCodeStr.equals("안양")) result="&areaCode=31&sigunguCode=17";
		else if(areaCodeStr.equals("양평")) result="&areaCode=31&sigunguCode=19";
		else if(areaCodeStr.equals("평택")) result="&areaCode=31&sigunguCode=28";
		else if(areaCodeStr.equals("화성")) result="&areaCode=31&sigunguCode=31";
		// 강원도
		else if(areaCodeStr.equals("강릉")) result="&areaCode=32&sigunguCode=1";
		else if(areaCodeStr.equals("동해")) result="&areaCode=32&sigunguCode=3";
		else if(areaCodeStr.equals("영월")) result="&areaCode=32&sigunguCode=8";
		else if(areaCodeStr.equals("정선")) result="&areaCode=32&sigunguCode=11";
		else if(areaCodeStr.equals("춘천")) result="&areaCode=32&sigunguCode=13";
		else if(areaCodeStr.equals("태백")) result="&areaCode=32&sigunguCode=14";
		else if(areaCodeStr.equals("평창")) result="&areaCode=32&sigunguCode=15";
		// 충청권
		else if(areaCodeStr.equals("단양")) result="&areaCode=33&sigunguCode=2";
		else if(areaCodeStr.equals("대천")) result="&areaCode=34&sigunguCode=5";
		else if(areaCodeStr.equals("영동")) result="&areaCode=33&sigunguCode=4";
		else if(areaCodeStr.equals("제천")) result="&areaCode=33&sigunguCode=7";
		else if(areaCodeStr.equals("조치원")) result="&areaCode=34&sigunguCode=11";
		else if(areaCodeStr.equals("천안")) result="&areaCode=34&sigunguCode=12";
		else if(areaCodeStr.equals("천안아산")) result="&areaCode=34&sigunguCode=9";
		else if(areaCodeStr.equals("충주")) result="&areaCode=33&sigunguCode=11";
		else if(areaCodeStr.equals("홍성")) result="&areaCode=34&sigunguCode=15";
		// 전라권
		else if(areaCodeStr.equals("곡성")) result="&areaCode=38&sigunguCode=3";
		else if(areaCodeStr.equals("구례구")) result="&areaCode=38&sigunguCode=11";
		else if(areaCodeStr.equals("나주")) result="&areaCode=38&sigunguCode=6";
		else if(areaCodeStr.equals("남원")) result="&areaCode=37&sigunguCode=4";
		else if(areaCodeStr.equals("목포")) result="&areaCode=38&sigunguCode=8";
		else if(areaCodeStr.equals("순천")) result="&areaCode=38&sigunguCode=11";
		else if(areaCodeStr.equals("여수엑스포")) result="&areaCode=38&sigunguCode=13";
		else if(areaCodeStr.equals("익산")) result="&areaCode=37&sigunguCode=9";
		else if(areaCodeStr.equals("전주")) result="&areaCode=37&sigunguCode=12";
		else if(areaCodeStr.equals("정읍")) result="&areaCode=37&sigunguCode=13";
		// 경상권
		else if(areaCodeStr.equals("경주")) result="&areaCode=35&sigunguCode=2";
		else if(areaCodeStr.equals("구미")) result="&areaCode=35&sigunguCode=4";
		else if(areaCodeStr.equals("김천구미")) result="&areaCode=35&sigunguCode=6";
		else if(areaCodeStr.equals("마산")) result="&areaCode=36&sigunguCode=6";
		else if(areaCodeStr.equals("신경주")) result="&areaCode=35&sigunguCode=2";
		else if(areaCodeStr.equals("안동")) result="&areaCode=35&sigunguCode=11";
		else if(areaCodeStr.equals("영주")) result="&areaCode=35&sigunguCode=14";
		else if(areaCodeStr.equals("영천")) result="&areaCode=35&sigunguCode=15";
		else if(areaCodeStr.equals("점촌")) result="&areaCode=35&sigunguCode=7";
		else if(areaCodeStr.equals("진주")) result="&areaCode=36&sigunguCode=13";
		else if(areaCodeStr.equals("춘양")) result="&areaCode=35&sigunguCode=8";
		else if(areaCodeStr.equals("포항")) result="&areaCode=35&sigunguCode=23";
		
		else result="&areaCode=1";
		
		return result;
	}
	
	@Override
	public CategoryVO findCatNameByCat3(String cat3) {
		return categoryMapper.findCatNameByCat3(cat3);
	}

}
