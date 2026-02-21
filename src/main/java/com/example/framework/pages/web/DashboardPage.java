package com.example.framework.pages.web;

import com.example.framework.core.BasePage;

public class DashboardPage extends BasePage {
    private String titleText = "xpath=//h3[normalize-space()='Get Started']";
    private String challengesDropdownIcon = "xpath=//a[@id='navbarDropdownMenuLink']/following-sibling::a";
    private String challengesLink = "css=#navbarDropdownMenuLink";
    private String createChallengesLink = "xpath=//a[normalize-space()='Create Challenge']";
    private String myChallengesLink = "//div[contains(@class,'dropdown-menu') and @aria-labelledby='navbarDropdownMenuLink']//a[normalize-space()='My Challenges']";

    // Constructor
    public DashboardPage() {
        super();
    }

    public boolean isDashboardPage() {
        if(getWebElementActions().getElementText(titleText).compareTo("Get Started") != 0)
            return false;
        
        return true;
    }

    // Method to navigate to Challenges page
    public void navigateToChallenges() {
        getWebElementActions().clickElement(challengesLink);
    }

    // Method to navigate to Create Challenge page
    public void navigateToCreateChallenge() {
        getWebElementActions().clickElement(challengesDropdownIcon);
        getWebElementActions().clickElement(createChallengesLink);
    }

    // Method to navigate to My Challenges page
    public void navigateToMyChallenges() {
        getWebElementActions().clickElement(challengesDropdownIcon);
        getWebElementActions().clickElement(myChallengesLink);
    }
}
