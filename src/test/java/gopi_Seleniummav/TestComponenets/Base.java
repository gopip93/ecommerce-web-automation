package gopi_Seleniummav.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gopi_Seleniummav.pageObjects.LandingPage;

public class Base {

	public WebDriver driver;
	public LandingPage p2;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/gopi_Seleniummav/Resources/GlobalFile.properties");

		prop.load(file);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		return driver;
	}

	public List<HashMap<String, String>> getJsonDataMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath));

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "/reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "/reports" + testCaseName + ".png";
	}

	@BeforeTest(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		p2 = new LandingPage(driver);
		p2.goTo();
		return p2;
	}

	@AfterSuite(alwaysRun = true)
	public void closeDriver() {
		driver.close();

	}

}
