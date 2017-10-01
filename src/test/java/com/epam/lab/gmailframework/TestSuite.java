package com.epam.lab.gmailframework;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
        features = "com.epam.lab.gmailframework/",
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report",
                "json:target/cucumber-reports/cucumber-json-report/cucumber.json",
                "junit:target/cucumber-reports/cucumber-xml-reports/cucumber-junit-results.xml"
        },
        glue = "com.epam.lab.gmailframework.steps",
        tags = {"~@ignored"}
)

public class TestSuite {
}

