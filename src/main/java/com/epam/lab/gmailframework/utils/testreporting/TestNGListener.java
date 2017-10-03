package com.epam.lab.gmailframework.utils.testreporting;

import org.apache.log4j.Logger;
import org.testng.*;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.testng.AllureTestListener;

public class TestNGListener implements ITestListener, ISuiteListener {

    private static final Logger LOGGER = Logger.getLogger(TestNGListener.class);

    @Attachment
    @Step
    @Override
    public void onTestStart(ITestResult iTestResult) {
        String logMessage = String.format("Test method has started running: %s", iTestResult.getMethod().getMethodName());
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    @Attachment
    @Step
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String logMessage = String.format("Test method :%s%s", iTestResult.getMethod().getMethodName(), " result was success");
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    @Attachment
    @Step
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String logMessage = String.format("Test method :%s%s", iTestResult.getMethod().getMethodName(), " result was failure");
        AllureStepListener.log(logMessage);
        LOGGER.error(logMessage);
    }

    @Attachment
    @Step
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String logMessage = String.format("Test method :%s%s", iTestResult.getMethod().getMethodName(), "  was skipped");
        AllureStepListener.log(logMessage);
        LOGGER.error(logMessage);
    }

    @Attachment
    @Step
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Attachment
    @Step
    @Override
    public void onStart(ITestContext iTestContext) {
        String logMessage = String.format("About to begin executing test: %s", iTestContext.getName());
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    @Attachment
    @Step
    @Override
    public void onFinish(ITestContext iTestContext) {
        String logPassedTestsMessage = String.format("Passed tests: %s", iTestContext.getPassedTests());
        AllureStepListener.log(logPassedTestsMessage);
        LOGGER.info(logPassedTestsMessage);
        String logPFailedTestsMessage = String.format("Failed tests: %s", iTestContext.getFailedTests());
        AllureStepListener.log(logPFailedTestsMessage );
        LOGGER.info(logPFailedTestsMessage );
    }

    @Attachment
    @Step
    @Override
    public void onStart(ISuite iSuite) {
        String logMessage = String.format("Begin executing suite: %s ", iSuite.getName());
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    @Attachment
    @Step
    @Override
    public void onFinish(ISuite iSuite) {
        String logMessage = String.format("The end executing suite: %s ", iSuite.getName());
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

}