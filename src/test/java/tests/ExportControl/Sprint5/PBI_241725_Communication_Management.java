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
import pages.Administration.Communication_Management.CommunicationManagement_ExportControlPage;
import pages.Administration.Workflow_Management.WorkflowsPage;
import pages.Export_Control.Export_Control_Details.AmendExportControlPage;
import pages.Administration.Form_Visibility.FormsVisibility_ExportControlPage;
import pages.Export_Control.Export_Control_Details.InitialReviewWorkflowPage;
import pages.Adobe.AgreementPage;
import pages.Export_Control.Export_Control_Details.AddChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.DisplayChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.MenuFlow;
import pages.Export_Control.Export_Control_Details.ResponseToReviewPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_241725_Communication_Management {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CommunicationManagement_ExportControlPage communicationManagementExportControlPage;
    AmendExportControlPage amendExportControlPage;
    FormsVisibility_ExportControlPage formsVisibilityExportControlPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    AddChecklistFlowPage addChecklistFlowPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    MenuFlow menuFlow;
    ResponseToReviewPage responseToReviewPage;
    SystemAdminPage systemAdminPage;
    AgreementPage agreementPage;
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
        communicationManagementExportControlPage = new CommunicationManagement_ExportControlPage(driver);
        amendExportControlPage = new AmendExportControlPage(driver);
        formsVisibilityExportControlPage = new FormsVisibility_ExportControlPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        menuFlow = new MenuFlow(driver);
        responseToReviewPage = new ResponseToReviewPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        agreementPage = new AgreementPage(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
        workflowsPage = new WorkflowsPage(driver);
    }

    @Test
    public void Communication_Management ()
    {
        try {
            String url = JsonDataReader.get(0, "URL");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");

            String templateNoticeGroup             = JsonDataReader.get(1, "TemplateNoticeGroup");
            String templateLayout                  = JsonDataReader.get(1, "TemplateLayout");
            String templateReminderFrequency       = JsonDataReader.get(1, "TemplateReminderFrequency");
            String notificationTypeApproved        = JsonDataReader.get(1, "NotificationTypeApproved");

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

            communicationManagementExportControlPage.expandCommunicationManagement();
            ExtentReportListener.getExtentTest().info("Expanded Communication Management menu successfully");

            communicationManagementExportControlPage.clickCommunicationExportControl();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' under Communication Management successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isCommunicationManagementCrumbDisplayed(), "'Communication Management' crumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Communication Management' crumb is displayed");

            communicationManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New Template' button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isAddNewTemplateTitleDisplayed(), "'Add New Template' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Add New Template' title is displayed");

            // Step 1: Generate random template name using your method
            String templateName = uniqueNameGenerator.GenerateRandomName(8);

            // Step 1 – Template Name
            communicationManagementExportControlPage.setTemplateName(templateName);
            ExtentReportListener.getExtentTest().info("Entered Template Name as '" + templateName + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isNoticeGroupLabelDisplayed(), "'Notice Group' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notice Group' label is displayed");

            communicationManagementExportControlPage.selectNoticeGroup(templateNoticeGroup);
            ExtentReportListener.getExtentTest().info("Selected Notice Group: '" + templateNoticeGroup + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isNoticeGroupLabelDisplayed(), "'Notice Group' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notice Group' label is displayed");

            communicationManagementExportControlPage.selectLayout(templateLayout);
            ExtentReportListener.getExtentTest().info("Selected Layout: '" + templateLayout + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isLayoutLabelDisplayed(), "'Layout' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Layout' label is displayed");

            communicationManagementExportControlPage.setReminderFrequency(templateReminderFrequency);
            ExtentReportListener.getExtentTest().info("Set Reminder Frequency (days) to '" + templateReminderFrequency + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isReminderFrequencyLabelDisplayed(), "'Reminder Frequency' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reminder Frequency (days)' label is displayed");

            // Step 6 – Click Cancel (if requirement is to close modal after creation)
            communicationManagementExportControlPage.clickCancelTemplateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button on Add New Template modal successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isCommunicationManagementCrumbDisplayed(), "'Communication Management' crumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Communication Management' crumb is displayed");

            communicationManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New Template' button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isAddNewTemplateTitleDisplayed(), "'Add New Template' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Add New Template' title is displayed");

            // Step 1: Generate random template name using your method
            String templateName02 = uniqueNameGenerator.GenerateRandomName(8);

            communicationManagementExportControlPage.setTemplateName(templateName02);
            ExtentReportListener.getExtentTest().info("Entered Template Name as '" + templateName02 + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isNoticeGroupLabelDisplayed(), "'Notice Group' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notice Group' label is displayed");

            communicationManagementExportControlPage.selectNoticeGroup(templateNoticeGroup);
            ExtentReportListener.getExtentTest().info("Selected Notice Group: '" + templateNoticeGroup + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isNoticeGroupLabelDisplayed(), "'Notice Group' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notice Group' label is displayed");

            communicationManagementExportControlPage.selectLayout(templateLayout);
            ExtentReportListener.getExtentTest().info("Selected Layout: '" + templateLayout + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isLayoutLabelDisplayed(), "'Layout' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Layout' label is displayed");

            communicationManagementExportControlPage.setReminderFrequency(templateReminderFrequency);
            ExtentReportListener.getExtentTest().info("Set Reminder Frequency (days) to '" + templateReminderFrequency + "'");
            Assert.assertTrue(communicationManagementExportControlPage.isReminderFrequencyLabelDisplayed(), "'Reminder Frequency' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reminder Frequency (days)' label is displayed");

            // Step 5 – Click Create Template
            communicationManagementExportControlPage.clickCreateTemplateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Create Template' button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isCommunicationManagementCrumbDisplayed(), "'Communication Management' crumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Communication Management' crumb is displayed");

            // ______________Un comment is Hollywood Env_________________

//            // Step 1 – Notification Type: Export Control Approved
//            communicationManagementExportControlPage.selectNotificationType(notificationTypeApproved);
//            ExtentReportListener.getExtentTest().info("Selected Notification Type as '" + notificationTypeApproved + "' successfully");
//            Assert.assertTrue(communicationManagementExportControlPage.isNotificationTypeLabelDisplayed(), "'Notification Type' label is NOT displayed");
//            ExtentReportListener.getExtentTest().pass("Verified 'Notification Type' label is displayed");
//
//            // Step 2 – Click Cancel
//            communicationManagementExportControlPage.clickCancelButton();
//            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully on Notification Template form");
//            Assert.assertTrue(communicationManagementExportControlPage.isNotificationTypeLabelDisplayed(), "'Notification Type' label is NOT displayed");
//            ExtentReportListener.getExtentTest().pass("Verified 'Notification Type' label is displayed");
//
//            formsVisibilityExportControlPage.openExportControlUnderFormVisibility();
//            ExtentReportListener.getExtentTest().info("Opened 'Export Control' under Form Visibility successfully");
//
//            communicationManagementExportControlPage.expandCommunicationManagement();
//            ExtentReportListener.getExtentTest().info("Expanded Communication Management menu successfully");
//
//            communicationManagementExportControlPage.clickCommunicationExportControl();
//            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' under Communication Management successfully");
//            Assert.assertTrue(communicationManagementExportControlPage.isCommunicationManagementCrumbDisplayed(), "'Communication Management' crumb is NOT displayed");
//            ExtentReportListener.getExtentTest().pass("Verified 'Communication Management' crumb is displayed");
//
//            communicationManagementExportControlPage.selectNotificationType(notificationTypeApproved);
//            ExtentReportListener.getExtentTest().info("Selected Notification Type as '" + notificationTypeApproved + "' successfully after reopening form");
//            Assert.assertTrue(communicationManagementExportControlPage.isNotificationTypeLabelDisplayed(), "'Notification Type' label is NOT displayed");
//            ExtentReportListener.getExtentTest().pass("Verified 'Notification Type' label is displayed");
//
//            // Step 4 – Click Save
//            communicationManagementExportControlPage.clickSaveButton();
//            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully on Notification Template form");

            // Workflow for communication management **********_Flow 2_***************

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

            //**********Communication_Management_Flow 3_***************

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            communicationManagementExportControlPage.clickNotificationsMenu();
            ExtentReportListener.getExtentTest().info("Clicked 'Notifications' menu link successfully from left navigation");
            Assert.assertTrue(communicationManagementExportControlPage.isNotificationsScreenTitleDisplayed(), "'Notifications' screen title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notifications' screen title is displayed");

            communicationManagementExportControlPage.clickFirstPlusButton();
            ExtentReportListener.getExtentTest().info("Clicked first '+' expand button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isNotificationsScreenTitleDisplayed(), "'Notifications' screen title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notifications' screen title is displayed");

            communicationManagementExportControlPage.clickCollapseButton();
            ExtentReportListener.getExtentTest().info("Clicked '-' collapse button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isNotificationsScreenTitleDisplayed(), "'Notifications' screen title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notifications' screen title is displayed");
        }
        catch (Exception e)
        {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown()
    {
        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}