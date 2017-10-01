package com.epam.lab.gmailframework.models;


import javax.xml.bind.annotation.XmlElement;


public class Letter {
    private String receiverEmail;
    private String subjectText;
    private String contentLetter;
    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getSubjectText() {
        return subjectText;
    }

    public String getContentLetter() {
        return contentLetter;
    }


    @XmlElement
    public void setContentLetter(String contentLetter) {
        this.contentLetter = contentLetter;
    }

    @XmlElement
    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    @XmlElement
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }




    @Override
    public String toString() {
        return "Letter{" +
                "receiverEmail='" + receiverEmail + '\'' +
                ", subjectText='" + subjectText + '\'' +
                ", contentLetter='" + contentLetter + '\'' +
                '}';
    }
}
