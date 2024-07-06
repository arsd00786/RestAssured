package dataProviderUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAPI.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import reporter.ExtentManager;

public class dataProviderUtil {

	
	
	@DataProvider(name = "testData")
	public Object[][] testData123() {
		String requestData = "./data/DataForTesting.xlsx";
		Object[][] testData = null;
		try {
			FileInputStream file = new FileInputStream(new File(requestData));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(1);
			int rowCount = sheet.getLastRowNum();
			testData = new Object[rowCount][4];
			for (int i = 0; i < rowCount; i++) {
				Row row = sheet.getRow(i + 1);
				testData[i][0] = row.getCell(0).getStringCellValue(); // Assuming first column contains request data
				testData[i][1] = row.getCell(1).getStringCellValue();
			}
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}
	
	
	 @DataProvider(name = "testData1")
	    public Object[][] getTestData() {
	        String requestData = "./data/DataForTesting1.xlsx";
	        Object[][] testData = null;
	        try {
	            FileInputStream file = new FileInputStream(new File(requestData));
	            Workbook workbook = new XSSFWorkbook(file);
	            Sheet sheet = workbook.getSheetAt(0);
	            int rowCount = sheet.getLastRowNum();
	            testData = new Object[rowCount][2]; // Assuming two columns (request data and response data)
	            for (int i = 0; i < rowCount; i++) {
	                Row row = sheet.getRow(i + 1);
	               testData[i][0] = row.getCell(0).getStringCellValue(); // Assuming first column contains request data
	               testData[i][1] = row.getCell(1).getStringCellValue(); // Assuming second column contains response data
	               // testData[i][2] = row.getCell(2).getStringCellValue();
	               //testData[i][3] = row.getCell(3).getStringCellValue();
	            }
	            workbook.close();
	            file.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return testData;
	    }

	







	@Test(dataProvider = "testData1")
	public void testAPI(String requestData, String endpointURL) {//
	
		// Make API request and get response
		Response response = RestAssured.given().body(requestData).when().post("https://reqres.in/api/users");
		int statuscode = response.statusCode();
		Assert.assertEquals(statuscode, 201);

		// Save response to output Excel sheet
		try {
			FileInputStream file = new FileInputStream(new File("./data/Response.xlsx"));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();

			// Assuming the third column (index 2) is for storing API responses
			Row row = sheet.createRow(rowCount + 1); // Create a new row for the response
			Cell cell = row.createCell(2); // Create a cell in the third column
			cell.setCellValue(response.getBody().asString()); // Set the response value

			// Save changes to the Excel file
			FileOutputStream outFile = new FileOutputStream(new File("./data/Response.xlsx"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Test(dataProvider = "testData")
	public void TestTwo(String requestData, String endpointURL) {

		// Make API request and get response
		Response response = RestAssured.given().body(requestData).when().post("https://reqres.in/api/users");
		int statuscode = response.statusCode();
		Assert.assertEquals(statuscode, 201);

		ExtentManager.logPassDetails("Response Code is :" + response.getStatusCode());

		// Save response to output Excel sheet
		try {
			FileInputStream file = new FileInputStream(new File("./data/Response.xlsx"));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();

			// Assuming the third column (index 2) is for storing API responses
			Row row = sheet.createRow(rowCount + 1); // Create a new row for the response
			Cell cell = row.createCell(2); // Create a cell in the third column
			cell.setCellValue(response.getBody().asString()); // Set the response value

			// Save changes to the Excel file
			FileOutputStream outFile = new FileOutputStream(new File("./data/Response.xlsx"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
}
