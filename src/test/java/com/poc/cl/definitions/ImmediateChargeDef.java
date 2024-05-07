package com.poc.cl.definitions;

import com.poc.cl.steps.ImmediateChargeStep;
import com.poc.cl.steps.MenuStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

public class ImmediateChargeDef {

    @Steps
    private MenuStep menuStep;

    @Steps
    private ImmediateChargeStep immediateChargeStep;

    @Cuando("ingresa a la opción de Cobro inmediato")
    public void ingresa_a_la_opción_de_cobro_inmediato() {
        menuStep.clickChargesOptionMenu();
        menuStep.clickImmediateChargeSubMenu();
    }
    @Cuando("genera un QR con los datos de cobro:")
    public void genera_un_qr_con_los_datos_de_cobro(DataTable immediateChargeDataTable) {
        immediateChargeStep.generateImmediateChargeQR(immediateChargeDataTable.transpose().asMap(String.class, String.class));
    }

    @Entonces("se generará un QR con los datos de cobro para {string}")
    public void se_generará_un_qr_con_los_datos_de_cobro(String description) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(immediateChargeStep.isQRGenerated()).as("QR is not generated").isTrue();
            softly.assertThat(immediateChargeStep.isQRDescriptionCorrect(description)).as("QR description is not correct").isTrue();
        });
    }

}
