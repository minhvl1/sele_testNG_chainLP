package pageActions.TC2;

import commons.BasePage;
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
    }

    @Step("Open new google tab")
    public void openNewGoogleTab(){
        openNewTab(driver,"https://www.google.com.vn/");
    }

    @Step("Close DemoQa tab")
    public void closeDemoQATab(){
        closeTabByTittle(driver,"DEMOQA");
    }

    @Step("Switch to google tab")
    public void switchToGoogleTab(){
        switchToWindowByTitle(driver,"Google");
    }

}
