package Common.Helper;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyTransformer implements IAnnotationTransformer {

    /**
     * with this approach it should be implemented on Run time and all the failed test cases will be automatically re-executed
     * all we have to do is add to the TestNG.xml as a listener
     * check AndroidTests and the TestNG.xml listener
     */
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
