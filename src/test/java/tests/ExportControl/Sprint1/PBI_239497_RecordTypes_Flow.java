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
import pages.Administration.Record_Types.RecordTypes_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_239497_RecordTypes_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    RecordTypes_ExportControlPage recordTypesExportControlPage;

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
        recordTypesExportControlPage = new RecordTypes_ExportControlPage(driver);
    }

    @Test
    public void ExportControl_RecordTypes_Test() {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String initialRecordType = JsonDataReader.get(1, "InitialRecordType");
            //String recordTypePrefix = JsonDataReader.get(1, "RecordTypePrefix");
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");
            String negativeSearchText = JsonDataReader.get(1, "NegativeSearchText");
            String refMeaningValue = JsonDataReader.get(1, "RefMeaningValue");

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

            recordTypesExportControlPage.openRecordTypesExportControl();
            ExtentReportListener.getExtentTest().info("Clicked 'Record Types' link successfully");

            recordTypesExportControlPage.clickExportControlUnderRecordTypes();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' under 'Record Types' successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickAddRecordTypeLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Record Type' link successfully");
            Assert.assertTrue(recordTypesExportControlPage.isCreateRecordTypePageDisplayed(), "Create Record Type page is NOT displayed after clicking 'Add Record Type' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Record Type page.");

            recordTypesExportControlPage.selectModuleAsExportControl();
            ExtentReportListener.getExtentTest().info("Selected 'Export Control' from Module dropdown successfully");
            Assert.assertTrue(recordTypesExportControlPage.isCreateRecordTypePageDisplayed(), "Create Record Type page is NOT displayed after clicking 'Add Record Type' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Record Type page.");

            recordTypesExportControlPage.enterRecordType(initialRecordType);
            ExtentReportListener.getExtentTest().info("Entered '" + initialRecordType + "' into Record Type input field");
            Assert.assertTrue(recordTypesExportControlPage.isCreateRecordTypePageDisplayed(), "Create Record Type page is NOT displayed after clicking 'Add Record Type' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Record Type page.");

            recordTypesExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("'Cancel' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickAddRecordTypeLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Record Type' link successfully");
            Assert.assertTrue(recordTypesExportControlPage.isCreateRecordTypePageDisplayed(), "Create Record Type page is NOT displayed after clicking 'Add Record Type' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Record Type page.");

            recordTypesExportControlPage.selectModuleAsExportControl();
            ExtentReportListener.getExtentTest().info("Selected 'Export Control' from Module dropdown successfully");
            Assert.assertTrue(recordTypesExportControlPage.isCreateRecordTypePageDisplayed(), "Create Record Type page is NOT displayed after clicking 'Add Record Type' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Record Type page.");

            String recordTypeName = "Test" + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            recordTypesExportControlPage.enterRecordType(recordTypeName);
            ExtentReportListener.getExtentTest().info("Entered '" + recordTypeName + "' into Record Type input field");
            Assert.assertTrue(recordTypesExportControlPage.isCreateRecordTypePageDisplayed(), "Create Record Type page is NOT displayed after clicking 'Add Record Type' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Record Type page.");

            recordTypesExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("'Create' button is clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.searchRecordByName(positiveSearchText);
            ExtentReportListener.getExtentTest().info("Search executed with name '" + positiveSearchText + "'");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.searchRecordByName(negativeSearchText);
            ExtentReportListener.getExtentTest().info("Search executed with name '" + negativeSearchText + "'");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().info("'Add Category' link clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isCreateCategoryPageDisplayed(), "Create Category page is NOT displayed after clicking 'Add Category' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Category page.");

            recordTypesExportControlPage.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().info("Selected '" + recordTypeName + "' from Category dropdown");
            Assert.assertTrue(recordTypesExportControlPage.isCreateCategoryPageDisplayed(), "Create Category page is NOT displayed after clicking 'Add Category' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Category page.");

            recordTypesExportControlPage.enterRefMeaning(refMeaningValue);
            ExtentReportListener.getExtentTest().info("Entered '" + refMeaningValue + "' into Ref Meaning input field");
            Assert.assertTrue(recordTypesExportControlPage.isCreateCategoryPageDisplayed(), "Create Category page is NOT displayed after clicking 'Add Category' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Category page.");

            recordTypesExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("'Cancel' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().info("'Add Category' link clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isCreateCategoryPageDisplayed(), "Create Category page is NOT displayed after clicking 'Add Category' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Category page.");

            recordTypesExportControlPage.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().info("Selected '" + recordTypeName + "' from Category dropdown");
            Assert.assertTrue(recordTypesExportControlPage.isCreateCategoryPageDisplayed(), "Create Category page is NOT displayed after clicking 'Add Category' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Category page.");

            recordTypesExportControlPage.enterRefMeaning(refMeaningValue);
            ExtentReportListener.getExtentTest().info("Entered '" + refMeaningValue + "' into Ref Meaning input field");
            Assert.assertTrue(recordTypesExportControlPage.isCreateCategoryPageDisplayed(), "Create Category page is NOT displayed after clicking 'Add Category' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Create Category page.");

            recordTypesExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("'Create' button is clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesPageDisplayed(), "Record Types page is NOT displayed after clicking Record Types link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Record Types page.");

            recordTypesExportControlPage.searchRecordByName(positiveSearchText);
            ExtentReportListener.getExtentTest().info("Search executed with name '" + positiveSearchText + "'");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");

            recordTypesExportControlPage.searchRecordByName(negativeSearchText);
            ExtentReportListener.getExtentTest().info("Search executed with name '" + negativeSearchText + "'");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");
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