package pageActions.TC1;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.TC1.GoogleHomePageUI;

public class GoogleHomePageAction extends BasePage {

    WebDriver driver;

    public GoogleHomePageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void InputKeywordIntoSearchBox(String keyWord){
        sendKeyToElement(driver,keyWord,GoogleHomePageUI.SEARCH_BOX);
    }

    public void PressEnterInSearchBox(){
        pressKeyFromKeyboard(driver,GoogleHomePageUI.SEARCH_BOX,"enter");
    }


}
