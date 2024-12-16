package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL {

	public int getRowCount(String sheetName) throws IOException  
	{
	//Path of the excel file
	FileInputStream fs = new FileInputStream("path");
		
	//Creating a workbook
	XSSFWorkbook workbook = new XSSFWorkbook(fs);
	
	XSSFSheet sheet = workbook.getSheet(sheetName);
	
	int rowcount=sheet.getLastRowNum();
	
	workbook.close();
	fs.close();
	return rowcount;
	
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		FileInputStream fs = new FileInputStream("C:/Users/sachin.Burkul/git/PetStoreAutomation2/PetStoreAutomation/testData/Userdata.xlsx");

		XSSFWorkbook workbook=new XSSFWorkbook(fs);
		
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
		XSSFRow row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fs.close();
	    return cellcount;
	}
	
	
	
	
	
}
