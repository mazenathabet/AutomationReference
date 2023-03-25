package Common.mobile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    /**
     * We need to use the Commons-io and jackson-databind dependency from mvn repo so that we can read data the data from json files
     * and jackson-databind dependency to read the json string and write it into HashMap
     * steps :
     * 1 - parse our json file to json string using ( commons-io )
     * 2 - convert the json string to hashmap ( jackson-databind)
     * 3 - HashMap will be converted to data provider, so it can be used in the testcase
     */
    public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // convert json file content to json string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<>() {
        });
        return data;
    }

    public static String getScreenshotPath(String testcaseName, AppiumDriver driver) throws IOException {
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//screenshots" + testcaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}

