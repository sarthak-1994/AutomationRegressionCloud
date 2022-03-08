import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.net.URL;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserStack {

        WebDriver driver;

        public static final String AUTOMATE_USERNAME = "sarthaktomar_RiXv8y";
        public static final String AUTOMATE_ACCESS_KEY = "FG8Do3B7dVZ5EFT82swb";
        public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

        @BeforeTest
        @Parameters("browser")
         public void setup(String browser) throws Exception {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os_version", "10");
            caps.setCapability("resolution", "1920x1080");
            caps.setCapability("browser", "Chrome");
            caps.setCapability("browser_version", "latest");
            caps.setCapability("os", "Windows");
            caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
            caps.setCapability("build", "BStack Build Number 1"); // CI/CD job or build name
            driver = new RemoteWebDriver(new URL(URL), caps);
        }

        @Test
        public void login()
        {
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
            markTestStatus("passed","Able to detect element",driver);

        }

    public static void markTestStatus(String status, String reason, WebDriver driver) {  // the same WebDriver instance should be passed that is being used to run the test in the calling funtion
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
    }

        @AfterTest
       public void teardown(){driver.quit();}
}