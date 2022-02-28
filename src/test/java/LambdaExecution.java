import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LambdaExecution {



    public RemoteWebDriver driver = null;
    String username = "sartom1994";
    String accessKey = "d4XMMojeIQjwWaYfs1e5GgePfcvwHQOJEl5pdMi92YhaStKszR";
    private String Status=null;

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
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }
    }

    @Test(enabled = true)
    public void testScript() throws Exception {
        try {
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
            driver.findElement(By.id("sampletodotext")).clear();
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            String text=  driver.findElement(By.id("sampletodotext")).getText();
            if(text.equalsIgnoreCase("Yey, Let's add it to list"))
            {Status="passed";}
            else
            {Status="passed";}
            driver.findElement(By.id("addbutton")).click();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterTest
    void markTestStatus()
    {
        ((JavascriptExecutor)driver).executeScript("lambda-status="+ Status);
        driver.quit();
    }

}
