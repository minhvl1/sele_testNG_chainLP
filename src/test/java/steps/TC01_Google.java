package steps;

import commons.BaseTest;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageActions.TC1.GoogleHomePageAction;


public class TC01_Google extends BaseTest {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TC01_Google.class);
    protected GoogleHomePageAction googleHomePage ;


    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName);
        googleHomePage = new GoogleHomePageAction(driver);
        logger.info("BROWSER:" + browserName);
        driver.get(url);
    }

    @Test
    public void GotoGoogle() {
        googleHomePage.InputKeywordIntoSearchBox("youtube");
        googleHomePage.PressEnterInSearchBox();
    }

    @Test
    public void VerifyResult() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("youtube"));
    }
}
