package RestNotes;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerLibrary {
	
	// To use faker library to generate random data for api testing we have to use faker java library dependency.
	
	@Test
	void fakerDataGenerator() {
		
		
		Faker faker = new Faker();
		System.out.println(faker.funnyName());
		System.out.println(faker.address().fullAddress());
		System.out.println(faker.business().creditCardNumber());
		
	}
	

}
