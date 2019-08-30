package com.railgo.service;

import java.util.ArrayList;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerService {
	void insertPlanner(PlannerVO vo);
	void insertPlannerSchedule(PlannerScheduleVO vo);
	void insertPlannerDate(PlannerDateVO rvo);
	ArrayList<PlannerJsonDTO> PlanSchedulelist(String mem_code);
}
