package Common.mobile.Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidInstallApplicationOptions;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidActions extends AppiumUtils {

    private static AndroidDriver driver;
    protected WebDriverWait wait;

    public AndroidActions(AndroidDriver driver) {
        AndroidActions.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
    //    Scrolling Actions :

    /**
     * mobile: scrollGesture
     * This gesture performs scrollToWebElement gesture on the given element/area. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be scrolled. If the element id is missing then scrollToWebElement bounding area must be provided. If both the element id and the scrollToWebElement bounding area are provided then this area is effectively ignored.
     * left: The left coordinate of the scrollToWebElement bounding area
     * top: The top coordinate of the scrollToWebElement bounding area
     * width: The width of the scrollToWebElement bounding area
     * height: The height of the scrollToWebElement bounding area
     * direction: Scrolling direction. Mandatory value. Acceptable values are: up, down, left and right (case-insensitive)
     * percent: The size of the scrollToWebElement as a percentage of the scrolling area size. Valid values must be float numbers greater than zero, where 1.0 is 100%. Mandatory value.
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 5000 * displayDensity
     */
    public static void scrollToTheEnd(int left, int top, int width, int height, String direction, double percentage) {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", left, "top", top, "width", width, "height", height,
                    "direction", direction,
                    "percent", percentage
            ));
        } while (canScrollMore);
    }

    public static void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(String.format("new UiScrollable(new UiSelector()).scrollIntoView(text(\"%s\"));", text)));
    }

    //    Pressing Actions :

    /**
     * mobile: doubleClickGesture
     * This gesture performs double click action on the given element/coordinates. Available since Appium v1.21
     * <p>
     * Supported arguments
     * elementId: The id of the element to be clicked. If the element is missing then both click offset coordinates must be provided. If both the element id and offset are provided then the coordinates are parsed as relative offsets from the top left corner of the element.
     * x: The x-offset coordinate
     * y: The y-offset coordinate
     * Usage examples
     */

    public static void doubleClickGesture(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    /**
     * mobile: longClickGesture
     * This gesture performs long click action on the given element/coordinates. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be clicked. If the element is missing then both click offset coordinates must be provided. If both the element id and offset are provided then the coordinates are parsed as relative offsets from the top left corner of the element.
     * x: The x-offset coordinate
     * y: The y-offset coordinate
     * duration: Click duration in milliseconds. 500 by default. The value must not be negative
     *
     * @param element Web Element we want to click
     */
    public static void longPressAction(WebElement element, int millisecondsToHold) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", millisecondsToHold
        ));
    }

    /**
     * mobile: clickGesture
     * This gesture performs click action on the given element/coordinates. Available since Appium UiAutomator2 driver 1.71.0. Usage of this gesture is recommended as a possible workaround for cases where the "native" tap call fails, even though tap coordinates seem correct. This issue is related to the fact these calls use the legacy UIAutomator-based calls while this extension is based on the same foundation as W3C does.
     * <p>
     * Supported arguments
     * elementId: The id of the element to be clicked. If the element is missing then both click offset coordinates must be provided. If both the element id and offset are provided then the coordinates are parsed as relative offsets from the top left corner of the element.
     * x: The x-offset coordinate
     * y: The y-offset coordinate
     * Usage examples
     */

    public static void clickGesture(WebElement element) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }
    // Swipe Actions :

    /**
     * mobile: swipeGesture
     * This gesture performs swipe gesture on the given element/area. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be swiped. If the element id is missing then swipe bounding area must be provided. If both the element id and the swipe bounding area are provided then the area is effectively ignored.
     * left: The left coordinate of the swipe bounding area
     * top: The top coordinate of the swipe bounding area
     * width: The width of the swipe bounding area
     * height: The height of the swipe bounding area
     * direction: Swipe direction. Mandatory value. Acceptable values are: up, down, left and right (case-insensitive)
     * percent: The size of the swipe as a percentage of the swipe area size. Valid values must be float numbers in range 0..1, where 1.0 is 100%. Mandatory value.
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 5000 * displayDensity
     *
     * @param element   Web Element we want to swipe
     * @param direction Direction of the swipe
     */
    public static void swipeElement(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    // Drag-Drop Actions :

    /**
     * mobile: dragGesture
     * This gesture performs drag action from the given element/coordinates to the given point. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be dragged. If the element id is missing then both start coordinates must be provided. If both the element id and the start coordinates are provided then these coordinates are considered as offsets from the top left element corner.
     * startX: The x-start coordinate
     * startY: The y-start coordinate
     * endX: The x-end coordinate. Mandatory argument
     * endY: The y-end coordinate. Mandatory argument
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 2500 * displayDensity
     *
     * @param element WebElement we want to drag
     */
    public static void dragDropElement(WebElement element, int endX, int endY) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", endX,
                "endY", endY
        ));
    }

    /**
     * mobile: flingGesture
     * This gesture performs fling gesture on the given element/area. Available since Appium v1.19
     * <p>
     * Supported arguments
     * elementId: The id of the element to be flung. If the element id is missing then fling bounding area must be provided. If both the element id and the fling bounding area are provided then this area is effectively ignored.
     * left: The left coordinate of the fling bounding area
     * top: The top coordinate of the fling bounding area
     * width: The width of the fling bounding area
     * height: The height of the fling bounding area
     * direction: Direction of the fling. Mandatory value. Acceptable values are: up, down, left and right (case-insensitive)
     * speed: The speed at which to perform this gesture in pixels per second. The value must be greater than the minimum fling velocity for the given view (50 by default). The default value is 7500 * displayDensity
     * Returned value
     * The returned value is a boolean one and equals to true if the object can still scrollToWebElement in the given direction
     * <p>
     * Usage examples
     */

    public static void flingGesture(WebElement element, String direction, int pixelsPerSec) {
        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: flingGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "speed", pixelsPerSec
        ));
    }

    /**
     * mobile: pinchOpenGesture
     * This gesture performs pinch-open gesture on the given element/area. Available since Appium v1.19
     * <p>
     * Supported arguments
     * elementId: The id of the element to be pinched. If the element id is missing then pinch bounding area must be provided. If both the element id and the pinch bounding area are provided then the area is effectively ignored.
     * left: The left coordinate of the pinch bounding area
     * top: The top coordinate of the pinch bounding area
     * width: The width of the pinch bounding area
     * height: The height of the pinch bounding area
     * percent: The size of the pinch as a percentage of the pinch area size. Valid values must be float numbers in range 0..1, where 1.0 is 100%. Mandatory value.
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 2500 * displayDensity
     * Usage examples
     */
    public static void pinchOpenGesture(WebElement element, int percentage) {
        ((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "percent", percentage
        ));
    }

    /**
     * mobile: pinchCloseGesture
     * This gesture performs pinch-close gesture on the given element/area. Available since Appium v1.19
     * <p>
     * Supported arguments
     * elementId: The id of the element to be pinched. If the element id is missing then pinch bounding area must be provided. If both the element id and the pinch bounding area are provided then the area is effectively ignored.
     * left: The left coordinate of the pinch bounding area
     * top: The top coordinate of the pinch bounding area
     * width: The width of the pinch bounding area
     * height: The height of the pinch bounding area
     * percent: The size of the pinch as a percentage of the pinch area size. Valid values must be float numbers in range 0..1, where 1.0 is 100%. Mandatory value.
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 2500 * displayDensity
     * Usage examples
     */
    public static void pinchCloseGesture(WebElement element, int percentage) {
        ((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "percent", percentage
        ));
    }

    // Miscellaneous Functions

