package tests.ExportControl.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Administration.Transaction_Types.TransactionTypes_ExportControlPage;
import pages.Administration.Record_Types.RecordTypes_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239497_TransactionTypes_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    TransactionTypes_ExportControlPage transactionTypesExportControlPage;
    RecordTypes_ExportControlPage recordTypesExportControlPage;

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
        agreementPage = new AgreementPage(driver);
        transactionTypesExportControlPage = new TransactionTypes_ExportControlPage(driver);
        recordTypesExportControlPage = new RecordTypes_ExportControlPage(driver);
    }

    @Test
    public void ExportControl_TransactionTypes_Test () {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            //String transactionTypeInitial = JsonDataReader.get(1, "TransactionTypeInitial");   // e.g. "Test"
            //String transactionTypePrefix = JsonDataReader.get(1, "TransactionTypePrefix");     // e.g. "Test"
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");           // e.g. "Test"
            String negativeSearchText = JsonDataReader.get(1, "NegativeSearchText");

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

            transactionTypesExportControlPage.clickTransactionTypesLink();
            ExtentReportListener.getExtentTest().info("Clicked on 'Transaction Types' link from left navigation");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypesPageDisplayed(), "Transaction Types page is NOT displayed after clicking Transaction Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Transaction Types page");

            transactionTypesExportControlPage.clickAddNewTransactionType();
            ExtentReportListener.getExtentTest().info("Clicked 'Add new' link on Transaction Types page");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypeCreatePageDisplayed(), "'Transaction Type' create page is NOT displayed after clicking 'Add new' link");
            ExtentReportListener.getExtentTest().pass("'Transaction Type' create page is displayed successfully");

            transactionTypesExportControlPage.enterTransactionType("Test");
            ExtentReportListener.getExtentTest().info("Entered 'Test' into Transaction Type input field");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypeCreatePageDisplayed(), "'Transaction Type' create page is NOT displayed after clicking 'Add new' link");
            ExtentReportListener.getExtentTest().pass("'Transaction Type' create page is displayed successfully");

            transactionTypesExportControlPage.checkActiveCheckbox();
            ExtentReportListener.getExtentTest().info("Checked 'Active' checkbox");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypeCreatePageDisplayed(), "'Transaction Type' create page is NOT displayed after clicking 'Add new' link");
            ExtentReportListener.getExtentTest().pass("'Transaction Type' create page is displayed successfully");

            transactionTypesExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().info("Clicked Cancel link successfully");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypesPageDisplayed(), "Transaction Types page is NOT displayed after clicking Transaction Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Transaction Types page");

            transactionTypesExportControlPage.clickAddNewTransactionType();
            ExtentReportListener.getExtentTest().info("Clicked 'Add new' link on Transaction Types page");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypeCreatePageDisplayed(), "'Transaction Type' create page is NOT displayed after clicking 'Add new' link");
            ExtentReportListener.getExtentTest().pass("'Transaction Type' create page is displayed successfully");

            String transactionTypeName = transactionTypesExportControlPage.generateUniqueTransactionTypeName();
            transactionTypesExportControlPage.enterTransactionType(transactionTypeName);
            ExtentReportListener.getExtentTest().info("Entered '" + transactionTypeName + "' into Transaction Type input field");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypeCreatePageDisplayed(), "'Transaction Type' create page is NOT displayed after clicking 'Add new' link");
            ExtentReportListener.getExtentTest().pass("'Transaction Type' create page is displayed successfully");

            transactionTypesExportControlPage.checkActiveCheckbox();
            ExtentReportListener.getExtentTest().info("Checked 'Active' checkbox");

            recordTypesExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("'Create' button is clicked successfully");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypesPageDisplayed(), "Transaction Types page is NOT displayed after clicking Transaction Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Transaction Types page");

            transactionTypesExportControlPage.enterSearchByName(positiveSearchText);
            ExtentReportListener.getExtentTest().info("Entered '" + positiveSearchText + "' into Search by Name input field");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");

            transactionTypesExportControlPage.enterSearchByName(negativeSearchText);
            ExtentReportListener.getExtentTest().info("Entered '" + negativeSearchText + "' into Search by Name input field");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypesPageDisplayed(), "Transaction Types page is NOT displayed after clicking Transaction Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Transaction Types page");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypesPageDisplayed(), "Transaction Types page is NOT displayed after clicking Transaction Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Transaction Types page");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");
            Assert.assertTrue(transactionTypesExportControlPage.isTransactionTypesPageDisplayed(), "Transaction Types page is NOT displayed after clicking Transaction Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Transaction Types page");

        } catch (Exception e) {
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