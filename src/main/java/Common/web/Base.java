package Common.web;

import Common.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    Properties properties = new Properties();

    @BeforeClass
    public void setUp() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Config.properties");
        properties.load(fis);
        String browserType = properties.getProperty("browserType");
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = DriverType.getChrome();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = DriverType.getFirefox();
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = DriverType.getEdge();
        } else if (browserType.equalsIgnoreCase("safari")) {
            driver = DriverType.getSafari();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown(){
        driver.manage().deleteAllCookies();
        if(driver != null) {
            driver.quit();
        }
    }

    public void navigateToURL(String url){
        driver.get(url);
    }
}

