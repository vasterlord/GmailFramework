package com.epam.lab.gmailframework.pageobjects;


import com.epam.lab.gmailframework.controls.Button;
import com.epam.lab.gmailframework.controls.TextInput;
import com.epam.lab.gmailframework.utils.testreporting.AllureStepListener;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
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

    @Step(value = "login")
    public void typeLoginAndSubmit(String email) {
        String logMessage = "Entering credentials...";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        inputEmailElement.sendKeys(email);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.clickAndHold(this.webDriver);
    }

    public void typePasswordAndSubmit(String password) {
        waitPresenceOfElement("//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']");
        passwordElement.sendKeys(password);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.clickAndHold(this.webDriver);
    }
}
