package com.railgo.service;

import java.util.ArrayList;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerService {
	void createPlanner(PlannerJsonDTO dto);
	void updatePlanner(PlannerJsonDTO dto);
	Boolean deleteScheduleList(String plan_code);
	//schedule 페이지에서 나의 스케쥴목록과 planner 페이지에서 다른 내일러들의 일정들을 보여주는 메소드
	ArrayList<PlannerJsonDTO> PlanScheduleList(String mem_code,String page,int start,int end);
}
