package Common.Cucumber.CucumberOptions;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Common/Cucumber/features",
        glue = "src/test/java/Common/Cucumber/StepDefinitions",
        plugin = {"pretty","html:target/cucumberTestNg.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
