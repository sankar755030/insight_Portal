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
import pages.Administration.Forms_Management.FormsManagement_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;
import utils.WaitUtility;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_251471_Instruction_Forms {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FormsManagement_ExportControlPage formsManagementExportControlPage;
    CreateExportControlPage createExportControlPage;
    WaitUtility waitUtility;
    UniqueNameGenerator uniqueNameGenerator;

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
        formsManagementExportControlPage = new FormsManagement_ExportControlPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        waitUtility = new WaitUtility(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
    }

    @Test
    public void TC_256634_CreateAndActivateFormsInAdminAndVerifyEndUserCanViewThemUnderExportControl ()
    {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

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

            String piName = JsonDataReader.get(3,"PIName");
            String description = JsonDataReader.get(2,"FormDescription");
            String formCat = JsonDataReader.get(2,"FormCategory");
            String formCatSeqNo = JsonDataReader.get(2,"CategorySeqNo");
            String formInst = JsonDataReader.get(2,"FormInstructions");
            String formType;

            for (int i=1; i<=2; i++)
            {
                // Navigate to Administration module
                dashboardPage.NavigateToAdministrationModule();
                ExtentReportListener.getExtentTest().info("User navigated to Administration module.");

                // Navigate to Export Control under Forms Management
                waitUtility.waitUntilPageLoad(driver, 120);
                formsManagementExportControlPage.NavigateToFormsManagementExportControlPage();
                Assert.assertEquals(driver.getCurrentUrl(), url + "administration/forms-management-export-control");
                ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under Forms Management.");

                // Click on Add New link to Add a new Form
                formsManagementExportControlPage.ClickAddNewLink();
                Assert.assertTrue(formsManagementExportControlPage.VerifyUserIsOnNewFormPage());
                ExtentReportListener.getExtentTest().pass("User clicked on Add New link and landed on add new form page.");

                if(i==1)
                {
                    formType = JsonDataReader.get(2,"FormType");
                }
                else {
                    formType = JsonDataReader.get(2,"FormType2");
                }

                // Create new form
                String formName = uniqueNameGenerator.GenerateRandomName(6);
                formsManagementExportControlPage.CreateNewForm(formName, description, formType, formCat, formCatSeqNo);
                Assert.assertTrue(formsManagementExportControlPage.VerifyFormIsCreatedSuccessfully(formName));
                ExtentReportListener.getExtentTest().pass("New form is created successfully with formName = " + formName + " and form type : " + formType);

                waitUtility.waitUntilPageLoad(driver, 120);

                // Activate the form and verify status
                Assert.assertTrue(formsManagementExportControlPage.ChangeFormStatusToActiveAndVerifyStatus());
                ExtentReportListener.getExtentTest().pass("Form status has been changed to: Active");

                waitUtility.waitUntilPageLoad(driver, 120);

                // Click on the Active version to add the instructions
                formsManagementExportControlPage.ClickOnTheActiveVersion();
                ExtentReportListener.getExtentTest().info("User clicked on the Active Version link in order to add the instructions.");

                //Enter instructions and save form
                Assert.assertTrue(formsManagementExportControlPage.AddInstructionsAndVerifyItIsSavedSuccessfully(formInst, formName));
                ExtentReportListener.getExtentTest().pass("Form Saved successfully with instructions: " + formInst);

                waitUtility.waitUntilPageLoad(driver, 120);

                // Navigate back to Dashboard page
                dashboardPage.NavigateBackToDashboardPage();
                ExtentReportListener.getExtentTest().info("User navigated back to dashboard page.");

                // Navigate back to Export Control module
                dashboardPage.NavigateToExportControlModule();
                ExtentReportListener.getExtentTest().info("User navigated to Export Control module.");

                // Navigate to Create Export Control page
                createExportControlPage.NavigateToCreateExportControlPage();
                ExtentReportListener.getExtentTest().info("User navigated to Create Export Control page.");

                // Create Export Control
                createExportControlPage.CreateExportControlRequest(piName, formType);
                waitUtility.waitUntilPageLoad(driver, 120);

                Assert.assertTrue(createExportControlPage.VerifyExportControlIsCreatedSuccessfully());
                String recordNo = createExportControlPage.GetExportControlRecordNumber();
                ExtentReportListener.getExtentTest().pass("Export Control created successfully with Record Number : " + recordNo);

                // Verify created form is visible for the newly created Export Control
                Assert.assertTrue(createExportControlPage.VerifyCreatedFormIsVisible(formName));
                ExtentReportListener.getExtentTest().pass("Form : " + formName + " is visible for the newly created Export Control with Record Number : " + recordNo);

                // Verify the instructions mentioned in the form is visible in the Export Control to the End user
                Assert.assertTrue(createExportControlPage.VerifyInstructionsAssociatedWithFormIsVisibleUnderExportControl(formName, formInst));
                ExtentReportListener.getExtentTest().pass("Instructions : " + formInst + " mentioned in the form : " + formName + " is visible for the created Export Control with Record Number: " + recordNo);
            }
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