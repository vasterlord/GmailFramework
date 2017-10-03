package com.epam.lab.gmailframework.utils.testreporting;

import org.testng.Reporter;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.events.*;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

public class AllureStepListener extends LifecycleListener {

    @Step("{0}")
    public synchronized static void log(String message) {
        Allure.LIFECYCLE.fire(new StepStartedEvent(message));
        Allure.LIFECYCLE.fire(new StepFinishedEvent());
        Allure.LIFECYCLE.fire(new StepFailureEvent());
        Reporter.log(message);
    }
}
