package login.PageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import login.ExtentReportTestNG;


public class Listeners extends prismLoginExtendReport implements ITestListener{
	
	ExtentTest test;
	
	ExtentReports extend = ExtentReportTestNG.getReportObject();//ExtentReportTestNG
	
	public void onTestStart(ITestResult result) {
		
		test = extend.createTest(result.getMethod().getMethodName());
		
	}
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test Passed");
		
	}
	 public void onTestFailure(ITestResult result) {
		 
		 test.fail(result.getThrowable());
		
		 
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 
		String filePath = null;
		try {
			filePath = takeScreenShot(result.getMethod().getMethodName(), driver);
		}
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
		 test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	 }
	 public void onTestSkipped(ITestResult result) {
			
			
			
		}
	 
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
			
			
		}
	 public void onStart(ITestResult context) {
			
			
			
		}
	 
	 public void onFinish(ITestResult context) {
			
			
			
		}
}


