package RestNotes;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class QueryAndPathParameters {
	
	@Test
	public void QuesryAndPathParam() {
		
		// https://reqres.in/api/users?page=2
		
		given()
				.pathParam("myPara", "users")   //Path parameter
				.queryParam("page",2)		//Query Parameter
				.queryParam("id", 7)
		.when()
				.get("https://reqres.in/api/{myPara}")
		.then()
				.statusCode(200)
				.log().body();
		
		
		
	}

}
