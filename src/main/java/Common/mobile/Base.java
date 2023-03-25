package Common.mobile;

import Common.DriverType;
import Common.Helper.Properties;
import Common.mobile.Utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Base {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected AndroidDriver driver;
    protected IOSDriver iosDriver;

    @BeforeSuite(alwaysRun = true)
    public void startServer() throws IOException {
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : Properties.getProperty("ipAddress");
        int port = System.getProperty("port") != null ? Integer.parseInt(System.getProperty("port")) : Integer.parseInt(Properties.getProperty("port"));
        AppiumUtils.startAppiumServer(ipAddress, port);
    }

    //@Parameters({"platform", "appPath"})
    //public void startDriver(@Optional String platform, @Optional String appPath) throws IOException {
    @BeforeClass(alwaysRun = true)
    public void startDriver() throws IOException {
        String platform = Properties.getProperty("Platform");
        if (platform.equalsIgnoreCase("Android")) {
            driver = DriverType.getAndroidDriver(Properties.getProperty("GeneralStoreApkPath"));
        } else if (platform.equalsIgnoreCase("IOS")) {
            iosDriver = DriverType.getIosDriver(Properties.getProperty("UIKitCatalogApp"));
//            IosActions.lunchApp("com.apple.mobileslideshow");
        }
        System.out.println("Starting " + platform + " driver ... " + "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
        if (iosDriver != null) {
            iosDriver.quit();
        }
        System.out.println("Tearing down the driver ... " + "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer() {
        AppiumUtils.stopAppiumServer();
    }
}
