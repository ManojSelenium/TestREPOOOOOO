package com.maven.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExcelReadTest {


	public List<String> readExcel(int columnNo) throws IOException{
		FileInputStream fis=new FileInputStream(new File("C:\\Users\\DELL\\Desktop\\TestCasesExcelRead.xls"));
		
		
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		
		HSSFSheet s=wb.getSheetAt(2);
		
		
		Iterator<Row> rowIt=s.iterator();
		
		List<String> testData=new ArrayList<String>();

		rowIt.next();
		
		while(rowIt.hasNext()) {
			testData.add(rowIt.next().getCell(columnNo).getStringCellValue());
		}
		return testData;
	}
	
	@Test
	public void readDataFromExcelFile() throws IOException {

		List<String> userName=readExcel(0);
		
		System.out.println(userName);

		List<String> password=readExcel(1);
		
		System.out.println(password);
		
		System.setProperty("webdriver.chrome.driver", "C:\\LatestChrome\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		driver.findElement(By.id("email")).sendKeys(userName.get(0));
		driver.findElement(By.id("passwd")).sendKeys(password.get(0));
		
		
/*		List<String> l=new ArrayList<String>();

		l.add("10");
		l.add("20");
		l.add("30");
		l.add("40");



		Iterator<String> it=l.iterator();

		while(it.hasNext()) {
			System.out.println(it.next());
		}*/


	}
}
