package com.qa.api.gorest.pojo;

import org.testng.annotations.Test;

import com.qa.api.gorest.util.TestUtil;

public class UserResult {

	//There is shortcut to ctreate java classes for complex json
	// this website https://www.jsonschema2pojo.org/ check use premitive,getter and setter and constructor
	
	@Test
	public void CreateUserWithFullJson() {
	Self sf = new Self("http//www.sf.com");
	Edit ed = new Edit("http//www.ed.com");
	Avatar av = new Avatar("http//www.av.com");
	
	Links ln = new Links(sf,ed,av);
	UserInfo uf = new UserInfo("Tom","Pk@gmail.com","male","active",ln);
	
	
	String UserJsonPayload=TestUtil.getSerializedJSON(uf);
	
	System.out.println(UserJsonPayload);
	}
	
	
}
