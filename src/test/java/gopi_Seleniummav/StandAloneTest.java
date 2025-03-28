package gopi_Seleniummav;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import gopi_Seleniummav.TestComponenets.Base;
import gopi_Seleniummav.TestComponenets.Retry;
import gopi_Seleniummav.pageObjects.CartCheckout;
import gopi_Seleniummav.pageObjects.ProductCatalogue;
import gopi_Seleniummav.pageObjects.ProductPage;

public class StandAloneTest extends Base {

	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void submitOrderFlow(String email, String pwd, String productName) throws IOException, InterruptedException {

		ProductCatalogue catalogue = p2.loginActions(email, pwd);

		// List<WebElement> producs= catalogue.getProductList();
		catalogue.getProductName(productName);

		CartCheckout checkout = catalogue.addProductToCart(productName);

		Thread.sleep(2000);

		checkout.goToCart();
		String P_name = checkout.assertCartProductName(productName);
		Assert.assertEquals(P_name, productName);

		checkout.clickcheckout();
		checkout.placeOrder("Ind", "India");

		Thread.sleep(2000);
		String message = checkout.placeOrderConfirmation();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrderFlow" }, dataProvider = "getData")
	public void orderHistoryValidation(String email, String pwd, String productName) throws InterruptedException {
		CartCheckout checkout = new CartCheckout(driver);
		ProductPage page = checkout.orderClickEvent();
		Thread.sleep(5000);
		Assert.assertTrue(page.orderHistory(productName));
		System.out.println("passed");
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] loginCred = { { "Gops@test.com", "Test@123", "IPHONE 13 PRO" } };
		return loginCred;

	}

}
