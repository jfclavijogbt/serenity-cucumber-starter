package com.poc.cl.steps;

import com.poc.cl.pages.ProductsPage;
import net.serenitybdd.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class ProductsStep {

    @Page
    ProductsPage productsPage;

    @Step("Get Products header")
    public String getProductsHeaderText() {
        return productsPage.getLblProducts().getText();
    }

    @Step("Verify Products header is displayed")
    public boolean isProductsHeaderDisplayed() {
        return productsPage.getLblProducts().isDisplayed();
    }
}
