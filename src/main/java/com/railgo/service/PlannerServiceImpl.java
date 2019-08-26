package com.railgo.service;

import java.util.ArrayList;

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
	public void insertPlanner(PlannerVO vo) {
		mapper.insertPlanner(vo);
	}
	public void insertPlannerSchedule(PlannerScheduleVO vo) {
		mapper.insertPlannerSchedule(vo);
	}
	public void insertPlannerDate(PlannerDateVO vo) {
		mapper.insertPlannerDate(vo);
	}
	public ArrayList<PlannerJsonDTO> PlanSchedulelist(String mem_code){
		ArrayList<PlannerJsonDTO> list = new ArrayList<>();
		PlannerJsonDTO pjdto;
		ArrayList<PlannerScheduleVO> plannerSchedule = new ArrayList<>();
		ArrayList<PlannerDateVO> plannerDate = new ArrayList<>();
		ArrayList<PlannerVO> plannerlist = mapper.plannerList(mem_code);
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
				System.out.println("plannerDate : "+i+" : "+plannerDate);
				System.out.println("plannerSchedule : "+i+" : "+plannerSchedule);
			}
		}
		return list;
	}
}
