package com.epam.lab.gmailframework.businessobjects;


import com.epam.lab.gmailframework.models.User;
import com.epam.lab.gmailframework.pageobjects.GmailLoginPage;

public class GmailLoginBO {

    private GmailLoginPage gmailLoginPage;

    public void signIn(User user) {
        gmailLoginPage = new GmailLoginPage();
        gmailLoginPage.typeLoginAndSubmit(user.getUserEmail());
        gmailLoginPage.typePasswordAndSubmit(user.getUserPassword());
    }

    public void passLogin(String user) {
        gmailLoginPage = new GmailLoginPage();
        gmailLoginPage.typeLoginAndSubmit(user);
    }

    public void passPassword(String user) {
        gmailLoginPage.typePasswordAndSubmit(user);
    }
}
