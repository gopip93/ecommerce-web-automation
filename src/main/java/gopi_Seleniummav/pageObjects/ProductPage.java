package gopi_Seleniummav.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gopi_Seleniummav.AbstractComponenets.ReusableAbstractComponent;

public class ProductPage extends ReusableAbstractComponent {
	// Product detail page

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> productNames;

	WebDriver driver3;

	public ProductPage(WebDriver driver) {
		super(driver);

		driver3 = driver;
		PageFactory.initElements(driver3, this);
	}

	public Boolean orderHistory(String pName) {
		Boolean match = productNames.stream().anyMatch(a -> a.getText().equalsIgnoreCase(pName));
		return match;
	}

}
