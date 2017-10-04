package com.epam.lab.gmailframework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static int counter = 0;
    private static final int THREADS_COUNT_IN_ONE_TIME = 3;

    public static WebDriver getWebDriverThreadLocal() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties();
        if (WEB_DRIVER_THREAD_LOCAL.get() != null) {
            return WEB_DRIVER_THREAD_LOCAL.get();
        }
        System.setProperty(configurationProperties.getWebDriverName(), configurationProperties.getWebDriverPath());
        synchronized (WEB_DRIVER_THREAD_LOCAL) {
            while (counter == THREADS_COUNT_IN_ONE_TIME) {
                WEB_DRIVER_THREAD_LOCAL.notify();
            }
            WebDriver webDriverInstance = new ChromeDriver();
            WEB_DRIVER_THREAD_LOCAL.set(webDriverInstance);
            counter++;
            WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();
            WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().setScriptTimeout(configurationProperties.getTimeOutInSeconds(), TimeUnit.SECONDS);
            WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().pageLoadTimeout(configurationProperties.getTimeOutInSeconds(), TimeUnit.SECONDS);
            WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(configurationProperties.getTimeOutInSeconds(), TimeUnit.SECONDS);
            return WEB_DRIVER_THREAD_LOCAL.get();
        }
    }

//    public static WebDriver getWebDriverThreadLocal() {
//        ConfigurationProperties configurationProperties = new ConfigurationProperties();
//        if (WEB_DRIVER_THREAD_LOCAL.get() != null) {
//            return WEB_DRIVER_THREAD_LOCAL.get();
//        }
//        System.setProperty(configurationProperties.getWebDriverName(), configurationProperties.getWebDriverPath());
//        WebDriver webDriverInstance = new ChromeDriver();
//        WEB_DRIVER_THREAD_LOCAL.set(webDriverInstance);
//        //WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();
//        WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(configurationProperties.getTimeOutInSeconds(), TimeUnit.SECONDS);
//        return WEB_DRIVER_THREAD_LOCAL.get();
//    }

    private WebDriverUtils() {
    }

    public static void quit() {
        try {
            WEB_DRIVER_THREAD_LOCAL.get().quit();
            counter--;
        } finally {
            WEB_DRIVER_THREAD_LOCAL.remove();
        }

    }

}
