package RestAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.restassured.response.Response;

public class test {
	private static Response response;
	private static final String BASE_URL = "https://reqres.in/api/users";

	@Test
	public void post() throws JsonMappingException, JsonProcessingException {
		String excelPath = "./data/DataForTesting.xlsx";
		String sheetName = "Sheet1";
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		JSONObject request = new JSONObject();
		request.put("name", excel.getCellData(1, 0));
		request.put("job", excel.getCellData(1, 1));

		response = given().header("Content-type", "application/json").and().body(request).when().post(BASE_URL).then()
				.extract().response();

		String stringToParse = response.getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserInfo userInfo = objectMapper.readValue(stringToParse, UserInfo.class);
		writeResponse(userInfo, excel);
	}

	public void writeResponse(UserInfo userInfo, ExcelUtils excel) { try {
  FileInputStream fileInputStream = new
  FileInputStream("./data/DataForTesting.xlsx"); XSSFWorkbook workbook = new
  XSSFWorkbook(fileInputStream); XSSFSheet sheet = workbook.getSheet("Sheet1");
  
  int rowNum = 1; // Assuming you're reading data from the second row (row  index 1) 
  for(int i =rowNum; i<= 10; i++) {
  
  
  XSSFRow row = sheet.getRow(rowNum); if (row == null) { row =
  sheet.createRow(rowNum); }
  
  
  // Write the response data to the same row 
  int cellNum = 2; // Assuming you  want to write after the second column
  XSSFCell cell = row.createCell(cellNum);
  row.createCell(cellNum); cell.setCellValue(userInfo.getId()); cell =
  row.createCell(cellNum + 1); cell.setCellValue(userInfo.getName()); cell =
  row.createCell(cellNum + 2); cell.setCellValue(userInfo.getJob()); cell =
  row.createCell(cellNum + 3); cell.setCellValue(userInfo.getCreatedAt()); }
  FileOutputStream outputStream = new
  FileOutputStream("./data/DataForTesting.xlsx"); workbook.write(outputStream);
  workbook.close(); outputStream.close(); } catch (Exception e) {
  e.printStackTrace(); 
  }
	
	}
}





