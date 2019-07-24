package com.railgo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/Webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ARTMapperTests {
	@Setter(onMethod_ = @Autowired)
	private ARTMapper artmapper;
	
	@Test
	public void testCityListFood() {
		artmapper.cityListFood("서울");
	}
	
	@Test
	public void testTownListFood() {
		artmapper.townListFood("가평");
	}
}
