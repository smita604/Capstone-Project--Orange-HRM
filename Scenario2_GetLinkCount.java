package HumanResourceManagement;

import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Scenario2_GetLinkCount extends LaunchBrowserAndOpenHRM {
	    LoginPage loginPage;
		AdminPage adminPage;
		
		@BeforeMethod
	    public void setup()  {
	        LaunchBrowser("Chrome");
	        LaunchURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	        sleep(2000);
	        loginPage =  new LoginPage(driver);
	        adminPage = new AdminPage(driver);
	    }
		
		@Test
	    public void clickLink() {
			
			loginPage.login("Admin", "admin123");
	        sleep(3000);
	        adminPage.links();
			System.out.println("Total no of links are "+adminPage.LinkCount);
			sleep(3000);
			Assert.assertTrue(adminPage.url.contains("admin"),"Admin page couldn't be opened");
			System.out.println("Admin page has opened successfully");
		}
		@AfterMethod
		public void close() {
			
			driver.close();
		}
}
