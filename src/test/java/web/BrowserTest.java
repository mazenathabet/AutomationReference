package web;

import Common.web.Base;
import Common.web.PageObjects.TestPage;
import org.testng.annotations.Test;

public class BrowserTest extends Base {

    @Test
    public void webTest() {
        TestPage testpage = new TestPage(driver);
        testpage.navigate();
        testpage.search("Appium");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
    }
}
