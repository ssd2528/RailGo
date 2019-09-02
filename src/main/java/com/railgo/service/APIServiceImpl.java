package com.railgo.service;

import java.net.URI;
import java.nio.charset.Charset;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class APIServiceImpl implements APIService {
	
	@Override
	public String findAreaCode(String areaCodeStr) {
		String result="";
		
		if(areaCodeStr.equals("서울")) result="&areaCode=1";
		else if(areaCodeStr.equals("인천")) result="&areaCode=2";
		else if(areaCodeStr.equals("대전")) result="&areaCode=3";
		else if(areaCodeStr.equals("대구")) result="&areaCode=4";
		else if(areaCodeStr.equals("광주")) result="&areaCode=5";
		else if(areaCodeStr.equals("부산")) result="&areaCode=6";
		else if(areaCodeStr.equals("울산")) result="&areaCode=7";
		// 경기도
		else if(areaCodeStr.equals("가평")) result="&areaCode=31&sigunguCode=1";
		else if(areaCodeStr.equals("고양")) result="&areaCode=31&sigunguCode=2";
		else if(areaCodeStr.equals("광명")) result="&areaCode=31&sigunguCode=4";
		else if(areaCodeStr.equals("동두천")) result="&areaCode=31&sigunguCode=10";
		else if(areaCodeStr.equals("수원")) result="&areaCode=31&sigunguCode=13";
		else if(areaCodeStr.equals("안양")) result="&areaCode=31&sigunguCode=17";
		else if(areaCodeStr.equals("양평")) result="&areaCode=31&sigunguCode=19";
		else if(areaCodeStr.equals("평택")) result="&areaCode=31&sigunguCode=28";
		else if(areaCodeStr.equals("화성")) result="&areaCode=31&sigunguCode=31";
		// 강원도
		else if(areaCodeStr.equals("강릉")) result="&areaCode=32&sigunguCode=1";
		else if(areaCodeStr.equals("동해")) result="&areaCode=32&sigunguCode=3";
		else if(areaCodeStr.equals("영월")) result="&areaCode=32&sigunguCode=8";
		else if(areaCodeStr.equals("정선")) result="&areaCode=32&sigunguCode=11";
		else if(areaCodeStr.equals("춘천")) result="&areaCode=32&sigunguCode=13";
		else if(areaCodeStr.equals("태백")) result="&areaCode=32&sigunguCode=14";
		else if(areaCodeStr.equals("평창")) result="&areaCode=32&sigunguCode=15";
		// 충청권
		else if(areaCodeStr.equals("단양")) result="&areaCode=33&sigunguCode=2";
		else if(areaCodeStr.equals("대천")) result="&areaCode=34&sigunguCode=5";
		else if(areaCodeStr.equals("영동")) result="&areaCode=33&sigunguCode=4";
		else if(areaCodeStr.equals("제천")) result="&areaCode=33&sigunguCode=7";
		else if(areaCodeStr.equals("조치원")) result="&areaCode=34&sigunguCode=11";
		else if(areaCodeStr.equals("천안")) result="&areaCode=34&sigunguCode=12";
		else if(areaCodeStr.equals("천안아산")) result="&areaCode=34&sigunguCode=9";
		else if(areaCodeStr.equals("충주")) result="&areaCode=33&sigunguCode=11";
		else if(areaCodeStr.equals("홍성")) result="&areaCode=34&sigunguCode=15";
		// 전라권
		else if(areaCodeStr.equals("곡성")) result="&areaCode=38&sigunguCode=3";
		else if(areaCodeStr.equals("구례구")) result="&areaCode=38&sigunguCode=11";
		else if(areaCodeStr.equals("나주")) result="&areaCode=38&sigunguCode=6";
		else if(areaCodeStr.equals("남원")) result="&areaCode=37&sigunguCode=4";
		else if(areaCodeStr.equals("목포")) result="&areaCode=38&sigunguCode=8";
		else if(areaCodeStr.equals("순천")) result="&areaCode=38&sigunguCode=11";
		else if(areaCodeStr.equals("여수엑스포")) result="&areaCode=38&sigunguCode=13";
		else if(areaCodeStr.equals("익산")) result="&areaCode=37&sigunguCode=9";
		else if(areaCodeStr.equals("전주")) result="&areaCode=37&sigunguCode=12";
		else if(areaCodeStr.equals("정읍")) result="&areaCode=37&sigunguCode=13";
		// 경상권
		else if(areaCodeStr.equals("경주")) result="&areaCode=35&sigunguCode=2";
		else if(areaCodeStr.equals("구미")) result="&areaCode=35&sigunguCode=4";
		else if(areaCodeStr.equals("김천구미")) result="&areaCode=35&sigunguCode=6";
		else if(areaCodeStr.equals("마산")) result="&areaCode=36&sigunguCode=6";
		else if(areaCodeStr.equals("신경주")) result="&areaCode=35&sigunguCode=2";
		else if(areaCodeStr.equals("안동")) result="&areaCode=35&sigunguCode=11";
		else if(areaCodeStr.equals("영주")) result="&areaCode=35&sigunguCode=14";
		else if(areaCodeStr.equals("영천")) result="&areaCode=35&sigunguCode=15";
		else if(areaCodeStr.equals("점촌")) result="&areaCode=35&sigunguCode=7";
		else if(areaCodeStr.equals("진주")) result="&areaCode=36&sigunguCode=13";
		else if(areaCodeStr.equals("춘양")) result="&areaCode=35&sigunguCode=8";
		else if(areaCodeStr.equals("포항")) result="&areaCode=35&sigunguCode=23";
		
		else result="없음";
		
		return result;
	}
	
	
	@Override
	public String findAreaName(int areaCode, int sigunguCode) {
		String areaName = "";
		
		if(areaCode==1) areaName="서울";
		else if(areaCode==2) areaName="인천";
		else if(areaCode==3) areaName="대전";
		else if(areaCode==4) areaName="대구";
		else if(areaCode==5) areaName="광주";
		else if(areaCode==6) areaName="부산";
		else if(areaCode==7) areaName="울산";
		//경기도
		else if(areaCode==31) areaName="경기도";
		else if(areaCode==31 && sigunguCode==1) areaName="가평";
		else if(areaCode==31 && sigunguCode==2) areaName="고양";
		else if(areaCode==31 && sigunguCode==4) areaName="광명";
		else if(areaCode==31 && sigunguCode==10) areaName="동두천";
		else if(areaCode==31 && sigunguCode==13) areaName="수원";
		else if(areaCode==31 && sigunguCode==17) areaName="안양";
		else if(areaCode==31 && sigunguCode==19) areaName="양평";
		else if(areaCode==31 && sigunguCode==28) areaName="평택";
		else if(areaCode==31 && sigunguCode==31) areaName="화성";
		//강원도
		else if(areaCode==32) areaName="강원도";
		else if(areaCode==32 && sigunguCode==1) areaName="강릉";
		else if(areaCode==32 && sigunguCode==3) areaName="동해";
		else if(areaCode==32 && sigunguCode==8) areaName="영월";
		else if(areaCode==32 && sigunguCode==11) areaName="정선";
		else if(areaCode==32 && sigunguCode==13) areaName="춘천";
		else if(areaCode==32 && sigunguCode==14) areaName="태백";
		else if(areaCode==32 && sigunguCode==15) areaName="평창";
		//충청북도
		else if(areaCode==33) areaName="충청북도";
		else if(areaCode==33 && sigunguCode==2) areaName="단양";
		else if(areaCode==33 && sigunguCode==4) areaName="영동";
		else if(areaCode==33 && sigunguCode==7) areaName="제천";
		else if(areaCode==33 && sigunguCode==11) areaName="충주";
		//충청남도
		else if(areaCode==34) areaName="충청남도";
		else if(areaCode==34 && sigunguCode==5) areaName="대천";
		else if(areaCode==34 && sigunguCode==11) areaName="조치원";
		else if(areaCode==34 && sigunguCode==12) areaName="천안";
		else if(areaCode==34 && sigunguCode==9) areaName="천안아산";
		else if(areaCode==34 && sigunguCode==15) areaName="홍성";
		//경상북도
		else if(areaCode==35) areaName="경상북도";
		else if(areaCode==35 && sigunguCode==2) areaName="경주";
		else if(areaCode==35 && sigunguCode==4) areaName="구미";
		else if(areaCode==35 && sigunguCode==6) areaName="김천구미";
		else if(areaCode==35 && sigunguCode==11) areaName="안동";
		else if(areaCode==35 && sigunguCode==14) areaName="영주";
		else if(areaCode==35 && sigunguCode==15) areaName="영천";
		else if(areaCode==35 && sigunguCode==7) areaName="점촌";
		else if(areaCode==35 && sigunguCode==8) areaName="춘양";
		else if(areaCode==35 && sigunguCode==23) areaName="포항";
		// 경상남도
		else if(areaCode==36) areaName="경상남도";
		else if(areaCode==36 && sigunguCode==6) areaName="마산";
		else if(areaCode==36 && sigunguCode==13) areaName="진주";
		// 전라북도
		else if(areaCode==37) areaName="전라북도";
		else if(areaCode==37 && sigunguCode==4) areaName="남원";
		else if(areaCode==37 && sigunguCode==9) areaName="익산";
		else if(areaCode==37 && sigunguCode==12) areaName="전주";
		else if(areaCode==37 && sigunguCode==13) areaName="정읍";
		// 전라남도
		else if(areaCode==38) areaName="전라남도";
		else if(areaCode==38 && sigunguCode==3) areaName="곡성";
		else if(areaCode==38 && sigunguCode==11) areaName="순천";
		else if(areaCode==38 && sigunguCode==6) areaName="나주";
		else if(areaCode==38 && sigunguCode==8) areaName="목포";
		else if(areaCode==38 && sigunguCode==13) areaName="여수엑스포";
		else areaName="정보없음";
		
		return areaName;
	}
	

	@Override 
	public String getResponseStr(String url) throws Exception {
		URI uri = new URI(url); 
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String responseStr = restTemplate.getForObject(uri, String.class); 
		//System.out.println("\n## responseStr : " + responseStr);
		return responseStr;
	}
	
	
	@Override
	public JsonObject getItemsObject(String responseStr) { // JsonParser를 이용해 필요한 JsonObject(item)를 추출해서 다시 JsonArray로 가공
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(responseStr);
		JsonObject itemsObject = (JsonObject)((JsonObject)((JsonObject)jsonObject.get("response")).get("body")).get("items");
		System.out.println("## itemsObject : " + itemsObject);
		return itemsObject;
	}
	
	@Override
	public int getContentId(JsonObject itemObject) {
		int contentId = itemObject.get("contentid").getAsInt();
		//System.out.println("## contentId : " + contentId);
		return contentId;
	}
	
	
	@Override
	public JsonArray makeItemsArray(JsonObject itemsObject) {
		JsonArray itemsArray = null;
		if(itemsObject.get("item").isJsonObject()) { // 배열이 아닌  {}로 둘러쌓여 있는 경우 
			String itemsObjectStr = "[".concat(itemsObject.get("item").toString().concat("]"));
			//System.out.println("## itemsObjectStr : " + itemsObjectStr);
			JsonParser jsonParser = new JsonParser();
			itemsArray = (JsonArray) jsonParser.parse(itemsObjectStr);
			//System.out.println("## 가공된 itemsArray : " + itemsArray);
		}else {
			itemsArray = (JsonArray) itemsObject.get("item"); 
		}
		return itemsArray;
	}

	@Override
	public int getTotalCount(String responseStr) {
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(responseStr);
		JsonObject bodyObject = (JsonObject)((JsonObject)jsonObject.get("response")).get("body");
		//System.out.println("## bodyObject : " + bodyObject);	
		int totalCount = bodyObject.get("totalCount").getAsInt(); 
		//System.out.println("## categoryCount : " + totalCount);
		return totalCount;
	}
	
	@Override
	public String getOverview(JsonObject itemsObject) {
		String overview = ((JsonObject)itemsObject.get("item")).get("overview").getAsString();
		//System.out.println("## overview : " + overview);
		return overview;
	}

}
