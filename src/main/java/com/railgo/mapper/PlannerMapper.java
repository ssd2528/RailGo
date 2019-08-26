package com.railgo.mapper;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerMapper {
	void insertPlanner(PlannerVO vo);
	void insertPlannerSchedule(PlannerScheduleVO vo);
	void insertPlannerDate(PlannerDateVO vo);
}
