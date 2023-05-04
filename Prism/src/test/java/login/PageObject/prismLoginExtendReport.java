package login.PageObject;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.ExpectedCondition;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	//import org.testng.annotations.BeforeTest;
	/*import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
	import org.testng.annotations.Test;

	import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.google.common.io.Files;

	public class prismLoginExtendReport {
		
	
		ExtentReports extend; // need to initialize global level

		WebDriver driver;
		String username = null;
		String password = null;

		String summary = null;
		String description = null;

		String time = null;
		String description1 = null;

		@SuppressWarnings("deprecation")
		
		@BeforeTest
		public void config() {
			
			//Configuration
			String path = System.getProperty("user+dir")+"\\reports\\index.html";
			ExtentSparkReporter reports = new ExtentSparkReporter(path);
			reports.config().setReportName("Reports");
			reports.config().setDocumentTitle("Test Results");
			
			
			//This is main class
			extend = new ExtentReports();
			extend.attachReporter(reports);
			extend.setSystemInfo("Tester", "Karmukilann");
			
		}
		
		//Now how will attached the test cases in test report
		@Test
		public void main() throws IOException, InterruptedException {
			
			ExtentTest test =extend.createTest("main");
			
			String fileLocation = "C:\\Users\\karmukilan.selva\\eclipse-workspace\\Prism\\src\\test\\resources\\Login.xls";

			System.setProperty("webdriver.chrome.driver","C:\\Users\\karmukilan.selva\\Downloads\\chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
		     options.addArguments("--remote-allow-origins=*");
	        driver = new ChromeDriver(options);
	        
			//WebDriver driver = new ChromeDriver();
	        
			driver.get("https://prism.aspiresys.com/PMO/myprojects");
			driver.manage().window().maximize();
			
			test.fail("doesn't match"); //If we enter fail then report automatically generated fail
			
			
			
			extend.flush();
			}
		
		@Test		
		public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {
			
			//String testCaseName = null;
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
			
		}
		
		
		
		
	}
