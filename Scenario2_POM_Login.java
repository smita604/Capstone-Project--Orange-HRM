package HumanResourceManagement;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Scenario2_POM_Login extends LaunchBrowserAndOpenHRM {
	
	LoginPage loginPage;

    @BeforeMethod
    public void setup()  {
        LaunchBrowser("Chrome");
        LaunchURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        sleep(2000);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.login("Admin", "admin123");
        sleep(3000); // Optional: wait for dashboard to load
        boolean isDashboardPresent = driver.findElements(By.xpath("//h6[text()='Dashboard']")).size() > 0;
        System.out.println(isDashboardPresent);
        Assert.assertTrue(isDashboardPresent,"Login Failed");
        
        System.out.println("User logged in and Dashboard is visible.");
    }

    @AfterMethod
    public void teardown() {
    	
        driver.close();
    }
       
    }
	
		


