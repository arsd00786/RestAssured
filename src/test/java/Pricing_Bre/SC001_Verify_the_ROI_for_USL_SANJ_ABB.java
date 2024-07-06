package Pricing_Bre;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;

import UtilityClasses.XLUtils;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payload.pricingbre;
import reporter.ExtentManager;


public class SC001_Verify_the_ROI_for_USL_SANJ_ABB extends baseClass1 {
	public RequestSpecification res;
	//public static Response resp;
	@Test

public void TC001() throws IOException  {


String XlSX="./data/Pricing combintion.xlsx";
//String Path = "C:\\Users\\Mohd.Arshad\\eclipse-workspace1\\yrestassured_bre\\Pricing_bre.json";
 String xlFile ="./data/Pricing combintion.xlsx";

//String postBody = readJson(Path);
res = given().spec(requestSpecification()).header("Content-Type", "application/json").header("Authorization",
"Bearer tD6tL7BpRIW9vNmvDBgZ");

int rowNum = XLUtils.getRowCount(xlFile, "Sheet1");
int colNum = XLUtils.getCellCount(xlFile, "Sheet1", rowNum);

for (int i = 1; i <=rowNum; i++) {

String modifiedJson = pricingbre.payload(XLUtils.getCellData(xlFile, "sheet1", i, 0),
XLUtils.getCellData(xlFile, "sheet1", i, 1), XLUtils.getCellData(xlFile, "sheet1", i, 2),
XLUtils.getCellData(xlFile, "sheet1", i, 3), XLUtils.getCellData(xlFile, "sheet1", i, 4),XLUtils.getCellData(xlFile, "sheet1", i, 5));
// System.out.println(test.toString());
res.body(modifiedJson);


Response resp = res.request(Method.POST, "/api/v1/run");
String resBody = resp.getBody().asString();

// System.out.println("Response=>" + resp.prettyPrint());

Boolean success = resp.jsonPath().get("success");
Assert.assertEquals(success, true);
Assert.assertEquals(success, true);

int statuscode = resp.getStatusCode();
Assert.assertEquals(statuscode, 200);


getJsondata(resp);
/*
                   * float ROI = resp.jsonPath().getFloat(getresponse + ".standardROI.value");
                   * float pRocessingFee = resp.jsonPath().getFloat(getresponse +
                   * ".standardProcessingFee.value"); float NET_INSURANCE_PENETRATION =
                   * resp.jsonPath() .getFloat(getresponse +
                   * ".standardInsurancePenetration.value"); String PRE_OR_PART_PAYMENT_CHARGES =
                   * resp.jsonPath() .getString(getresponse +
                   * ".standardPartPaymentcharges.value"); String FORECLOSURE_CHARGES =
                   * resp.jsonPath().getString(getresponse + ".standardForeclosureCharges.value");
                   * String message = resp.jsonPath().getString(getresponse +
                   * ".standardROI.message");
                   *
                   * Boolean result = resp.jsonPath().getBoolean(getresponse +
                   * ".standardROI.result"); Boolean result1 =
                   * resp.jsonPath().getBoolean(getresponse + ".standardProcessingFee.result");
                   * Boolean result2 = resp.jsonPath().getBoolean(getresponse +
                   * ".standardInsurancePenetration.result"); Boolean result3 =
                   * resp.jsonPath().getBoolean(getresponse +
                   * ".standardPartPaymentcharges.result"); Boolean result4 =
                   * resp.jsonPath().getBoolean(getresponse +
                   * ".standardForeclosureCharges.result"); String final_status =
                   * resp.jsonPath().getString("data.final_status"); Boolean final_result =
                   * resp.jsonPath().getBoolean("data.final_result"); String
                   * applicationProgramCode = resp.jsonPath().getString(getresponse +
                   * ".applicationProgramCode.value");
                   *
                   * String deviationCode = resp.jsonPath().getString(dev +
                   * ".standardROI.deviationCode"); String deviationCode1 =
                   * resp.jsonPath().getString(dev + ".standardProcessingFee.deviationCode");
                   * String deviationCode2 = resp.jsonPath().getString(dev +
                   * ".standardInsurancePenetration.deviationCode"); String deviationCode3 =
                   * resp.jsonPath().getString(dev + ".standardPartPaymentcharges.deviationCode");
                   * String deviationCode4 = resp.jsonPath().getString(dev +
                   * ".standardForeclosureCharges.deviationCode"); String message1 =
                   * resp.jsonPath().getString(dev + ".standardROI.message");
                   * Assert.assertEquals(applicationProgramCode, "USL-SANJ-ABB");
                   */
           
if (ROI >= 19) {
Assert.assertEquals(result, true);

setCellData(XlSX,"Sheet1","result",i+1,String.valueOf(result));
setCellData(XlSX,"Sheet1","roi",i+1,String.valueOf(ROI));
setCellData(XlSX,"Sheet1","message",i+1,String.valueOf(message));
Assert.assertEquals(message, "Standard ROI norm is met");
} else if (ROI > 18.50) {


setCellData(XlSX,"Sheet1","result",i+1,String.valueOf(result));
setCellData(XlSX,"Sheet1","roi",i+1,String.valueOf(ROI));
setCellData(XlSX,"Sheet1","message",i+1,String.valueOf(message1));
setCellData(XlSX,"Sheet1","deviaton code",i+1,String.valueOf(deviationCode));
Assert.assertEquals(deviationCode, "[DEV-CUST-IRR-0]");
Assert.assertEquals(message1, "[Commerical Norms-Customer IRR > 0%]");
Assert.assertEquals(result, false);//false

} else if (ROI > 16) {


setCellData(XlSX,"Sheet1","result",i+1,String.valueOf(result));
setCellData(XlSX,"Sheet1","roi",i+1,String.valueOf(ROI));
setCellData(XlSX,"Sheet1","message",i+1,String.valueOf(message1));
setCellData(XlSX,"Sheet1","deviaton code",i+1,String.valueOf(deviationCode));
Assert.assertEquals(deviationCode, "[DEV-CUST-IRR-0.5]");
Assert.assertEquals(message1, "[Commerical Norms-Customer IRR >=0.5%]");
Assert.assertEquals(result, false);

} else {


setCellData(XlSX,"Sheet1","result",i+1,String.valueOf(result));
setCellData(XlSX,"Sheet1","roi",i+1,String.valueOf(ROI));
setCellData(XlSX,"Sheet1","message",i+1,String.valueOf(message1));
setCellData(XlSX,"Sheet1","deviaton code",i+1,String.valueOf(deviationCode));
Assert.assertEquals(deviationCode, "[DEV-CUST-IRR-3]");
Assert.assertEquals(message1, "[Commerical Norms - Customer IRR >=3%]");
Assert.assertEquals(result, false);
}
if (pRocessingFee >= 2.0) {



} else if (pRocessingFee < 2.0) {


Assert.assertEquals(result1, false);
Assert.assertEquals(deviationCode, "[DEV-PF-0]"); //[DEV-PF-0]
// message pending
}
if (NET_INSURANCE_PENETRATION >= 1.50) {

Assert.assertEquals(result2, true);
}

else if (NET_INSURANCE_PENETRATION < 1.50) {


Assert.assertEquals(result2, false);///false
Assert.assertEquals(deviationCode2, "[DEV-INSPEN-0]");
// message pending
}
if (PRE_OR_PART_PAYMENT_CHARGES.contains("Yes")) {

Assert.assertEquals(result3, true);
// message pending
} else if (PRE_OR_PART_PAYMENT_CHARGES.contains("No")) {

Assert.assertEquals(result3, false);
Assert.assertEquals(deviationCode3, "[DEV-PRE-PAR]");  //[DEV-PRE-PART]
}
if (FORECLOSURE_CHARGES.contains("Yes")) {

Assert.assertEquals(result4, true);
// message pending
} else if (FORECLOSURE_CHARGES.contains("No")) {

Assert.assertEquals(result4, false);
Assert.assertEquals(deviationCode4, "[DEV-FORE-CLOSURE]");
// message pending
}
if (ROI >= 19 && pRocessingFee >= 2 && NET_INSURANCE_PENETRATION >= 1.50
&& PRE_OR_PART_PAYMENT_CHARGES.contains("Yes") && FORECLOSURE_CHARGES.contains("Yes")) {

Assert.assertEquals(final_status, "PASS");
Assert.assertEquals(final_result, true);
} 
	
	  Assert.assertEquals(final_status,	  "FAIL");
	  Assert.assertEquals(final_result, false); 
	  //assert.assertAll(); }
	 
	  }
}

	
	private String readJson(String fileNam) throws IOException {
return new String(Files.readAllBytes(Paths.get(fileNam)));

	}

}

	
