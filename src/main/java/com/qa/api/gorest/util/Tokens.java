package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath; 


public class Tokens {
public static Map<Object,Object>appTokenMap;
public static Map<String,String>tokenMap = new HashMap<String,String>();
public static String ClientId="bdfef232990deba";



public static Map<Object, Object> getAccessToken() {
	
	Map<String,String>formsparams = new HashMap<String,String>();
	formsparams.put("refresh_token", "c6656bb8baa5df7b4083a997f2fa9f04caa3b2b7");
	formsparams.put("client_id", "bdfef232990deba");
	formsparams.put("client_secret", "1e057efc3784b9367ce2b02e16f8098e1b2585a4");
	formsparams.put("grant_type", "refresh_token");
	
	
	
	JsonPath tokenjson =given()
	.formParams(formsparams)
	.when().post("https://api.imgur.com/oauth2/token") 
	.then().extract().jsonPath(); // Here we are using extract().jsonPath()
	//whic will return one jsonPath object and then use .getMap("") method to store 
	//json in to format of MAP collection
	
	System.out.println(tokenjson.getMap(""));
	appTokenMap= tokenjson.getMap("");
	
	return appTokenMap;
	
}	
public static Map<String, String> getAuthenticationToken() {
	
	String authToken =appTokenMap.get("access_token").toString();
	System.out.println("Auth Token ======> "+authToken);
	tokenMap.put("Authorization", "Bearer "+authToken);
	return tokenMap;
}
	
public static Map<String, String> getClientID() {
	
	System.out.println("Client id is  ======> "+ClientId);
	tokenMap.put("Authorization", "Client-ID "+ClientId);
	return tokenMap;
}
	
	
	
	
	
	
	
}
