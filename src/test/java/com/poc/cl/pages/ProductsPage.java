package com.poc.cl.pages;

import lombok.Getter;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@Getter
public class ProductsPage extends PageObject {

    @FindBy(xpath = "//span[@class='title']")
    protected WebElementFacade lblProducts;


}
