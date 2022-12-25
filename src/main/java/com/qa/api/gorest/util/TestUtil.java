package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	
	
	
	
	
/**
 * this method is use to convert POJO object to a JSON String	
 * @param obj
 * @return
 */
public static String getSerializedJSON(Object obj) {
	
	ObjectMapper mapper = new ObjectMapper();
	String jsonString =null;
	
	
	try {
		jsonString= mapper.writeValueAsString(obj);
		System.out.println("JSON body Payload =====> "+jsonString);
	} catch (JsonProcessingException e) {
		
		e.printStackTrace();
	}
	
return jsonString;	
	
}
	
	
	
}
