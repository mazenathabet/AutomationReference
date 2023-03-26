package Common.web;

import Common.DriverType;
import Common.Helper.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Base {

    public WebDriver driver;
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @BeforeClass
    public void setUp() throws IOException {
        String browserType = Properties.getProperty("browserType");
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = DriverType.getChrome();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = DriverType.getFirefox();
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = DriverType.getEdge();
        } else if (browserType.equalsIgnoreCase("safari")) {
            driver = DriverType.getSafari();
        }
        System.out.println("Launching the "+browserType+" driver ... " +
                "\n" + df.format(new Date()) +
                "\n----------------------------------------------------------------");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown() {
        driver.manage().deleteAllCookies();
        if (driver != null) {
            System.out.println("Tearing the driver down ... " +
                    "\n" + df.format(new Date()) +
                    "\n----------------------------------------------------------------");
            driver.quit();
        }
    }
}

