package tests.ExportControl.Sprint5;


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
import pages.Administration.Attachment_Types.AttachmentTypes_ExportControlPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_251474_Category_Configuration_Attachment_Admin {
    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AttachmentTypes_ExportControlPage attachmentTypesExportControlPage;

    @BeforeMethod
    public void setupBrowser(){
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
        attachmentTypesExportControlPage = new AttachmentTypes_ExportControlPage(driver);
    }

    @Test
    public void Export_control_Category_Configuration_Admin_Attachment_Admin () {
        ExtentReportListener.getExtentTest().info("your log message");
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
            AgreementPage agreementPage = new AgreementPage(driver);

            agreementPage.clickAdministrationLink();
            Assert.assertTrue(agreementPage.isDashboardNotificationsSummaryDisplayed(), "Dashboard Notifications - Summary page is NOT displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Dashboard Notifications - Summary page.");

            attachmentTypesExportControlPage.expandAttachmentTypes();
            ExtentReportListener.getExtentTest().info("Expanded Attachment Types menu");

            attachmentTypesExportControlPage.clickAttachmentTypesExportControl();
            ExtentReportListener.getExtentTest().info("Clicked Export Control under Attachment Types successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isAttachmentTypeSectionDisplayed(), "'Attachment Type' section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Attachment Type' section is displayed");

            attachmentTypesExportControlPage.clickAddAttachmentType();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Attachment Type' button successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isAddAttachmentTypeHeaderDisplayed(), "'Add Attachment Type' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Add Attachment Type' header is displayed");

            attachmentTypesExportControlPage.enterRandomTypeName();
            ExtentReportListener.getExtentTest().info("Entered random Type Name successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isAddAttachmentTypeHeaderDisplayed(), "'Add Attachment Type' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Add Attachment Type' header is displayed");

            attachmentTypesExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked Cancel successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isAttachmentTypeSectionDisplayed(), "'Attachment Type' section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Attachment Type' section is displayed");

            attachmentTypesExportControlPage.clickAddAttachmentType();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Attachment Type' button successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isTypeNameLabelDisplayed(), "'Type Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Type Name' label is displayed");

            attachmentTypesExportControlPage.enterRandomTypeName();
            ExtentReportListener.getExtentTest().info("Entered random Type Name successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isTypeNameLabelDisplayed(), "'Type Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Type Name' label is displayed");

            attachmentTypesExportControlPage.clickAddButton();
            ExtentReportListener.getExtentTest().info("Clicked Add button successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isAttachmentTypeSectionDisplayed(), "'Attachment Type' section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Attachment Type' section is displayed");

            attachmentTypesExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().info("Clicked first Edit button successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isActiveLabelDisplayed(), "'Active' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Active' label is displayed");

            attachmentTypesExportControlPage.appendTypeNameWithTest();
            ExtentReportListener.getExtentTest().info("Appended 'Test' to Type Name successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isActiveLabelDisplayed(), "'Active' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Active' label is displayed");

            attachmentTypesExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().info("Clicked Cancel button successfully");
            Assert.assertTrue(attachmentTypesExportControlPage.isAttachmentTypeSectionDisplayed(), "'Attachment Type' section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Attachment Type' section is displayed");

            attachmentTypesExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().info("Clicked first Edit button successfully");

            attachmentTypesExportControlPage.appendTypeNameWithTest();
            ExtentReportListener.getExtentTest().info("Appended 'Test' to Type Name successfully");

            attachmentTypesExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().info("Clicked Save button successfully");
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