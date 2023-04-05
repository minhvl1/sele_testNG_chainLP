package steps;

import commons.BasePage;
import commons.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageActions.TC2.DemoQABrowserWindowAction;

public class TC02_HandleTabs extends BaseTest {
    private WebDriver driver;
    private DemoQABrowserWindowAction demoQABrowserWindow;
    private static final Logger logger = LogManager.getLogger(TC02_HandleTabs.class);

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName);
        demoQABrowserWindow = new DemoQABrowserWindowAction(driver);
        logger.info("BROWSER:" + browserName);
        driver.get(url);
    }

    @Test
    public void openNewTab() throws InterruptedException {
        demoQABrowserWindow.clickNewTabButton();
        demoQABrowserWindow.openNewGoogleTab();
        demoQABrowserWindow.closeDemoQATab();
        Thread.sleep(2000);
    }

}
