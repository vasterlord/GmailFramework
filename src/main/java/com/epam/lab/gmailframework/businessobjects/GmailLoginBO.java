package com.epam.lab.gmailframework.businessobjects;


import com.epam.lab.gmailframework.models.User;
import com.epam.lab.gmailframework.pageobjects.GmailLoginPage;

public class GmailLoginBO {

    private GmailLoginPage gmailLoginPage;

    public boolean isSignIn(User user) {
        gmailLoginPage = new GmailLoginPage();
        boolean singInResult = false;
        if (gmailLoginPage.typeLoginAndSubmit(user.getUserEmail())) {
            singInResult = true;
        }
        if (gmailLoginPage.typePasswordAndSubmit(user.getUserPassword())) {
            singInResult = true;
        }
        return singInResult;
    }
}
