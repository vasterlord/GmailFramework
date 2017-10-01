package com.epam.lab.gmailframework.models;

import javax.xml.bind.annotation.XmlElement;

public class User {

    private String userEmail;
    private String userPassword;
    private Letter letter;

    public User() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @XmlElement
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Letter getLetter() {
        return letter;
    }

    @XmlElement
    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    @XmlElement
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", letter=" + letter +
                '}'+"\n";
    }
}
