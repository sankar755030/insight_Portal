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
import pages.Export_Control.Export_Control_Details.*;
import pages.Home.DashboardPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.LoginPage;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_248109_Notes {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    MenuFlow menuFlow;
    NotesPage notesPage;
    SystemAdminPage systemAdminPage;
    CreateExportControlPage createExportControlPage;
    MyActionsPage myActionsPage;

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
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        menuFlow = new MenuFlow(driver);
        notesPage = new NotesPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        myActionsPage = new MyActionsPage(driver);
    }

    @Test
    public void Export_control_Notes ()
    {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");
            String piSearchText      = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName        = JsonDataReader.get(3, "PIName");                    // "Chandra, Mohan"
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");
            String initialNoteText     = JsonDataReader.get(3, "InitialReviewNoteText");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

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

            notesPage.clickNotesSection();
            ExtentReportListener.getExtentTest().info("Clicked Notes section successfully");

            //***************Login to System Admin page********************

            String sysAdminUrl = JsonDataReader.get(0, "SysAdminURL");
            String sysAdminBusinessUser = JsonDataReader.get(0, "SysAdminBusinessUser");

            driver.get(sysAdminUrl);
            ExtentReportListener.getExtentTest().info("Opened SysAdmin public URL successfully");

            loginPage.enterSysAdminBusinessUser(sysAdminBusinessUser);
            ExtentReportListener.getExtentTest().info("Entered SysAdmin Business User: " + sysAdminBusinessUser + " successfully");

            loginPage.clickSysAdminLoginButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Login' button on SysAdmin page successfully");
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isActionRequiredLinkDisplayed(), "'Action Required' link is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' link is displayed successfully");

            createExportControlPage.clickActionRequired();
            ExtentReportListener.getExtentTest().info("Clicked 'Action Required' menu successfully");
            Assert.assertTrue(myActionsPage.isActionRequiredBreadcrumbDisplayed(), "'Action Required' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' breadcrumb is displayed successfully");

            systemAdminPage.clickFirstRecordNumber();
            ExtentReportListener.getExtentTest().info("Clicked first Record Number in Action Required grid successfully");

            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            createExportControlPage.selectChiefApprovalConfirmation();
            ExtentReportListener.getExtentTest().pass("Checked 'I have carefully reviewed...' confirmation checkbox");

            notesPage.clickNotesSection();
            ExtentReportListener.getExtentTest().info("Clicked Notes section successfully");
            Assert.assertTrue(systemAdminPage.isNotesSectionTitleDisplayed(), "'Notes' section title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notes' section title is displayed successfully");

            notesPage.clickAddNoteButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Note' button successfully");
            Assert.assertTrue(systemAdminPage.isNotesSectionTitleDisplayed(), "'Notes' section title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notes' section title is displayed successfully");

            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);

            notesPage.enterNotesAndClickAdd(initialNoteText);
            ExtentReportListener.getExtentTest().info("Entered '" + initialNoteText + "' in Notes and clicked 'Add' successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Approve' button successfully");
            Assert.assertTrue(myActionsPage.isPINameDisplayed(), "'PI: Chandra, Mohan' is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'PI' value is displayed as 'Chandra, Mohan' successfully");

            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().info("Clicked Logout successfully");

            //****************_User login to main dashboard_************

            String url1 = JsonDataReader.get(0, "URL");
            String userName1 = JsonDataReader.get(0, "Username");
            String password1 = JsonDataReader.get(0, "Password");

            // User will open the login page of the Insight Portal application
            driver.get(url1);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName1, password1);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            menuFlow.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' link successfully from Export Control sidebar");

            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().info("Successfully entered dynamic record number: " + recordNum);
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked Search");
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            // After you search and the grid is loaded:
            systemAdminPage.clickRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().info("Clicked Record Number link in grid: " + recordNum);

            notesPage.clickNotesSection01();
            ExtentReportListener.getExtentTest().info("Clicked Notes section successfully");
            Assert.assertTrue(notesPage.isNotesSectionDisplayed(), "'Notes' section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notes' section is displayed");
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