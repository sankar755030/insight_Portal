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
import pages.Administration.Form_Visibility.FormsVisibility_ExportControlPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.WaitUtility;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239492_Forms_Visibility {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FormsVisibility_ExportControlPage formsVisibilityExportControlPage;
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
        formsVisibilityExportControlPage = new FormsVisibility_ExportControlPage(driver);
        waitUtility = new WaitUtility(driver);
    }

    @Test
    public void Forms_Visibility () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String formVisibilityForm = JsonDataReader.get(1, "FormVisibilityForm");                         // e.g. "Test"
            String formVisibilityAccess = JsonDataReader.get(1, "FormVisibilityAccess");                     // e.g. "Hidden"
            String formVisibilityQueryBuilderOp = JsonDataReader.get(1, "FormVisibilityQueryBuilderOperator"); // e.g. "AND"

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

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            agreementPage.clickAdministrationLink();
            Assert.assertTrue(agreementPage.isDashboardNotificationsSummaryDisplayed(), "Dashboard Notifications - Summary page is NOT displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Dashboard Notifications - Summary page.");

            formsVisibilityExportControlPage.openExportControlUnderFormVisibility();
            ExtentReportListener.getExtentTest().info("Opened 'Export Control' under Form Visibility successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isFormVisibilityExportControlHeaderDisplayed(), "'Form Visibility > Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Form Visibility > Export Control' header is displayed successfully");

            formsVisibilityExportControlPage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add rule' button successfully");

            formsVisibilityExportControlPage.waitForFormVisibilityModal();
            ExtentReportListener.getExtentTest().info("Form Visibility modal loaded successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isFormLabelDisplayed(), "Form label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Form' label with mandatory asterisk is displayed");

            formsVisibilityExportControlPage.selectForm(formVisibilityForm);
            ExtentReportListener.getExtentTest().info("Selected Form: " + formVisibilityForm + " successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isFormLabelDisplayed(), "Form label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Form' label with mandatory asterisk is displayed");

            formsVisibilityExportControlPage.selectAccess(formVisibilityAccess);
            ExtentReportListener.getExtentTest().info("Selected Access: " + formVisibilityAccess + " successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isAccessLabelDisplayed(), "Access label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Access' label with mandatory asterisk is displayed");

            formsVisibilityExportControlPage.selectQueryBuilder(formVisibilityQueryBuilderOp);
            ExtentReportListener.getExtentTest().info("Selected Query Builder: " + formVisibilityQueryBuilderOp + " successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isQueryBuilderSectionDisplayed(), "Query Builder section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Query Builder' section is displayed successfully");

            formsVisibilityExportControlPage.clickAddRuleInsideModal();
            ExtentReportListener.getExtentTest().info("Clicked 'Rule' button inside the modal successfully");

            formsVisibilityExportControlPage.clickRemoveRule();
            ExtentReportListener.getExtentTest().info("Clicked 'Remove rule' button successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isRuleButtonDisplayed(), "Rule button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule' button is displayed successfully");

            formsVisibilityExportControlPage.clickAddGroup();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Group' button successfully");

            formsVisibilityExportControlPage.clickRemoveGroup();
            ExtentReportListener.getExtentTest().info("Clicked 'Remove Group' button successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isGroupButtonDisplayed(), "Group button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Group' button is displayed successfully");

            formsVisibilityExportControlPage.clickMigration();
            ExtentReportListener.getExtentTest().info("Clicked 'Migration' button successfully");

            formsVisibilityExportControlPage.clickMigration();
            ExtentReportListener.getExtentTest().info("Clicked 'Migration' button successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isMigrationButtonDisplayed(), "Migration button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Migration' button is displayed successfully");

            formsVisibilityExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button on the modal successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isFormVisibilityExportControlHeaderDisplayed(), "'Form Visibility > Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Form Visibility > Export Control' header is displayed successfully");

            formsVisibilityExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().info("Clicked first 'Edit' button successfully");

            formsVisibilityExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isFormVisibilityExportControlHeaderDisplayed(), "'Form Visibility > Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Form Visibility > Export Control' header is displayed successfully");

            formsVisibilityExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().info("Clicked first 'Edit' button successfully");

            formsVisibilityExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");
            Assert.assertTrue(formsVisibilityExportControlPage.isFormVisibilityExportControlHeaderDisplayed(), "'Form Visibility > Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Form Visibility > Export Control' header is displayed successfully");

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