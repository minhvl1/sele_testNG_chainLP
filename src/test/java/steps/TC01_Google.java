package steps;

import common.BaseTest;
import org.testng.annotations.Test;
import pageActions.GoogleHomePageAction;


public class TC01_Google extends BaseTest {
    protected GoogleHomePageAction googleHomePage = new GoogleHomePageAction();



    @Test(priority = 1)
    public void GotoGoogle() {
        googleHomePage.clickToSearchBox();
        googleHomePage.InputKeywordIntoSearchBox("youtube");
        googleHomePage.PressEnterInSearchBox();
    }

    @Test(priority = 2)
    public void VerifyResult() {
        googleHomePage.VerifyUrlContains("youtube");
    }
}
