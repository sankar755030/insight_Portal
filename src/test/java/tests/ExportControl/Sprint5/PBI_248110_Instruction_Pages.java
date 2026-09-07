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
import pages.Administration.Instructions_Management.InstructionsManagement_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.WaitUtility;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_248110_Instruction_Pages {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CreateExportControlPage createExportControlPage;
    InstructionsManagement_ExportControlPage instructionsManagementExportControlPage;
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
        createExportControlPage = new CreateExportControlPage(driver);
        instructionsManagementExportControlPage = new InstructionsManagement_ExportControlPage(driver);
        waitUtility = new WaitUtility(driver);
    }

    @Test
    public void Instruction_Pages ()
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

            // Navigate to Administration module
            dashboardPage.NavigateToAdministrationModule();
            ExtentReportListener.getExtentTest().info("User navigated to Administration module.");

            // Navigate to Export Control under Instructions Management module
            waitUtility.waitUntilPageLoad(driver, 120);

            instructionsManagementExportControlPage.NavigateToInstructionsManagementExportControlPage();
            Assert.assertEquals(driver.getCurrentUrl(),  url + "administration/instructions-management");
            ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under Instructions Management.");

            String officeCode = JsonDataReader.get(6,"OfficeCode");
            String office = JsonDataReader.get(6,"Office");
            String content1 = JsonDataReader.get(6,"Content1");
            String content2 = JsonDataReader.get(6,"Content2");
            String content3 = JsonDataReader.get(6,"Content3");
            String errorMsg1 = JsonDataReader.get(6,"DuplicateInstructionErrorMessage");
            String errorMsg2 = JsonDataReader.get(6,"InvalidOfficeCodeErrorMessage");
            String invalidOffCode = JsonDataReader.get(6,"InvalidOfficeCode");

            String pageName;

            for (int i=1; i<=3; i++) {
                if(i==1)
                {
                    pageName = JsonDataReader.get(6,"Page1");

                    // Delete previously added instructions if any
                    instructionsManagementExportControlPage.DeleteInstructionsIfAlreadyExist(pageName);
                    ExtentReportListener.getExtentTest().info("There are no existing instructions with key : " + pageName + " and record type : " + office);

                    // Add New instructions
                    instructionsManagementExportControlPage.AddNewInstructions(pageName, officeCode, content1);

                    Assert.assertTrue(instructionsManagementExportControlPage.VerifyInstructionsAreAddedSuccessfully(pageName, office, content1));
                    ExtentReportListener.getExtentTest().pass("Instructions : " + content1 + " added successfully for page : " + pageName + " and office : " + office);

                    // Verify Error message If user tries to add an instruction with existing page no and office code combination
                    Assert.assertTrue(instructionsManagementExportControlPage.VerifyErrorMsgIfInstructionsForAPageNoAndOfficeCodeCombinationAlreadyExist(pageName, officeCode, content1));
                    ExtentReportListener.getExtentTest().pass("Error Message : " + errorMsg1 + " is displayed when admin user tries to add an instruction with existing page no and office code combination.");

                    // Verify Error message If user tries to add an instruction with Invalid office code combination
                    Assert.assertTrue(instructionsManagementExportControlPage.VerifyErrorMsgIfUserTriesToAddAnInstructionWithInvalidOfficeCodeCombination(invalidOffCode));
                    ExtentReportListener.getExtentTest().pass("Error Message : " + errorMsg2 + " is displayed when admin user tries to add an instruction with invalid office code combination.");

                    instructionsManagementExportControlPage.NavigateBackToInstructionsManagementPage();
                    ExtentReportListener.getExtentTest().info("User navigated back to instruction management page.");

                } else if (i==2)
                {
                    pageName = JsonDataReader.get(6,"Page2");

                    // Delete previously added instructions if any
                    instructionsManagementExportControlPage.DeleteInstructionsIfAlreadyExist(pageName);
                    ExtentReportListener.getExtentTest().info("There are no existing instructions with key : " + pageName + " and record type : " + office);

                    // Add New instructions
                    instructionsManagementExportControlPage.AddNewInstructions(pageName, officeCode, content2);

                    Assert.assertTrue(instructionsManagementExportControlPage.VerifyInstructionsAreAddedSuccessfully(pageName, office, content2));
                    ExtentReportListener.getExtentTest().pass("Instructions : " + content2 + " added successfully for page : " + pageName + " and office : " + office);
                }
                else
                {
                    pageName = JsonDataReader.get(6,"Page3");

                    // Delete previously added instructions if any
                    instructionsManagementExportControlPage.DeleteInstructionsIfAlreadyExist(pageName);
                    ExtentReportListener.getExtentTest().info("There are no existing instructions with key : " + pageName + " and record type : " + office);

                    // Add New instructions
                    instructionsManagementExportControlPage.AddNewInstructions(pageName, officeCode, content3);

                    Assert.assertTrue(instructionsManagementExportControlPage.VerifyInstructionsAreAddedSuccessfully(pageName, office, content3));
                    ExtentReportListener.getExtentTest().pass("Instructions : " + content3 + " added successfully for page : " + pageName + " and office : " + office);
                }
            }

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
            String piName = JsonDataReader.get(3,"PIName");

            createExportControlPage.CreateExportControl(piName);
            Assert.assertTrue(createExportControlPage.VerifyExportControlIsCreatedSuccessfully());
            String recordNo = createExportControlPage.GetExportControlRecordNumber();
            ExtentReportListener.getExtentTest().pass("Export Control created successfully with Record Number : " + recordNo);

            // Navigate to People and Verify Instructions
            createExportControlPage.NavigateToPeople();
            Assert.assertTrue(createExportControlPage.VerifyInstructionsForPeople(content1));
            ExtentReportListener.getExtentTest().pass("Instructions : " + content1 + " are visible for People.");

            // Navigate to Attachments and Verify Instructions
            createExportControlPage.NavigateToAttachments();
            Assert.assertTrue(createExportControlPage.VerifyInstructionsForAttachments(content2));
            ExtentReportListener.getExtentTest().pass("Instructions : " + content2 + " are visible for Attachments.");

            // Navigate to Review Letter and Verify Instructions
            Assert.assertTrue(createExportControlPage.VerifyInstructionsForReviewLetter(content3));
            ExtentReportListener.getExtentTest().pass("Instructions : " + content3 + " are visible for Review Letter.");
        }
        catch (Exception e)
        {
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