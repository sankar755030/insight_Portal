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
import pages.Administration.Workflow_Management.StepNamePage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239474_WorkflowManagement_Stepname_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    StepNamePage stepNamePage;
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
        stepNamePage = new StepNamePage(driver);
        agreementPage = new AgreementPage(driver);
    }

    @Test
    public void ExportControl_WorkflowManagement_StepName_Test () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            // Step name test data from JSON (index 1)
            String stepNameInitial = JsonDataReader.get(1, "StepNameInitial");   // e.g. "Sample Name"
            String stepNamePrefix = JsonDataReader.get(1, "StepNamePrefix");     // e.g. "Test"

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

            stepNamePage.clickWorkflowManagementStepName();
            ExtentReportListener.getExtentTest().info("Opened 'Workflow Management'");

            stepNamePage.clickExportControlStepName();
            ExtentReportListener.getExtentTest().info("Opened ExportControlTransaction > Step name successfully");
            Assert.assertTrue(stepNamePage.isStepNameHeaderDisplayed(), "Step Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step Name header is displayed successfully");

            stepNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button successfully");
            Assert.assertTrue(stepNamePage.isStepNameLabelDisplayed(), "Step name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step name label with mandatory asterisk is displayed successfully");

            stepNamePage.enterName(stepNameInitial);
            ExtentReportListener.getExtentTest().info("Entered '" + stepNameInitial + "' into Name input field successfully");
            Assert.assertTrue(stepNamePage.isStepNameLabelDisplayed(), "Step name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step name label with mandatory asterisk is displayed successfully");

            stepNamePage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(stepNamePage.isStepNameHeaderDisplayed(), "Step Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step Name header is displayed successfully");

            stepNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New' button successfully");
            Assert.assertTrue(stepNamePage.isStepNameLabelDisplayed(), "Step name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step name label with mandatory asterisk is displayed successfully");

            String generatedName = stepNamePage.enterUniqueName(stepNamePrefix);
            ExtentReportListener.getExtentTest().info("Entered unique name '" + generatedName + "' into Name input field successfully");
            Assert.assertTrue(stepNamePage.isStepNameLabelDisplayed(), "Step name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step name label with mandatory asterisk is displayed successfully");

            stepNamePage.clickAddButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add' button successfully");
            Assert.assertTrue(stepNamePage.isStepNameHeaderDisplayed(), "Step Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step Name header is displayed successfully");

            stepNamePage.clickEditButtonForStepName(generatedName);
            ExtentReportListener.getExtentTest().info("Clicked 'Edit' button for step name '" + generatedName + "' successfully");
            Assert.assertTrue(stepNamePage.isStepNameHeaderDisplayed(), "Step Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step Name header is displayed successfully");

            stepNamePage.clickCancelForStepName(generatedName);
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' for step name '" + generatedName + "' successfully");
            Assert.assertTrue(stepNamePage.isStepNameHeaderDisplayed(), "Step Name header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Step Name header is displayed successfully");

            stepNamePage.clickEditButtonForStepName(generatedName);
            ExtentReportListener.getExtentTest().info("Clicked 'Edit' button for step name '" + generatedName + "' successfully");

            stepNamePage.clickSaveForStepName(generatedName);
            ExtentReportListener.getExtentTest().info("Clicked 'Save' for step name '" + generatedName + "' successfully");

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
//        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}