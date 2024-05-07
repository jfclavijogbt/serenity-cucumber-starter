package com.poc.cl.definitions;

import com.poc.cl.api.GenericRestClient;
import com.poc.cl.models.TransitCard;
import com.poc.cl.models.TransitRecharge;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class BipChargeApiDef {

    private static final String BASE_URI = "mobile";
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZpY2VJZCI6IjlDRDk4MjAyLUI1MzktNDJCNC05NDFDLUMwNUEzQUI0MkI2NyIsIm1hY2hJZCI6IjFkMjJiMjZkLTc3ZmEtNGU3NC05MmRkLWQyNDcwZjQwZTQwYSIsInNlc3Npb25JZCI6ImZlZTYxYjM5LTUwNzItNGNkZi1hYTY5LTg4OTM5YTFjYWIyNiIsInNjb3BlcyI6WyJhY2NvdW50LmJhbGFuY2UiLCJhY2NvdW50LmluZm9ybWF0aW9uIiwiYWNjb3VudC5tb3ZlbWVudHMiLCJjb250YWN0cy5hZGQiLCJub3RpZmljYXRpb25zLnJlZ2lzdGVyIiwicHJvZmlsZXMucmVhZCIsInByb2ZpbGUudXBkYXRlIiwicmVxdWVzdHMuY3JlYXRlIiwicmVhbHRpbWUub25saW5lIiwicmVxdWVzdHMucmVqZWN0IiwidXNlcnMuaWRlbnRpZnkiLCJjYXNoLm91dCIsInBheW1lbnRzLmNyZWF0ZSIsInJlcXVlc3RzLmFjY2VwdCJdLCJpYXQiOjE3MDAxODI1MzIsIm5iZiI6MTcwMDE4MjUzMiwiZXhwIjoxNzMxMjg2NTMyLCJhdWQiOiJhbGwiLCJpc3MiOiJtb2JpbGVBcGkiLCJzdWIiOiIxZDIyYjI2ZC03N2ZhLTRlNzQtOTJkZC1kMjQ3MGY0MGU0MGEiLCJqdGkiOiI2NTU2YmEwNGI5MmZiNTFhZThhNDhiZmIifQ.jXpXZ2-NgxItSJ-STATocWzBHvkE7-NW7ThuAUsApAM";
    private GenericRestClient restClient;
    private ValidatableResponse validatableResponse;
    private TransitCard specificResponse;

    @Dado("que accedí al API de Recarga Bip!")
    public void que_accedí_al_api_de_recarga_bip() {
        restClient = new GenericRestClient(BASE_URI, TOKEN);
    }

    @Cuando("consulte la tarjeta número {int} en el servicio de validación de tarjetas")
    public void consulte_la_tarjeta_número_en_el_servicio_de_validación_de_tarjetas(int cardNumber) {
        validatableResponse =
                restClient.runSimpleWebService(Method.GET, "/transit-card/" + cardNumber + "/validate")
                        .log().all()
                        .assertThat()
                        .statusCode(SC_OK);
    }

    @Entonces("La tarjeta número {int} aparecerá validada")
    public void la_tarjeta_aparecerá_validada(int cardNumber) {
        SoftAssertions.assertSoftly(softly -> {
            TransitCard specificResponse = validatableResponse.extract().body().as(TransitRecharge.class).getTransitRecharge();
            softly.assertThat(specificResponse.isSuccess()).as("Bip card validation is not success").isTrue();
            softly.assertThat(specificResponse.getTransitCardNumber())
                    .as("Bip card number is not as expected").contains(String.valueOf(cardNumber));
        });
    }

    @Cuando("consulte el saldo de la tarjeta número {int} en el servicio de validación de tarjetas")
    public void consulte_el_saldo_de_la_tarjeta_número_en_el_servicio_de_validación_de_tarjetas(int bipCardNumber) {
        validatableResponse =
                restClient.runSimpleWebService(Method.GET, "/transit-card/" + bipCardNumber + "/balance")
                        .log().all()
                        .assertThat()
                        .statusCode(SC_OK);
    }

    @Entonces("El saldo de la tarjeta aparecerá con saldo")
    public void verifyCardAmountDifferentZero() {
        SoftAssertions.assertSoftly(softly -> {
            TransitCard specificResponse = validatableResponse.extract().body().as(TransitCard.class);
            softly.assertThat(specificResponse.getAmount())
                    .as("Balance is not as expected").isNotZero();
            softly.assertThat(specificResponse.getCreatedAt())
                    .as("Date is not as expected").isEqualTo("2021-02-16T13:42:00.000Z");
        });
    }


    @Cuando("realice una recarga de {int} pesos a la tarjeta número {int}")
    public void realice_una_recarga_de_pesos_a_la_tarjeta_número(int amount, int cardNumber) {
        TransitCard bipCardData = new TransitCard(String.valueOf(cardNumber), 1000);
        validatableResponse =
                restClient.runWebServiceWithBody(Method.POST, "/transit-card/recharge/validate", bipCardData)
                        .log().all()
                        .assertThat()
                        .statusCode(SC_CREATED);
    }

    @Entonces("La recarga de {int} pesos a la tarjeta número {int} aparecerá realizada")
    public void la_recarga_de_pesos_a_la_tarjeta_número_aparecerá_realizada(int amount, int bipCardNumber) {
        SoftAssertions.assertSoftly(softly -> {
            specificResponse = validatableResponse.extract().body().as(TransitRecharge.class).getTransitRecharge();
            softly.assertThat(specificResponse.getTransitCardNumber())
                    .as("Bip card number is not as expected").contains(String.valueOf(bipCardNumber));
            softly.assertThat(specificResponse.getAmount()).as("Bip card amount is not as expected").isEqualTo(amount);
            softly.assertThat(specificResponse.getMachId())
                    .as("Mach_id is not as expected").isEqualTo("1d22b26d-77fa-4e74-92dd-d2470f40e40a");
        });
    }

    @Entonces("el estado de la transacción será {string}")
    public void verifyTransactionStatus(String transactionStatus) {
        Assertions.assertThat(specificResponse.getState())
                .as("Recharge state is not as expected").isEqualTo(transactionStatus);
    }

}
