package steps;

import common.BaseTest;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageActions.GoogleHomePageAction;
import pageActions.DemoQABrowserWindowAction;

public class TC02_HandleTabs extends BaseTest {
    private DemoQABrowserWindowAction demoQABrowserWindow = new DemoQABrowserWindowAction();
    protected GoogleHomePageAction googleHomePage = new GoogleHomePageAction();


    @Test(priority = 1, description ="Open new google tab and close DemoQA tab")
    @Step("Open new google tab and close DemoQA tab")
    public void openNewTab(){
        demoQABrowserWindow.clickNewTabButton();
        demoQABrowserWindow.openNewGoogleTab();
        demoQABrowserWindow.closeDemoQATab();
        demoQABrowserWindow.switchToGoogleTab();
    }

    @Test(priority = 2, description = "Search youtube on google")
    @Step("Search youtube on google")
    public void searchOnGoogle() {
        googleHomePage.clickToSearchBox();
        googleHomePage.InputKeywordIntoSearchBox("youtube");
        googleHomePage.PressEnterInSearchBox();
        Assert.assertTrue(googleHomePage.IsH2YoutubeDisplay());
    }

}
