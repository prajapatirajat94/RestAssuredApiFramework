package com.qa.api.gorest.restclient;


import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all http method whic will call api and having generic methods for getting
 * the response and fetch value from response
 * @author Rajat Prajapati
 *
 */
public class Restclient {

//HTTP Methods:- GET, POST, PUT, DELETE 	
 
/**
 * 	This method is used to call GET api
 * @param contentType
 * @param baseURI
 * @param basePath
 * @param token
 * @param paramsMap
 * @param log
 * @return this method is returning response from the GET call
 */
public static Response doGet(String contentType,String baseURI, String basePath,String token,Map<String, String>paramsMap,boolean log) {
	
	if(setBaseURI(baseURI)) {
	RequestSpecification request=createRequest(contentType,token,paramsMap,log);
	return getResponse("GET", request, basePath);
	}
	return null;
}


/**
 * This method is used to call POST api
 * @param contentType
 * @param baseURI
 * @param basePath
 * @param token
 * @param paramsMap
 * @param log
 * @param obj
 * @return this method is returning response from the POST call
 */
public static Response doPost(String contentType,String baseURI, String basePath,String token,Map<String, String>paramsMap,boolean log, Object obj) {
	
	if(setBaseURI(baseURI)) {
	RequestSpecification request=createRequest(contentType,token,paramsMap,log);
	addRequestPayLoad(request, obj);
	return getResponse("POST", request, basePath);
	}
	return null;
}


public static void addRequestPayLoad(RequestSpecification request,Object obj) {
	String jsonPayload =TestUtil.getSerializedJSON(obj);
	request.body(jsonPayload);
	
}





	
private static boolean setBaseURI(String baseURI) {
	
if(baseURI==null || baseURI.isEmpty()) {
	System.out.println("Please pass the correct Base URI...");
	return false;
}
try {
	RestAssured.baseURI = baseURI;
	return true;
}catch(Exception e) {
	System.out.println("some exception got occured while assiging the base URI with rest assured....");
	return false;
}
}
	
	
private static RequestSpecification createRequest(String contentType,String token,Map<String, String>paramsMap,boolean log) {
RequestSpecification request;	
	
if(log) {
	request=RestAssured.given().log().all();
}
else {
	request=RestAssured.given();
}


if(token!=null) {
	request.header("Authorization","Bearer "+token);
}

if(!(paramsMap==null)) {
request.queryParams(paramsMap);

}


if(contentType.equalsIgnoreCase("JSON")) {
	request.contentType(ContentType.JSON);
}
else if(contentType.equalsIgnoreCase("XML")) {
	request.contentType(ContentType.XML);
}
else if(contentType.equalsIgnoreCase("TEXT")){
	request.contentType(ContentType.TEXT);
}



return request;

}	
	
private static Response getResponse(String httpMethod,RequestSpecification request,String basePath) {
	 
return	executeAPI(httpMethod, request, basePath);
	
}

private static Response executeAPI(String httpMethod,RequestSpecification request,String basePath) {
Response response=null;
	switch (httpMethod) {
	case "GET":
		response=request.get(basePath);
		break;
	case "POST":
		response=request.post(basePath);
		break;
	case "PUT":
		response=request.put(basePath);
		break;	
	case "DELETE":
		response=request.delete(basePath);
		break;	
	default:
		System.out.println("Plase pass correct http method");
		break;
	}
	return response;
}
	
	
	
	
	
	
}
