package mobile;

import Common.mobile.Base;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

import java.time.Duration;

public class IOSDeviceTest extends Base {


    @Test
    public void testApp() throws InterruptedException {
        System.out.println("I logged in ..");
        iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        iosDriver.findElement(AppiumBy.accessibilityId("Services")).click();
        Thread.sleep(2000);
        iosDriver.findElement(AppiumBy.accessibilityId("Digital Documents")).click();
        Thread.sleep(2000);
        iosDriver.findElement(AppiumBy.accessibilityId("Dashboard")).click();
        Thread.sleep(2000);
        iosDriver.findElement(AppiumBy.accessibilityId("Home")).click();
    }
}
