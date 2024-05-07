package com.poc.cl.steps;

import com.poc.cl.pages.MenuPage;
import net.serenitybdd.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class MenuStep {

    @Page
    private MenuPage menuPage;

    @Step
    public boolean isMachPayLogoVisible() {
        return menuPage.getMachPayLogo().isDisplayed();
    }

    @Step
    public String getPageTitle() {
        return menuPage.getTitle();
    }

    @Step
    public void clickChargesOptionMenu() {
        menuPage.getChargesOptionMenu().click();
    }

    @Step
    public void clickImmediateChargeSubMenu() {
        menuPage.getImmediateChargeSubMenu().click();
    }
}
