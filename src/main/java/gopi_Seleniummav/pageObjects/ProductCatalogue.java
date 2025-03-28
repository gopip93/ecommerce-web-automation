package gopi_Seleniummav.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import gopi_Seleniummav.AbstractComponenets.ReusableAbstractComponent;

public class ProductCatalogue extends gopi_Seleniummav.AbstractComponenets.ReusableAbstractComponent {

	WebDriver driver2;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By toast = By.id("toast-container");
	By cartDetail = By.cssSelector(".cartSection h3");

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		driver2 = driver;
		PageFactory.initElements(driver2, this);
	}

	// Get Product List

	@FindBy(css = ".col-sm-10")
	List<WebElement> productList;

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

	public List<WebElement> getProductList() {
		waitforElelementToAppear(productsBy);
		return productList;
	}

	public WebElement getProductName(String productName) {
		// Getting product list by getProductList method or we can use value of "productList"
		WebElement product_Name = getProductList().stream()
				.filter(pd -> pd.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElseThrow();

		return product_Name;
	}

	public CartCheckout addProductToCart(String productName) {
		WebElement product_Name = getProductName(productName);
		product_Name.findElement(addToCart).click();
		waitforElelementToAppear(toast);
		CartCheckout checkout = new CartCheckout(driver2);
		return checkout;
	}

}
