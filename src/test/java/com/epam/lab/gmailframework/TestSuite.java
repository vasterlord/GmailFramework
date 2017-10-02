package com.epam.lab.gmailframework;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
        features = "com.epam.lab.gmailframework/",
        format = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "junit:target/cucumber-junit-results.xml"
        },
        glue = "com.epam.lab.gmailframework.steps",
        tags = {"~@ignored"}
)

public class TestSuite {
}

