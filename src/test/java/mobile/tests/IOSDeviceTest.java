package mobile.tests;

import Common.mobile.Base;
import mobile.Pages.LandingPage;
import org.testng.annotations.Test;

public class IOSDeviceTest extends Base {

    @Test
    public void checkManasikElMadina(){
        LandingPage landingPage = new LandingPage(iosDriver);
        landingPage.goToServicesTab()
                .getReligiousServices()
                .goToManasikScreen()
                .selectManasikElMadina();
    }
    @Test
    public void checkManasikElHaram(){
        LandingPage landingPage = new LandingPage(iosDriver);
        landingPage.goToServicesTab()
                .getReligiousServices()
                .goToManasikScreen()
                .selectManasikElHaram();
    }
}
