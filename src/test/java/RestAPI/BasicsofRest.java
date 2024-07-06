package RestAPI;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.Assert;

import org.testng.annotations.Test;


import dataProviderUtility.dataProviderUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import reporter.ExtentManager;



public class BasicsofRest {
	
	
	
	@Test (invocationCount = 10)
	public void Verifying_ROI() {
		
		Response response =	RestAssured.get("https://reqres.in/api/users?page=2");
		ExtentManager.logJson(response.prettyPrint());
		ExtentManager.logHeaders(response.getHeaders().asList());
		ExtentManager.logPassDetails("Actual Status Code is :- "+response.getStatusCode());
		
		ExtentManager.logInfoDetails("Time is "+response.getTime());
		ExtentManager.logInfoDetails("Header is "+response.getHeader("content-type"));
		System.out.println("Total res "+response);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		System.out.println(response.getTime());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statuscode = response.getStatusCode();
	
		Assert.assertEquals(statuscode, 201);
	//ExtentManager.logPassDetails("Test Case Pass");
	//ExtentManager.logFailDetails("Failed");
		
	}
	
	

	@Test
	
	public void Test_Get() {
		
		baseURI = "https://reqres.in/api";
		ExtentManager.logInfoDetails("Time is ");
		ExtentManager.logPassDetails("XYZ"+given().get("/users?page=2").then().statusCode(200));
		
		//ExtentManager.logInfoDetails(given().get("/users?page=2").then().statusCode(200));
		ExtentManager.logPassDetails("Use ID has been matched  :-"+given().get("/users?page=2").then().statusCode(200).body("data[1].id", equalTo(8)));
		given().get("/users?page=2")
		.then().statusCode(200).body("data[1].email", equalTo("lindsay.ferguson@reqres.in"));
		given().get("/users?page=2").then().statusCode(200)
		.body("data[1].email", equalTo("lindsay.ferguson@reqres.in")).log().all();
	}
	
	@Test

	public void Test_Post() {
		
	System.out.println("POST start here=====================================");
		
	JSONObject reqjson = new JSONObject();
	ExtentManager.logInfoDetails("Data has been saved :-"+reqjson.put("name", "morpheus"));
	reqjson.put("job", "leader");
	System.out.println(reqjson.toJSONString());
	
	baseURI = "https://reqres.in/api";
	
	given().body(reqjson.toJSONString()).when().post("/users").then().statusCode(200);
	
	
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
