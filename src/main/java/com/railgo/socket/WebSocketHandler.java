package com.railgo.socket;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.railgo.domain.MemberVO;

@Repository
public class WebSocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>(); // 웹 소켓 세션을 저장할 리스트 생성 
	
	@Override // 웹 소켓 클라이언트가 연결되면 호출
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		Map<String, Object> map = session.getAttributes();
        MemberVO member = (MemberVO)map.get("member");
        System.out.println("## "+ member.getName() + " 접속");
        System.out.println("## map : " + map + "/ \nmember : " + member);
	}
	@Override // 웹 소켓 클라이언트가 데이터를 전송하면 호출
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
        MemberVO member = (MemberVO)map.get("member");
		System.out.println("## " + member.getName() + " 로 부터 '" + message.getPayload() + "' 받음");
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage("메시지 : " + message.getPayload()));
		}
	}	
	@Override // 웹 소켓 클라이언트가 연결을 끊거나, 서버에서 타임아웃을 발생해 연결을 끊을 때 호출
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		Map<String, Object> map = session.getAttributes();
        MemberVO member = (MemberVO)map.get("member");
		System.out.println("## " + member.getName() + " 연결 끊김");
	}
	
	@Override // 웹 소켓 클라이언트와의 연결에 문제가 발생하면 호출
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		Map<String, Object> map = session.getAttributes();
        MemberVO member = (MemberVO)map.get("member");
		System.out.println("## " + member.getName() + " 에러 발생 : " + exception.getMessage());
	}

}
