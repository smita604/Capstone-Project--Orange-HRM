package HumanResourceManagement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Scenario1_LoginWithExcelData extends LaunchBrowserAndOpenHRM {
	static boolean isValid;
	
	   
	
	  public static void readDataFromLoginExcelFile(int i) throws IOException, FileNotFoundException {	  
		 
	  
		  	String FilePath="C:\\Selenium\\CapstoneProjects\\LoginData.xlsx";
		    FileInputStream fis = new FileInputStream(new File(FilePath));
		    XSSFWorkbook workbook = new XSSFWorkbook(fis);
		    XSSFSheet sheet = workbook.getSheetAt(0);
		  
			    XSSFRow row = sheet.getRow(i);
	            String username = row.getCell(0).getStringCellValue();
	            String password = row.getCell(1).getStringCellValue();
	            String flag = row.getCell(2).getStringCellValue();
	            isValid=Boolean.parseBoolean(flag);
	            sleep(2000);
	            driver.findElement(By.name("username")).sendKeys(username);
	            driver.findElement(By.name("password")).sendKeys(password);
	            driver.findElement(By.xpath("//button[@type='submit']")).click();
	            sleep(2000);
	            workbook.close();
	    		fis.close();	                       
		  
   }
	 	  
	  static public void takeScreenShot(int i) throws IOException {
			
			try {
				
				File SourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileHandler.copy(SourceFile, new File ("Screenshot"+i+".png"));
												
			    } catch(Exception e) {
				
				System.out.println("error " + e.getMessage()); 
			}
		}
	  
	  static public void simpleExtentReports(int i) {
		  
		// below class uses to create an HTML file. 
		  ExtentSparkReporter  htmlReport = new ExtentSparkReporter("HRM Report"+i+".html");
			
			// below class generates report and write the content on it.
			ExtentReports report = new ExtentReports();
			
			report.attachReporter(htmlReport);
			
			
			ExtentTest test;
			//Configuring system info
			report.setSystemInfo("Project Name", "HRMProject");
			report.setSystemInfo("Machine","Windows ASUS");
			report.setSystemInfo("OS","Windows OS");
			report.setSystemInfo("Company","Open Source");
			report.setSystemInfo("User","Smita");
			report.setSystemInfo("Browser","Google Chrome");
			
			//Configuration and look and feel of reports;
			
			htmlReport.config().setDocumentTitle("MyHRMReport");
			htmlReport.config().setReportName("HRM Automation");
			htmlReport.config().setTheme(Theme.STANDARD);
			htmlReport.config().setTimeStampFormat("dd-MMMM-YYYY");
			
			//creates the report
			test = report.createTest("HomePage");
			if(isValid) {
				
				test.log(Status.PASS, MarkupHelper.createLabel("HomePage", ExtentColor.GREEN));
			}
			else {
				test.log(Status.FAIL, MarkupHelper.createLabel("HomePage", ExtentColor.RED));
			}
			//Generates the report
			report.flush();
	  }
	  
	  

	  public static void main(String[] args) throws IOException {
		  
		 
		  for(int i=1;i<=5;i++) {
			  
		  LaunchBrowser("Chrome");
		  LaunchURL("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		 
		  	  readDataFromLoginExcelFile(i);
			  takeScreenShot(i);
			  simpleExtentReports(i);
			  
			  
			  		if(isValid) {
		            // Check for Dashboard presence after valid login credential
		            boolean isDashboardPresent = driver.findElements(By.xpath("//h6[text()='Dashboard']")).size() > 0;
		            System.out.println(isDashboardPresent);
		            Assert.assertTrue(isDashboardPresent,"Login Failed");
		            System.out.println("Testcase passed with valid credentials");
			  		}else {
			  			
			  		// Check for Dashboard presence after invalid login credential
		            boolean isDashboardPresent = driver.findElements(By.xpath("//h6[text()='Dashboard']")).size() > 0;
			  		System.out.println(isDashboardPresent);
			  		Assert.assertFalse(isDashboardPresent,"Logged Successfully");
		            System.out.println("Testcase failed with invalid credentials");
			  		}
			  
			  driver.quit();
			  
		  }
	  }

}
