/*

 * @author Neelesh Goyal
 * 
 * @Description : i have used this class to read data from excel sheet by using apache POI library
 * 
 */

package com.youtube.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;

public class ExcelUtilities {
	String filepath;
	
	public ExcelUtilities(String filepath) {
		this.filepath=filepath;
	}
	
	public String readData(String sheetName, int row, int cell) {
		String value=null;
		
		try {
			FileInputStream fis=new FileInputStream(new File(filepath));
			Workbook wb = WorkbookFactory.create(fis);
			Cell cl = wb.getSheet(sheetName).getRow(row).getCell(cell);
			
			switch(cl.getCellType()) {
			case STRING:
				value=cl.getStringCellValue();
				break;
				
			case NUMERIC:
				if(DateUtil.isCellDateFormatted(cl)) {
						SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
						value=sdf.format(cl.getDateCellValue());
				}
				
				else {
					long LongValue=(long)cl.getNumericCellValue();
					value=Long.toString(LongValue);
				}
				break;
				
			case BOOLEAN:
				Boolean.toString(cl.getBooleanCellValue());
				break;
				
			default:
				Reporter.log("Cell Format is not matched ", true);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
