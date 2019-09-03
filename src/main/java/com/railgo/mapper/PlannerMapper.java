package com.railgo.mapper;

import java.util.ArrayList;
import java.util.Map;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerMapper {
	String selectPlanCode();
	//일정 만들기 첫 생성해서 저장시 seq를 통해 plan_code 부여
	void firstInsertPlanner(PlannerVO vo);
	void firstInsertPlannerSchedule(PlannerScheduleVO vo);
	void firstInsertPlannerDate(PlannerDateVO vo);
	//일정 수정시에 기존 plan_code유지하고 기존 일정 삭제 후 현재 데이터 저장하기 위함
	void insertPlanner(PlannerVO vo);
	void insertPlannerSchedule(PlannerScheduleVO vo);
	void insertPlannerDate(PlannerDateVO vo);
	int deletePlanner(String plan_code);
	int deletePlannerSchedule(String plan_code);
	int deletePlannerDate(String plan_code);
	ArrayList<PlannerVO> otherPlannerList(Map<String, Object> parameters);
	ArrayList<PlannerVO> plannerList(Map<String, Object> parameters);
	ArrayList<PlannerScheduleVO> plannerScheduleList(String plan_code);
	ArrayList<PlannerDateVO> plannerDateList(String plan_code);
	
	ArrayList<PlannerVO> findPlanSheduleListByTheme(String theme); // 컨셉에 맞는 PLANNER 목록 조회
}
