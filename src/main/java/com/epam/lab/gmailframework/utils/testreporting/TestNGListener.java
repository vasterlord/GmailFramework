package com.epam.lab.gmailframework.utils.testreporting;


import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.*;

public class TestNGListener implements ITestListener, ISuiteListener {
    private static final Logger LOGGER = Logger.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info(String.format("Test method has started running: %s", iTestResult.getMethod().getMethodName()));
    }

    @Step(value = "test")
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info(String.format("Test method :%s%s", iTestResult.getMethod().getMethodName(), " result was success"));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.error(String.format("Test method :%s%s", iTestResult.getMethod().getMethodName(), " result was failure"));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.error(String.format("Test method :%s%s", iTestResult.getMethod().getMethodName(), "  was skipped"));
    }

    @Step(value = "test")
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.info(String.format("About to begin executing test: %s", iTestContext.getName()));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("Passed tests: " + iTestContext.getPassedTests());
        LOGGER.error("Failed tests:" + iTestContext.getFailedTests());
    }

    @Override
    public void onStart(ISuite iSuite) {
        LOGGER.info(String.format("Begin executing suite: %s ", iSuite.getName()));
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LOGGER.info(String.format("The end executing suite: %s ", iSuite.getName()));
    }

}