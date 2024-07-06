package Pricing_Bre;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;

import UtilityClasses.XLUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class baseClass1 {
	
	  public String getresponse = "data.rules_result.application.varResult"; 
	  public String dev = "data.deviation.application";
	  public float ROI;
	  public float pRocessingFee;
	  public float  NET_INSURANCE_PENETRATION;
	  public String PRE_OR_PART_PAYMENT_CHARGES;
	  public String FORECLOSURE_CHARGES;
	  public int NO_OF_PROPERTIES;
	 public String deviationCode,deviationCode1,deviationCode2,deviationCode3,deviationCode4;
	  public Boolean result,result1,result2,result3,result4;
	 public  String final_status;
	 public Boolean final_result;
	 public String message,message1;
	  /*result,result1,result2,result3,result4; public String
	  deviationCode,deviationCode1,deviationCode2,deviationCode3,deviationCode4;*/
	 	
	public static RequestSpecification req;
	
	public void getJsondata(Response resp)
	{
		
		
		 ROI = resp.jsonPath().getFloat(getresponse + ".standardROI.value");
		 pRocessingFee = resp.jsonPath().getFloat(getresponse + ".standardProcessingFee.value");
	  NET_INSURANCE_PENETRATION = resp.jsonPath()
				.getFloat(getresponse + ".standardInsurancePenetration.value");
		 PRE_OR_PART_PAYMENT_CHARGES = resp.jsonPath()
				.getString(getresponse + ".standardPartPaymentcharges.value");
		 FORECLOSURE_CHARGES = resp.jsonPath().getString(getresponse + ".standardForeclosureCharges.value");
		// float CERSAI=resp.jsonPath().getFloat(getresponse+".");
		 message = resp.jsonPath().getString(getresponse + ".standardROI.message");

		  result = resp.jsonPath().getBoolean(getresponse + ".standardROI.result");
		  result1 = resp.jsonPath().getBoolean(getresponse + ".standardProcessingFee.result");
		  result2 = resp.jsonPath().getBoolean(getresponse + ".standardInsurancePenetration.result");
		  result3 = resp.jsonPath().getBoolean(getresponse + ".standardPartPaymentcharges.result");
		  result4 = resp.jsonPath().getBoolean(getresponse + ".standardForeclosureCharges.result");
		 final_status = resp.jsonPath().getString("data.final_status");
		 final_result = resp.jsonPath().getBoolean("data.final_result");
//		 applicationProgramCode = resp.jsonPath().getString(getresponse + ".applicationProgramCode.value");

		 deviationCode = resp.jsonPath().getString(dev + ".standardROI.deviationCode");
		  deviationCode1 = resp.jsonPath().getString(dev + ".standardProcessingFee.deviationCode");
		 deviationCode2 = resp.jsonPath().getString(dev + ".standardInsurancePenetration.deviationCode");
		 deviationCode3 = resp.jsonPath().getString(dev + ".standardPartPaymentcharges.deviationCode");
		 deviationCode4 = resp.jsonPath().getString(dev + ".standardForeclosureCharges.deviationCode");
		 message1 = resp.jsonPath().getString(dev + ".standardROI.message");
//		Assert.assertEquals(applicationProgramCode, "USL-SANJ-ABB");
		
		
		
	}

	/*
	 * public String readJson(String fileNam) {
	 * 
	 * try { return new String(Files.readAllBytes(Paths.get(fileNam))); } catch
	 * (Exception e) {
	 * 
	 * throw new RuntimeException("Error reading json file+"); }}
	 */
	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;

	}



	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\test\\resources\\testdata\\config.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}
	public void setCellData(String path, String sheetName, String colName, int rowNum, String data) {
		try {
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int index = workbook.getSheetIndex(sheetName);
		int colNum = 1;
		 
		XSSFSheet sheet = workbook.getSheetAt(index);
		XSSFRow row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
		if (row.getCell(i).getStringCellValue().trim().equals(colName))
		colNum = i;
		}
		sheet.autoSizeColumn(colNum);
		row = sheet.getRow(rowNum - 1);
		if (row == null)
		row = sheet.createRow(rowNum - 1);
		XSSFCell cell = row.getCell(colNum);
		if (cell == null)
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		FileOutputStream fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}

}
