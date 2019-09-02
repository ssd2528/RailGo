package com.railgo.socket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
  
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.railgo.domain.MemberVO;
  
// 웹 소켓 핸들러 클래스가 호출되기 전에 HttpSession에 접속해서 '이용자 아이디를 추출하는 기능' 등을 인터셉터가 수행
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	@Override
    public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {    
    
        // 위의 파라미터 중, attributes 에 값을 저장하면 웹소켓 핸들러 클래스의 WebSocketSession에 전달된다
        System.out.println("Before Handshake");
          
          
        ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
        System.out.println("URI:"+request.getURI());
  
        HttpServletRequest req =  ssreq.getServletRequest();
 
        /*String userId = req.getParameter("userid");
        System.out.println("param, id:"+userId);
        attributes.put("userId", userId);*/
  
        // HttpSession 에 저장된 이용자의 아이디를 추출하는 경우
        MemberVO member = (MemberVO)req.getSession().getAttribute("member");
        if(member==null) return false;
        attributes.put("member", member);
        System.out.println("HttpSession에 저장된 member:"+member);
         
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
	
	
	@Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("After Handshake");
  
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
