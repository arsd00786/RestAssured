package RestNotes;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeaderDemo {
	
	
	@Test
	public void verifyHeaders(){
		
		given()
		.when()
				.get("https://www.google.com/")
		.then()
				.header("Content-Type", "text/html; charset=ISO-8859-1")
				.and()
				.header("Content-Encoding", "gzip");
		
	}
	
	
	@Test
	public void getContentTypeFromHeaders(){
		
		Response res = given()
		.when()
				.get("https://www.google.com/");
		
		/*/ Getting single header info
		
		String singleHeader = res.getHeader("Content-Type");
		System.out.println("Available single header is------> "+singleHeader);
		*/
		
		//Getting all or multiple headers
		
		Headers allHeaders = res.headers();
		
		for(Header header : allHeaders) {
			
			System.out.println("Header Name: "+header.getName()+":  Header Values:  "+header.getValue());
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
