package Common.Helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.IOException;

public class ExtentReportNG {

    static ExtentReports extentReports;

    public static ExtentReports getReporterObject() {
        // ExtentReports , ExtentSparkReporter

        String allReportPath = System.getProperty("user.dir") + "//reports//AllReport.html";
        String failsReport = System.getProperty("user.dir") + "//reports//FailsReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(allReportPath) // to create the report on that path
                .viewConfigurer().viewOrder().
                as(new ViewName[]{ViewName.DASHBOARD,
                        ViewName.TEST,
                        ViewName.AUTHOR,
                        ViewName.CATEGORY,
                        ViewName.LOG,
                        ViewName.DEVICE
                , ViewName.EXCEPTION})
                .apply();
        reporter.config().setReportName("Automation Results");
        reporter.config().setDocumentTitle("Automation Results");
        reporter.config().setTheme(Theme.DARK);

        ExtentSparkReporter failReporter = new ExtentSparkReporter(failsReport)
                .filter()
                .statusFilter()
                .as(new Status[] {Status.FAIL})
                        .apply();
        failReporter.config().setReportName("Automation Failed Results");
        failReporter.config().setDocumentTitle("Automation Failed Results");
        failReporter.config().setTheme(Theme.DARK);
        failReporter.config().setTimelineEnabled(true);

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter,failReporter);
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        try {
            extentReports.setSystemInfo("Browser",Properties.getProperty("browserType"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return extentReports;
    }
}
