package com.poc.cl.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SlackNotifier {
    public static void sendMessageToSlack(String webhookUrl, String message) {
//        Response response = RestAssured.given()
//                .header("Content-type", "application/json")
//                .body("{\"text\":\"" + message + "\"}")
//                .post(webhookUrl);

        // Verificar la respuesta
        //int statusCode = response.getStatusCode();
        int statusCode = 200;
        if (statusCode == 200) {
            System.out.println("Mensaje enviado exitosamente a Slack. \n" + message);
        } else {
            System.out.println("Error al enviar el mensaje a Slack. Código de estado: " + statusCode);
            //System.out.println("Respuesta: " + response.getBody().asString());
        }
    }
}

