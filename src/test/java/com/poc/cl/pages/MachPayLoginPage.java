package com.poc.cl.pages;

import lombok.Getter;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

@Getter
public class MachPayLoginPage extends PageObject {

    @FindBy(id = "mat-input-0")
    protected WebElementFacade txtEmail;

    @FindBy(id = "mat-input-1")
    protected WebElementFacade txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElementFacade btnLogin;

    @FindBy(className = "snackbar__content")
    protected WebElementFacade lblLoginError;

    public void waitForLoginErrorVisible() {
        withTimeoutOf(Duration.ofSeconds(5)).waitFor(lblLoginError).isVisible();
    }


}
