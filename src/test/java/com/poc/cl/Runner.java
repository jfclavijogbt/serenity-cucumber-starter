package com.poc.cl;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(net.serenitybdd.cucumber.CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.poc.cl.definitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = "pretty",
        tags = "@GetEmployee"
        //tags = "not @SauceLabs"
)
public class Runner {
}
