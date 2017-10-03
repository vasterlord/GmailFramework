package com.epam.lab.gmailframework.pageobjects;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AllureLogger {
    private static final Logger logger = LogManager.getLogger(AllureLogger.class);

    @Step("{0}")
    public static void logToAllure(String log) {
        logger.debug("Logged to allure: " + log);
    }
}
