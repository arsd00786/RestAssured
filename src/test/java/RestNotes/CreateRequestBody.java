package RestNotes;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.checkerframework.checker.units.qual.K;
import org.json.JSONObject;
import org.testng.annotations.Test;


import io.opentelemetry.sdk.logs.data.Body;
import io.restassured.response.Response;

public class CreateRequestBody {
	
	
//	How many ways we can create request body in restassured
//	1) Hashmap
//	2) Using org.json
//	3) Using POJO(Plain Old Java Object)
//	4) Using externl json file

//  To start out own API we have go to folder where we have stored it and open CMD and write json-server student.json command.
// To enable json server we have to install node npm install -g json-server
	
	
	//  Create Request body using Hashmap
	
	//@Test(priority = 1)
	public void createRequestHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Prafull Kalle");
		data.put("location", "Amrawati");
		data.put("phone", "123456789");
		
		String courseArray[] = {"Protractor", "RestAssured"};
		data.put("courses", courseArray);
		
		given()
				.contentType("application/json")
				.body(data)
			
		.when()
				.post("http://localhost:3000/student")
		
		.then()
				.statusCode(201)
				.body("name", equalTo("Prafull Kalle"))
				.body("location", equalTo("Amrawati"))
				.body("courses[0]", equalTo("Protractor"))
				//.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
		
	}
	
	
//  Create Request body using ORG.Json Library
	@Test
	public void createRequestORGJSONLibrary() {
		

		JSONObject data = new JSONObject();
		
		data.put("name", "Rehmat Naaz");
		data.put("location", "Ahmedabad");
		data.put("phone", "123456789");
		
		String courseArray[] = {"Physics", "Math"};
		data.put("courses", courseArray);

		
		given()
				.contentType("application/json")
				.body(data.toString())
			
		.when()
				.post("http://localhost:3000/student")
		
		.then()
				.statusCode(201)
				.body("name", equalTo("Rehmat Naaz"))
				.body("location", equalTo("Ahmedabad"))
				.body("courses[0]", equalTo("Physics"))
				//.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
		
	}
	
	@Test(priority = 2)
	public void DeleteUser() {
		
		 given()
		    
			.when()
					.delete("http://localhost:3000/student/3ea1")
			.then()
					.statusCode(200);
						
	   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
