package RestNotes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class AuthenticationsInRestAssured {
	
	@Test(priority =1)
	public void basicAuthentication() {
		
		given()
				.auth().basic("postman", "password")
		.when()
				.get("https://postman-echo.com/basic-auth")
		.then()
				.statusCode(200)
				.body("authenticated",equalTo(true))
				.log().all();
		
		
	}
	
	@Test(priority =2) 
	public void digistAuthentication() {
		
		given()
				.auth().digest("postman", "password")
		.when()
				.get("https://postman-echo.com/basic-auth")
		.then()
				.statusCode(200)
				.body("authenticated",equalTo(true))
				.log().all();
		
		
	}
	
	@Test(priority =3) 
	public void preemtiveAuthentication() {
		
		given()
				.auth().preemptive().basic("postman", "password")
		.when()
				.get("https://postman-echo.com/basic-auth")
		.then()
				.statusCode(200)
				.body("authenticated",equalTo(true))
				.log().all();
		
		
	}
	
	@Test(priority =4)
	public void bearerTokenAuthentication() {
		
		String token= "";
		
		given()
				.header("Authorization", token)
		.when()
				.get("https://github.com/arsd00786")
		.then()
				.statusCode(200)
				.log().all();
		
	}
	
	@Test(priority =5)
	public void oAuthTokenAuthentication() {
		
		String token= "";
		
		given()
				.auth().oauth2(token)
		.when()
				.get("https://github.com/arsd00786")
		.then()
				.statusCode(200)
				.log().all();
		
	}
	
	@Test(priority =6)
	public void APIKeyTokenAuthentication() {
		
				
		given()
				.queryParam("apiid", "")
		.when()
				.get("https://github.com/arsd00786")
		.then()
				.statusCode(200)
				.log().all();
		
	}
	

}
