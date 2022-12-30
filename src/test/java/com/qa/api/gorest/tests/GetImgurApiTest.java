package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.Restclient;
import com.qa.api.gorest.util.Tokens;

import io.restassured.response.Response;

public class GetImgurApiTest {

	
Map<Object,Object> tokenmap;	
	String accesstoken;
	String accountUsername;
	
	
@BeforeMethod
public void setup() {
	tokenmap=	Tokens.getAccessToken();
	 
	accesstoken=tokenmap.get("access_token").toString();
	accountUsername=tokenmap.get("account_username").toString();
	
	
	
}
	
	
@Test
public void GetAccountBlockedStatusTest() {
	
	Map<String,String>authTokenMap=Tokens.getAuthenticationToken();
	Response response=Restclient.doGet(null, "https://api.imgur.com", "/account/v1/prajapatirajat94/block", authTokenMap, null, true);
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
	
	System.out.println(response.jsonPath().getString("data.blocked"));
}
	

@Test
public void GetAccountImageTest() {
	Map<String,String>authTokenMap=Tokens.getAuthenticationToken();
	Response response=Restclient.doGet(null, "https://api.imgur.com", "/3/account/me/images", authTokenMap, null, true);
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
	
	System.out.println(response.jsonPath().getString("data.id[0]"));
}
	

@Test
public void uploadImagePostAPITest() {
	
	Map<String,String>ClientIdMap=Tokens.getClientID();
	
	Map<String,String>formMap= new HashMap<String,String>();
	formMap.put("title", "Test title api");
	formMap.put("description", "Test Description api");
	
	
	Response response =Restclient.doPost("multipart", "https://api.imgur.com", "/3/upload", ClientIdMap, null, true, formMap);
	
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
	
	
	
	
}

















	
	
	
	
}
