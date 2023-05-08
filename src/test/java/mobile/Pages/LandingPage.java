package mobile.Pages;

import Common.mobile.Utils.IosActions;
import io.appium.java_client.ios.IOSDriver;
import mobile.Pages.Services.ServicesPage;
import mobile.locators.LandingPageLocators;

public class LandingPage extends IosActions {
    public LandingPage(IOSDriver driver) {
        super(driver);
    }

    public LandingPage goToHomeTab() {
        driver.findElement(LandingPageLocators.HOME_TAB).click();
        return this;
    }
    public ServicesPage goToServicesTab(){
        driver.findElement(LandingPageLocators.SERVICES_TAB).click();
        return new ServicesPage(driver);
    }
}
