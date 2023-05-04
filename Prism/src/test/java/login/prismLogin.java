package login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class prismLogin {
	
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
		extend.setSystemInfo("Tester", "Karmukilan");
		
	}
	
	//Now how will attached the test cases in test report
	@Test	
	public void main() throws IOException, InterruptedException {
		
		extend.createTest("main");
		
		String fileLocation = "C:\\Users\\karmukilan.selva\\eclipse-workspace\\Prism\\src\\test\\resources\\Login.xls";

		System.setProperty("webdriver.chrome.driver","C:\\Users\\karmukilan.selva\\Downloads\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
	     options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        
		//WebDriver driver = new ChromeDriver();
        
		driver.get("https://prism.aspiresys.com/PMO/myprojects");
		driver.manage().window().maximize();
		
		//extend.flush();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);

		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileLocation));
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.getRow(1);

		for (int i = 0; i < 2; i++) {
			HSSFCell cell = row.getCell(i);

			if (i == 0) {
				username = cell.getStringCellValue();

			}

			if (i == 1) {
				password = cell.getStringCellValue();
			}
			/*
			 * String value = cell.getStringCellValue(); 
			 * System.out.println(value);
			 */
		}

		driver.findElement(By.xpath("/html/body/div/div/div[3]/label/input")).sendKeys(username + Keys.ENTER);
		driver.findElement(By.xpath("/html/body/div/div/div[3]/form/label/input")).sendKeys(password + Keys.ENTER);

		System.out.println("username : " + username + "\n" + "password :" + password);
		
		

		driver.findElement(By.xpath("//a[text()='TestingSL-Bench']")).click();
		driver.findElement(By.xpath("//a[text()='Timesheet']")).click();
		driver.findElement(By.xpath("//a[text()='Daily']")).click();
		
		/*
		 * File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 * Files.copy(src, new File(""));
		 */
		
////ScreenShots	
		
//		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		Files.copy(src, new File("C:\\Users\\karmukilan.selva\\OneDrive - Aspire Systems (India) Private Limited\\Desktop\\Prism\\Screenshots\\dailyPage.jpeg"));
//		
//		driver.navigate().back();
//		
//		File src1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		Files.copy(src1, new File("C:\\Users\\karmukilan.selva\\OneDrive - Aspire Systems (India) Private Limited\\Desktop\\Prism\\Screenshots"));
//		
		
		
		driver.findElement(By.cssSelector("div table tbody ul li:nth-child(2)")).click();
		

////Create a ticket----------------------------------------------------------------------------------------------//		
		
		driver.findElement(By.xpath("//a[text()='Tickets']")).click();
		driver.findElement(By.xpath("//a[text()='Create Ticket']")).click();
		
		//Drop Down

		Select type = new Select(driver.findElement(By.id("field-type")));
		type.selectByVisibleText("Bench");

		String fileLocation1 = "C:\\Users\\karmukilan.selva\\eclipse-workspace\\Prism\\src\\test\\resources\\SummaryDescription.xls";

		HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream(fileLocation1));
		HSSFSheet sheet1 = workbook1.getSheetAt(0);
		HSSFRow row1 = sheet1.getRow(1);

		for (int i = 0; i < 2; i++) {
			HSSFCell cell = row1.getCell(i);

			if (i == 0) {
				summary = cell.getStringCellValue();

			}

			if (i == 1) {
				description = cell.getStringCellValue();
			}
			/*
			 * String value = cell.getStringCellValue(); System.out.println(value);
			 */
		}

		driver.findElement(By.xpath("//input[@id='field-summary']")).sendKeys(summary);
		driver.findElement(By.xpath("//textarea[@id='field-description']")).sendKeys(description);

		//DropDown
		
		Select subType = new Select(driver.findElement(By.id("field-serviceline_subgroup")));
		subType.selectByVisibleText("Testing");

		Select fieldOwner = new Select(driver.findElement(By.id("field-owner")));
		fieldOwner.selectByVisibleText("karmukilan.selva");

		driver.findElement(By.xpath("//input[@name='submit']")).click();

//After ticked created -------------------------------------------------------------------------------------- //

		driver.findElement(By.xpath("//a[text()='Timesheet']")).click();
		driver.findElement(By.xpath("//a[text()='Daily']")).click();

////Pick my Tickets using option method drop down concept (Auto Select Drop-Down)
		
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[1]/td[1]/input")).sendKeys("Python");

		List<WebElement> TicketSelection = driver.findElements(By.cssSelector("li[class ='ui-menu-item'] a"));
	
		for(WebElement option : TicketSelection) {
			
			if(option.getText().equalsIgnoreCase("#6026 - Python Training")) {

				option.click();
			}
			break;
						
		}

	
		
