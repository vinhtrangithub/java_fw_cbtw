package com.example.tests.web;

import com.example.framework.annotations.WebTest;
import com.example.framework.config.ConfigReader;
import com.example.framework.core.BaseTest;
import com.example.framework.core.TestContext;
import com.example.framework.pages.web.LoginPage;
import com.example.framework.utils.JsonReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@WebTest
public class WebLoginTest extends BaseTest {
    @Test(dataProvider = "testData")
    public void testWebLogin(Map<String, String> data) {
        if (!TestContext.getPlatform().toString().equals("WEB")) return;
        LoginPage loginPage = new LoginPage();
        String url = ConfigReader.getWebUrl();
        loginPage.getWebElementActions().navigateTo(url);
        loginPage.login(data.get("username"), data.get("password"));
    
    }

    @DataProvider(name = "testData")
    public Object[] getTestData() {
        List<Map<String, String>> data = JsonReader.readTestData("testData.json");
        return data.toArray();
    }
}