package steps;

import commons.BaseTest;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TC01_Google extends BaseTest {
    private WebDriver driver;
    private static final Logger logger =LogManager.getLogger(TC01_Google.class);
    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {

        driver = getBrowserDriver(browserName);
        logger.info("BROWSER:"+browserName);
        driver.get(url);
    }

    @Test
    public void GotoGoogle() {
        driver.findElement(By.xpath("//input[@class='gLFyf']")).sendKeys("youtube");
        driver.findElement(By.xpath("//input[@class='gLFyf']")).sendKeys(Keys.ENTER);
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("youtube"));
    }
}
