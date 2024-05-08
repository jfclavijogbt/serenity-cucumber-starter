package com.poc.cl.definitions;

import com.poc.cl.utils.MessageSentTracker;
import com.poc.cl.utils.SlackNotifier;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;

public class CustomSlackServiceHook extends SlackNotifier {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CustomSlackServiceHook.class);
    public static final String SLACK_WEBHOOK_URL = "https://hooks.slack.com/services/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";
    public static final String SERENITY_REPORT_URL = "file:///Users/john.clavijo/Documents/GitHub/serenity-cucumber-starter/target/site/serenity/index.html";

    @BeforeAll
    public static void before_all() {
        LOG.info("Iniciando ejecución de pruebas...");
    }

    @After()
    public void sendSlackNotification(Scenario scenario) {
        if (scenario.isFailed()) {
            MessageSentTracker.setAnyScenarioFailed(true);
        }
        LOG.info("Scenario: " + scenario.getName() + " - Status: " + scenario.getStatus());
    }

    @AfterAll
    public static void after_all() {
        if (!MessageSentTracker.isMessageSent()) {
            String message;
            if (MessageSentTracker.isAnyScenarioFailed()) {
                message = ":x: **Se han encontrado fallas en la ejecución de pruebas**\n\n[Reporte de Pruebas](" + SERENITY_REPORT_URL + ")";
            } else {
                message = ":white_check_mark: **Todas las pruebas han tenido éxito**\n\n[Reporte de Pruebas](" + SERENITY_REPORT_URL + ")";
            }
            sendMessageToSlack(SLACK_WEBHOOK_URL, message);
            MessageSentTracker.setMessageSent(true);
        }
    }
}
