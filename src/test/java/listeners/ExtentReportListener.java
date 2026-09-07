package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import utils.TestConstants;
import utils.DriverManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal <ExtentTest> test = new ThreadLocal < > ();
    private static Map< String, ExtentTest> classLevelTests = new ConcurrentHashMap< >();
    public static ExtentTest getExtentTest() {
        return test.get();
    }

    private void configureReport(ITestContext context)
    {
        // Determine the best report base name
        String baseName;

        if (context.getSuite() != null && context.getSuite().getName() != null) {
            // Running from runner.xml → use suite name
            baseName = context.getSuite().getName();
        }
        else if (context.getName() != null) {
            // Test-level fallback
            baseName = context.getName();
        }
        else if (context.getAllTestMethods().length > 0) {
            // Class-level fallback
            baseName = context.getAllTestMethods()[0].getRealClass().getSimpleName();
        }
        else {
            baseName = "AutomationReport";
        }

        //String className = context.getAllTestMethods()[0].getRealClass().getSimpleName();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFileName = baseName + "_" + timeStamp + ".html";

        String reportsDirPath = System.getProperty("user.dir") + "/test_reports";
        File reportsDir = new File(reportsDirPath);
        if (!reportsDir.exists()) reportsDir.mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportsDirPath + "/" + reportFileName);
        spark.config().setDocumentTitle(baseName + " Report");

        spark.config().setReportName("MGB Automation Report");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Host Name", "Automation Host");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Shankar");
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        if (extent == null) configureReport(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            System.err.println("Extent is null in onTestStart. Skipping report entry.");
            return;
        }

        String className = result.getTestClass().getRealClass().getSimpleName();
        String method = result.getMethod().getMethodName();

        ExtentTest extentTest = extent.createTest(className + " - " + method); // one line report
        test.set(extentTest);
        extentTest.log(Status.INFO, "Test Started: " + method);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;

        String stepName = "Test Passed: " + result.getMethod().getMethodName();

        if (TestConstants.TAKE_SCREENSHOT_ON_PASS && DriverManager.getDriver() != null) {
            try {
                WebDriver driver = DriverManager.getDriver();
                String format = TestConstants.SCREENSHOT_FORMAT;
                String screenshotPath = null;

                if (TestConstants.TAKE_FULL_PAGE_SCREENSHOT) {
                    screenshotPath = "base64".equalsIgnoreCase(format)
                            ? ScreenshotUtility.takeFullPageScreenshotAsBase64(driver)
                            : ScreenshotUtility.takeFullPageScreenshotAsPNG(driver, "passed", result.getMethod().getMethodName());
                } else {
                    screenshotPath = "base64".equalsIgnoreCase(format)
                            ? ScreenshotUtility.takeScreenshotAsBase64(driver)
                            : ScreenshotUtility.takeScreenshotAsPNG(driver, "passed", result.getMethod().getMethodName());
                }

                if ("base64".equalsIgnoreCase(format)) {
                    if (screenshotPath != null && !screenshotPath.trim().isEmpty()) {
                        extentTest.pass(stepName, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
                    } else {
                        extentTest.pass(stepName);
                        extentTest.log(Status.WARNING, "Screenshot base64 string is null or empty");
                    }
                } else if ("png".equalsIgnoreCase(format)) {
                    if (screenshotPath != null && !screenshotPath.trim().isEmpty()) {
                        extentTest.pass(stepName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    } else {
                        extentTest.pass(stepName);
                        extentTest.log(Status.WARNING, "Screenshot file path is null or empty");
                    }
                } else {
                    extentTest.pass(stepName);
                    extentTest.log(Status.WARNING, "Invalid screenshot format: " + format);
                }
            } catch (Exception e) {
                extentTest.pass(stepName);
                extentTest.log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
            }
        } else {
            extentTest.log(Status.PASS, stepName);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;

        extentTest.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, result.getThrowable());

        if (TestConstants.TAKE_SCREENSHOT_ON_FAILURE && DriverManager.getDriver() != null) {
            try {
                WebDriver driver = DriverManager.getDriver();
                String format = TestConstants.SCREENSHOT_FORMAT;
                String screenshot = TestConstants.TAKE_FULL_PAGE_SCREENSHOT
                        ? ("base64".equalsIgnoreCase(format)
                        ? ScreenshotUtility.takeFullPageScreenshotAsBase64(driver)
                        : ScreenshotUtility.takeFullPageScreenshotAsPNG(driver, "failed", result.getMethod().getMethodName()))
                        : ("base64".equalsIgnoreCase(format)
                        ? ScreenshotUtility.takeScreenshotAsBase64(driver)
                        : ScreenshotUtility.takeScreenshotAsPNG(driver, "failed", result.getMethod().getMethodName()));

                if ("base64".equalsIgnoreCase(format)) {
                    if (screenshot != null && !screenshot.trim().isEmpty()) {
                        extentTest.addScreenCaptureFromBase64String(screenshot, "Failed Screenshot");
                    } else {
                        extentTest.log(Status.WARNING, "Screenshot base64 string is null or empty");
                    }
                } else if ("png".equalsIgnoreCase(format)) {
                    if (screenshot != null && !screenshot.trim().isEmpty()) {
                        extentTest.addScreenCaptureFromPath(screenshot);
                    } else {
                        extentTest.log(Status.WARNING, "Screenshot file path is null or empty");
                    }
                } else {
                    extentTest.log(Status.WARNING, "Invalid screenshot format: " + format);
                }
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;

        extentTest.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        extentTest.log(Status.SKIP, result.getThrowable());

        if (TestConstants.TAKE_SCREENSHOT_ON_FAILURE && DriverManager.getDriver() != null) {
            try {
                WebDriver driver = DriverManager.getDriver();
                String format = TestConstants.SCREENSHOT_FORMAT;
                String screenshot = TestConstants.TAKE_FULL_PAGE_SCREENSHOT
                        ? ("base64".equalsIgnoreCase(format)
                        ? ScreenshotUtility.takeFullPageScreenshotAsBase64(driver)
                        : ScreenshotUtility.takeFullPageScreenshotAsPNG(driver, "skipped", result.getMethod().getMethodName()))
                        : ("base64".equalsIgnoreCase(format)
                        ? ScreenshotUtility.takeScreenshotAsBase64(driver)
                        : ScreenshotUtility.takeScreenshotAsPNG(driver, "skipped", result.getMethod().getMethodName()));

                if ("base64".equalsIgnoreCase(format)) {
                    if (screenshot != null && !screenshot.trim().isEmpty()) {
                        extentTest.addScreenCaptureFromBase64String(screenshot, "Skipped Screenshot");
                    } else {
                        extentTest.log(Status.WARNING, "Screenshot base64 string is null or empty");
                    }
                } else if ("png".equalsIgnoreCase(format)) {
                    if (screenshot != null && !screenshot.trim().isEmpty()) {
                        extentTest.addScreenCaptureFromPath(screenshot);
                    } else {
                        extentTest.log(Status.WARNING, "Screenshot file path is null or empty");
                    }
                } else {
                    extentTest.log(Status.WARNING, "Invalid screenshot format: " + format);
                }
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Could not capture screenshot for skipped test: " + e.getMessage());
            }
        }
    }

    // ####################################################################################################

    public static void logPassWithScreenshot(String logMessage) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().pass(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeScreenshotAsPNG(DriverManager.getDriver(), "passed", logMessage);
                getExtentTest().pass(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(logMessage);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(logMessage);
            getExtentTest().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }
    public static void logFailWithScreenshot(String logMessage) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().fail(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeScreenshotAsPNG(DriverManager.getDriver(), "passed", logMessage);
                getExtentTest().fail(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().fail(logMessage);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().fail(logMessage);
            getExtentTest().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }
    public static void logPassWithElementScreenshot(WebElement element, String stepName) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeElementScreenshotAsBase64(element);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeElementScreenshotAsPNG(element, "passed", stepName);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(stepName);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(stepName);
            getExtentTest().log(Status.WARNING, "Could not capture element screenshot: " + e.getMessage());
        }
    }
    public static void logStepPassWithFullElementScreenshot(WebElement element,String stepName) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeFullElementScreenshotAsBase64(DriverManager.getDriver(),element);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeFullElementScreenshotAsPNG(DriverManager.getDriver(),element, "passed", stepName);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(stepName);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(stepName);
            getExtentTest().log(Status.WARNING, "Could not capture element screenshot: " + e.getMessage());
        }
    }
    public static void logStepPassWithFullPageScreenshot(String stepName) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;

            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeFullPageScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeFullPageScreenshotAsPNG(DriverManager.getDriver(), "passed", stepName);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(stepName);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(stepName);
            getExtentTest().log(Status.WARNING, "Could not capture full page screenshot: " + e.getMessage());
        }
    }
    public static void logStepPassWithFullPageScreenshot() {
        String defaultStepMessage = "Full page screenshot captured";

        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;

            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeFullPageScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().pass(defaultStepMessage,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeFullPageScreenshotAsPNG(DriverManager.getDriver(), "passed", "full_page");
                getExtentTest().pass(defaultStepMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(defaultStepMessage);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(defaultStepMessage);
            getExtentTest().log(Status.WARNING, "Could not capture full page screenshot: " + e.getMessage());
        }
    }

}