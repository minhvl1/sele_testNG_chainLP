package pageActions.TC2;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.TC2.DemoQAUI;

public class DemoQABrowserWindowAction extends BasePage {
    WebDriver driver;

    public DemoQABrowserWindowAction(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNewTabButton(){
        clickToElement(driver, DemoQAUI.NEW_TAB);
    }

    public void openNewGoogleTab(){
        openNewTab(driver,"https://www.google.com.vn/");
    }

    public void closeDemoQATab(){
        closeTabByTittle(driver,"DEMOQA");
    }

}
