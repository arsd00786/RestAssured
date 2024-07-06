package reporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;

public class ExtentManager {

	public static ExtentReports extentReport;
	public static ExtentReports createInstance(String filename, String reportName, String documentTitle) {
		ExtentSparkReporter extentSparkResport = new ExtentSparkReporter(filename);
		extentSparkResport.config().setReportName(reportName);
		extentSparkResport.config().setDocumentTitle(documentTitle);
		extentSparkResport.config().setTheme(Theme.DARK);
		
		 extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkResport);
		return extentReport;
		
	}
	
	public static String getReportNamewithTime() {
		
		DateTimeFormatter datetimeformater =  DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime localDatetime = LocalDateTime.now();
		String formattedTime = datetimeformater.format(localDatetime);
		String reportName = "NewReport"+formattedTime+".html";
		
		return reportName;
		
	}
	
	public static void logPassDetails(String log) {
		Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
		
	}
	
	public static void logFailDetails(String log) {
		Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
		
	}
	
	public static void logInfoDetails(String log) {
		Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
		
	}
	
	public static void logWarningDetails(String log) {
		Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.ORANGE));
		
	}
	
	public static void logJson(String json) {
		Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
		
	}
	
	public static void logHeaders(List<Header> headerList) {
		
		String [][] arrayHeaders = headerList.stream().map(header-> new String[] {header.getName(), header.getValue()})
		.toArray(String [][] :: new);
		Setup.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
		
	}
	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}