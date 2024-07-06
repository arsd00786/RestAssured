package reporter;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Setup implements ITestListener {
	
	private static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); 
	
	public void onStart(ITestContext context) {
	    
		//Referring to Extent_manager class and there method with name getReportNamewithTime(); 
		
		String fileName = ExtentManager.getReportNamewithTime();
		String fullReportPath = System.getProperty("user.dir")+"\\reports\\"+fileName;
		extentReport = ExtentManager.createInstance(fullReportPath, "Test API Automation", "Test Execution Report");
	  }

	 
	  public void onFinish(ITestContext context) {
	    
		  if(extentReport !=null)
			  extentReport.flush();
		  
	  }
	  
	  public void onTestStart(ITestResult result) {
		    
		 ExtentTest Test =  extentReport.createTest("Test Name "+result.getTestClass().getName()+" - "+ result.getMethod().getMethodName());
		 extentTest.set(Test);
	  }
	  
		
		  public void onTestFailure(ITestResult result) {
		  
		  ExtentManager.logFailDetails(result.getThrowable().getMessage());
		  
		  String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		  extentTest.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
							+ " \n");
		  
		  }
		 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	
}