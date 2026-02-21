package com.example.framework.pages.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.framework.core.BasePage;

public class MyChallengePage extends BasePage{

    private String challengeCards = "css=div.card.challenge-card";

    public MyChallengePage() {
        super();
    }

    public boolean isChallengeDisplayed(String challengeName) {

        List<WebElement> cards = getWebElementActions().getElements(challengeCards);
        for (WebElement card : cards) {
            String title = card
                    .findElement(By.cssSelector(".card-header span"))
                    .getText()
                    .trim();

            if (title.equalsIgnoreCase(challengeName)) {
                return true;
            }
        }
        return false;
    }
}
