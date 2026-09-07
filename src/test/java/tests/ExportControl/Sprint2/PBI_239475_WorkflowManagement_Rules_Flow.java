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
import pages.Administration.Workflow_Management.RulesPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239475_WorkflowManagement_Rules_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    RulesPage rulesPage;
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
        rulesPage = new RulesPage(driver);
        agreementPage = new AgreementPage(driver);
    }

    @Test
    public void ExportControl_WorkflowManagement_Rules_Test () {
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String rulesQueryBuilderSection = JsonDataReader.get(1, "RulesQueryBuilderSection");   // "Query Builder"
            String rulesQueryBuilderOperator = JsonDataReader.get(1, "RulesQueryBuilderOperator"); // "AND"

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

            rulesPage.clickWorkflowManagementLinkRules();
            ExtentReportListener.getExtentTest().info("Clicked 'Workflow Management' menu link successfully");

            rulesPage.clickRulesLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Rules' link for scopeId = 5 successfully");
            Assert.assertTrue(rulesPage.isRulesHeaderDisplayed(), "Rules header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Rules page header is displayed successfully");

            rulesPage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add rule' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            String generatedName = rulesPage.enterUniqueNameWithTestPrefix();
            ExtentReportListener.getExtentTest().info("Entered unique name: '" + generatedName + "' into Name input field successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.selectQueryBuilderOperator(rulesQueryBuilderSection, rulesQueryBuilderOperator);
            ExtentReportListener.getExtentTest().info("Selected '" + rulesQueryBuilderOperator + "' from " + rulesQueryBuilderSection + " dropdown successfully");
            Assert.assertTrue(rulesPage.isQueryBuilderSectionDisplayed(), "Query Builder section is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Query Builder' section is displayed successfully");

            rulesPage.clickAddRuleButtonRule();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Rule' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickRemoveRuleButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Remove rule' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickAddGroupButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Group' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickRemoveGroupButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Remove group' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickMigrationButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Migration' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickMigrationButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Migration' button successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");
            Assert.assertTrue(rulesPage.isRulesHeaderDisplayed(), "Rules header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Rules page header is displayed successfully");

            rulesPage.clickOKButton();
            ExtentReportListener.getExtentTest().info("Clicked 'OK' button on the confirmation popup");

            rulesPage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add rule' button successfully");

            String generatedNameLast = rulesPage.enterUniqueNameWithTestPrefix();
            ExtentReportListener.getExtentTest().info("Entered unique name: '" + generatedNameLast + "' into Name input field successfully");

            rulesPage.selectQueryBuilderOperator(rulesQueryBuilderSection, rulesQueryBuilderOperator);
            ExtentReportListener.getExtentTest().info("Selected '" + rulesQueryBuilderOperator + "' from " + rulesQueryBuilderSection + " dropdown successfully");

            rulesPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

            rulesPage.clickEditButtonForRule(generatedNameLast);
            ExtentReportListener.getExtentTest().info("Clicked 'Edit' button for rule: '" + generatedNameLast + "' successfully");
            Assert.assertTrue(rulesPage.isRuleNameLabelDisplayed(), "Rule name label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Rule name' label with mandatory asterisk is displayed successfully");

            rulesPage.clickWorkflowsCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button on Workflows popup successfully");
            Assert.assertTrue(rulesPage.isRulesHeaderDisplayed(), "Rules header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified Rules page header is displayed successfully");

            rulesPage.clickEditButtonForRule(generatedNameLast);
            ExtentReportListener.getExtentTest().info("Clicked 'Edit' button for rule: '" + generatedNameLast + "' successfully");

            rulesPage.clickSaveButton();
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