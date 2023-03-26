package Common.Helper;

import org.openqa.selenium.WebDriver;

public class ThreadLocalConfig {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static ThreadLocal<WebDriver> getThreadLocalDriver (){
        return threadLocalDriver;
    }
}
