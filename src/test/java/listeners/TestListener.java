package listeners;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelpers;
import helpers.FileHelpers;
import helpers.PropertiesHelpers;
import org.testng.*;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelpers.loadAllFiles();
        FileHelpers.cleanFiles("ExportData/");

    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End testing " + result.getName());

        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
//        CaptureHelpers.stopRecord();

    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Running test case " + result.getName());
        count_totalTCs = count_totalTCs + 1;
//        CaptureHelpers.startRecord(result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");
        count_passedTCs = count_passedTCs + 1;

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        count_failedTCs = count_failedTCs + 1;
        //Screenshot khi fail
        CaptureHelpers.captureScreenshot(result.getName());
        LogUtils.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;

        LogUtils.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.error("Test failed but it is in defined success ratio: " + getTestName(result));
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio: " + getTestName(result));
        LogUtils.error(result.getThrowable().toString());
    }

}
