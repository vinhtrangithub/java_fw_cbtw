package com.example.framework.pages.web;

import com.example.framework.core.BasePage;

public class LoginPage extends BasePage {
    private String usernameField = "id=identifier";
    private String passwordField = "id=password";
    private String loginButton = "css=button[type='submit']";

    public LoginPage() {
        super();
    }

    public void login(String username, String password) {
        getWebElementActions().enterText(usernameField, username);
        getWebElementActions().enterText(passwordField, password);
        getWebElementActions().clickElement(loginButton);
    }
}