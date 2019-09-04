package com.railgo.service;

import java.util.ArrayList;

import com.railgo.domain.PlannerBookmarkVO;
import com.railgo.domain.PlannerDateVO;
import com.railgo.domain.PlannerJsonDTO;
import com.railgo.domain.PlannerScheduleVO;
import com.railgo.domain.PlannerVO;

public interface PlannerService {
	void createPlanner(PlannerJsonDTO dto);
	void updatePlanner(PlannerJsonDTO dto);
	Boolean deleteScheduleList(String plan_code);
	int getPostingCount(String mem_code);
	//좋아요한 일정 목록 출력 메소드
	ArrayList<PlannerJsonDTO> LikePlanScheduleList(String mem_code,int start,int end);
	//schedule 페이지에서 나의 스케쥴목록과 planner 페이지에서 다른 내일러들의 일정들을 보여주는 메소드
	ArrayList<PlannerJsonDTO> PlanScheduleList(String mem_code, String page, int start, int end, String city, String date, String theme);
	void insertPlannerBookmark(PlannerBookmarkVO vo);
	void deletePlannerBookmark(PlannerBookmarkVO vo);
	int getLikeOrNotPlannerBookmark(PlannerBookmarkVO vo);
}