////Choose a reason and workplace		
		

////Implicit wait condition for to select a reason and work place

////Why : May be the drop-down is not properly loaded, when you try to access it.

		/*
		 * Try the below code to wait till the number of options in the drop-down
		 * becomes greater than 1, and then select the first option from it:
		 */

		try {
			
			//Waits for 20 seconds
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			//Wait until expected condition size of the dropdown increases and becomes more than 1
			
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					
					
					//Xpath is 
					Select workplace = new Select(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[1]/td[4]/select")));
					return workplace.getOptions().size() > 1;

				}
			});

			
			
			WebElement element= driver.findElement(By.xpath("//*[@id=\"timesheet_table\"]/tbody/tr[1]/td[4]/select"));
			Select select=new Select (element);
			select.selectByVisibleText("on-premise");

		} catch (Throwable e) {
			System.out.println("Error found: " + e.getMessage());
		}

		Select reason = new Select(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[1]/td[5]/select")));
		reason.selectByVisibleText("bench");
		
		
		

	
		
//// Time and Description --------------------------------------------------------------------- //	

		String fileLocation2 = "C:\\Users\\karmukilan.selva\\eclipse-workspace\\Prism\\src\\test\\resources\\Time and Description.xls";

		HSSFWorkbook workbook2 = new HSSFWorkbook(new FileInputStream(fileLocation2));
		HSSFSheet sheet2 = workbook2.getSheetAt(0);
		HSSFRow row2 = sheet2.getRow(1);

		for (int i = 0; i < 2; i++) {
			HSSFCell cell = row2.getCell(i);

			if (i == 0) {
				time = cell.getStringCellValue();

			}

			if (i == 1) {
				description1 = cell.getStringCellValue();
			}
			
		}

		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[1]/td[6]/div/input"))
				.sendKeys(time);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[1]/td[7]/textarea"))
				.sendKeys(description1);

		driver.findElement(By.xpath("//a[@title='save']")).click();
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/ul/li")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/ul/li")).getText(), "A timesheet entry of - 09:00 Hrs has been logged to the employee - karmukilan.selva under reason - bench for Task #6026 dated 27-Mar-2023.");
	

	
		
////// Add a new row
		
		//new WebDriverWait(getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Add a new row']"))).click();

		Thread.sleep(10000);
	
		driver.findElement(By.xpath("//a[contains(@class,'ui-add')]")).click();	
		
	
		WebElement TicketSelection2 = driver.findElement(By.xpath("//input[contains(@class,'ui-autocomplete-input')]"));
		TicketSelection2.sendKeys("ISTQB");		
		driver.findElement(By.xpath("//a[text()='#6048 - ISTQB Preparation']")).click();
		
		
    try {
	
	// Waits for 20 seconds
	
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// Wait until expected condition size of the dropdown increases and becomes more than 1
	
	   wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			
			
			// Xpath is 
			Select workplace = new Select(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[3]/td[4]/select")));
			return workplace.getOptions().size() > 1;

		}
	});

	   //html/body/div[4]/div/div/div[1]/table/tbody/tr[3]/td[4]/select/option[3]
	
	WebElement element= driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[3]/td[4]/select"));
	Select select=new Select (element);
	select.selectByVisibleText("on-premise");

} catch (Throwable e) {
	System.out.println("Error found: " + e.getMessage());
}

Select reason1 = new Select(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[1]/td[5]/select")));
reason1.selectByVisibleText("bench");
		
		
		
		
		String fileLocation21 = "C:\\Users\\karmukilan.selva\\eclipse-workspace\\Prism\\src\\test\\resources\\Time and Description.xls";

		HSSFWorkbook workbook21 = new HSSFWorkbook(new FileInputStream(fileLocation21));
		HSSFSheet sheet21 = workbook21.getSheetAt(0);
		HSSFRow row21 = sheet21.getRow(1);

		for (int i = 0; i < 2; i++) {
			HSSFCell cell = row21.getCell(i);

			if (i == 1) {
				time = cell.getStringCellValue();

			}

			if (i == 2) {
				description1 = cell.getStringCellValue();
			}
			
		}

		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[3]/td[6]/div/input"))
				.sendKeys(time);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[3]/td[7]/textarea"))
				.sendKeys(description1);

		

		
		
		WebElement Save = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/table/tbody/tr[3]/td[8]/div[1]/a[1]"));
		Save.click();
	
	   
		
	}

	@AfterTest
	private void Sysout() {
		System.out.println("Success");

	}
	
	

}
