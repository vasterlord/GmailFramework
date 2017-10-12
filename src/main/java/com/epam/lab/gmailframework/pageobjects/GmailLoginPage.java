package com.epam.lab.gmailframework.pageobjects;


import com.epam.lab.gmailframework.controls.Button;
import com.epam.lab.gmailframework.controls.TextInput;
import com.epam.lab.gmailframework.utils.testreporting.AllureStepListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(GmailLoginPage.class);
    private static final int WRONG_MESSAGE_ELEMENT_TIME_WAITING = 5;

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    private Button signInElement;

    @FindBy(xpath = "//input[normalize-space(@id)='identifierId']")
    private TextInput inputEmailElement;

    @FindBy(xpath = "//content[@class='CwaK9']/span[1]")
    private Button nextClickElement;

    @FindBy(xpath = "//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']")
    private TextInput passwordElement;

    @FindBy(xpath = "//div[normalize-space(@class)='dEOOab RxsGPe'][1]")
    private WebElement wrongPasswordTextElement;

    public GmailLoginPage() {
        AllureStepListener.log("Starting browser");
        LOGGER.info("Starting browser");
        String startUrl = configurationProperties.getStartURL();
        this.webDriver.navigate().to(startUrl);
        String logMessage = String.format("Navigate to: %s", startUrl);
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        String signInUrl = signInElement.getAttribute("href");
        this.webDriver.navigate().to(signInUrl);
        logMessage = String.format("Navigate to: %s", signInUrl);
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    public boolean typeLoginAndSubmit(String email) {
        String logMessage = "Entering credentials...";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        logMessage =  "Input email";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        inputEmailElement.sendKeys(email);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.click();
        return !waitForWrongMessageElement() || verifyWrongMessage();

    }

    public boolean typePasswordAndSubmit(String password) {
        waitPresenceOfElement("//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']");
        String logMessage =  "Input password";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        passwordElement.sendKeys(password);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.click();
        return !waitForWrongMessageElement() || verifyWrongMessage();
    }

    private boolean waitForWrongMessageElement() {
        try {
            waitPresenceOfElement("//div[normalize-space(@class)='dEOOab RxsGPe'][1]", WRONG_MESSAGE_ELEMENT_TIME_WAITING);
            return true;
        } catch (TimeoutException e) {
            LOGGER.info("Tried to wait for sing in wrong message. Wrong sign in message wasn't detected");
            return false;
        }
    }

    private boolean verifyWrongMessage() {
        String logMessage;
        boolean result;
        if (!wrongPasswordTextElement.getText().isEmpty()) {
            logMessage = String.format("Gmail error message: %s", wrongPasswordTextElement.getAttribute("innerText"));
            AllureStepListener.log(logMessage);
            LOGGER.error(logMessage);
            result = false;
        } else {
            result = true;
        }
        return result;
    }

}