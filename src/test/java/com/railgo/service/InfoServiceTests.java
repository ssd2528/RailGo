package com.railgo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.railgo.service.InfoService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/Webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class InfoServiceTests {
	@Setter(onMethod_ = {@Autowired })
	InfoService service;
	
	@Test
	public void testCityListFood() {
		service.cityListFood("서울");
	}
}
