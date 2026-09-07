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
import pages.Administration.Workflow_Management.WorkflowsPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239474_WorkflowManagement_Workflows {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    WorkflowsPage workflowsPage;

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
        workflowsPage = new WorkflowsPage(driver);
    }

    @Test
    public void ExportControl_WorkflowManagement_Workflows_Test () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String workflowNamePrefix = JsonDataReader.get(1, "WorkflowNamePrefix");                     // "Test_"
            String workflowRecordTypeOption = JsonDataReader.get(1, "WorkflowRecordTypeOption");         // "Test1"
            String workflowTransactionTypeOption = JsonDataReader.get(1, "WorkflowTransactionTypeOption"); // "tesss"
            String workflowExportControlStatusOption = JsonDataReader.get(1, "WorkflowExportControlStatusOption"); // "Draft"
            String workflowEmailFrom = JsonDataReader.get(1, "WorkflowEmailFrom");                       // "insighthelpdesk@partners.org"

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

            // Work flow page
            workflowsPage.clickWorkflowManagementLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Workflow Management' menu link successfully");

            basePage.pause(2000);
            workflowsPage.clickWorkflowsLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Workflows' link from left navigation successfully");
            Assert.assertTrue(workflowsPage.isWorkflowsHeaderDisplayed(), "Workflows header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Workflows page header is displayed successfully");

            workflowsPage.clickAddNewButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button successfully");

            String uniqueName = workflowNamePrefix + System.currentTimeMillis();
            workflowsPage.enterName(uniqueName);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + uniqueName + "' in the Name input field successfully");
            Assert.assertTrue(workflowsPage.isNameLabelDisplayed(), "Name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Name' label with mandatory asterisk is displayed");

            workflowsPage.selectOptionFromDropdown("Record Type", workflowRecordTypeOption);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowRecordTypeOption + "' from Record Type dropdown successfully");
            Assert.assertTrue(workflowsPage.isRecordTypeLabelDisplayed(), "Record Type label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Record Type' label with mandatory asterisk is displayed");

            workflowsPage.selectOptionFromDropdown("Transaction Type", workflowTransactionTypeOption);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowTransactionTypeOption + "' from Transaction Type dropdown successfully");
            Assert.assertTrue(workflowsPage.isTransactionTypeLabelDisplayed(), "Transaction Type label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Transaction Type' label with mandatory asterisk is displayed");

            workflowsPage.selectOptionFromDropdownExportControlStatus("Export Control Status", workflowExportControlStatusOption);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowExportControlStatusOption + "' from Export Control Status dropdown successfully");
            Assert.assertTrue(workflowsPage.isExportControlStatusLabelDisplayed(), "Export Control Status label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control Status' label is displayed");

            workflowsPage.selectOptionFromDropdown("Email From", workflowEmailFrom);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowEmailFrom + "' from Email From dropdown successfully");
            Assert.assertTrue(workflowsPage.isEmailFromLabelDisplayed(), "Email From label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Email From' label with mandatory asterisk is displayed");

            // ** Negative_case **
            workflowsPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");

            workflowsPage.clickAddNewButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button successfully");

            String uniqueName01 = workflowNamePrefix + System.currentTimeMillis();
            workflowsPage.enterName(uniqueName01);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + uniqueName01 + "' in the Name input field successfully");

            workflowsPage.selectOptionFromDropdown("Record Type", workflowRecordTypeOption);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowRecordTypeOption + "' from Record Type dropdown successfully");

            workflowsPage.selectOptionFromDropdown("Transaction Type", workflowTransactionTypeOption);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowTransactionTypeOption + "' from Transaction Type dropdown successfully");

            workflowsPage.selectOptionFromDropdownExportControlStatus("Export Control Status", workflowExportControlStatusOption);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowExportControlStatusOption + "' from Export Control Status dropdown successfully");

            workflowsPage.selectOptionFromDropdown("Email From", workflowEmailFrom);
            ExtentReportListener.getExtentTest().info("Selected '" + workflowEmailFrom + "' from Email From dropdown successfully");

            workflowsPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(workflowsPage.isWorkflowsHeaderDisplayed(), "Workflows header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Workflows page header is displayed successfully");

            workflowsPage.clickFirstEdit();
            ExtentReportListener.getExtentTest().info("Clicked the first visible 'Edit' in Workflows grid.");

            workflowsPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(workflowsPage.isWorkflowsHeaderDisplayed(), "Workflows header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Workflows page header is displayed successfully");

            workflowsPage.clickFirstEdit();
            ExtentReportListener.getExtentTest().info("Clicked the first visible 'Edit' in Workflows grid.");
            Assert.assertTrue(workflowsPage.isNameLabelDisplayed(), "Name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Name' label with mandatory asterisk is displayed");

            workflowsPage.clickUpdateButton();
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