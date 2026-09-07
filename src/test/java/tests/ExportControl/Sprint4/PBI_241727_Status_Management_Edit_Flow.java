package tests.ExportControl.Sprint4;


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
import pages.Administration.Status_Management.StatusManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_241727_Status_Management_Edit_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    StatusManagement_ExportControlPage statusManagementExportControlPage;

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
        statusManagementExportControlPage = new StatusManagement_ExportControlPage(driver);
    }

    @Test
    public void Export_control_status_management_edit_flow() {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String statusNamePrefix = JsonDataReader.get(1, "StatusNamePrefix");   // e.g. "Test"
            String statusNameSuffix = JsonDataReader.get(1, "StatusNameSuffix");

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
            ExtentReportListener.getExtentTest().info("Clicked Administration link");

            // StatusManagement Page Actions
            statusManagementExportControlPage.clickStatusManagementLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Status Management' link successfully");

            statusManagementExportControlPage.clickStatusManagementExportControl();
            ExtentReportListener.getExtentTest().info("Clicked 'Status Management > Export Control' successfully");
            Assert.assertTrue(statusManagementExportControlPage.isStatusManagementPageDisplayed01(), "Status Management page did not load correctly");
            ExtentReportListener.getExtentTest().pass("Verified that user lands on Status Management page successfully");

            statusManagementExportControlPage.clickAddStatusButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Status' button successfully");
            Assert.assertTrue(statusManagementExportControlPage.isAddStatusHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Add Status' header is displayed");

            // Generate dynamic status name using prefix from JSON
            String statusName = statusNamePrefix + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            statusManagementExportControlPage.enterStatusName(statusName);
            ExtentReportListener.getExtentTest().info("Entered '" + statusName + "' into Status Name input field");
            Assert.assertTrue(statusManagementExportControlPage.isStatusNameLabelDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Status Name' label is displayed");

            statusManagementExportControlPage.clickAddButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add' button successfully");
            Assert.assertTrue(statusManagementExportControlPage.isStatusManagementPageDisplayed01(), "Status Management page did not load correctly");
            ExtentReportListener.getExtentTest().pass("Verified that user lands on Status Management page successfully");

            statusManagementExportControlPage.clickDeleteButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().info("Clicked delete icon for: " + statusName);

            statusManagementExportControlPage.clickEditButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().info("Clicked edit icon for: " + statusName);
            Assert.assertTrue(statusManagementExportControlPage.isActiveLabelDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Active' label is displayed");

            statusManagementExportControlPage.appendToStatusName(statusNameSuffix);
            ExtentReportListener.getExtentTest().info("Appended '" + statusNameSuffix + "' to Status Name input field");
            Assert.assertTrue(statusManagementExportControlPage.isActiveLabelDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Active' label is displayed");

            statusManagementExportControlPage.selectActiveAsNo();
            ExtentReportListener.getExtentTest().info("Selected 'No' from Active dropdown");

            statusManagementExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");
            Assert.assertTrue(statusManagementExportControlPage.isStatusManagementPageDisplayed01(), "Status Management page did not load correctly");
            ExtentReportListener.getExtentTest().pass("Verified that user lands on Status Management page successfully");

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