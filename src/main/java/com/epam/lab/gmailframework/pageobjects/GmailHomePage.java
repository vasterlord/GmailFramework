package com.epam.lab.gmailframework.pageobjects;


import com.epam.lab.gmailframework.controls.Button;
import com.epam.lab.gmailframework.controls.Label;
import com.epam.lab.gmailframework.controls.TextInput;
import com.epam.lab.gmailframework.utils.WebDriverUtils;
import com.epam.lab.gmailframework.utils.testreporting.AllureStepListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GmailHomePage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(GmailHomePage.class);

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private List<Button> writeActionElement;

    @FindBy(xpath = "//textarea[@class='vO']")
    private TextInput receiverElement;

    @FindBy(xpath = "//input[@class='aoT']")
    private TextInput subjectElement;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private TextInput contentLetterElement;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private Button sendLetterActionElement;

    @FindBy(xpath = "//table[@class='cf Ht']//img[@class='Ha']")
    private Button closeLetterActionElement;

    @FindBy(xpath = "(//table[@class='F cf zt']/tbody/tr[1]/td[6]/div[1]/div[1]/div[1]/span)[3]")
    private Label letterSubjectElement;

    @FindBy(xpath = "(//table[@class='F cf zt']/tbody/tr[1]/td[6]/div[1]/div[1]/div[1]/span)[4]")
    private Label letterContentElement;

    @FindBys({
            @FindBy(xpath = "//div[@class='n3']//a")
    })
    private List<Button> mailSections;

    public GmailHomePage() {
    }

    public void writeLetter(String receiverText, String subjectText, String contentLetterText) {
        writeActionElement.get(0).click();
        String logMessage = "Compose button was clicked";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        receiverElement.sendKeys(receiverText);
        subjectElement.sendKeys(subjectText);
        contentLetterElement.sendKeys(contentLetterText);
        logMessage = "Letter successfully was written";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        closeLetterActionElement.click();
        logMessage = "Letter was closed";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    public void getLetterFromDraft() {
        getLettersFromSection(mailSections.stream().filter(item -> item.getAttribute("href").toLowerCase().contains("drafts".toLowerCase())).findFirst().get().getAttribute("href"));
    }

    public void getLetterFromSent() {
        getLettersFromSection(mailSections.stream().filter(item -> item.getAttribute("href").toLowerCase().contains("sent".toLowerCase())).findFirst().get().getAttribute("href"));
    }

    public boolean isSavedInDraft(String subjectText, String contentLetterText) {
        return isSavedInSection(subjectText, contentLetterText, mailSections.stream().filter(item -> item.getAttribute("href").toLowerCase().
                contains("drafts".toLowerCase())).findFirst().get().getText());
    }

    public boolean isSavedInSent(String subjectText, String contentLetterText) {
        return isSavedInSection(subjectText, contentLetterText, mailSections.stream().filter(item -> item.getAttribute("href").toLowerCase().
                contains("sent".toLowerCase())).findFirst().get().getText());
    }

    private boolean isSavedInSection(String subjectText, String contentLetterText, String lettersSectionName) {
        boolean isSaved;
        if (letterSubjectElement.getText().equalsIgnoreCase(subjectText) && letterContentElement.getText().toLowerCase().contains(contentLetterText.toLowerCase())) {
            String logPositiveMessage = String.format("Message successfully saved in %s", lettersSectionName);
            AllureStepListener.log(logPositiveMessage);
            LOGGER.info(logPositiveMessage);
            isSaved = true;
        } else {
            String logNegativeMessage = String.format("Message didn't save in %s", lettersSectionName);
            AllureStepListener.log(logNegativeMessage);
            LOGGER.error(logNegativeMessage);
            isSaved = false;
        }
        return isSaved;
    }

    public void sendLetterFromDraft() {
        letterSubjectElement.click();
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji aoO T-I-atl L3']");
        sendLetterActionElement.click();
        String logMessage = "Message from draft successfully sent";
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
    }

    private void getLettersFromSection(String maiLSectionURL) {
        this.webDriver.navigate().to(maiLSectionURL);
        String logMessage = String.format("Navigate to: %s", maiLSectionURL);
        AllureStepListener.log(logMessage);
        LOGGER.info(logMessage);
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), configurationProperties.getTimeOutInSeconds()).until(ExpectedConditions.urlToBe(maiLSectionURL));
    }

}