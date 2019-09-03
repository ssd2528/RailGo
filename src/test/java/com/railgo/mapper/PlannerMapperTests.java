package com.railgo.mapper;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.railgo.domain.PlannerVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PlannerMapperTests {
	@Autowired
	private PlannerMapper mapper;
	/*
	@Test
	public void testInsertPlanner() {
		PlannerVO vo = new PlannerVO();
		vo.setMem_code("001");
		vo.setPlan_code("00700");
		vo.setSubject("first");
		vo.setHash_tag("couple");
		mapper.insertPlanner(vo);
		log.info("success");
	}*/
	@Test
	public void testPlannerList() {
		//mapper.plannerList("AA000001");
		log.info("##test planner list : ");
	}
}
