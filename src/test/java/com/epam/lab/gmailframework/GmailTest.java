package com.epam.lab.gmailframework;

import com.epam.lab.gmailframework.businessobjects.GmailHomeBO;
import com.epam.lab.gmailframework.businessobjects.GmailLoginBO;
import com.epam.lab.gmailframework.models.User;
import com.epam.lab.gmailframework.pageobjects.GmailLoginPage;
import com.epam.lab.gmailframework.utils.testreporting.AllureStepListener;
import com.epam.lab.gmailframework.utils.DataUtils;
import com.epam.lab.gmailframework.utils.WebDriverUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class GmailTest {

    private static final Logger LOGGER = Logger.getLogger(GmailLoginPage.class);

    @Attachment
    @Step
    @AfterMethod
    public void tearDown() throws Exception {
        String quiteLogMessage = "@AfterMethod";
        AllureStepListener.log(quiteLogMessage);
        LOGGER.info(quiteLogMessage);
        WebDriverUtils.quit();
    }

    @Test(dataProvider = "testData", threadPoolSize = 3)
    public void testGmailFunctionality(User user) {
//        String quiteLogMessage = "@Test method";
//        AllureStepListener.log(quiteLogMessage);
//        LOGGER.info(quiteLogMessage);
        GmailLoginBO gmailLoginBO = new GmailLoginBO();
        gmailLoginBO.signIn(user);
        GmailHomeBO gmailHomeBO = new GmailHomeBO();
        Assert.assertTrue(gmailHomeBO.isSignIn());
        Assert.assertTrue(gmailHomeBO.isLetterSavedInDraft(user.getLetter()));
        Assert.assertTrue(gmailHomeBO.isLetterSent(user.getLetter()));
    }

    @DataProvider(name = "testData", parallel = true)
    public static Object[][] getTestData() throws Exception {
        List<User> userList = DataUtils.getUsersDataFromXML().getUsers();
        int rowCount = userList.size();
        int columnCount = 1;
        Object[][] testData = new Object[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            testData[i][0] = userList.get(i);
        }
        return testData;
    }

}