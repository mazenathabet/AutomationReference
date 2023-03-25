package Common.web.PageObjects;

import Common.web.DriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestPage extends DriverActions {
    public TestPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        openUrl("https://google.com");
        System.out.println("I am a dummy Web Test");
    }

    public void search(String text) {
        clickOn(driver.findElement(By.name("q")), 5);
        sendKeysToElement(text, driver.findElement(By.name("q")), 3);
    }
}
