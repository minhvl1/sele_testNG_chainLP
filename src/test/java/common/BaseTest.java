package common;
import driver.DriverManager;

import helpers.Constructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


public class BaseTest {
    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "url"})
    public void createDriver(@Optional("edge") String browserName, String url) {
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(url);
//        LogUtils.info("ENVIRONMENT: " + FrameworkConstants.ENVIRONMENT);
    }

    public WebDriver setupBrowser(String browserName) {
        return switch (browserName.trim().toLowerCase()) {
            case "chrome" -> initChromeDriver();
            case "firefox" -> initFirefoxDriver();
            case "edge" -> initEdgeDriver();
            case "hedge" -> initHeadlessEdgeDriver();
            case "hchrome" -> initHeadlessChromeDriver();
            default -> {
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                yield initEdgeDriver();
            }
        };
    }

    // Viết các hàm khởi chạy cho từng Browser đó
    private WebDriver initChromeDriver() {
        WebDriver driver;
        System.out.println("Launching Chrome browser...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        return driver;
    }

    private WebDriver initHeadlessChromeDriver() {
        WebDriver driver;
        System.out.println("Launching Chrome browser...");
        ChromeOptions chromeOptionsoptions = new ChromeOptions();
        chromeOptionsoptions.addArguments("--headless");
        chromeOptionsoptions.addArguments("--disable-dev-shm-usage");
        chromeOptionsoptions.addArguments("--no-sandbox");
        chromeOptionsoptions.addArguments("--remote-allow-origins=*");
        chromeOptionsoptions.addArguments("--window-size=2560x1440");
        driver = new ChromeDriver(chromeOptionsoptions);
        return driver;
    }

    private WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");
        driver = new EdgeDriver(edgeOptions);
        return driver;
    }

    private WebDriver initHeadlessEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");
        EdgeOptions hedgeOptions = new EdgeOptions();
        hedgeOptions.addArguments("--headless");
        hedgeOptions.addArguments("--window-size=2560x1440");
        driver = new EdgeDriver(hedgeOptions);
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("Launching Firefox browser...");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterSuite(alwaysRun = true)
    public void closeDriver() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Close Browser:" + DriverManager.getDriver());

        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
        Constructor.cleanProductId();
        Constructor.cleanProductName();
    }
}
