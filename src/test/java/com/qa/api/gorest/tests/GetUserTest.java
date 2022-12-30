package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.Restclient;

import io.restassured.response.Response;

public class GetUserTest {

String baseURI ="https://gorest.co.in";	
String basePath="/public/v2/users";
String token="7ed0d7f31bc21caed71d3b3ece46fa2dd5a94208ef6215d5eec2b913c71a85a4";


@Test(priority = 1)
public void getAllUserListApiTest() {
	Map<String,String>authTokenMap=new HashMap<String,String>();
	authTokenMap.put("Authorization", "Bearer "+token);
	Response response =Restclient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
	System.out.println(response.prettyPrint());
	Assert.assertEquals(response.getStatusCode(), 200);
	Assert.assertEquals(response.header("Server"),"cloudflare");
	
}


@Test(priority = 2)
public void getUserWithQueryParamApiTest() {
	
	Map<String,String>params = new HashMap<String,String>();
	params.put("name","Kamalesh Sethi");
	params.put("gender","female");
	params.put("status","active");
	Map<String,String>authTokenMap=new HashMap<String,String>();
	authTokenMap.put("Authorization", "Bearer "+token);
	
	Response response =Restclient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
	System.out.println(response.prettyPrint());
	Assert.assertEquals(response.getStatusCode(), 200);
	Assert.assertEquals(response.header("Server"),"cloudflare");
	
}






















}
