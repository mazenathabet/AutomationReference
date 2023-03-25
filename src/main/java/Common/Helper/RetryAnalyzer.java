package Common.Helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    /**
     * with this approach it should be implemented on Test Level and all the test cases should have the
     *
     * @Test(retryAnalyzer = RetryAnalyzer.class)
     * Check IOSTests
     */
    int counter = 0;
    int retryLimit = 2; // number of tries before returning failure

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}
