package HumanResourceManagement;


	
	import java.time.Duration;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;


	public class LaunchBrowserAndOpenHRM {
		
		public static WebDriver driver = null;

		public static void LaunchBrowser(String BrowserName) {
			
			if(BrowserName.equals("Chrome")) {
			driver = new ChromeDriver();
			} else if(BrowserName.equals("Firefox")) {
				driver = new FirefoxDriver();
			} 
					driver.manage().window().maximize();
		
			}
		
		public static void LaunchURL(String url) {
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); //using this totally it waits for 40 seconds 10+30
			driver.get(url); //30 seconds wait inbuilt.
			
			
		}
			
		public static void sleep(int milliseconds) {
	        try {
	            Thread.sleep(milliseconds);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
				
			}


