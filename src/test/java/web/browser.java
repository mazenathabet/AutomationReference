package web;

import Common.web.Base;
import org.testng.annotations.Test;

public class browser extends Base {

    @Test
    public void webTest(){
        navigateToURL("https://google.com");
        System.out.println("I am a web test");
    }
}
