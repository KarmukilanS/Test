package login;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	public static ExtentReports getReportObject() { //static is used for without creating object of the class


		String path = System.getProperty("user+dir")+"/reports/index.html";
		ExtentSparkReporter reports = new ExtentSparkReporter(path);
		reports.config().setReportName("Reports");
		reports.config().setDocumentTitle("Test Results");
		
		
		//This is main class
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reports);
		extend.setSystemInfo("Tester", "Karmukila");
		return extend;
		
	}

}
