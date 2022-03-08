import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Saucelab {

    WebDriver driver;

    public static final String AUTOMATE_USERNAME = "oauth-anuragtomar6-ef076";
    public static final String AUTOMATE_ACCESS_KEY = "5aee57a7-41c7-41e3-9faa-0a3b91462057";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@ondemand.saucelabs.com/wd/hub";


    @BeforeTest
    @Parameters("browser")
    public void setUp(String browserType) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(browserType.equals("Chrome")) {
            capabilities.setCapability("browserName", "Chrome");
            capabilities.setCapability("version", "92.0");
            capabilities.setCapability("platform", "Windows 10");
            capabilities.setCapability("resolution","1024x768");
            capabilities.setCapability("build", "Chrome First Test");
            capabilities.setCapability("name", "Chrome Test");
        }

        if(browserType.equals("Firefox")) {
            capabilities.setCapability("browserName", "Firefox");
            capabilities.setCapability("version", "97.0");
            capabilities.setCapability("platform", "Windows 10");
            capabilities.setCapability("resolution", "1024x768");
            capabilities.setCapability("build", "Firefox First Test");
            capabilities.setCapability("name", "firefox Test");
        }

        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL(URL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }


    }

    @Test
    public void login()
    {
        driver.get("https://lambdatest.github.io/sample-todo-app/");
        driver.findElement(By.name("li1")).click();
        driver.findElement(By.name("li2")).click();
        Assert.assertEquals(driver.getTitle().length(),10);

    }

    @AfterTest
    public void teardown()
    {
        driver.quit();
    }
}
