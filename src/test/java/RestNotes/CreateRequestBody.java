package RestNotes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class CreateRequestBody {
	
	
//	How many ways we can create request body in restassured
//	1) Hashmap
//	2) Using org.json
//	3) Using POJO(Plain Old Java Object) Getter Setter
//	4) Using external json file

//  To start out own API we have go to folder where we have stored it and open CMD and write json-server student.json command.
// To enable json server we have to install node npm install -g json-server
	
	
	//  Create Request body using Hashmap
	
	//@Test(priority = 1)
	@SuppressWarnings({ "unchecked"})
	public void createRequestHashMap() {
		
		@SuppressWarnings("rawtypes")
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
	//@Test
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
	
//  Create Request body using POJO class Getter setter method
	
	@Test
	public void createRequestBodyUsingPOJO_Class() {
	
		POJO_Post_Request_Getter_Setter PojoData = new POJO_Post_Request_Getter_Setter();
		
		PojoData.setName("Mohd Shadan");
		PojoData.setLocation("Dilshad Garden");
		PojoData.setPhone("9999999999");
		
		String courseArray[] = {"English", "Science"};
		PojoData.setCourses(courseArray);
		
		given()
				.contentType("application/json")
				.body(PojoData)
			
		.when()
				.post("http://localhost:3000/student")
		
		.then()
				.statusCode(201)
				.body("name", equalTo("Mohd Shadan"))
				.body("location", equalTo("Dilshad Garden"))
				.body("courses[0]", equalTo("English"))
				.log().all();
		
	}
	
//  Create Request body using External JSON file.  we can send data multiple parameter or few parameter as per choice.
	
	@Test	
	public void createRequestBodyUsingJSON_File() throws FileNotFoundException {
	
		File file = new File(".\\Data_RestNotes\\RequestBody.json");
		//File file = new File(".\\Data_RestNotes\\RequestBody2.json");
		FileReader fileReader = new FileReader(file);
		JSONTokener jt = new JSONTokener(fileReader);
		
		JSONObject data = new JSONObject(jt);
		
		given()
				.contentType("application/json")
				.body(data.toString())
			
		.when()
				.post("http://localhost:3000/student")
		
		.then()
				.statusCode(201)
				.body("name", equalTo("Azman"))
				.body("location", equalTo("Ahmedabad"))
				.body("courses[0]", equalTo("Math"))
				.log().all();
		
	}
	
	
	
	
	@Test(priority = 2)
	public void DeleteUser() {
		
		 given()
		    
			.when()
					.delete("http://localhost:3000/student/5714")
			.then()
					.statusCode(200);
						
	   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
