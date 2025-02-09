package utils;

import com.aventstack.extentreports.Status;
import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.AllureManager;
import reports.ExtentTestManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class WebUI {
    private final static int TIMEOUT = 60;
    private final static double STEP_TIME = 1;
    private final static int PAGE_LOAD_TIMEOUT = 30;

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
        sleep(3);
    }
    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static void verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        logConsole("Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, "Fail. Not match. '" + actual.toString() + "' != '" + expected.toString() + "'");
    }

    public static void verifyEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        logConsole("Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static Boolean checkElementExist(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(2);
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            System.out.println("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }


    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        logConsole("Open: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
        AllureManager.saveTextLog("Open URL: " + url);
        waitForPageLoaded();
    }

    public static void clickElement(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        logConsole("Click element: " + by);
        AllureManager.saveTextLog("Click on element " + by);
    }

    public static void clickElement(By by, long timeout) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        logConsole("Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click on element " + by);
        AllureManager.saveTextLog("Click on element " + by);
    }

    public static void setText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        logConsole("Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text " + value + " on element " + by);
        AllureManager.saveTextLog("Set text " + value + " on element " + by);

    }

    public static String getElementText(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        logConsole("Get text: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    //Wait for Element

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            logConsole("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            logConsole("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            logConsole("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            logConsole("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static void pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception _) {
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static WebElement highLightElement(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            logConsole("Javascript in NOT Ready!");
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }


    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            logConsole("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    public static void addCookieToBrowser(String key, String value){
        DriverManager.getDriver().manage().addCookie(new Cookie(key, value));
    }

    public void selectOption(By element, String option) {
        waitForPageLoaded();
        waitForElementPresent(element);
        Select select = new Select(getWebElement(element));
        select.selectByValue(option);
        AllureManager.saveTextLog("Select option " + element);
    }

    public Boolean isElementDisplayed(By by) {
        return !getWebElements(by).isEmpty();
    }

    public void switchIframe(String iframe) {
        sleep(1 / 2);
        DriverManager.getDriver().switchTo().frame(iframe);
        sleep(1 / 2);
    }

    public void switchIframe(WebElement iframe) {
        sleep(1);
        DriverManager.getDriver().switchTo().frame(iframe);
        sleep(1);
    }

    public void switchDefaultIframe() {
        sleep(1);
        DriverManager.getDriver().switchTo().defaultContent();
        sleep(1);
    }

    public void uploadFileToWeb(By by, String path) {
        getWebElement(by).sendKeys(path);
    }

    public void clickElementByJs(String css, int i) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("document.getElementsByClassName('" + css + "')[" + i + "].click()");
    }

    public void switchToWindowByTitle(String tabTitle) {
        Set<String> allWindowIDs = DriverManager.getDriver().getWindowHandles();
        for (String id : allWindowIDs) {
            DriverManager.getDriver().switchTo().window(id);
            String actualTitle = DriverManager.getDriver().getTitle();
            assert actualTitle != null;
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    public void closeAllTabWithoutParent( String parentID) {
        Set<String> allWindowIDs = DriverManager.getDriver().getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentID)) {
                DriverManager.getDriver().switchTo().window(id);
                DriverManager.getDriver().close();
            }
            DriverManager.getDriver().switchTo().window(parentID);
        }
    }

    public void closeTabByTittle(String tabTitle) {
        Set<String> allWindowIDs = DriverManager.getDriver().getWindowHandles();
        for (String id : allWindowIDs) {
            DriverManager.getDriver().switchTo().window(id);
            String actualTitle = DriverManager.getDriver().getTitle();
            assert actualTitle != null;
            if (actualTitle.equals(tabTitle)) {
                DriverManager.getDriver().close();
            }
        }
    }

    public void openNewTab(String url) {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManager.getDriver().navigate().to(url);
    }

    public void openNewTab() {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    public void openNewWindow(String url) {
        DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
        DriverManager.getDriver().navigate().to(url);
    }

    public void openNewWindow() {
        DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
    }

    public String getPageUrl() {
        return  DriverManager.getDriver().getCurrentUrl();
    }

    public void pressKeyFromKeyboard(By xpath, String key) {
        waitForElementVisible(xpath);
        switch (key.toLowerCase()) {
            case "enter":
                getWebElement(xpath).sendKeys(Keys.ENTER);
        }
    }
}
