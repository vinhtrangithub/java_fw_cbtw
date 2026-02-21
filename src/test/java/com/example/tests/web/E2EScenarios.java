package com.example.tests.web;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.framework.annotations.WebTest;
import com.example.framework.config.ConfigReader;
import com.example.framework.core.BaseTest;
import com.example.framework.core.TestContext;
import com.example.framework.pages.web.CreateChallengePage;
import com.example.framework.pages.web.DashboardPage;
import com.example.framework.pages.web.LoginPage;
import com.example.framework.pages.web.MyChallengePage;
import com.example.framework.utils.JsonReader;
import com.example.framework.utils.RandomUtils;

@WebTest
public class E2EScenarios extends BaseTest{
    @Test(dataProvider = "challengedata")
    public void testWebCreateChallenge(Map<String, String> data) {
        if (!TestContext.getPlatform().toString().equals("WEB")) return;
        
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        CreateChallengePage createChallengePage = new CreateChallengePage();
        MyChallengePage myChallengePage = new MyChallengePage();

        String url = ConfigReader.getWebUrl();
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();

        String titleString = RandomUtils.generateUniqueTitle();
        String flagString = RandomUtils.generateCTFFlag();
        String descriptionString = data.get("description");
        String howtosolveString = data.get("howtosolve");

        loginPage.getWebElementActions().navigateTo(url);
        loginPage.login(username,password);
        //verify the dashboard page is displayed
        dashboardPage.isDashboardPage();

        dashboardPage.navigateToCreateChallenge();

        createChallengePage.createChallenge(titleString, flagString, descriptionString, howtosolveString);

        dashboardPage.navigateToMyChallenges();

        myChallengePage.isChallengeDisplayed(titleString);
    }

    @DataProvider(name = "challengedata")
    public Object[] getTestData() {
        List<Map<String, String>> data = JsonReader.readTestData("challengedata.json");
        return data.toArray();
    }
}
