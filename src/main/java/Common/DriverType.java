package Common;

import Common.Helper.Properties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class DriverType {

    public static AndroidDriver getAndroidDriver(String appPath) throws IOException {
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : Properties.getProperty("ipAddress");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(Properties.getProperty("AndroidDeviceName")); // name of the device or the emulator
        options.setApp(System.getProperty("user.dir") + appPath); // path of the apk
        options.setChromedriverExecutable(System.getProperty("user.dir") + "/src/main/resources/browserDrivers/chromedriver");
//            options.setCapability("browserName","Chrome"); to automate web apps
        AndroidDriver driver = new AndroidDriver(new URL("http://" + ipAddress + ":" + Integer.parseInt(Properties.getProperty("port"))), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public static IOSDriver getIosDriver(String appPath) throws IOException {
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : Properties.getProperty("ipAddress");
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(Properties.getProperty("IosDeviceName"));
//            options.setApp(System.getProperty("user.dir") + properties.getProperty("TestApp"));
        options.setApp(System.getProperty("user.dir") + appPath);
        options.setPlatformVersion("16.2");
        // Appium will install WebDriverAgent in the Apple device so this WebDriverAgent will help to automate in IOS apps
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        IOSDriver driver = new IOSDriver(new URL("http://" + ipAddress + ":" + Integer.parseInt(Properties.getProperty("port"))), options);
        //https://support.apple.com/en-eg/guide/deployment/depece748c41/web for all the bundleId
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public static WebDriver getChrome() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.addArguments("--incognito");
        options.addArguments("--lang=es");
        boolean headless = Boolean.parseBoolean(Properties.getProperty("HeadlessMode"));
        if (headless) options.addArguments("--headless");
        // " https://peter.sh/experiments/chromium-command-line-switches/ " for more arguments references
        return new ChromeDriver(options);
    }

    public static WebDriver getFirefox() {
        return new FirefoxDriver();
    }

    public static WebDriver getEdge() {
        return new EdgeDriver();
    }

    public static WebDriver getSafari() {
        return new SafariDriver();
    }
}
