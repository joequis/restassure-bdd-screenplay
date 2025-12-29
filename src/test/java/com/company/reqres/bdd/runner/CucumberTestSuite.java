package com.company.reqres.bdd.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Qué hace: Ejecuta los Features con Cucumber usando Serenity + JUnit.
 * De dónde obtiene data: Los .feature ubicados en src/test/resources/features.
 * Quién la usa: Ejecución vía Maven o IntelliJ (Run).
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.company.reqres.bdd.steps",
        plugin = {
                "pretty",
                "summary",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"
        },
        monochrome = true,
        tags = "@Obtener-usuario"
)
public class CucumberTestSuite {
}

