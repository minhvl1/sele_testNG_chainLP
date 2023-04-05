package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName) {
        if (driver == null) {
            try {
                switch (browserName) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new FirefoxDriver(firefoxOptions);
                        break;

                    case "hfirefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions hfirefoxOptions = new FirefoxOptions();
                        hfirefoxOptions.addArguments("--headless");
                        driver = new FirefoxDriver(hfirefoxOptions);
                        break;

                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("start-maximized");
                        options.addArguments("--remote-allow-origins=*");
                        driver = new ChromeDriver(options);
                        break;

                    case "hchrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptionsoptions = new ChromeOptions();
                        chromeOptionsoptions.addArguments("--headless");
                        chromeOptionsoptions.addArguments("--disable-dev-shm-usage");
                        chromeOptionsoptions.addArguments("--no-sandbox");
                        chromeOptionsoptions.addArguments("--remote-allow-origins=*");
                        driver = new ChromeDriver(chromeOptionsoptions);
                        break;

                    case "hedge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions hegdeoptions = new EdgeOptions();
                        hegdeoptions.addArguments("--headless");
                        driver = new EdgeDriver(hegdeoptions);
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("start-maximized");
                        driver = new EdgeDriver(edgeOptions);
                        break;

                    default:
//                        driver = new FirefoxDriver();
                        driver = new EdgeDriver();
                        break;

                }
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    @AfterTest
    public void TearDown() {
        driver.close();
    }

    private class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            close();
        }
    }

    public void close() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close the browser");
        }
    }

}
