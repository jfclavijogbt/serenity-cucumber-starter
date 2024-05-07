package com.poc.cl.pages;

import lombok.Getter;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPage extends PageObject {

    @FindBy(id = "user-name")
    protected WebElementFacade txtUsername;

    @FindBy(id = "password")
    protected WebElementFacade txtPassword;

    @FindBy(id = "login-button")
    protected WebElementFacade btnLogin;


}
