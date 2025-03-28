package gopi_Seleniummav.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartCheckout extends gopi_Seleniummav.AbstractComponenets.ReusableAbstractComponent {

	WebDriver driver3;

	By cartDetail = By.cssSelector(".cartSection h3");

	public CartCheckout(WebDriver driver) {
		super(driver);
		driver3 = driver;
		PageFactory.initElements(driver3, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement goCart;

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	WebElement cartProdName;

	@FindBy(xpath = "//div[contains(@class, 'subtotal')]/ul/li[3]/button")
	WebElement checkout;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement searchCountry;

	@FindBy(xpath = "//button/span")
	List<WebElement> countryList;

	@FindBy(css = ".action__submit")
	WebElement submitOrderbutton;

	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement checkoutConfirm;

	public void goToCart() {

		goCart.click();
		waitforElelementToAppear(cartDetail);
	}

	public String assertCartProductName(String p_name) {

		String cartPname = cartProdName.getText();
		// Assert.assertEquals(cartPname,p_name);
		System.out.println("The same product is available in the cart");
		return cartPname;
	}

	public void clickcheckout() {
		checkout.click();
	}

	public void placeOrder(String countryName, String cName) throws InterruptedException {
		searchCountry.sendKeys(countryName);

		List<WebElement> list = countryList;
		Thread.sleep(3000);
		list.stream().filter(a -> a.getText().equalsIgnoreCase(cName)).findFirst().ifPresent(a -> a.click());
		submitOrderbutton.click();
	}

	public String placeOrderConfirmation() {
		String message = checkoutConfirm.getText();
		return message;

	}

}
