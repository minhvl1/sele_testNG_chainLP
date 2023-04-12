package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage {
    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public WebElement getElementByXpath(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    private Duration longTimeout = FrameworkConstant.IMPLICIT_WAIT_DEFAULT;

    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorType)));
    }

    public void clickToElement(WebDriver driver, String xpath) {
        waitForElementVisible(driver, xpath);
        getElementByXpath(driver, xpath).click();
    }

    public void pressKeyFromKeyboard(WebDriver driver, String xpath, String key) {
        waitForElementVisible(driver, xpath);
        switch (key.toLowerCase()) {
            case "enter":
                getElementByXpath(driver, xpath).sendKeys(Keys.ENTER);
        }
    }

    public void sendKeyToElement(WebDriver driver, String key, String xpath) {
        waitForElementVisible(driver, xpath);
        getElementByXpath(driver, xpath).sendKeys(key);
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    public void closeTabByTittle(WebDriver driver, String tabTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                driver.close();
            }
        }
    }

    public void openNewTab(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to(url);
    }

    public void openNewTab(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void openNewWindow(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to(url);
    }

    public void openNewWindow(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public Boolean isElementDisplayed(WebDriver driver, String locatorType) {
        waitForElementVisible(driver, locatorType);
        return getElementByXpath(driver, locatorType).isDisplayed();
    }


    public boolean isElementPresent(WebDriver driver, String xpath) {
        int sizeElements = driver.findElements(By.xpath(xpath)).size();
        if (sizeElements > 0) {
            return true;
        } else {
            return false;
        }
    }

}
