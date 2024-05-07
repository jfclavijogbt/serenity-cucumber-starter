package com.poc.cl.definitions;

import com.poc.cl.steps.LoginStep;
import com.poc.cl.steps.ProductsStep;
import com.poc.cl.utils.BasePage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

public class LoginDef {

    @Steps(shared = true)
    private BasePage basePage;

    @Steps(shared = true)
    private LoginStep loginStep;

    @Steps(shared = true)
    private ProductsStep productsStep;

    @Dado("que el usuario navegó a la página de inicio de sesión")
    public void userNavigateTo() {
        basePage.openBrowser("https://www.saucedemo.com/");
    }
    @Cuando("ingresa las credenciales de acceso correctas")
    public void userLoginWithValidCredentials() {
        loginStep.login("standard_user", "secret_sauce");
    }
    @Entonces("podrá ver la página principal")
    public void userShouldSeeMainPage() {
        Assert.assertTrue("Products header is not displayed", productsStep.isProductsHeaderDisplayed());
    }

}
