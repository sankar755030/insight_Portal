package tests.ExportControl.Sprint2;


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
import pages.Administration.Workflow_Management.AncillaryWorkflowsPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239475_WorkflowManagement_Ancillary_Workflow_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AncillaryWorkflowsPage ancillaryWorkflowsPage;
    AgreementPage agreementPage;

    @BeforeMethod
    public void setupBrowser() {
        //User will set up and configure the Chrome WebDriver using WebDriverManager
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
        ancillaryWorkflowsPage = new AncillaryWorkflowsPage(driver);
        agreementPage = new AgreementPage(driver);
    }

    @Test
    public void ExportControl_WorkflowManagement_Ancillary_workflow_Test () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String ancillaryNamePrefix = JsonDataReader.get(1, "AncillaryWorkflowNamePrefix");           // "Test_"
            String ancillaryEmailFrom = JsonDataReader.get(1, "AncillaryWorkflowEmailFrom");             // "insighthelpdesk@partners.org"

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

            ancillaryWorkflowsPage.clickWorkflowManagement();
            ExtentReportListener.getExtentTest().info("Opened 'Workflow Management'");

            ancillaryWorkflowsPage.clickAncillaryWorkflowsLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Ancillary Workflows' link for scopeId = 5 successfully");
            Assert.assertTrue(ancillaryWorkflowsPage.isAncillaryWorkflowsHeaderDisplayed(), "Ancillary Workflows header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Ancillary Workflows' page header is displayed successfully");

            ancillaryWorkflowsPage.clickAddNew();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button");
            Assert.assertTrue(ancillaryWorkflowsPage.isNameLabelDisplayed(), "Name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Name' label with mandatory asterisk is displayed");

            String uniqueName01 = ancillaryNamePrefix + System.currentTimeMillis();
            ancillaryWorkflowsPage.enterNameAnc(uniqueName01);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + uniqueName01 + "' in the Name input field successfully");
            Assert.assertTrue(ancillaryWorkflowsPage.isNameLabelDisplayed(), "Name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Name' label with mandatory asterisk is displayed");

            ancillaryWorkflowsPage.selectOptionFromDropdownAncillary("Email From", ancillaryEmailFrom);
            ExtentReportListener.getExtentTest().info("Selected '" + ancillaryEmailFrom + "' from Email From dropdown successfully");
            Assert.assertTrue(ancillaryWorkflowsPage.isTriggeringRuleLabelDisplayed(), "Triggering Rule label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Triggering Rule' label is displayed");

            ancillaryWorkflowsPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(ancillaryWorkflowsPage.isAncillaryWorkflowsHeaderDisplayed(), "Ancillary Workflows header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Ancillary Workflows' page header is displayed successfully");

            ancillaryWorkflowsPage.clickAddNew();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button");
            Assert.assertTrue(ancillaryWorkflowsPage.isNameLabelDisplayed(), "Name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Name' label with mandatory asterisk is displayed");

            String uniqueName02 = ancillaryNamePrefix + System.currentTimeMillis();
            ancillaryWorkflowsPage.enterNameAnc(uniqueName02);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + uniqueName02 + "' in the Name input field successfully");

            ancillaryWorkflowsPage.selectOptionFromDropdownAncillary("Email From", ancillaryEmailFrom);
            ExtentReportListener.getExtentTest().info("Selected '" + ancillaryEmailFrom + "' from Email From dropdown successfully");

            ancillaryWorkflowsPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

            ancillaryWorkflowsPage.clickLastEdit();
            ExtentReportListener.getExtentTest().info("Clicked last 'Edit' on Ancillary Workflows list");
            Assert.assertTrue(ancillaryWorkflowsPage.isNameLabelDisplayed(), "Name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Name' label with mandatory asterisk is displayed");

            ancillaryWorkflowsPage.clickUpdateButtonAnc();
            ExtentReportListener.getExtentTest().info("Clicked 'Update' button successfully");

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