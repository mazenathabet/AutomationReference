package mobile.Pages.Services.ReligiousServices;

import Common.mobile.Utils.IosActions;
import io.appium.java_client.ios.IOSDriver;
import mobile.locators.ManasikLocators;

public class ManasikPage extends IosActions {
    public ManasikPage(IOSDriver driver) {
        super(driver);
    }

    public ManasikPage selectManasikElHaram(){
        driver.findElement(ManasikLocators.EL_HARAM_FILTER).click();
        return this;
    }
    public ManasikPage selectManasikElMadina(){
        driver.findElement(ManasikLocators.EL_MADINA_FILTER).click();
        return this;
    }
}
