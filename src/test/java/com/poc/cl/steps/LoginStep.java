package com.poc.cl.steps;

import com.poc.cl.pages.LoginPage;
import com.poc.cl.pages.ProductsPage;
import net.serenitybdd.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class LoginStep {

    @Page
    LoginPage loginPage;

    @Step("Login with username: {0} and password: {1}")
    public void login(String username, String password) {
       loginPage.getTxtUsername().type(username);
       loginPage.getTxtPassword().type(password);
       loginPage.getBtnLogin().click();
    }

}
