package com.nttdata;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"html:target/cucumber/cucumber-report.html",
                "json:target/cucumber/cucumber.json"},
        stepNotifications = true,
        features = "src/test/resources/features",
        glue = "com.nttdata",
        tags = "@Order"
)
public class CucumberTestSuite {
}
