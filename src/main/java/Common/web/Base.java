package Common.web;

import Common.DriverType;
import Common.Helper.Properties;
import Common.Helper.ThreadLocalConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Base {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeClass
    public void setUp() throws IOException {

        String browserType = Properties.getProperty("browserType");
        if (browserType.equalsIgnoreCase("chrome")) {
            //get thread-local value
            ThreadLocalConfig.getThreadLocalDriver().set(DriverType.getChrome());
        } else if (browserType.equalsIgnoreCase("firefox")) {
            //get thread-local value
            ThreadLocalConfig.getThreadLocalDriver().set(DriverType.getFirefox());
        } else if (browserType.equalsIgnoreCase("edge")) {
            //get thread-local value
            ThreadLocalConfig.getThreadLocalDriver().set(DriverType.getEdge());
        } else if (browserType.equalsIgnoreCase("safari")) {
            //get thread-local value
            ThreadLocalConfig.getThreadLocalDriver().set(DriverType.getSafari());
        }
        System.out.println("Launching the " + browserType + " driver ... " +
                "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static WebDriver getDriver() {
        return ThreadLocalConfig.getThreadLocalDriver().get();
    }

    @AfterClass
    public void tearDown() {
        getDriver().manage().deleteAllCookies();
        System.out.println("Tearing the driver down ... " +
                "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        getDriver().quit();
        //remove thread-local value for the current thread
        ThreadLocalConfig.getThreadLocalDriver().remove();

    }


}

