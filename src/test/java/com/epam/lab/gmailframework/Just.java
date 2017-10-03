package com.epam.lab.gmailframework;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Test title")
public class Just {

    private static final Logger LOGGER = Logger.getLogger(Just.class);

    @Title("first")
    @Test
    public void test(){
        Assert.assertTrue(true);
    }
}
