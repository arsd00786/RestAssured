package RestNotes;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;



public class VerifyResponseFromResponseBody {
	
	@Test	
	public void TestJSON_Response() {
		
		//  Approach one for verifying any Response parameters value using JSON path.
		
		given()
				.contentType(ContentType.JSON)
		.when()
				.get("http://localhost:3000/student")
		.then()
				.statusCode(200)
				.header("Content-Type","application/json")
				.body("[5].courses[0]", equalTo("English"));
		
			
	}
	
	@Test	
	public void TestJSON_ResponseClass() {
		
		//  Approach two for verifying any value in Response body using Response class.
		
		Response res = given()
				.contentType(ContentType.JSON)
		.when()
				.get("http://localhost:3000/student");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json");
		String courseName =  res.jsonPath().get("[5].courses[0]").toString();
		Assert.assertEquals(courseName, "English");
			
	}

	// Third 3rd Approach using JSONObject Class
	// used JSONObject class but it did not worked because This will only work if the server response is a single JSON object.
	// Now using JSONARRAY class 
	@Test	
	public void verifyResponseUsingJsonObject() {
		
		Response res = given()
				.contentType(ContentType.JSON)
		.when()
				.get("http://localhost:3000/book");
		
		//JSONObject jsonObject = new JSONObject(res.asString());
		JSONArray jsonArray = new JSONArray(res.asString());
		
		for(int i=0; i<jsonArray.length();i++) {
		String booTitles = jsonArray.getJSONObject(i).get("title").toString();
		System.out.println(booTitles);
		}
		
		}
	
	@Test	
	public void verifyResponseUsingJsonArray() {
		
		Response res = given()
				.contentType(ContentType.JSON)
		.when()
				.get("http://localhost:3000/book");
		
		//JSONObject jsonObject = new JSONObject(res.asString());
		JSONArray jsonArray = new JSONArray(res.asString());
		
		boolean status = false;
		for(int i=0; i<jsonArray.length();i++) {
		String booTitles = jsonArray.getJSONObject(i).get("title").toString();
		if(booTitles.equalsIgnoreCase("Father of computer")) {
		System.out.println(booTitles);
		status =true;
		break;
		}
		
		}
	
			Assert.assertEquals(status, true);
		
		
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
