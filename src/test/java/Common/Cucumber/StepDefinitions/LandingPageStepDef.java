package Common.Cucumber.StepDefinitions;

import Common.DriverType;
import Common.web.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class LandingPageStepDef extends Base {

    private WebDriver driver;
    @Given("As i user i want to launch the browser")
    public void openBrowser() throws IOException {
        System.out.println("browser launched");
        driver = DriverType.getChrome();
    }

    @When("navigating to the landing page")
    public void navigateToLandingPage(){
        System.out.println("I am on the landing page");
        driver.get("https://www.google.com/");
    }
    @Then("The landing page is loaded correctly")
    public void verifyPageLoaded(){
        System.out.println("page is loaded");
        Assert.assertEquals("Google",driver.getTitle());
        Assert.assertEquals("https://www.google.com/",driver.getCurrentUrl());
        driver.quit();
    }
}
