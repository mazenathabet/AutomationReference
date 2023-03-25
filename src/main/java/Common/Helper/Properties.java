package Common.Helper;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    static java.util.Properties properties = new java.util.Properties();

    public static String getProperty(String propertyKey) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Config.properties");
        properties.load(fis);
        return properties.getProperty(propertyKey);
    }
}
