package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	    public ExtentSparkReporter sparkReporter; // UI of the report 
	    public ExtentReports extent; // Common Information of the report
	    public ExtentTest test;  // Create test case entries in the report and update status of the test methods
	    
	    String repname;
	    
	    public void onStart(ITestContext testContext) {
	    	
	    	/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	    	Date dt = new Date();
	    	String currentdatetimestamp = df.format(dt);*/
	    	
	    	// Create a new folder and call the object using the method to create the UI for the report.
	    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
	    	
	    	repname = "Test-Report-" + timeStamp + ".html";
	    	
	    	
	    	sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repname);// specify location of the report
	    	
	    	// configure the name, title and theme of the report
	    	sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //Title of the Report 
	    	sparkReporter.config().setReportName("OpenCart Functional Testing"); //name of the report
	    	sparkReporter.config().setTheme(Theme.DARK); //theme of the report Dark(Black) and Standard(White) themes
	    	
	    	// extent is the object created for the method called as extent reports
	    	// here we create the commonly used information in the report
	    	extent = new ExtentReports();  
	    	extent.attachReporter(sparkReporter); // Mandatory.  we attach the fields to the extent report using this line
	    	
	    	//common information to be given
	    	extent.setSystemInfo("Application","OpenCart"); // key and value pair
	    	extent.setSystemInfo("Module","Admin");
	    	extent.setSystemInfo("Sub Module","Customers");
	    	extent.setSystemInfo("User Name ",System.getProperty("user.name"));
	    	extent.setSystemInfo("Environment","QA");
	    	
	    	String os = testContext.getCurrentXmlTest().getParameter("os");
	    	extent.setSystemInfo("Operating System", os);
	    	
	    	String browser = testContext.getCurrentXmlTest().getParameter("browser");
	    	extent.setSystemInfo("Browser", browser);
	    	
	       List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	       if(!includedGroups.isEmpty())
	       {
	    	   extent.setSystemInfo("Groups", includedGroups.toString());
	       }
	      }
	    // here the result will store the actual status of the test execution whether pass./fail/skip
	    public void onTestSuccess(ITestResult result) {
	    	
			 test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report
			 test.assignCategory(result.getMethod().getGroups()); // to display groups in the report
			 test.log(Status.PASS, "Test case Passed : "+result.getName()); // update the result p/f/s
			  }
	    public void onTestFailure(ITestResult result) {
	    	
	    	 test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report
	    	 test.assignCategory(result.getMethod().getGroups()); // to display groups in the report
	    	 
			 test.log(Status.FAIL, "Test case Failed : "+result.getName());
			 test.log(Status.INFO, "Test case Failed and cause is : "+result.getThrowable().getMessage()); // captures the thrown error message
			
			 try
			 {
				 String imgPath = new BaseClass().captureScreen(result.getName());
				 test.addScreenCaptureFromPath(imgPath);
			 }
			 catch(IOException e1)
			 {
				 e1.printStackTrace();
			 }
			  }
	    public void onTestSkipped(ITestResult result) {
			
	    	test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report
	    	 test.assignCategory(result.getMethod().getGroups()); // to display groups in the report
			 test.log(Status.SKIP, "Test case Skipped : "+result.getName());
			 test.log(Status.INFO, result.getThrowable().getMessage());
			  }
	    
	    public void onFinish(ITestContext context) {
			
	    	extent.flush(); // Mandatory
	    	
	    	 String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repname;
	    	    File extentReport = new File(pathOfExtentReport);

	    	    try {
	    	        Desktop.getDesktop().browse(extentReport.toURI());
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }

	    	    
			  }
	    
}
