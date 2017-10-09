package com.epam.lab.gmailframework.businessobjects;


import com.epam.lab.gmailframework.models.User;
import com.epam.lab.gmailframework.pageobjects.GmailLoginPage;

public class GmailLoginBO {

    private GmailLoginPage gmailLoginPage;

    public GmailLoginBO() {
        gmailLoginPage = new GmailLoginPage();
    }

    public boolean signIn(User user) {
        return gmailLoginPage.typeLoginAndSubmit(user.getUserEmail()) && gmailLoginPage.typePasswordAndSubmit(user.getUserPassword());
    }
}