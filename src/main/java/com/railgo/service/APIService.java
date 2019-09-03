package com.railgo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface APIService {
	String findAreaCode(String areaCodeStr);
	String getResponseStr(String url) throws Exception;
	JsonObject getItemsObject(String responseStr);
	int getContentId(JsonObject itemObject);
	JsonArray makeItemsArray(JsonObject itemsObject);
	int getTotalCount(String responseStr);
	String getOverview(JsonObject itemsObject);
	
	
	String findAreaName(int areaCode, int sigunguCode);
}
