package petstore.api.serenity.automation.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/",
        tags = "@regresion",
        glue = {
                "petstore.api.serenity.automation.stepdefinitions.hook",
                "petstore.api.serenity.automation.stepdefinitions"
        },
        plugin = {"pretty","json:target/cucumber-reports/cucumber.json"},
        publish = true
)
public class AutomationRunner {

}
