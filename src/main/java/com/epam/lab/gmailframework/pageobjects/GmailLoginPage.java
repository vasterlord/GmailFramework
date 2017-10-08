package com.epam.lab.gmailframework.pageobjects;

import com.epam.lab.gmailframework.controls.Button;
import com.epam.lab.gmailframework.controls.TextInput;
import com.epam.lab.gmailframework.utils.testreporting.AllureStepListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(GmailLoginPage.class);

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
        String logMessage = "Starting browser";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        String startUrl = configurationProperties.getStartURL();
        this.webDriver.navigate().to(startUrl);
        logMessage = String.format("Navigate to: %s", startUrl);
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
        inputEmailElement.sendKeys(email);
        logMessage = String.format("Entered email: %s ", email);
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.clickAndHold(this.webDriver);
        return verifyWrongMessage();
    }

    public boolean typePasswordAndSubmit(String password) {
        waitPresenceOfElement("//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']");
        passwordElement.sendKeys(password);
        String logMessage = String.format("Entered password: %s ", password);
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.clickAndHold(this.webDriver);
        return verifyWrongMessage();
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
