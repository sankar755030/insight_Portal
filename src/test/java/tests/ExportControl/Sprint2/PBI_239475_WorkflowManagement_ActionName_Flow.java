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
import pages.Administration.Workflow_Management.ActionNamePage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239475_WorkflowManagement_ActionName_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ActionNamePage actionNamePage;
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
        actionNamePage = new ActionNamePage(driver);
        agreementPage = new AgreementPage(driver);
    }

    @Test
    public void ExportControl_WorkflowManagement_ActionName_Test () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String actionNamePrefix = JsonDataReader.get(1, "ActionNamePrefix");       // e.g. "Test_"
            String actionHistoryTitle = JsonDataReader.get(1, "ActionHistoryTitle");   // e.g. "TestSan"

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

            actionNamePage.clickWorkflowManagementaction();
            ExtentReportListener.getExtentTest().info("Opened 'Workflow Management'");

            actionNamePage.clickActionNameLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Action name' link successfully");
            Assert.assertTrue(actionNamePage.isActionNameHeaderDisplayed(), "Action Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Action Name page header is displayed successfully");

            actionNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button successfully");
            Assert.assertTrue(actionNamePage.isActionNameLabelDisplayed(), "Action name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action name' label with mandatory asterisk is displayed");

            String uniqueName03 = actionNamePrefix + System.currentTimeMillis();
            actionNamePage.enterName(uniqueName03);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + uniqueName03 + "' in the Name input field successfully");
            Assert.assertTrue(actionNamePage.isActionNameLabelDisplayed(), "Action name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action name' label with mandatory asterisk is displayed");

            actionNamePage.enterHistoryTitle(actionHistoryTitle);
            ExtentReportListener.getExtentTest().info("Entered '" + actionHistoryTitle + "' into History Title input field successfully");
            Assert.assertTrue(actionNamePage.isActionNameLabelDisplayed(), "Action name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action name' label with mandatory asterisk is displayed");

            actionNamePage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(actionNamePage.isActionNameHeaderDisplayed(), "Action Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Action Name page header is displayed successfully");

            actionNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button successfully");
            Assert.assertTrue(actionNamePage.isActionNameLabelDisplayed(), "Action name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action name' label with mandatory asterisk is displayed");

            String uniqueName5 = actionNamePrefix + System.currentTimeMillis();
            actionNamePage.enterName(uniqueName5);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + uniqueName5 + "' in the Name input field successfully");

            actionNamePage.enterHistoryTitle(actionHistoryTitle);
            ExtentReportListener.getExtentTest().info("Entered '" + actionHistoryTitle + "' into History Title input field successfully");

            actionNamePage.clickAddButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add' button successfully");
            Assert.assertTrue(actionNamePage.isActionNameHeaderDisplayed(), "Action Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Action Name page header is displayed successfully");

            actionNamePage.clickEditForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().info("Clicked 'Edit' for action name '" + uniqueName5 + "' successfully");
            Assert.assertTrue(actionNamePage.isActionNameHeaderDisplayed(), "Action Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Action Name page header is displayed successfully");

            actionNamePage.clickCancelForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' for action name '" + uniqueName5 + "' successfully");
            Assert.assertTrue(actionNamePage.isActionNameHeaderDisplayed(), "Action Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Action Name page header is displayed successfully");

            actionNamePage.clickEditForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().info("Clicked 'Edit' for action name '" + uniqueName5 + "' successfully");

            actionNamePage.clickSaveForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().info("Clicked 'Save' for action name '" + uniqueName5 + "' successfully");

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