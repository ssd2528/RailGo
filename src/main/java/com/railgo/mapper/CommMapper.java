package com.railgo.mapper;

import java.util.ArrayList;

import com.railgo.domain.CommJoinDTO;
import com.railgo.domain.CommVO;

public interface CommMapper {
	ArrayList<CommJoinDTO> getCommList(String sns_code);
	void commRegister(CommVO vo);
	String commDelete(int comm_code);
}
