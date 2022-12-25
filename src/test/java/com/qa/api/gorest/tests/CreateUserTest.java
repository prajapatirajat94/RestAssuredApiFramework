package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.Restclient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	String baseURI ="https://gorest.co.in";	
	String basePath="/public/v2/users";
	String token="7ed0d7f31bc21caed71d3b3ece46fa2dd5a94208ef6215d5eec2b913c71a85a4";


	
@DataProvider
public Object[][] getUserData() {
	 Object userdata [][]=ExcelUtil.getTestData("userdata");
	 return userdata;
}
	
	
	
@Test(dataProvider="getUserData")
public void createUserAPIPOSTTest(String name,String email,String gender, String status) {
	User user = new User(name,email,gender,status);
	
	Response response =Restclient.doPost("JSON", baseURI, basePath, token, null, true, user);
	System.out.println(response.prettyPrint());
	Assert.assertEquals(response.getStatusCode(), 201);
	System.out.println("<========================================================>");
 
	
	
}
	
	
	
	 
	
	
	
	
	
	
	
}
