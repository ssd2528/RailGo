package com.railgo.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

// 사용자 토큰 받기 (code를 얻어, 그 code를 이용하여 사용자 토큰을 받는 클래스
public class KakaoAccessToken {
	public static JsonNode getKakaoAccessToken(String code) {
		 
        final String requestUrl = "https://kauth.kakao.com/oauth/token"; // Host
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
 
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("client_id", "96efae73b25f4b5afa4875a7d4a8839f")); // REST API KEY
        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/kakaoSignin")); // 리다이렉트 URI
        params.add(new BasicNameValuePair("code", code)); // 로그인 과정중 얻은 code 값
 
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(requestUrl);
 
        JsonNode returnNode = null;
 
        try {
            post.setEntity(new UrlEncodedFormEntity(params));
           
            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("\n## Sending 'POST' request to URL : " + requestUrl);
            System.out.println("## Post parameters : " + params);
            System.out.println("## Response Code : " + responseCode);
            
            ObjectMapper mapper = new ObjectMapper(); // JSON 형태 반환값 처리
 
            returnNode = mapper.readTree(response.getEntity().getContent());
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        } catch (ClientProtocolException cp) {
            cp.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
 
        return returnNode;
    }
}
