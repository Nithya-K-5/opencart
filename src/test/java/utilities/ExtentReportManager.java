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

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testcontext) {
		
		//1st  approach for timestamp
		/*
		 * SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=
		 * new Date(); String currrentdatetimestamp=df.format(dt);
		 */
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //2 nd approach for timestamp
	    repName="Test-Report-"+timeStamp+".html";
	    sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
	    
	    sparkReporter.config().setDocumentTitle("OpenCart Automation Report");//title of report
	    sparkReporter.config().setReportName("OpenCart Functional Testing");//name of report
	    sparkReporter.config().setTheme(Theme.DARK);
	    
	    extent= new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Application", "OpenCart");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("SubModule", "Customers");
	    extent.setSystemInfo("UserName", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    
	    String os= testcontext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);
	    
	    String browser= testcontext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);
	    
	    List<String> includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
	    if(!includedGroups.isEmpty()) {
	    	extent.setSystemInfo("Groups", includedGroups.toString());
	    }
	}
	
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS, result.getName()+" Got successfully executed");
	}
	
     public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.FAIL, result.getName()+" Got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
		String imgPath= new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
     public void onTestSkipped(ITestResult result) {
			
    	 test= extent.createTest(result.getTestClass().getName());
    	 test.assignCategory(result.getMethod().getGroups());
    	 test.log(Status.SKIP, result.getName()+" Got Skipped");
    	 test.log(Status.INFO, result.getThrowable().getMessage());
    	 
		}
     
     public void onFinish(ITestContext context) {
			
    	 extent.flush();
    	 
    	 //Once test case executed automatically report to get open
    	 
    	 String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+repName;
    	 File extentReport= new File(pathOfExtentReport);
    	 
    	 try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 
    	 //To send report to mail
    	 /* 
 		 * try { URL url = new
 		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
 		 * 
 		 * // Create the email message 
 		 * ImageHtmlEmail email = new ImageHtmlEmail();
 		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
 		 * email.setHostName("smtp.googlemail.com"); 
 		 * email.setSmtpPort(465);
 		 * email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
 		 * email.setSSLOnConnect(true);
 		 * email.setFrom("pavanoltraining@gmail.com"); //Sender
 		 * email.setSubject("Test Results");
 		 * email.setMsg("Please find Attached Report....");
 		 * email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
 		 * email.attach(url, "extent report", "please check report..."); 
 		 * email.send(); // send the email 
 		 * }
 		 * catch(Exception e) { e.printStackTrace(); }
 		 */
    	 
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
