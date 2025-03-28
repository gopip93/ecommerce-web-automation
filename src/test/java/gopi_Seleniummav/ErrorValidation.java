package gopi_Seleniummav;

import org.testng.annotations.Test;

import gopi_Seleniummav.TestComponenets.Base;
import gopi_Seleniummav.TestComponenets.Retry;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class ErrorValidation extends Base{
	
	@Test(retryAnalyzer = Retry.class)
	public void errorMessage() throws IOException, InterruptedException
	{
	
		
		p2.loginActions("Gops@test1.com", "Test@123");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessageElementId")));
	    
	    String actualErrorMessage = p2.getErrormessage().trim().toLowerCase();
	    System.out.println("Actual Error Message: " + actualErrorMessage);

	    Assert.assertEquals(actualErrorMessage, "incorrect email or password");
		
	}

}
