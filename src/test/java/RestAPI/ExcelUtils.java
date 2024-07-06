package RestAPI;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	
	public ExcelUtils(String excelPath, String sheetName) {
		
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			
			System.out.println("Exception Message "+e.getMessage());
			System.out.println("Exception Cause "+e.getCause());
			
		}

	}
	
	public static Object getCellData(int rowNum, int colNum) {
		
		DataFormatter formatter = new DataFormatter();

		Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

		return value;
		
		
	}

	
	  public int getRowCount() {
	  
	  return sheet.getPhysicalNumberOfRows(); }
	  
	  
	 
	 

		

	
	  
	
	

}
