package com.qa.api.gorest.restclient;


import java.util.Map;

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
 
	
public static Response doGet(String contentType,String baseURI, String basePath,String token,Map<String, String>paramsMap,boolean log) {
	
	setBaseURI(baseURI);
	RequestSpecification request=createRequest(contentType,token,paramsMap,log);
	return getResponse("GET", request, basePath);
	
		
}
	
private static void setBaseURI(String baseURI) {

	RestAssured.baseURI = baseURI;
	
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
	request.header("Authorization","Bearer"+token);
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
