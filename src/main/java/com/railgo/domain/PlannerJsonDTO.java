package com.railgo.domain;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PlannerJsonDTO {
	private PlannerVO planner;
	private ArrayList<PlannerDateVO> plannerDate;
	private ArrayList<PlannerScheduleVO> plannerSchedule;
}
