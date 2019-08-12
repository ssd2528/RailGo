package com.railgo.oauth;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.servlet.http.HttpSession;

//import org.apache.commons.lang.StringUtils;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class NaverLoginBO {

	// 인증 요청문을 구성하는 파라미터
	private final static String CLIENT_ID = "s1r4t43eDeeuvIG04q1M";
	private final static String CLIENT_SECRET = "px9wPSYaIN";
	private final static String REDIRECT_URI = "http://localhost:8080/naverSignin";
	private final static String SESSION_STATE = "oauth_state";
	
	// 프로필 조회 API URL
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	
	public String generateRandomState() { // 세션 유효성 검증을 위한 난수생성기 
	    return UUID.randomUUID().toString();
	}
	
	private void setSession(HttpSession session, String state){ // Http Session에 데이터 저장
		session.setAttribute(SESSION_STATE, state);		
	}

	private String getSession(HttpSession session){ // Http Session에서 데이터 가져오기
		return (String) session.getAttribute(SESSION_STATE);
	}

	/* 네이버 아이디로 인증 URL 생성 메소드 */
	public String getAuthorizationUrl(HttpSession session) {
		String state = generateRandomState(); // 세션 유효성 검증을 위한 난수 생성
		setSession(session, state); // 생성한 난수 값을 session에 저장
		
		// Scribe에서 제공하는 인증 URL 생성기능을 이용한 네아로 인증 URL 생성
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI)
				.state(state) // 앞서 생성한 난수값을 인증 URL 생성 시 사용
				.build(NaverLoginApi.instance());

		return oauthService.getAuthorizationUrl();
	}
	
	/* 네이버아이디로 Callback처리 및 AccessToken 획득 메소드 */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
		
		// Callback으로 전달받은 세션검증용 난수값과 세션에 저장되어 있는 값이 일치하는 지 확인
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)){
		
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI)
					.state(state)
					.build(NaverLoginApi.instance());
					
			// Scribe에서 제공하는 AccessToken 획득기능으로 네아로 Access Token 획득
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}
	
	/* Access Token을 이용해 네이버 사용자 프로필 API 호출 */
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{
		OAuth20Service oauthService = new ServiceBuilder()
    			.apiKey(CLIENT_ID)
    			.apiSecret(CLIENT_SECRET)
    			.callback(REDIRECT_URI).build(NaverLoginApi.instance());
    	
    	OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
}
