package Common.Cucumber.StepDefinitions.Hooks;

import Common.web.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class GlobalHooks extends Base {

    @Before
    public void BeforeAllHoke() throws IOException {
        System.out.println("global before hook");
    }

    @After
    public void afterAllHook() {
        System.out.println("global after hook");
    }
}
