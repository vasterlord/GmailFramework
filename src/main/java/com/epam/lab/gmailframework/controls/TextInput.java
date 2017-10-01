package com.epam.lab.gmailframework.controls;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextInput extends Element {

    private static final Logger LOGGER = Logger.getLogger(TextInput.class);

    public TextInput(WebElement webElement) {
        super(webElement);

    }

    public boolean isVisible() {
        return this.webElement.getCssValue("visibility").equalsIgnoreCase("visible");
    }

    public boolean isFullEnabled() {
        return this.webElement.isEnabled() && this.webElement.isDisplayed() && this.isVisible();
    }

    public void click() {
        if (isFullEnabled()) {
            this.webElement.click();
        } else {
            LOGGER.error("This element is not clickable!");
        }
    }

    public String getText() {
        return this.webElement.getAttribute("innerText");
    }

    public void sendKeys(CharSequence... charSequences) {
        if (isFullEnabled()) {
            this.webElement.sendKeys(charSequences);
        } else {
            LOGGER.error("This element is not enabled!");
        }
    }

}