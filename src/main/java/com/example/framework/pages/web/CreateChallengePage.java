package com.example.framework.pages.web;

import com.example.framework.core.BasePage;

public class CreateChallengePage extends BasePage{
    private String createChallengeTitle = "xpath=//span[normalize-space()='Create A Challenge']";
    private String titleField = "id=title";
    private String flagField = "id=flag";
    private String descriptionField = "name=description";
    private String howtosolveField = "id=howtosolve";
    private String submitButton = "css=button[type='submit']";

    public CreateChallengePage() {
        super();
    }

    public void createChallenge(String title, String flag, String description, String howToSolve) {
        getWebElementActions().enterText(titleField, title);
        getWebElementActions().enterText(flagField, flag);
        getWebElementActions().enterText(descriptionField, description);
        getWebElementActions().enterText(howtosolveField, howToSolve);
        getWebElementActions().clickElement(submitButton);
    }
}
