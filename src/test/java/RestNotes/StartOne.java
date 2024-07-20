package RestNotes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

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
	
	static AllureRestAssured allureFilter = new AllureRestAssured();
            
	
	@Test(priority = 1) 
	@Epic("Verifying User")
	@Feature("How to verify status code")
	@Owner("Mohd Arshad")
	@Severity(SeverityLevel.CRITICAL)
	@Description("allureParameterizedTest description")
	@Step("We hit the api")
	
	public void getUsers() {
		
		given()
		
				.filter(allureFilter)
		.when()
				.get("https://reqres.in/api/users?page=2")
						
		.then()
				.statusCode(200)
				.body("page",equalTo(2))
				.log().all();
		
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority = 2)
	@Epic("Creating request")
	@Feature("We hit POST request and create a user")
	@Severity(SeverityLevel.CRITICAL)
	@Description("No description required")
	@Step("We hit the api")
	void createUser() {
		
		@SuppressWarnings("rawtypes")
		HashMap data = new HashMap();
		
		data.put("name", "Mohd Arshad");
		data.put("Job", "Automation Tester");
		
		
		given()
		.filter(allureFilter)
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
		   .filter(allureFilter)
		.contentType("application/json")
		.body(data)
		.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
   
	}
	
	
	@Test(priority = 4)
	void updatingData() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Mohd Arshad");
		data.put("Job", "Senior Automation Tester");
		
		
    given()
    .filter(allureFilter)
		.contentType("application/json")
		.body(data)
		.when()
				.put("https://reqres.in/api/users/"+id)
		.then()
				.statusCode(200)
				.log().all();		
   
	}
	
	@Test(priority = 5)
	void deleteUser() {
		
		
    given()
    .filter(allureFilter)
		.when()
				.delete("https://reqres.in/api/users/"+id)
		.then()
				.statusCode(204)
				.log().all();		
   
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
