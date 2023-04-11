package steps;

import commons.BasePage;
import commons.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageActions.TC1.GoogleHomePageAction;
import pageActions.TC2.DemoQABrowserWindowAction;

public class TC02_HandleTabs extends BaseTest {
    private WebDriver driver;
    private DemoQABrowserWindowAction demoQABrowserWindow;
    protected GoogleHomePageAction googleHomePage ;
    private static final Logger logger = LogManager.getLogger(TC02_HandleTabs.class);

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName);
        demoQABrowserWindow = new DemoQABrowserWindowAction(driver);
        googleHomePage = new GoogleHomePageAction(driver);
        logger.info("BROWSER:" + browserName);
        driver.get(url);
    }

    @Test
    public void openNewTab() throws InterruptedException {
        demoQABrowserWindow.clickNewTabButton();
        demoQABrowserWindow.openNewGoogleTab();
        demoQABrowserWindow.closeDemoQATab();
        demoQABrowserWindow.switchToGoogleTab();
        Thread.sleep(4000);
    }

    @Test
    public void searchOnGoogle(){
        googleHomePage.InputKeywordIntoSearchBox("youtube");
        googleHomePage.PressEnterInSearchBox();
    }

    @Test
    public void VerifyResult() {
        String url = driver.getCurrentUrl();
        System.out.println(url);
//        Assert.assertTrue(url.contains("youtube"));
    }

}
