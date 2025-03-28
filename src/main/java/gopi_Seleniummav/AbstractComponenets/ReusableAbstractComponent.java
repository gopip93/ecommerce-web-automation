package gopi_Seleniummav.AbstractComponenets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gopi_Seleniummav.pageObjects.CartCheckout;
import gopi_Seleniummav.pageObjects.ProductPage;

public class ReusableAbstractComponent {
	
	WebDriver driver2;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement oderPage;

	public ReusableAbstractComponent(WebDriver driver) {
		
		driver2=driver;
	}

	public void waitforElelementToAppear(By findBy)
	{
	
	WebDriverWait w = new WebDriverWait(driver2,Duration.ofSeconds(10));
	w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

	}

	public ProductPage orderClickEvent()
	{
		oderPage.click();
		ProductPage page= new ProductPage(driver2);
		return page;
	}
	
}
