package gopi_Seleniummav;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import gopi_Seleniummav.TestComponenets.Base;
import gopi_Seleniummav.pageObjects.CartCheckout;
import gopi_Seleniummav.pageObjects.ProductCatalogue;
import gopi_Seleniummav.pageObjects.ProductPage;

public class Hashmap extends Base {

	@Test(dataProvider = "getData")
	public void submitOrderFlow(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue catalogue = p2.loginActions(input.get("email"), input.get("pwd"));

		//List<WebElement> producs = catalogue.getProductList();
		catalogue.getProductName(input.get("productName"));

		CartCheckout checkout = catalogue.addProductToCart(input.get("productName"));

		Thread.sleep(2000);

		checkout.goToCart();
		String P_name = checkout.assertCartProductName(input.get("productName"));
		Assert.assertEquals(P_name, input.get("productName"));

		checkout.clickcheckout();
		checkout.placeOrder("Ind", input.get("countryName"));

		Thread.sleep(2000);
		String message = checkout.placeOrderConfirmation();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrderFlow" }, dataProvider = "getData")
	public void orderHistoryValidation(HashMap<String, String> input) throws InterruptedException {
		CartCheckout checkout = new CartCheckout(driver);
		ProductPage page = checkout.orderClickEvent();
		Thread.sleep(5000);
		Assert.assertTrue(page.orderHistory(input.get("productName")));
		Assert.assertTrue(page.orderHistory(input.get("productName")));
		System.out.println("passed");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataMap(
				System.getProperty("user.dir") + "/src/test/java/Gopi_Selenium/data/purchaseOrder.json");
		return new Object[][] { { data.get(0) } };

	}

}
