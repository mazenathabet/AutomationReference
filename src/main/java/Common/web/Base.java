package Common.web;

import Common.DriverType;
import Common.Helper.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Base {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() throws IOException {

        String browserType = Properties.getProperty("browserType");
        if (browserType.equalsIgnoreCase("chrome")) {
            //get thread-local value
            driver.set(DriverType.getChrome());
        } else if (browserType.equalsIgnoreCase("firefox")) {
            //get thread-local value
            driver.set(DriverType.getFirefox());
        } else if (browserType.equalsIgnoreCase("edge")) {
            //get thread-local value
            driver.set(DriverType.getEdge());
        } else if (browserType.equalsIgnoreCase("safari")) {
            //get thread-local value
            driver.set(DriverType.getSafari());
        }
        System.out.println("Launching the " + browserType + " driver ... " +
                "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().manage().deleteAllCookies();
        System.out.println("Tearing the driver down ... " +
                "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        getDriver().quit();
        //remove thread-local value for the current thread
    }


}

