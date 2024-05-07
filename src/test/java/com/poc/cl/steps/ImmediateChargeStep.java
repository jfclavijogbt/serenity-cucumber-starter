package com.poc.cl.steps;

import com.poc.cl.pages.ImmediateChargePage;
import net.serenitybdd.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;

import java.util.Map;

public class ImmediateChargeStep {

    @Page
    private ImmediateChargePage immediateChargePage;

    @Step
    public void generateImmediateChargeQR(Map<String, String> immediateChargeData) {
        immediateChargePage.typeAmount(Integer.parseInt(immediateChargeData.get("Monto")));
        immediateChargePage.selectTip(immediateChargeData.get("Propina"));
        immediateChargePage.typeNameInput(immediateChargeData.get("Nombre"));
        immediateChargePage.typeDescriptionInput(immediateChargeData.get("Descripción"));
        immediateChargePage.selectOfficeCombo(immediateChargeData.get("Sucursal"));
        immediateChargePage.selectTillCombo(immediateChargeData.get("Caja"));
        immediateChargePage.clickChargeButton();
    }

    @Step
    public boolean isQRGenerated() {
        return immediateChargePage.isQRImageDisplayed();
    }

    @Step
    public boolean isQRDescriptionCorrect(String description) {
        return immediateChargePage.getQRDescription().equals(description);
    }
}

