import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ZaleniumGrid {

    RemoteWebDriver driver;

    @BeforeTest
    void Setup() throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc=new DesiredCapabilities();
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

        URL url=new URL(" http://localhost:4444/wd/hub");
        driver=new RemoteWebDriver(url,dc);
        driver.get("https://www.toolsqa.com/selenium-webdriver/selenium-tutorial/");
        Thread.sleep(5000);
    }

    @Test
    void Login()
    {
        Assert.assertEquals(driver.getTitle().length(),12);
    }


    @AfterTest
    void tearDown()
    {
        driver.quit();
    }
}