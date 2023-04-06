package Common.web;

import Common.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Base {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    @Parameters("browserType")
    public void setUp(@Optional String browserType) throws IOException {

//        String browserType = Properties.getProperty("browserType");
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
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @AfterMethod
    public void tearDown() {
        getDriver().manage().deleteAllCookies();
        System.out.println("Tearing the driver down ... " +
                "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        getDriver().quit();
        //remove thread-local value for the current thread
        driver.remove();
    }


}

