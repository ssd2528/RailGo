package com.railgo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommVO {
	private int comm_code; // ?Œ“ê¸?ì½”ë“œ
	private String code; // ?ˆ™/?‹/ê´?/ê³?/?–‰/SNS ?†µ?•©ì½”ë“œ
	private int mem_code; // ?šŒ?›ì½”ë“œ
	private int origin_code; // ???Œ“ê¸??˜ ?› ?šŒ?›ì½”ë“œ
	private Date regDate; // ?“±ë¡ë‚ ì§?
}
