package tests.ExportControl.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Administration.Status_Management.StatusManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239497_Status_Management_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
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
        agreementPage = new AgreementPage(driver);
        statusManagementExportControlPage = new StatusManagement_ExportControlPage(driver);
    }

    @Test
    public void Export_control_Status_Management_Test() {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String statusNamePrefix = JsonDataReader.get(1, "StatusNamePrefix");  // e.g. "Test"
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

            agreementPage.clickAdministrationLink();
            Assert.assertTrue(agreementPage.isDashboardNotificationsSummaryDisplayed(), "Dashboard Notifications - Summary page is NOT displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Dashboard Notifications - Summary page.");

            // StatusManagement Page Actions

            statusManagementExportControlPage.clickStatusManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Status Management' link successfully");

            statusManagementExportControlPage.clickStatusManagementExportControl();
            ExtentReportListener.getExtentTest().info("Clicked 'Status Management > Export Control' successfully");
            Assert.assertTrue(statusManagementExportControlPage.isStatusManagementPageDisplayed(), "Status Management page is NOT displayed after clicking 'Status Management > Export Control'");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Status Management page.");

            statusManagementExportControlPage.clickAddStatusButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Status' button successfully");
            Assert.assertTrue(statusManagementExportControlPage.isAddStatusModalDisplayed(), "'Add Status' modal is NOT displayed after clicking 'Add Status' button");
            ExtentReportListener.getExtentTest().pass("'Add Status' modal is displayed successfully.");

            String statusName = statusNamePrefix + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            statusManagementExportControlPage.enterStatusName(statusName);
            ExtentReportListener.getExtentTest().info("Entered '" + statusName + "' into Status Name input field");
            Assert.assertTrue(statusManagementExportControlPage.isAddStatusModalDisplayed(), "'Add Status' modal is NOT displayed after clicking 'Add Status' button");
            ExtentReportListener.getExtentTest().pass("'Add Status' modal is displayed successfully.");

            statusManagementExportControlPage.clickAddButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add' button successfully");
            Assert.assertTrue(statusManagementExportControlPage.isStatusManagementPageDisplayed(), "Status Management page is NOT displayed after clicking 'Status Management > Export Control'");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Status Management page.");

            statusManagementExportControlPage.clickDeleteButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().info("Clicked delete icon for: " + statusName);

            statusManagementExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Clear Selections' button successfully");
            Assert.assertTrue(statusManagementExportControlPage.isSearchByNameCleared(), "'Search by Name' input is NOT cleared after clicking 'Clear Selections' button");
            ExtentReportListener.getExtentTest().pass("'Search by Name' input is cleared successfully after clicking 'Clear Selections' button.");

            statusManagementExportControlPage.clickEditButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().info("Clicked edit icon for: " + statusName);
            Assert.assertTrue(statusManagementExportControlPage.isEditStatusModalDisplayed(), "'Edit Status' modal is NOT displayed after clicking edit icon for: " + statusName);
            ExtentReportListener.getExtentTest().pass("'Edit Status' modal is displayed successfully for: " + statusName);

            statusManagementExportControlPage.appendToStatusName(statusNameSuffix);
            ExtentReportListener.getExtentTest().info("Appended '" + statusNameSuffix + "' to Status Name input field");
            Assert.assertTrue(statusManagementExportControlPage.isEditStatusModalDisplayed(), "'Edit Status' modal is NOT displayed after clicking edit icon for: " + statusName);
            ExtentReportListener.getExtentTest().pass("'Edit Status' modal is displayed successfully for: " + statusName);

            statusManagementExportControlPage.selectActiveAsNo();
            ExtentReportListener.getExtentTest().info("Selected 'No' from Active dropdown");

            statusManagementExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

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