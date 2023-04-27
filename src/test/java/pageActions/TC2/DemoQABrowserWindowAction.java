package pageActions.TC2;

import com.aventstack.extentreports.Status;
import commons.BasePage;
import extentreport.ExtentTestManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.TC2.DemoQAUI;

public class DemoQABrowserWindowAction extends BasePage {
    WebDriver driver;

    public DemoQABrowserWindowAction(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click new Tab button")
    public void clickNewTabButton(){
        clickToElement(driver, DemoQAUI.NEW_TAB);
        ExtentTestManager.logMessage(Status.INFO,"Click new tab button");
    }

    @Step("Open new google tab")
    public void openNewGoogleTab(){
        openNewTab(driver,"https://www.google.com.vn/");
        ExtentTestManager.logMessage(Status.INFO,"Open new google tab");
    }

    @Step("Close DemoQa tab")
    public void closeDemoQATab(){
        closeTabByTittle(driver,"DEMOQA");
        ExtentTestManager.logMessage(Status.INFO,"Close DemoQa tab");
    }

    @Step("Switch to google tab")
    public void switchToGoogleTab(){
        switchToWindowByTitle(driver,"Google");
        ExtentTestManager.logMessage(Status.INFO,"Switch to google tab");
    }

}
