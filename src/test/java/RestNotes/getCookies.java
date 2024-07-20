package RestNotes;

import org.testng.annotations.Test;

import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class getCookies {
	
	
	@Test
	public void getCookiesInfo() {
		
		Response res = given()
				
		.when()
				//.get("https://mypayroll.paysquare.com/");
		.get("https://www.google.com/");
		
		
		/* Getting Single cookies available
		String singleCookies = res.getCookie("ASP.NET_SessionId");
		String singleCookies = res.getCookie("AEC");
		System.out.println("Value of single cookies is ====> "+singleCookies);
		*/
		
		//Multiple cookies extraction
		
		Map<String, String> multipleCookies = res.getCookies();
		System.out.println(multipleCookies.keySet());
		
		for(String key : multipleCookies.keySet()) {
			String cookiesValue = res.getCookie(key);
			System.out.println(key+cookiesValue);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
