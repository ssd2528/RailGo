package com.railgo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railgo.domain.PlannerBookmarkVO;
import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;
import com.railgo.mapper.PlannerBookmarkMapper;
import com.railgo.mapper.PlannerMapper;

@Service
public class PlannerServiceImpl implements PlannerService {
	@Autowired
	private PlannerMapper mapper;
	@Autowired
	private PlannerBookmarkMapper bookMarkMapper;

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
	public int getPostingCount(String mem_code) {
		return mapper.getPostingCount(mem_code);
	}
	public ArrayList<PlannerJsonDTO> LikePlanScheduleList(String mem_code,int start,int end){
		ArrayList<PlannerJsonDTO> list = new ArrayList<>();
		PlannerJsonDTO pjdto = null;
		ArrayList<PlannerScheduleVO> plannerSchedule = new ArrayList<>();
		ArrayList<PlannerDateVO> plannerDate; plannerDate = new ArrayList<>();
		ArrayList<PlannerVO> plannerlist = new ArrayList<>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("mem_code", mem_code);
		parameters.put("start", start);
		parameters.put("end", end);
		System.out.println("#########Like plan schedule list#############"+mem_code+","+start+","+end);
		plannerlist = mapper.LikePlannerList(parameters);
		int size = plannerlist.size();
		if(size == 0) {
			return null;
		}else {
			for(int i = 0; i < size; i++) {	// theme filter는 위에서 걸림 유무 확인 가능
				pjdto = new PlannerJsonDTO();
				System.out.println("planner list "+ i +" : " + plannerlist.get(i));
				String plan_code = plannerlist.get(i).getPlan_code();
				plannerDate = mapper.plannerDateList(plan_code);
				plannerSchedule = mapper.plannerScheduleList(plan_code);
				pjdto.setPlanner(plannerlist.get(i));
				pjdto.setPlannerDate(plannerDate);
				pjdto.setPlannerSchedule(plannerSchedule);
				list.add(pjdto);
			}
		}
		return list;
	}
	public ArrayList<PlannerJsonDTO> PlanScheduleList(String mem_code,String page,int start,int end, String city, String date, String theme){
		ArrayList<PlannerJsonDTO> list = new ArrayList<>();
		PlannerJsonDTO pjdto = null;
		ArrayList<PlannerScheduleVO> plannerSchedule = new ArrayList<>();
		ArrayList<PlannerScheduleVO> tempSchedule = new ArrayList<>();
		ArrayList<PlannerDateVO> plannerDate; plannerDate = new ArrayList<>();
		ArrayList<PlannerVO> plannerlist;
		Map<String, Object> parameters = new HashMap<String, Object>();
		int day = -1;
		ArrayList<Integer> areaCodeList = null;
		parameters.put("mem_code", mem_code);
		parameters.put("start", start);
		parameters.put("end", end);
		System.out.println("%%%%%$$$$$$$$$$ :"+city+','+date+','+theme+','+page );
		if(page.equals("schedule")) {	//schedule 페이지에서 호출
			plannerlist = new ArrayList<>();
			plannerlist = mapper.plannerList(parameters);/**/
		}else {
			/************planner page filter func***********************/			
			plannerlist = new ArrayList<>();
			if(city.equals("none")==false) {
				areaCodeList = new ArrayList<>();
				if(city.equals("수도권")) {areaCodeList.add(1);areaCodeList.add(2);areaCodeList.add(31);}
				else if(city.equals("강원권")) {areaCodeList.add(32);}
				else if(city.equals("충청권")) {areaCodeList.add(33);areaCodeList.add(34);}
				else if(city.equals("전라권")) {areaCodeList.add(37);areaCodeList.add(38);areaCodeList.add(5);}
				else if(city.equals("경상권")) {areaCodeList.add(35);areaCodeList.add(36);areaCodeList.add(4);areaCodeList.add(6);areaCodeList.add(7);}
			}
			if(date.equals("none")==false) {
				if(date.equals("3일권"))	day = 3;
				else if(date.equals("5일권"))	day = 5;
				else {day = 7;}
			}
			if(theme.equals("none")==false) {
				parameters.put("theme",theme);
				plannerlist = mapper.otherPlannerListTheme(parameters);
			}else {
				plannerlist = mapper.otherPlannerList(parameters);
			}			
		}
		int size = plannerlist.size();
		if(size == 0) {
			return null;
		}else {
			for(int i = 0; i < size; i++) {	// theme filter는 위에서 걸림 유무 확인 가능
				pjdto = new PlannerJsonDTO();
				System.out.println("planner list "+ i +" : " + plannerlist.get(i));
				String plan_code = plannerlist.get(i).getPlan_code();
				if (day > 0) { // day 필터 걸렸을때
					if (areaCodeList != null && areaCodeList.size() > 0) { // day filter o , city filter o
						tempSchedule = mapper.plannerScheduleList(plan_code);
						boolean tmp = false;
						for (int j = 0; j < tempSchedule.size(); j++) {
							for (int k = 0; k < areaCodeList.size(); k++) {
								int code = Integer.parseInt(tempSchedule.get(j).getAreacode());
								if (areaCodeList.get(k) == code) {
									tmp = true;
								}
							}
						}
						if (tmp && mapper.plannerDateList(plan_code).size() == day) {
							plannerDate = mapper.plannerDateList(plan_code);
							plannerSchedule = mapper.plannerScheduleList(plan_code);
							pjdto.setPlanner(plannerlist.get(i));
							pjdto.setPlannerDate(plannerDate);
							pjdto.setPlannerSchedule(plannerSchedule);
							list.add(pjdto);
						}
					}else {
						if(mapper.plannerDateList(plan_code).size() == day) {
							System.out.println("day filter가 맞을 때 진입");
							plannerDate = mapper.plannerDateList(plan_code);
							plannerSchedule = mapper.plannerScheduleList(plan_code);
							pjdto.setPlanner(plannerlist.get(i));
							pjdto.setPlannerDate(plannerDate);
							pjdto.setPlannerSchedule(plannerSchedule);
							list.add(pjdto);
						}
					}
				}else { //day filter x
					if(areaCodeList != null && areaCodeList.size() > 0) {	// city filter o
						tempSchedule = mapper.plannerScheduleList(plan_code);
						boolean tmp = false;
						for (int j = 0; j < tempSchedule.size(); j++) {
							for (int k = 0; k < areaCodeList.size(); k++) {
								int code = Integer.parseInt(tempSchedule.get(j).getAreacode());
								if (areaCodeList.get(k) == code) {
									tmp = true;
								}
							}
						}
						if (tmp) {
							plannerDate = mapper.plannerDateList(plan_code);
							plannerSchedule = mapper.plannerScheduleList(plan_code);
							pjdto.setPlanner(plannerlist.get(i));
							pjdto.setPlannerDate(plannerDate);
							pjdto.setPlannerSchedule(plannerSchedule);
							list.add(pjdto);
						}
					}else {	//  day filter x, city filter x 
						plannerDate = mapper.plannerDateList(plan_code);
						plannerSchedule = mapper.plannerScheduleList(plan_code);
						pjdto.setPlanner(plannerlist.get(i));
						pjdto.setPlannerDate(plannerDate);
						pjdto.setPlannerSchedule(plannerSchedule);
						list.add(pjdto);
					}
				}
				//System.out.println("plannerDate : "+i+" : "+plannerDate);
				//System.out.println("plannerSchedule : "+i+" : "+plannerSchedule);
			}
		}
		return list;
	}
	public void insertPlannerBookmark(PlannerBookmarkVO vo) {
		bookMarkMapper.insertPlannerBookmark(vo);
	}
	public void deletePlannerBookmark(PlannerBookmarkVO vo) {
		bookMarkMapper.deletePlannerBookmark(vo);
	}
	public int getLikeOrNotPlannerBookmark(PlannerBookmarkVO vo) {
		return bookMarkMapper.getLikeOrNotPlannerBookmark(vo);
	}
}
