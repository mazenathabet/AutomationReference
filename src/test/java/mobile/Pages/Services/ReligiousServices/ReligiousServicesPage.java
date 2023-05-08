package mobile.Pages.Services.ReligiousServices;

import Common.mobile.Utils.IosActions;
import io.appium.java_client.ios.IOSDriver;
import mobile.locators.ReligiousServicesLocators;

public class ReligiousServicesPage extends IosActions {
    public ReligiousServicesPage(IOSDriver driver) {
        super(driver);
    }

    public ManasikPage goToManasikScreen(){
        scrollTo("down");
        driver.findElement(ReligiousServicesLocators.MANASIK_GATE).click();
        return new ManasikPage(driver);
    }
}
