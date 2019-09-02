package com.railgo.mapper;

import java.util.ArrayList;

import com.railgo.domain.CommJoinDTO;
import com.railgo.domain.CommVO;

public interface CommMapper {
	ArrayList<CommJoinDTO> getCommList(String sns_code); // 댓글 리스트
	void commRegister(CommVO vo); // 댓글 작성
	void commDelete(int comm_code); // 댓글 삭제
	int commCount(String sns_code); // 댓글 갯수확인
	CommJoinDTO commCheck(); // 댓글 입력후 ajax 뿌려주기
	
	ArrayList<CommJoinDTO> getRereList(int comm_code); // 대댓글 리스트
}
