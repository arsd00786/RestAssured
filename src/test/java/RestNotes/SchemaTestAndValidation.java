package RestNotes;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static io.restassured.matcher.RestAssuredMatchers.*;
public class SchemaTestAndValidation {
	
	
	@Test
	public void schemaValidation() {
		
		given()
		
		.when()
				.get("http://localhost:3000/student")
		.then()
				.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("studenSchema.json"));
		
	}

}
