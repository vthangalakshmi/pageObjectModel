package adminSmokeTest;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

    import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
    import com.aventstack.extentreports.MediaEntityBuilder;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.ChartLocation;
	import com.aventstack.extentreports.reporter.configuration.Theme;

    import configPack.poModelForSmoke;
	 
	public class BaseExtent
	{
	    public static ExtentHtmlReporter htmlReporter;
	    public static ExtentReports extent;
	    public static ExtentTest test;
	    public static WebDriver driver;
	    public static String tc_name;
	     
	    @BeforeSuite
	    public void setUp()
	    {
	        htmlReporter = new ExtentHtmlReporter("./Results/Smoke_Test_Report.html");
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);	         
	        extent.setSystemInfo("OS", "Windows10");
	        extent.setSystemInfo("Host Name", "NTTPC");
	        extent.setSystemInfo("Environment", "Production");
	        extent.setSystemInfo("User Name", "Thangam");	         
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("Automation Test Executed in Jenkins");
	        htmlReporter.config().setReportName("Smoke Test Report");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	    }
	   @BeforeMethod
	    public void verify_reports(Method method)
	    {
	    	 
	    	 tc_name=method.getAnnotation(Test.class).testName();
			 //This will be: 'Verify if the save button is enabled'    	 
	    	 test=extent.createTest(tc_name);    	 
	    	 System.out.println("Test Case name is "+tc_name);
	    } 
	    @AfterMethod
		  public void get_status(ITestResult result) throws IOException
		  {	   
		  // Here will compare if test is failing then only it will enter into if condition
		  if(ITestResult.FAILURE==result.getStatus())
		  {	  
		    String temp=poModelForSmoke.getScreenshot(driver,result.getName());
		    test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		   
		  }
		  else  if(ITestResult.SUCCESS==result.getStatus())
		  {
			  String temp=poModelForSmoke.getScreenshot(driver,result.getName());
			 
			  test.pass("Testcase is Passed", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			  test.addScreenCaptureFromPath(temp);
		  }
		 
		  }  
	     
	    @AfterSuite
	    public void tearDown()
	    {
	        extent.flush();
	        driver.close();
	    }
	}




