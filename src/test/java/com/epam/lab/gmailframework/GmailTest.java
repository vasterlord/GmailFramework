package com.epam.lab.gmailframework;

import com.epam.lab.gmailframework.businessobjects.GmailHomeBO;
import com.epam.lab.gmailframework.businessobjects.GmailLoginBO;
import com.epam.lab.gmailframework.models.User;
import com.epam.lab.gmailframework.utils.DataUtils;
import com.epam.lab.gmailframework.utils.WebDriverUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

public class GmailTest {

    @AfterMethod
    public void tearDown() throws Exception {
        WebDriverUtils.quit();
    }

    @Test(dataProvider = "testData")
    public void testGmailFunctionality(User user) {
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