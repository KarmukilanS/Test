package login;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.testng.annotations.Test;

public class Login {
	
	String username = null;
	String password = null;

	@Test
	public void main() throws IOException {
		// TODO Auto-generated method stub
		
		String fileLocation = "C:\\Users\\karmukilan.selva\\eclipse-workspace\\Prism\\src\\test\\resources\\login.xls";
		//XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		
		HSSFWorkbook workbook = new HSSFWorkbook( new FileInputStream(fileLocation));
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		
		
		for (int i = 0; i < 2; i++) 
		{
			HSSFRow row = sheet.getRow(i);
			for (int j=1; j<2; j++)
			{
				HSSFCell cell = row.getCell(j);
				
				if(j==1) {
					username = cell.getStringCellValue();
				}
				if(j==1) {
					password = cell.getStringCellValue();
				}
			}
			System.out.println("username : " + username + "Password :" + password);
			
			/*
			 * String value = cell.getStringCellValue(); System.out.println(value);
			 */
		}
		
	

	}
	

}
