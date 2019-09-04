package com.railgo.mapper;

import com.railgo.domain.PlannerBookmarkVO;

public interface PlannerBookmarkMapper {
	void insertPlannerBookmark(PlannerBookmarkVO vo);
	void deletePlannerBookmark(PlannerBookmarkVO vo);
	int getLikeOrNotPlannerBookmark(PlannerBookmarkVO vo);
}
