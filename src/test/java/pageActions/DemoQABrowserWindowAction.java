package pageActions;

import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import pageUI.DemoQAUI;
import reports.ExtentTestManager;
import utils.WebUI;

public class DemoQABrowserWindowAction extends WebUI {

    @Step("Click new Tab button")
    public void clickNewTabButton(){
        clickElement(DemoQAUI.NEW_TAB);
        ExtentTestManager.logMessage(Status.INFO,"Click new tab button");
    }

    @Step("Open new google tab")
    public void openNewGoogleTab(){
        openNewTab("https://www.google.com.vn/");
        ExtentTestManager.logMessage(Status.INFO,"Open new google tab");
    }

    @Step("Close DemoQa tab")
    public void closeDemoQATab(){
        closeTabByTittle("DEMOQA");
        ExtentTestManager.logMessage(Status.INFO,"Close DemoQa tab");
    }

    @Step("Switch to google tab")
    public void switchToGoogleTab(){
        switchToWindowByTitle("Google");
        ExtentTestManager.logMessage(Status.INFO,"Switch to google tab");
    }

}
