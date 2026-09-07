package tests.ExportControl.Sprint3;


import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Adobe.AgreementPage;
import pages.Administration.Template_Management.TemplateManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.nio.file.Paths;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239498_Template_Management_Negative_Cases {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TemplateManagement_ExportControlPage templateManagementExportControlPage;
    AgreementPage agreementPage;

    @BeforeMethod
    public void setupBrowser() {
        // User will set up and configure the Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // User will launch a new Chrome browser instance
        driver = new ChromeDriver();

        // Set driver to DriverManager for global access
        DriverManager.setDriver(driver);

        // User will maximize the browser window to ensure all UI elements are visible
        driver.manage().window().maximize();

        // User will initialize explicit wait with a timeout of 10 seconds for dynamic element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        basePage = new BasePage (driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        templateManagementExportControlPage = new TemplateManagement_ExportControlPage(driver);
        agreementPage = new AgreementPage(driver);
    }

    @Test
    public void ExportControl_TemplateManagement_Test() {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            // Agreement Page Actions
            agreementPage.clickAdministrationLink();
            Assert.assertTrue(agreementPage.isDashboardNotificationsSummaryDisplayed(), "Dashboard Notifications - Summary page is NOT displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Dashboard Notifications - Summary page.");

            templateManagementExportControlPage.clickTemplateManagementExportControl01();
            ExtentReportListener.getExtentTest().info("Clicked on 'Template Management > Export Control' successfully");
            Assert.assertTrue(templateManagementExportControlPage.isExportControlBreadcrumbDisplayed(), "'Template Management > Export Control' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Template Management > Export Control' breadcrumb is displayed successfully");

            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked on 'Add new' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            String generatedTitle = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().info("Entered unique title: " + generatedTitle);
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            String baseDir = System.getProperty("user.dir");
            String filePath = JsonDataReader.get(5,"TestExcelFilePath");
            String path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().info("Uploaded file: testexcel.xlsx was not successful");

            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isExportControlBreadcrumbDisplayed(), "'Template Management > Export Control' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Template Management > Export Control' breadcrumb is displayed successfully");

            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked on 'Add new' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            String generatedTitle05 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().info("Entered unique title: " + generatedTitle05);

            filePath = JsonDataReader.get(5,"TestJPGImgFilePath");
            path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().info("Uploaded file: testjpg.jpg was not successful");

            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isExportControlBreadcrumbDisplayed(), "'Template Management > Export Control' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Template Management > Export Control' breadcrumb is displayed successfully");

            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked on 'Add new' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            String generatedTitle03 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().info("Entered unique title: " + generatedTitle03);
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            filePath = JsonDataReader.get(5,"TestPNGImgFilePath");
            path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().info("Uploaded file: img.png was not successfully");

            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isExportControlBreadcrumbDisplayed(), "'Template Management > Export Control' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Template Management > Export Control' breadcrumb is displayed successfully");

            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked on 'Add new' under Template Management successfully");
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            String generatedTitle02 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().info("Entered unique title: " + generatedTitle02);

            filePath = JsonDataReader.get(5,"TestDOCXFilePath");
            path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().info("Uploaded file: Testdata.docx was uploaded successfully");

            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' under Template Management successfully");

            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked on 'Add new' under Template Management successfully");

            String generatedTitle01 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().info("Entered unique title: " + generatedTitle01);
            Assert.assertTrue(templateManagementExportControlPage.isCreateTemplateBreadcrumbDisplayed(), "'Create Template' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Template' breadcrumb is displayed successfully");

            String filePath01 = System.getProperty("user.dir") + "/src/test/resources/testfiles/Agreement Info 2025_03.pdf";
            templateManagementExportControlPage.uploadAgreementFile(filePath01);
            ExtentReportListener.getExtentTest().info("Uploaded file: Agreement Info 2025_03.pdf successfully");

            templateManagementExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Create' button under Template Management successfully");

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}