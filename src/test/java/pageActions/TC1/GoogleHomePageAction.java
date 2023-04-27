package pageActions.TC1;

import com.aventstack.extentreports.Status;
import commons.BasePage;
import extentreport.ExtentTestManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.TC1.GoogleHomePageUI;

public class GoogleHomePageAction extends BasePage {

    WebDriver driver;

    public GoogleHomePageAction(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Input Keyword {0} into search box")
    public void InputKeywordIntoSearchBox(String keyWord) {
        sendKeyToElement(driver, keyWord, GoogleHomePageUI.SEARCH_BOX);
        ExtentTestManager.logMessage(Status.INFO, "Input " + keyWord + "into search box");
    }

    @Step("Click to search box")
    public void clickToSearchBox() {
        clickToElement(driver, GoogleHomePageUI.SEARCH_BOX);
        ExtentTestManager.logMessage(Status.INFO, "Click search box");
    }

    @Step("Press Enter")
    public void PressEnterInSearchBox() {
        pressKeyFromKeyboard(driver, GoogleHomePageUI.SEARCH_BOX, "enter");
        ExtentTestManager.logMessage(Status.INFO, "Press enter");
    }

    @Step("Verify Youtube h2 heading is display")
    public boolean IsH2YoutubeDisplay() {
        return isElementDisplayed(driver, GoogleHomePageUI.H2_YOUTUBE);
    }

}
