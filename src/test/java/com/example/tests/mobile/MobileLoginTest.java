package com.example.tests.mobile;

import com.example.framework.annotations.MobileTest;
import com.example.framework.core.BaseTest;
import com.example.framework.core.TestContext;
import com.example.framework.enums.PlatformType;
import com.example.framework.pages.mobile.MobileLoginPage;
import com.example.framework.utils.AssertionUtils;
import com.example.framework.utils.JsonReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@MobileTest
public class MobileLoginTest extends BaseTest {
    @Test(dataProvider = "testData")
    public void testMobileLogin(Map<String, String> data) {
        PlatformType platform = TestContext.getPlatform();
        if (platform != PlatformType.ANDROID && platform != PlatformType.IOS) {
            return; // Skip test if not ANDROID or IOS platform
        }

        MobileLoginPage loginPage = new MobileLoginPage();
        loginPage.login(data.get("username"), data.get("password"));
        AssertionUtils.assertTrue(loginPage.getWebElementActions().isElementDisplayed(loginPage.getSuccessMessage()),
                "Login success message should be displayed");
    }

    @DataProvider(name = "testData")
    public Object[] getTestData() {
        try {
            List<Map<String, String>> data = JsonReader.readTestData("testdata.json");
            return data.stream()
                    .filter(d -> d.get("platform").equals("ANDROID") || d.get("platform").equals("IOS"))
                    .toArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }
}