package Common.Cucumber.StepDefinitions.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TaggedHooks {
    @After("@webTest")
    public void afterWebHook() {
        System.out.println("After web hook");
    }
    @Before("@webTest")
    public void beforeWebHook() {
        System.out.println("Before Web hook");
    }
}
