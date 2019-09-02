package com.railgo.domain;

import lombok.Data;

@Data
public class InfoItemDTO {
	private String addr1;
	private String addr2;
	private int areacode;
	private String cat1;
	private String cat2;
	private String cat3;
	private int contentid;
	private int contenttypeid;
	private long createdtime;
	private String firstimage;
	private String firstimage2;
	private String mapx;
	private String mapy;
	private int mlevel;
	private long modifiedtime;
	private long readcount;
	private int sigungucode;
	private String tel;
	private String title;
	private String zipcode;
	private String overview;
	
	private String courseSubNames; // 가공된 코스 목록 
}
