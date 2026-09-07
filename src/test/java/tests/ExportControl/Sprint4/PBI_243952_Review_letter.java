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
import pages.Administration.Workflow_Management.WorkflowsPage;
import pages.Adobe.AgreementPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Export_Control.Export_Control_Details.AddChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.DisplayChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.InitialReviewWorkflowPage;
import pages.Export_Control.Export_Control_Details.ResponseToReviewPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_243952_Review_letter {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    AddChecklistFlowPage addChecklistFlowPage;
    ResponseToReviewPage responseToReviewPage;
    CreateExportControlPage createExportControlPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    UniqueNameGenerator uniqueNameGenerator;
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
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        responseToReviewPage = new ResponseToReviewPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        agreementPage = new AgreementPage(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
        workflowsPage = new WorkflowsPage(driver);
    }

    @Test
    public void Export_control_Review_letter () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String piSearchText          = JsonDataReader.get(3, "InitialReviewPiSearchText");      // e.g. "mohan"
            String piFullName            = JsonDataReader.get(3, "PIName");                         // e.g. "Chandra, Mohan"
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");

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


            workflowsPage.clickWorkflowManagementLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Workflow Management' menu link successfully");
            Assert.assertTrue(addChecklistFlowPage.isWorkflowsHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Workflows' header displayed");

            workflowsPage.clickWorkflowsLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Workflows' link from left navigation successfully");
            Assert.assertTrue(workflowsPage.isWorkflowsHeaderDisplayed(), "Workflows header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Workflows page header is displayed successfully");

            addChecklistFlowPage.clickPersonnelExclusionWorkflow();
            ExtentReportListener.getExtentTest().pass("Clicked 'Personnal Exclusion' workflow successfully");
            Assert.assertTrue(addChecklistFlowPage.isPersonnelExclusionLabelDisplayed(), "'Personnal Exclusion' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnal Exclusion' label is displayed");

            addChecklistFlowPage.clickAddNewWorkflowVersion();
            ExtentReportListener.getExtentTest().info("Clicked 'Add new' button successfully on Versions page");

            // Step X: Enter description for Version 90
            String versionDescription = "Test01_" + uniqueNameGenerator.GenerateRandomName(6);

            addChecklistFlowPage.enterDescriptionForLatestVersion(versionDescription);
            ExtentReportListener.getExtentTest().info("Entered description for Version 90 as: " + versionDescription);
            Assert.assertTrue(addChecklistFlowPage.isVersionsHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Versions' header displayed");

            addChecklistFlowPage.clickSaveVersion();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully for Version 90");
            Assert.assertTrue(addChecklistFlowPage.isVersionsHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Versions' header displayed");

            addChecklistFlowPage.clickLatestVersionLink();
            ExtentReportListener.getExtentTest().info("Clicked latest Version link successfully");
            Assert.assertTrue(addChecklistFlowPage.isSaveButtonDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Save' button displayed");

            responseToReviewPage.clickChiefApprover();
            ExtentReportListener.getExtentTest().info("Clicked on Chief Approver node successfully");
            Assert.assertTrue(addChecklistFlowPage.isChiefApproverHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Chief Approver' section displayed");

            responseToReviewPage.clickActivitiesTab();
            ExtentReportListener.getExtentTest().info("Clicked Activities tab successfully");
            ExtentReportListener.getExtentTest().info("Generate Document is successfully Added in Activities Tab");
            Assert.assertTrue(addChecklistFlowPage.isChiefApproverHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Chief Approver' section displayed");

            addChecklistFlowPage.clickUpdateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Update' button successfully");
            Assert.assertTrue(addChecklistFlowPage.isSaveButtonDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Save' button displayed");

            addChecklistFlowPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            createExportControlPage.clickCreateExportControl();
            ExtentReportListener.getExtentTest().info("Clicked Actions → Create Export Control from left navigation successfully");
            Assert.assertTrue(createExportControlPage.isCreateExportControlHeaderDisplayed(), "'Create Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Export Control' header is displayed successfully");

            displayChecklistFlowPage.selectPersonnelExclusion();
            ExtentReportListener.getExtentTest().info("Selected Personnel Exclusion radio button successfully");
            Assert.assertTrue(displayChecklistFlowPage.isPersonnelExclusionRadioDisplayed(), "'Personnel Exclusion' radio button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' radio button is displayed");

            // Step 2: Select PI Name (type from JSON and choose PI name from JSON)
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().info("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");
            Assert.assertTrue(displayChecklistFlowPage.isSelectPINameDisabledDisplayed(), "'Select PI Name' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Select PI Name' disabled field is displayed successfully");

            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Create' button on Create Export Control sidebar successfully");

            try {
                createExportControlPage.clickSubmitRadioButton();
                ExtentReportListener.getExtentTest().pass("Clicked 'Submit' radio button successfully");

                createExportControlPage.enterPetName(positiveSearchText);
                ExtentReportListener.getExtentTest().pass("Entered Pet name as: " + positiveSearchText);

                // Step 1: Click Save
                displayChecklistFlowPage.clickSaveAction();
                ExtentReportListener.getExtentTest().info("Clicked Save button successfully");
                Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

                createExportControlPage.clickSignOffButton();
                ExtentReportListener.getExtentTest().pass("Clicked 'Sign Off' button successfully");
            }
            catch (Exception e)
            {

            }
            finally {
                displayChecklistFlowPage.clickSubmitAction();
                ExtentReportListener.getExtentTest().info("Clicked Submit button successfully");
            }

            initialReviewWorkflowPage.clickInitialReview();
            ExtentReportListener.getExtentTest().info("Clicked Initial Review (IR) successfully");

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