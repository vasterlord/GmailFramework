package com.epam.lab.gmailframework.pageobjects;

import com.epam.lab.gmailframework.controls.decorataion.CustomFieldDecorator;
import com.epam.lab.gmailframework.utils.ConfigurationProperties;
import com.epam.lab.gmailframework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    protected ConfigurationProperties configurationProperties;
    protected WebDriver webDriver = WebDriverUtils.getWebDriverThreadLocal();

    public PageObject() {
        configurationProperties = new ConfigurationProperties();
        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
    }

    protected void waitPresenceOfElement(String locator) {
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), configurationProperties.getTimeOutInSeconds()).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), configurationProperties.getTimeOutInSeconds()).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), configurationProperties.getTimeOutInSeconds()).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    protected void waitPresenceOfElement(String locator, int timeOutInSeconds) {
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
}
