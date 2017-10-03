package com.epam.lab.gmailframework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    private static final Logger LOGGER = Logger.getLogger(WebDriverUtils.class);
    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver getWebDriverThreadLocal() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties();
        if (WEB_DRIVER_THREAD_LOCAL.get() != null) {
            return WEB_DRIVER_THREAD_LOCAL.get();
        }
        System.setProperty(configurationProperties.getWebDriverName(), configurationProperties.getWebDriverPath());
        WebDriver webDriverInstance = new ChromeDriver();
        WEB_DRIVER_THREAD_LOCAL.set(webDriverInstance);
        //WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();
        WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(configurationProperties.getTimeOutInSeconds(), TimeUnit.SECONDS);
        return WEB_DRIVER_THREAD_LOCAL.get();
    }

    private WebDriverUtils() {
    }

    public static void quit() {
        try {
            WEB_DRIVER_THREAD_LOCAL.get().quit();
        } finally {
            WEB_DRIVER_THREAD_LOCAL.remove();
        }

    }

}
