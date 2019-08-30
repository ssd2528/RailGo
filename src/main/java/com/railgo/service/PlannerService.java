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
	ArrayList<PlannerJsonDTO> PlanScheduleList(String mem_code);
}
