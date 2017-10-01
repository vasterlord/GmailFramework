package com.epam.lab.gmailframework.steps;

import com.epam.lab.gmailframework.businessobjects.GmailHomeBO;
import com.epam.lab.gmailframework.businessobjects.GmailLoginBO;
import com.epam.lab.gmailframework.utils.WebDriverUtils;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

import cucumber.annotation.en.When;
import org.junit.Assert;

public class GmailLogin {

    private GmailLoginBO gmailLoginBO = null;
    private GmailHomeBO gmailHomeBO = null;

    @Given("^I go to Gmail login page$")
    public void goToGmail() {
        gmailLoginBO = new GmailLoginBO();
    }

    @When("^\"([^\"]*)\" email entered$")
    public void enterEmail(String email) {
        gmailLoginBO.passLogin(email);
    }

    @And("^\"([^\"]*)\" password entered$")
    public void enterPassword(String password) throws Throwable {
        gmailLoginBO.passPassword(password);
    }

    @Then("^Compose button should be available$")
    public void composeButtonShouldBeAvailable() {
        gmailHomeBO = new GmailHomeBO();
        Assert.assertTrue(gmailHomeBO.isSignIn());
    }

    @And("^Quit$")
    public void quit(){
        WebDriverUtils.quit();
    }

}
