package RestNotes;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.checkerframework.checker.units.qual.K;
import org.testng.annotations.Test;

import io.opentelemetry.sdk.logs.data.Body;
import io.restassured.response.Response;

public class StartOne {
	
	
	/*
	 * given() content type, set cookies, add auth, add param, set headers info
	 * etc....
	 * 
	 * when() get, post, put, delete
	 * 
	 * then() validate status code, extract response, extract headers cookies, and
	 * response body...
	 */
	
	int id;
	
	@Test(priority = 1) 
	public void getUsers() {
		
		given()
		
		.when()
				.get("https://reqres.in/api/users?page=2")
		
		.then()
				.statusCode(200)
				.body("page",equalTo(2))
				.log().all();
		
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Mohd Arshad");
		data.put("Job", "Automation Tester");
		
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
				.post("https://reqres.in/api/users")
		
		.then()
				.statusCode(201)
				.log().all();


	}
	
	
	@Test(priority = 3)
	void savingUserID() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Mohd Arshad");
		data.put("Job", "Automation Tester");
		
		
   id = given()
		.contentType("application/json")
		.body(data)
		.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
   
	}
	
	
	@Test(priority = 4, dependsOnMethods = {"savingUserID"})
	void updatingData() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Mohd Arshad");
		data.put("Job", "Senior Automation Tester");
		
		
    given()
		.contentType("application/json")
		.body(data)
		.when()
				.put("https://reqres.in/api/users/"+id)
		.then()
				.statusCode(200)
				.log().all();		
   
	}
	
	@Test(priority = 4)
	void deleteUser() {
		
		
    given()
    
		.when()
				.delete("https://reqres.in/api/users/"+id)
		.then()
				.statusCode(204)
				.log().all();		
   
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
