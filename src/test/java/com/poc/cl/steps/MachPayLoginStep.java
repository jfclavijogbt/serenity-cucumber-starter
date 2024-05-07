package com.poc.cl.steps;

import com.poc.cl.pages.MachPayLoginPage;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.annotations.locators.WaitForWebElements;
import org.fluentlenium.core.annotation.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachPayLoginStep {

    @Page
    private MachPayLoginPage machPayLoginPage;

    private Logger LOGGER = LoggerFactory.getLogger(MachPayLoginStep.class);

    @Step
    public void machPayLogin(String email, String password) {
        machPayLoginPage.getTxtEmail().type(email);
        machPayLoginPage.getTxtPassword().type(password);
        machPayLoginPage.getBtnLogin().click();
        machPayLoginPage.waitFor(2).seconds();
    }

    @Step
    public String getLoginError() {
        return machPayLoginPage.getLblLoginError().getText();
    }

    @Step
    public String getPageTitle() {
        return machPayLoginPage.getTitle();
    }

    @Step
    public boolean isLoginErrorPresent(String loginError) {
        machPayLoginPage.waitForLoginErrorVisible();
        String pageSource = machPayLoginPage.getDriver().getPageSource();
        LOGGER.info("Page source: " + pageSource);
        return pageSource.contains(loginError);
    }

}
