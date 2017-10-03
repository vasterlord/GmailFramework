package com.epam.lab.gmailframework.pageobjects;


import com.epam.lab.gmailframework.controls.Button;
import com.epam.lab.gmailframework.controls.Label;
import com.epam.lab.gmailframework.controls.TextInput;
import com.epam.lab.gmailframework.utils.WebDriverUtils;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GmailHomePage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(GmailHomePage.class);

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private Button writeActionElement;

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

    @Step(value = "wrt let")
    public void writeLetter(String receiverText, String subjectText, String contentLetterText) {
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji T-I-KE L3']");
        writeActionElement.click();
        LOGGER.info("Compose button was clicked");
        receiverElement.sendKeys(receiverText);
        subjectElement.sendKeys(subjectText);
        contentLetterElement.sendKeys(contentLetterText);
        LOGGER.info("Letter successfully was written");
        closeLetterActionElement.click();
        LOGGER.info("Letter was closed");
    }

    public boolean isLoggedIn() {
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji T-I-KE L3']");
        if (writeActionElement.isFullEnabled()) {
            LOGGER.info("User successfully logged in");
            return true;
        } else {
            return false;
        }
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
        if (letterSubjectElement.getText().equalsIgnoreCase(subjectText) && letterContentElement.getText().toLowerCase().contains(contentLetterText.toLowerCase())) {
            LOGGER.info(String.format("Message successfully saved in %s", lettersSectionName));
            return true;
        } else {
            LOGGER.info(String.format("Message didn't save in %s", lettersSectionName));
            return false;
        }
    }

    public void sendLetterFromDraft() {
        letterSubjectElement.click();
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji aoO T-I-atl L3']");
        sendLetterActionElement.click();
        LOGGER.info("Message from draft successfully sent");
    }

    private void getLettersFromSection(String maiLSectionURL) {
        this.webDriver.navigate().to(maiLSectionURL);
        LOGGER.info(String.format("Navigate to: %s", maiLSectionURL));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), configurationProperties.getTimeOutInSeconds()).until(ExpectedConditions.urlToBe(maiLSectionURL));
    }

}