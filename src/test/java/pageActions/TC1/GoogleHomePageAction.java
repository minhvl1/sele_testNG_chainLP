package pageActions.TC1;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageUIs.TC1.GoogleHomePageUI;

public class GoogleHomePageAction extends BasePage {

    WebDriver driver;

    public GoogleHomePageAction(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Input Keyword {0} into search box")
    public void InputKeywordIntoSearchBox(String keyWord){
        sendKeyToElement(driver,keyWord,GoogleHomePageUI.SEARCH_BOX);
    }

    @Step("Click to search box")
    public void clickToSearchBox(){
        clickToElement(driver,GoogleHomePageUI.SEARCH_BOX);
    }

    @Step("Press Enter")
    public void PressEnterInSearchBox(){
        pressKeyFromKeyboard(driver,GoogleHomePageUI.SEARCH_BOX,"enter");
    }

    @Step("Verify Youtube h2 heading is display")
    public boolean IsH2YoutubeDisplay(){
        return isElementDisplayed(driver,GoogleHomePageUI.H2_YOUTUBE);
    }

}
