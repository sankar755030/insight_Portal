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
import pages.Adobe.AgreementPage;
import pages.Administration.Record_Types.RecordTypes_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_248591_Administration_DropDown {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
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
        recordTypesExportControlPage = new RecordTypes_ExportControlPage(driver);
    }

    @Test
    public void Export_control_Administration_Drop_down() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String initialRecordType   = JsonDataReader.get(1, "InitialRecordType");     // "Test01"
            String positiveSearchText  = JsonDataReader.get(1, "PositiveSearchText");    // "Test"
            String negativeSearchText  = JsonDataReader.get(1, "NegativeSearchText");    // "@@@@@@@"
            String refMeaningValue     = JsonDataReader.get(1, "RefMeaningValue");       // "1"

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
            AgreementPage agreementPage = new AgreementPage(driver);

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
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesSectionDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Record Types' section title is displayed successfully");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesSectionDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Record Types' section title is displayed successfully");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");
            Assert.assertTrue(recordTypesExportControlPage.isRecordTypesSectionDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Record Types' section title is displayed successfully");

            recordTypesExportControlPage.searchRecordByName(negativeSearchText);
            ExtentReportListener.getExtentTest().info("Search executed with name '" + negativeSearchText + "'");

            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().info("'Search' button clicked successfully");

            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("'Clear Selections' button clicked successfully");

            recordTypesExportControlPage.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().info("Clicked Edit icon for the first Record Type in the list");
            Assert.assertTrue(recordTypesExportControlPage.isEditRecordTypeHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Edit Record Type' header is displayed successfully");

            recordTypesExportControlPage.clickCancelOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().info("Clicked Cancel on Edit Record Type modal");

            recordTypesExportControlPage.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().info("Clicked Edit icon for the first Record Type in the list");
            Assert.assertTrue(recordTypesExportControlPage.isEditRecordTypeHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Edit Record Type' header is displayed successfully");

            recordTypesExportControlPage.clickSaveOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().info("Clicked Save on Edit Record Type modal");

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