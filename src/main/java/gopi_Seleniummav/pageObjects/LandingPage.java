package gopi_Seleniummav.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends gopi_Seleniummav.AbstractComponenets.ReusableAbstractComponent {

	WebDriver driver1;

	public LandingPage(WebDriver driver) {
		super(driver);
		driver1 = driver;
		PageFactory.initElements(driver1, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorLogin;

	public ProductCatalogue loginActions(String email, String pwd) {
		username.sendKeys(email);
		password.sendKeys(pwd);
		loginButton.click();
		ProductCatalogue catalogue = new ProductCatalogue(driver1);
		return catalogue;
	}

	public String getErrormessage() {
		return errorLogin.getText();

	}

	public void goTo() {
		driver1.get("https://rahulshettyacademy.com/client/");
	}

}
