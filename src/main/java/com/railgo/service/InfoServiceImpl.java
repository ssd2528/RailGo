package com.railgo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railgo.domain.ARTVO;
import com.railgo.mapper.ARTMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Service
public class InfoServiceImpl implements InfoService {
	
	@Setter(onMethod_ = @Autowired)
	ARTMapper artmapper;
	
	@Override
	public List<ARTVO> cityListFood(String city) {
		return artmapper.cityListFood(city);
	}
	
	@Override
	public List<ARTVO> townListFood(String town) {
		return artmapper.townListFood(town);
	}
	
	@Override
	public int areaCode(String city) {
		
		if(city.equals("서울")) {
			return 1;
		}else {
			return 1;
		}
	}
}
