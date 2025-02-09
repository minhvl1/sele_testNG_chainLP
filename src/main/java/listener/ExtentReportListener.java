package listener;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import extentreport.ExtentManager;
import extentreport.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Log;

public class ExtentReportListener extends BaseTest implements ITestListener {
    public String getTestMethodName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestMethodName(result);
    }

    public WebDriver getDriver(ITestResult result){
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getWebDriver();
        return driver;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("Start testing " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("End testing " + iTestContext.getName());
        ExtentManager.createExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting...");
        ExtentTestManager.startTest(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is passed.");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.error(getTestMethodName(iTestResult) + " test is failed.");
        ExtentTestManager.addScreenShot(Status.FAIL, getTestMethodName(iTestResult), getDriver(iTestResult));

        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn(getTestMethodName(iTestResult) + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, getTestMethodName(iTestResult) + " test is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
