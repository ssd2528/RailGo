package com.railgo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;
import com.railgo.mapper.PlannerMapper;

@Service
public class PlannerServiceImpl implements PlannerService {
	@Autowired
	private PlannerMapper mapper;

	public void createPlanner(PlannerJsonDTO dto) {
		PlannerJsonDTO pjd = dto;
		PlannerVO planner = pjd.getPlanner();
		ArrayList<PlannerDateVO> plannerDateArr = pjd.getPlannerDate();
		ArrayList<PlannerScheduleVO> plannerScheduleArr = pjd.getPlannerSchedule();
		mapper.firstInsertPlanner(planner);
		for (PlannerDateVO vo : plannerDateArr) {
			mapper.firstInsertPlannerDate(vo);
		}
		if (plannerScheduleArr != null) {
			for (PlannerScheduleVO vo : plannerScheduleArr) {
				mapper.firstInsertPlannerSchedule(vo);
			}
		}
	}
	public void updatePlanner(PlannerJsonDTO dto) {
		PlannerJsonDTO pjd = dto;
		PlannerVO planner = pjd.getPlanner();
		ArrayList<PlannerDateVO> plannerDateArr = pjd.getPlannerDate();
		ArrayList<PlannerScheduleVO> plannerScheduleArr = pjd.getPlannerSchedule();
		
		mapper.insertPlanner(planner);
		for (PlannerDateVO vo : plannerDateArr) {
			mapper.insertPlannerDate(vo);
		}
		if (plannerScheduleArr != null) {
			for (PlannerScheduleVO vo : plannerScheduleArr) {
				mapper.insertPlannerSchedule(vo);
			}
		}
	}
	public Boolean deleteScheduleList(String plan_code) {
		int dps = mapper.deletePlannerSchedule(plan_code);
		int dpd = mapper.deletePlannerDate(plan_code);
		int dp = mapper.deletePlanner(plan_code);
		System.out.println("PlannerServiceImpl.java -> deleteScheduleList : "+dps+", "+dpd+", "+dp);
		if(dpd != 0 && dp != 0)	return true;
		else	return false;
	}
	public ArrayList<PlannerJsonDTO> PlanScheduleList(String mem_code,String page,int start,int end){
		ArrayList<PlannerJsonDTO> list = new ArrayList<>();
		PlannerJsonDTO pjdto = null;
		ArrayList<PlannerScheduleVO> plannerSchedule = new ArrayList<>();
		ArrayList<PlannerDateVO> plannerDate = new ArrayList<>();
		ArrayList<PlannerVO> plannerlist;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("mem_code", mem_code);
		parameters.put("start", start);
		parameters.put("end", end);
		if(page == "schedule") {	//schedule 페이지에서 호출
			plannerlist = mapper.plannerList(parameters);
		}else {	// planner 페이지에서 호출
			plannerlist = mapper.otherPlannerList(parameters);
		}
		int size = plannerlist.size();
		if(size == 0) {
			return null;
		}else {
			for(int i = 0; i < size; i++) {
				pjdto = new PlannerJsonDTO();
				System.out.println("planner list "+ i +" : " + plannerlist.get(i));
				String plan_code = plannerlist.get(i).getPlan_code();
				plannerDate = mapper.plannerDateList(plan_code);
				plannerSchedule = mapper.plannerScheduleList(plan_code);
				pjdto.setPlanner(plannerlist.get(i));
				pjdto.setPlannerDate(plannerDate);
				pjdto.setPlannerSchedule(plannerSchedule);
				list.add(pjdto);
				//System.out.println("plannerDate : "+i+" : "+plannerDate);
				//System.out.println("plannerSchedule : "+i+" : "+plannerSchedule);
			}
		}
		return list;
	}
}
