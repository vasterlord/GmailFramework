package com.epam.lab.gmailframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {

    private Properties properties;

    private static final String START_URL = "startURL";
    private static final String USERS_XML_DATA_KEY = "usersXML";
    private static final String USERS_CSV_DATA_KEY = "usersCSV";
    private static final String USERS_XLSX_DATA_KEY = "usersXLSX";
    private static final String WEB_DRIVER_NAME_KEY = "webDriverName";
    private static final String WEB_DRIVER_PATH_KEY = "webDriverPath";
    private static final String TIME_OUT_IN_SECONDS = "timeOutInSeconds";

    public ConfigurationProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/main/resources/configuration.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStartURL () {
        return properties.getProperty(START_URL);
    }

    public String getUsersXMLData() {
        return properties.getProperty(USERS_XML_DATA_KEY);
    }

    public String getUsersCSVData() {
        return properties.getProperty(USERS_CSV_DATA_KEY);
    }

    public String getUsersXLSXData() {
        return properties.getProperty(USERS_XLSX_DATA_KEY);
    }

    public String getWebDriverName() {
        return properties.getProperty(WEB_DRIVER_NAME_KEY);
    }

    public String getWebDriverPath() {
        return properties.getProperty(WEB_DRIVER_PATH_KEY);
    }

    public int getTimeOutInSeconds() {
        return Integer.parseInt(properties.getProperty(TIME_OUT_IN_SECONDS));
    }

}

