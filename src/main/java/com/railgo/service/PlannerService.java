package com.railgo.service;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerService {
	void insertPlanner(PlannerVO vo);
	void insertPlannerSchedule(PlannerScheduleVO vo);
	void insertPlannerDate(PlannerDateVO rvo);
}
