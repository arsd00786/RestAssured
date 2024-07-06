package UtilityClasses;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static String getCellData(String xlsFile, String xlsheet, int rownum, int cellnum) throws IOException {
		FileInputStream fis = new FileInputStream(xlsFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(xlsheet);
		XSSFRow row = sh.getRow(rownum);
		XSSFCell cell = row.getCell(cellnum);

		//String cellData = String.valueOf(cell.getNumericCellValue());
		
		String cellData = "";

		if(cell!=null) {
			
			switch (cell.getCellType()) {
			case STRING:
				cellData = cell.getStringCellValue();
				break;

			case NUMERIC:
				cellData = String.valueOf(cell.getNumericCellValue());
				break;
			/*
			 * case FLOAT:
			 * 
			 * cellData = String.valueOf(cell.getNumericCellValue()); break;
			 */
			case BOOLEAN:
				cellData  = String.valueOf(cell.getBooleanCellValue());
			
			default:
				break;
			}
		}

		wb.close();
		fis.close();
		return cellData;
	}

	public static int getRowCount(String xlsFile, String xlsheet) throws IOException {
		FileInputStream fis = new FileInputStream(xlsFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sh = wb.getSheet(xlsheet);
		int rowCount = sh.getLastRowNum();

		wb.close();
		fis.close();

		return rowCount;
	}

	public static int getCellCount(String xlsFile, String xlsheet, int rownum) throws IOException {
		FileInputStream fis = new FileInputStream(xlsFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sh = wb.getSheet(xlsheet);
		XSSFRow row = sh.getRow(rownum);

		int cellCount = row.getLastCellNum();

		wb.close();
		fis.close();

		return cellCount;
	}
	/*
	 * public static int printcell ( ) { HSSFCell cell =
	 * sheet.getRow(1).createCell(6); if(confirmationMessage.isDisplayed()){
	 * cell.setCellValue("PASS"); }else{ cell.setCellValue("FAIL"); }//To write into
	 * Excel FileFileOutputStream outputStream = new
	 * FileOutputStream("E:\\TestData\\TestData.xls"); wb.write(outputStream); }
	 */
}
