package pageActions;

import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.testng.Assert;
import pageUI.GoogleHomePageUI;
import reports.ExtentTestManager;
import utils.WebUI;

public class GoogleHomePageAction extends WebUI {


    @Step("Input Keyword {0} into search box")
    public void InputKeywordIntoSearchBox(String keyWord) {
        setText(GoogleHomePageUI.SEARCH_BOX, keyWord);
        ExtentTestManager.logMessage(Status.INFO, "Input " + keyWord + "into search box");
    }

    @Step("Click to search box")
    public void clickToSearchBox() {
        clickElement(GoogleHomePageUI.SEARCH_BOX);
        ExtentTestManager.logMessage(Status.INFO, "Click search box");
    }

    @Step("Press Enter")
    public void PressEnterInSearchBox() {
        pressKeyFromKeyboard(GoogleHomePageUI.SEARCH_BOX,"ENTER");
        ExtentTestManager.logMessage(Status.INFO, "Press enter");
    }

    @Step("Verify Youtube h2 heading is display")
    public boolean IsH2YoutubeDisplay() {
        return isElementDisplayed(GoogleHomePageUI.H2_YOUTUBE);
    }

    @Step("Verify current url contains key")
    public void VerifyUrlContains(String key) {
        String url = getPageUrl();
        assert url != null;
        Assert.assertTrue(url.contains(key));
    }

}
