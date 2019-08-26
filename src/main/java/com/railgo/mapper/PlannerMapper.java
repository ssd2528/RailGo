package com.railgo.mapper;

import java.util.ArrayList;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerMapper {
	void insertPlanner(PlannerVO vo);
	void insertPlannerSchedule(PlannerScheduleVO vo);
	void insertPlannerDate(PlannerDateVO vo);
	ArrayList<PlannerVO> plannerList(String mem_code);
	ArrayList<PlannerScheduleVO> plannerScheduleList(String plan_code);
	ArrayList<PlannerDateVO> plannerDateList(String plan_code);
}
