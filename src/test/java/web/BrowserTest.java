package web;

import Common.web.Base;
import Common.web.PageObjects.TestPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserTest extends Base {

    @Test
    public void webTest() {
        TestPage testpage = new TestPage(driver);
        testpage.navigate();
        testpage.search("Appium");
        Assert.assertEquals("Google",driver.getTitle());
        Assert.assertEquals("https://www.google.com/",driver.getCurrentUrl());
    }
}
