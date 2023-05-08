package mobile.Pages.Services;

import Common.mobile.Utils.IosActions;
import io.appium.java_client.ios.IOSDriver;
import mobile.Pages.Services.EducationalServices.EducationalServicesPage;
import mobile.Pages.Services.ReligiousServices.ReligiousServicesPage;

public class ServicesPage extends IosActions {
    public ServicesPage(IOSDriver driver) {
        super(driver);
    }
    public ReligiousServicesPage getReligiousServices(){
        return new ReligiousServicesPage(driver);
    }
    public EducationalServicesPage getEducationalServices(){
        return new EducationalServicesPage(driver);
    }
}
