package com.poc.cl.definitions;

import com.poc.cl.steps.MachPayLoginStep;
import com.poc.cl.steps.MenuStep;
import com.poc.cl.utils.BasePage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachPayLoginDef {

    @Steps(shared = true)
    private BasePage basePage;

    @Steps
    private MachPayLoginStep machPayLoginStep;

    @Steps
    private MenuStep menuStep;

    Logger LOGGER = LoggerFactory.getLogger(MachPayLoginDef.class);

    @Dado("que el usuario navegó a la página de inicio de sesión de MACH Pay")
    public void machPayLoadMainPage() {
        basePage.openBrowser("https://pay-staging.somosmach.com/");
    }

    @Y("ha ingresado credenciales de acceso válidas")
    @Cuando("ingresa credenciales de acceso válidas")
    public void userLoginWithValidCredentials() {
        machPayLoginStep.machPayLogin("john.clavijo@externos.bci.cl", "Cambio1234");
    }

    @Entonces("podrá ver la página de inicio")
    public void userShouldSeeMainPage() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(menuStep.isMachPayLogoVisible()).isTrue().as("Logo is not displayed");
            softly.assertThat(menuStep.getPageTitle()).isEqualTo("Inicio | MACH Pay").as("Title is not correct");
        });
    }

    @Cuando("ingresa credenciales de acceso inválidas")
    public void userLoginWithInvalidCredentials() {
        machPayLoginStep.machPayLogin("test@test.com", "test");
    }

    @Entonces("verá el mensaje de error: {string}")
    public void userShouldSeeErrorLoginPage(String errorMessage) {
        String error = machPayLoginStep.getLoginError();
        LOGGER.info("Error message: " + error);
        SoftAssertions.assertSoftly(
                softly -> softly.assertThat(machPayLoginStep.getLoginError()).as("Error message is not correct").isEqualTo(errorMessage));
        Assert.assertTrue("Error message is not displayed", machPayLoginStep.isLoginErrorPresent("errorMessage"));
    }

    @Entonces("no podrá acceder a la página de inicio")
    public void userCantSeeMainPage() {
        Assert.assertTrue("You're not in the correct page", machPayLoginStep.getPageTitle().contains("Iniciar Sesión"));
    }


}
