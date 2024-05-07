package com.poc.cl.utils;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.annotations.Steps;

public class BasePage {

    @Steps(shared = true)
    PageObject page;

    @Step("Open the browser and navigate to {0}")
    public void openBrowser(String url) {
        page.setDefaultBaseUrl(url);
        page.open();
    }
}
