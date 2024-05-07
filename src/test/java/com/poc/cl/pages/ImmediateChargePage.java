package com.poc.cl.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ImmediateChargePage extends PageObject {

    @FindBy(id = "mat-input-2")
    private WebElementFacade amountInput;

    @FindBy(id = "mat-input-3")
    private WebElementFacade nameInput;

    @FindBy(id = "mat-input-4")
    private WebElementFacade descriptionInput;

    @FindBy(id = "mat-select-6")
    private WebElementFacade officeSelect;

    @FindBy(id = "mat-select-8")
    private WebElementFacade tillSelect;

    @FindBy(xpath = "//app-payment-summary//button[contains(text(),'Cobrar')]")
    private WebElementFacade chargeButton;

    @FindBy(xpath = "//img[contains(@src, 'data:image/png')]")
    private WebElementFacade qrImage;

    @FindBy(xpath = "//app-payment-summary//div[@class='payment-summary__qr']/span")
    private WebElementFacade qrDescription;

    public void typeAmount(int amount) {
        amountInput.type(String.valueOf(amount));
    }

    public void selectTip(String tip) {
        By element = By.xpath("//app-tip-button//*[contains(text(),'" + tip + "')]");
        withTimeoutOf(Duration.ofSeconds(15)).waitFor(element).waitUntilClickable().click();
    }

    public void typeNameInput(String name) {
        nameInput.type(name);
    }

    public void typeDescriptionInput(String description) {
        descriptionInput.type(description);
    }

    public void selectOfficeCombo(String office) {
        officeSelect.click();
        getDriver().findElement(By.xpath("//span[contains(text(),'" + office + "')]")).click();
    }

    public void selectTillCombo(String till) {
        tillSelect.click();
        getDriver().findElement(By.xpath("//span[contains(text(),'" + till + "')]")).click();
    }

    public void clickChargeButton() {
        chargeButton.click();
    }

    public boolean isQRImageDisplayed() {
        return withTimeoutOf(Duration.ofSeconds(15)).waitFor(qrImage).isPresent();
    }


    public String getQRDescription() {
        return qrDescription.getText();
    }
}