//    public static void rotateDevice(int x, int y, int z) {
//        orientation = new DeviceRotation(x, y, z);
//        driver.rotate(orientation);
//    }

    public static void StartAndroidActivity(AndroidDriver driver, String packageName, String activityName) {
//           appPackage -> global name for that project
//           appActivity -> each and every functionality in the app will have one activity
        Activity activity = new Activity(packageName, activityName);
        driver.startActivity(activity);
//            to know the appPackage and appActivity
//            make sure you device is running and attached
//            adb shell dumpsys window | grep -E 'mCurrentFocus' - MAC
//            adb shell dumpsys window | find 'mCurrentFocus' - Windows
//            io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies
//            packageName/activityName
    }


    public static void terminateApp(AndroidDriver driver, String appPackage) {
        driver.terminateApp(appPackage);
    }

    public static void installApp(AndroidDriver driver, String appPath, boolean update) {
        if (update) {
            driver.installApp(appPath, new AndroidInstallApplicationOptions().withReplaceEnabled());
        } else driver.installApp(appPath, new AndroidInstallApplicationOptions().withReplaceDisabled());
    }

    public static void runAppInBackground(AndroidDriver driver, Duration seconds) {
        driver.runAppInBackground(seconds);
    }

    public static void activateApp(AndroidDriver driver, String appPackage) {
        driver.activateApp(appPackage);
    }

    public static void rotateScreen(AndroidDriver driver, String deviceOrientation) {
        if (deviceOrientation.equalsIgnoreCase("landscape")) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else if (deviceOrientation.equalsIgnoreCase("portrait")) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else throw new IllegalArgumentException("Invalid orientation !!");
    }

    public static void rotateDevice(AndroidDriver driver, int x, int y, int z) {
        driver.rotate(new DeviceRotation(x, y, z));
    }

    public static void lockDevice(AndroidDriver driver, int seconds) {
        driver.lockDevice(Duration.ofSeconds(seconds));
    }

    public static void lockDevice(AndroidDriver driver) {
        driver.lockDevice();
    }

    public static void unlockDevice(AndroidDriver driver) {
        driver.unlockDevice();
    }

    // Appium 1.22 Android Actions With touchAction class

//    protected void tabOnElement(WebElement element){
//        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
//    }
//    protected void tabOnElement(By by ){
//        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(driver.findElement(by)))).perform();
//    }
//
//    protected void longPressOnElement(By by,long seconds){
//        touchAction.longPress(ElementOption.element(driver.findElement(by)))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
//                .release()
//                .perform();
//    }
//    protected void longPressOnElement(WebElement element,long seconds){
//        touchAction.longPress(ElementOption.element(element))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
//                .release()
//                .perform();
//    }
//
//    protected void swipeFromToLocation(int startX , int startY,int endX, int endY,long inSeconds){
//        touchAction.press(PointOption.point(startX,startY))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(inSeconds)))
//                .moveTo(PointOption.point(endX,endY))
//                .release()
//                .perform();
//    }
//
//    protected void swipeUsingElementsOnScreen(By sourceElement, By targetElement,long inSeconds){
//        touchAction.press(ElementOption.element(driver.findElement(sourceElement)))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(inSeconds)))
//                .moveTo(ElementOption.element(driver.findElement(targetElement)))
//                .release()
//                .perform();
//    }
}
