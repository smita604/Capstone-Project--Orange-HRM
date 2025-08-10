package HumanResourceManagement;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Scensrio2_Search_UserRole extends LaunchBrowserAndOpenHRM {
	
	  LoginPage loginPage;
		AdminPage adminPage;
		
		@BeforeMethod
	    public void setup()  {
	        LaunchBrowser("Chrome");
	        LaunchURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	        sleep(3000);
	        loginPage =  new LoginPage(driver);
	        adminPage = new AdminPage(driver);
	    }
		
		@Test
	    public void clickLink() {
			
			loginPage.login("Admin", "admin123");
	        sleep(3000);
	        adminPage.links();
			sleep(3000);
			adminPage.SearchByUserRole();
		}
		@AfterMethod
		public void close() {
			
			driver.navigate().refresh();
			sleep(3000);
			driver.close();
		}

}
