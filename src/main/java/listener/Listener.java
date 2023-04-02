package listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(Listener.class);
    @Override
    public void onTestStart(ITestResult result) {
// TODO Auto-generated method stub
        logger.info("onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
// TODO Auto-generated method stub
        logger.info("Success of test cases and its details are : "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
// TODO Auto-generated method stub
        logger.info("Failure of test cases and its details are : "+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
// TODO Auto-generated method stub
        logger.info("Skip of test cases and its details are : "+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub
        logger.info("Failure of test cases and its details are : "+result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("onStart(");
// TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
        logger.info("onFinish");
    }
}
