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
import pages.Export_Control.Export_Control_Details.MenuFlow;
import pages.Export_Control.Export_Control_Details.MyActionsPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.WaitUtility;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239491_My_Actions {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    MenuFlow menuFlow;
    MyActionsPage myActionsPage;
    WaitUtility waitUtility;

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
        menuFlow = new MenuFlow(driver);
        myActionsPage = new MyActionsPage(driver);
        waitUtility = new WaitUtility(driver);
    }

    @Test
    public void Export_control_My_actions() {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String reviewerCode = JsonDataReader.get(1, "MyActionsReviewerCode");
            String reviewerDisplay = JsonDataReader.get(1, "MyActionsReviewerDisplay");
            String submitterCode = JsonDataReader.get(1, "MyActionsSubmitterCode");
            String submitterDisplay = JsonDataReader.get(1, "MyActionsSubmitterDisplay");
            String recordNumber = JsonDataReader.get(1, "MyActionsRecordNumber");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);
            waitUtility.waitUntilPageLoad(driver, 120);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            waitUtility.waitUntilPageLoad(driver, 120);
            menuFlow.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            myActionsPage.clickActionRequiredLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Action Required' from Export Control left navigation");
            Assert.assertTrue(myActionsPage.isActionRequiredBreadcrumbDisplayed(), "'Action Required' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' breadcrumb is displayed successfully");

            myActionsPage.selectReviewer(reviewerCode, reviewerDisplay);
            ExtentReportListener.getExtentTest().info("Selected Reviewer '" + reviewerDisplay + "' successfully");
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            // Record number
            myActionsPage.enterRecordNumber(recordNumber);
            ExtentReportListener.getExtentTest().info("Entered Record Number: " + recordNumber);
            Assert.assertTrue(myActionsPage.isRecordNumberLabelDisplayed(), "Record Number label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Record Number' label is displayed");

            myActionsPage.clickSubmitterFilter();
            ExtentReportListener.getExtentTest().info("Clicked Submitter filter successfully");

            myActionsPage.selectSubmitter(submitterCode, submitterDisplay);
            ExtentReportListener.getExtentTest().info("Selected Submitter '" + submitterDisplay + "' successfully");
            Assert.assertTrue(myActionsPage.isSubmitterLabelDisplayed(), "Submitter label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Submitter' label is displayed");

            myActionsPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked Search button successfully");

            myActionsPage.clickClearSelections();
            ExtentReportListener.getExtentTest().info("Clicked Clear Selections successfully");

            // Record number search again
            myActionsPage.enterRecordNumber(recordNumber);
            ExtentReportListener.getExtentTest().info("Entered Record Number: " + recordNumber);
            Assert.assertTrue(myActionsPage.isRecordNumberLabelDisplayed(), "Record Number label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Record Number' label is displayed");

            myActionsPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked Search on Action Required");
            Assert.assertTrue(myActionsPage.isActionRequiredBreadcrumbDisplayed(),"'Action Required' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' breadcrumb is displayed successfully");

            myActionsPage.clickClearSelections();
            ExtentReportListener.getExtentTest().info("Clicked Clear Selections");
            Assert.assertTrue(myActionsPage.isActionRequiredBreadcrumbDisplayed(),"'Action Required' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' breadcrumb is displayed successfully");

            // 7. Click record link in grid
            myActionsPage.clickFirstRecordNumberLink();
            ExtentReportListener.getExtentTest().info("Clicked first Record Number link 'Record_Number' from Action Required grid");

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