package mobile;

import Common.mobile.Base;
import Common.mobile.Utils.AndroidActions;
import org.testng.annotations.Test;

public class MobileTest extends Base {

    @Test
    public void mobileTest() {
        System.out.println("i am a mobile test");
        AndroidActions.rotateDevice(driver,0,0,90);
        AndroidActions.lockDevice(driver,5);
        AndroidActions.unlockDevice(driver);
        AndroidActions.rotateScreen(driver,"landscape");
        AndroidActions.rotateScreen(driver,"portrait");
        AndroidActions.lockDevice(driver);
        AndroidActions.unlockDevice(driver);
    }
}
