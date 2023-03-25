package Common.Helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    static ExtentReports extentReports;

    public static ExtentReports getReporterObject() {
        // ExtentReports , ExtentSparkReporter

        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path); // to create the report on that path
        reporter.config().setReportName("Appium Automation Results");
        reporter.config().setDocumentTitle("Test Automation Results");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Mazen Ahmed");

        return extentReports;
    }
}
