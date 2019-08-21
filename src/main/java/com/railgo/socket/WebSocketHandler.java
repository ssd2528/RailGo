package com.railgo.socket;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Repository
public class WebSocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>(); // 웹 소켓 세션을 저장할 리스트 생성 
	
	@Override // WebSocket 연결이 되고 사용준비될 때 호출  (접속)
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		System.out.println("## " + session.getId() + " 접속");
	}
	@Override // WebSocket 서버단으로 메시지가 도착했을 때 해주어야 할 일들을 정의하는 메소드 (메시지 전송)
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

	}	
	@Override // 웹 소켓 연결이 종료되고 나서 서버단에서 실행해야 할 일들을 정의해주는 메소드 (연결끊김)
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
	}

}
