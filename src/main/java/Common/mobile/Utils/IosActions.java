package Common.mobile.Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class IosActions extends AppiumUtils {

    public static IOSDriver driver;
    protected WebDriverWait wait;

    public IosActions(IOSDriver driver) {
        IosActions.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void longPress(WebElement element, int millisecondsToHold) {
//        ((JavascriptExecutor) driver).executeScript("mobile:touchAndHold",
//                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
//                "duration", millisecondsToHold
//        ));
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) element).getId());
        params.put("duration", millisecondsToHold);
        driver.executeScript("mobile:touchAndHold", params);
    }

    public void scrollTo(final String direction) {
        driver.executeScript("mobile:scroll",
                ImmutableMap.of("direction",direction)
        );
    }

    public static void swipe(final String direction) {
        final Map<String, Object> params = new HashMap<>();
        params.put("direction", direction); // mandatory right, left
        // by default appium swipes to the center of the screen so the target element is not mandatory
        driver.executeScript("mobile:swipe", params);
    }

    public static void lunchApp(String bundleId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("bundleId", bundleId);
        driver.executeScript("mobile:launchApp", params);
    }
}
