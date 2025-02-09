package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentReportManager.getExtentReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

        getTest().log(Status.INFO, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void addScreenShot(Status status, String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

        getTest().log(status, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }

    public static void logMessage(Status status, Object message) {
       getTest().log(status, (Throwable) message);
    }

    public static void pass(String message) {
        //System.out.println("ExtentReportManager class: " + getTest());
        getTest().pass(message);
    }

    public static void pass(Markup message) {
        getTest().pass(message);
    }

    public static void fail(String message) {
        getTest().fail(message);
    }

    public static void fail(Object message) {
        getTest().fail((String) message);
    }

    public static void fail(Markup message) {
        getTest().fail(message);
    }

    public static void skip(String message) {
        getTest().skip(message);
    }

    public static void skip(Markup message) {
        getTest().skip(message);
    }

    public static void info(Markup message) {
        getTest().info(message);
    }

    public static void info(String message) {
        getTest().info(message);
    }

    public static void warning(String message) {
        getTest().log(Status.WARNING, message);
    }
}
