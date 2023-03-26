package Common.Cucumber.CucumberOptions;

//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/java/features",
//// this is the whole feature package if you want to run only single feature file
//// we use this "src/test/java/features/login.feature"
//        glue = "stepDefinitions",
//        stepNotifications = false,
//        tags = "@SeleniumTest",
////      tags = "@SmokeTest or @RegTest" --> tests with either tags
////     tags = "@SmokeTest and  @RegTest" --> tests with both these tags.
////     tags = "not @SmokeTest" --> run all the tags except the @SmokeTest
//// this is to show the steps results separated in the Run section
//        plugin = {
//                "pretty",
//                "html:target/cucumber.html",
//                "json:target/cucumber.json",
//                "junit:target/cukes.xml"},
//        dryRun = false,
//// If  dryRun = ( TRUE )
//// it will not run your code, but it will run a dryRun
//// to make sure that all your steps are linked to STEP definition
////we don't need the full path since the testRunner package and the step def package have the same parent
//        monochrome = false
//)
public class JunitTestRunner {
}
