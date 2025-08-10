package HumanResourceManagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
		WebDriver driver;
		
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
	    }

    @FindBy(css="input[name='username']")
    WebElement login_id;

    @FindBy(css = "input[name='password']")
    WebElement login_password;

    @FindBy(css = "button[type='submit']")
    WebElement login_submit;

    public void login(String username, String password) {
        login_id.sendKeys(username);
        login_password.sendKeys(password);
        login_submit.click();
    }

}
