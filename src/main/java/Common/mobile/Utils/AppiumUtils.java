package Common.mobile.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppiumUtils {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static AppiumDriverLocalService service;

    public static void startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        System.out.println("Starting the Appium server on port " + port + "... " + "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        service.start();
    }

    public static void stopAppiumServer() {
        service.stop();
        System.out.println("Tearing down the Appium server ... " + "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
    }

    public static String getScreenshotPath(String testcaseName, AppiumDriver driver) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//screenshots" + testcaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}

