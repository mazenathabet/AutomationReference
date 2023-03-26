package web;

import Common.web.Base;
import Common.web.PageObjects.TestPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserTest2 extends Base {

    @Test
    public void webTest() {
        TestPage testpage = new TestPage(getDriver());
        testpage.navigate();
        testpage.search("Appium");
        Assert.assertEquals("Google",getDriver().getTitle());
        Assert.assertEquals("https://www.google.com/",getDriver().getCurrentUrl());
    }
}
