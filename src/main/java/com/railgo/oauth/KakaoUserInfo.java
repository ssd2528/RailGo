package com.railgo.oauth;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

// 사용자 정보요청 클래스 : 토큰을 얻어 사용자 정보를 요청해 가져온다. 
public class KakaoUserInfo {
	public static JsonNode getKakaoUserInfo(JsonNode accessToken) {
		 
        final String requestUrl = "https://kapi.kakao.com/v2/user/me";
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(requestUrl);
 
        post.addHeader("Authorization", "Bearer " + accessToken); // add header
 
        JsonNode returnNode = null;
 
        try {
            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();
            
            System.out.println("\n## Sending 'POST' request to URL : " + requestUrl);
            System.out.println("## Response Code : " + responseCode);
 
            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
 
        } catch (ClientProtocolException cp) {
            cp.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
 
        return returnNode;
    }
}