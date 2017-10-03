package com.epam.lab.gmailframework.utils.testreporting;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

public class AllureStepListener extends LifecycleListener {

    private static final Logger LOGGER = Logger.getLogger(TestNGListener.class);

    @Step("{0}")
    public synchronized static void log(String message) {
        LOGGER.info("Logged to allure: " + message);
        Allure.LIFECYCLE.fire(new StepStartedEvent(message));
        Allure.LIFECYCLE.fire(new StepFinishedEvent());
        Reporter.log(message);
    }
}
