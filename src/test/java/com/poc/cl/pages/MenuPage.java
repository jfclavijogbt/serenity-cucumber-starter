package com.poc.cl.pages;

import lombok.Getter;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@Getter
public class MenuPage extends PageObject {

    @FindBy(className = "sidebar__header__logo")
    private WebElementFacade machPayLogo;

    @FindBy(xpath = "//app-sidebar-item//span[text()='Cobros']")
    private WebElementFacade chargesOptionMenu;

    @FindBy(xpath = "//span[contains(text(),'Cobro inmediato')]")
    private WebElementFacade immediateChargeSubMenu;

}
