package com.railgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;
import com.railgo.mapper.PlannerMapper;

@Service
public class PlannerServiceImpl implements PlannerService {
	@Autowired
	private PlannerMapper mapper;
	public void insertPlanner(PlannerVO vo) {
		mapper.insertPlanner(vo);
	}
	public void insertPlannerSchedule(PlannerScheduleVO vo) {
		mapper.insertPlannerSchedule(vo);
	}
	public void insertPlannerDate(PlannerDateVO vo) {
		mapper.insertPlannerDate(vo);
	}
}
