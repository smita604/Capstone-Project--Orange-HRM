package HumanResourceManagement;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPage extends LaunchBrowserAndOpenHRM {
	
	int LinkCount;
	String url;
	public AdminPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath  ="//li/a") List<WebElement> links;
	@FindBy(css = ".oxd-main-menu-item-wrapper") WebElement AdminLink;
	@FindBy(css = ".oxd-input.oxd-input--active") List<WebElement> userName;
	@FindBy(xpath = "//label[contains(.,'User Role')]/../following-sibling::div//div[contains(@class,'oxd-select-text')]") WebElement UserRole;
	@FindBy(xpath = "//label[contains(.,'Status')]/../following-sibling::div//div[contains(@class,'oxd-select-text')]") WebElement Status;
	@FindBy(xpath= "//div[@role='listbox']//span[text()='Admin']") WebElement UserRoleOption;
	@FindBy(xpath= "//div[@role='listbox']//span[text()='Enabled']") WebElement StausOption;
	@FindBy(css = "button[type='submit']") WebElement SearchButton;
	@FindBy(xpath="/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div") List<WebElement> Rows;
	@FindBy(xpath ="//div/span") List<WebElement> Text;
	


	public void links() {
	
		//Dashboard.click();
		LinkCount = links.size();
		AdminLink.click();
		url= driver.getCurrentUrl();
	}
	
	public void searchByUserName() {
		
		
		userName.get(1).sendKeys("Admin");
		SearchButton.click();
		sleep(5000);
		int noOfRows = (Rows.size()-1);
		System.out.println(noOfRows);
		String text = Text.get(1).getText();
		System.out.println(text);
		String[] parts = text.split("\\)");
		String[] subPart = parts[0].split("\\(");		
		int finalCount = Integer.parseInt(subPart[1]);
		Assert.assertEquals(finalCount,noOfRows);
		System.out.println("Total no of rows in search results "+noOfRows);
		
	}
	
	public void SearchByUserRole() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(UserRole));
		UserRole.click();
		sleep(2000);	
		UserRoleOption.click();
		SearchButton.click();
		sleep(5000);
		int noOfRows = (Rows.size()-1);
		System.out.println(noOfRows);
		String text = Text.get(1).getText();
		System.out.println(text);
		String[] parts = text.split("\\)");
		String[] subPart = parts[0].split("\\(");		
		int finalCount = Integer.parseInt(subPart[1]);
		Assert.assertEquals(finalCount,noOfRows);
		System.out.println("Total no of rows in search results "+noOfRows);
	}
	
public void SearchByUserStatus() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(Status));
		Status.click();
		sleep(2000);	
		StausOption.click();
		SearchButton.click();
		sleep(5000);
		int noOfRows = (Rows.size()-1);
		System.out.println(noOfRows);
		String text = Text.get(1).getText();
		System.out.println(text);
		String[] parts = text.split("\\)");
		String[] subPart = parts[0].split("\\(");		
		int finalCount = Integer.parseInt(subPart[1]);
		Assert.assertEquals(finalCount,noOfRows);
		System.out.println("Total no of rows in search results "+noOfRows);
	}
	
			 
}
