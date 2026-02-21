package com.example.tests.api;

import com.example.framework.api.services.AuthService;
import com.example.framework.api.services.UserService;
import com.example.framework.api.dto.LoginRequest;
import com.example.framework.api.dto.UpdateUserRequest;
import com.example.framework.core.BaseTest;
import com.example.framework.core.TestContext;
import com.example.framework.enums.PlatformType;
import com.example.framework.utils.AssertionUtils;
import org.testng.annotations.Test;

public class RequestApiTest extends BaseTest {

    private void loginSuccessfully() {
        new AuthService().login(
                new LoginRequest("eve.holt@reqres.in", "cityslicka")
        );
    }

    // ==========================
    // LOGIN TEST CASES (3)
    // ==========================

    @Test
    public void login_success() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        int statusCode = new AuthService()
                .login(new LoginRequest("eve.holt@reqres.in", "cityslicka"))
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "200",
                "Verify login success status code"
        );
    }

    @Test
    public void login_missing_password() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        int statusCode = new AuthService()
                .login(new LoginRequest("eve.holt@reqres.in", ""))
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "400",
                "Verify login missing password"
        );
    }

    @Test
    public void login_invalid_email() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        int statusCode = new AuthService()
                .login(new LoginRequest("invalid", "123"))
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "400",
                "Verify login invalid email"
        );
    }

    // ==========================
    // GET USER TEST CASES (3)
    // ==========================

    @Test
    public void get_user_success() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        loginSuccessfully();

        int statusCode = new UserService()
                .getUser(2)
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "200",
                "Verify get user success"
        );
    }

    @Test
    public void get_user_not_found() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        loginSuccessfully();

        int statusCode = new UserService()
                .getUser(9999)
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "404",
                "Verify get user not found"
        );
    }

    @Test
    public void get_user_without_login() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        int statusCode = new UserService()
                .getUser(2)
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "401",
                "Verify get user without auth"
        );
    }

    // ==========================
    // UPDATE USER TEST CASES (3)
    // ==========================

    @Test
    public void update_user_success() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        loginSuccessfully();

        int statusCode = new UserService()
                .updateUser(2, new UpdateUserRequest("vinh", "qa"))
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "200",
                "Verify update user success"
        );
    }

    @Test
    public void update_user_not_found() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        loginSuccessfully();

        int statusCode = new UserService()
                .updateUser(9999, new UpdateUserRequest("vinh", "qa"))
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "404",
                "Verify update user not found"
        );
    }

    @Test
    public void update_user_without_auth() {
        if (!TestContext.getPlatform().equals(PlatformType.API)) return;

        int statusCode = new UserService()
                .updateUser(2, new UpdateUserRequest("vinh", "qa"))
                .getStatusCode();

        AssertionUtils.assertEquals(
                String.valueOf(statusCode),
                "401",
                "Verify update user without auth"
        );
    }
}
