package com.epam.lab.gmailframework.businessobjects;

import com.epam.lab.gmailframework.models.Letter;
import com.epam.lab.gmailframework.pageobjects.GmailHomePage;
import org.apache.log4j.Logger;

public class GmailHomeBO {

    private static final Logger LOGGER = Logger.getLogger(GmailHomeBO.class);

    private GmailHomePage gmailHomePage;

    public GmailHomeBO() {
        gmailHomePage = new GmailHomePage();
    }


    public boolean isLetterSavedInDraft(Letter letter) {
        gmailHomePage.writeLetter(letter.getReceiverEmail(), letter.getSubjectText(), letter.getContentLetter());
        gmailHomePage.getLetterFromDraft();
        return gmailHomePage.isSavedInDraft(letter.getSubjectText(), letter.getContentLetter());
    }

    public boolean isLetterSent(Letter letter) {
        gmailHomePage.sendLetterFromDraft();
        gmailHomePage.getLetterFromSent();
        return gmailHomePage.isSavedInSent(letter.getSubjectText(), letter.getContentLetter());
    }

}
