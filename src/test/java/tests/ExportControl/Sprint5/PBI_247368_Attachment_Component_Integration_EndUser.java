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
import pages.Administration.Attachment_Types.AttachmentTypes_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;
import utils.WaitUtility;

import java.nio.file.Paths;

import java.time.Duration;
import java.io.File;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_247368_Attachment_Component_Integration_EndUser {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CreateExportControlPage createExportControlPage;
    AttachmentTypes_ExportControlPage attachmentTypesExportControlPage;
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
        createExportControlPage = new CreateExportControlPage(driver);
        attachmentTypesExportControlPage = new AttachmentTypes_ExportControlPage(driver);
        waitUtility = new WaitUtility(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
    }

    @Test
    public void AttachmentComponentIntegration_EndUser ()
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

            // Navigate to Export Control under Attachment Types module
            waitUtility.waitUntilPageLoad(driver, 120);

            attachmentTypesExportControlPage.NavigateToAttachmentTypesExportControlPage();
            Assert.assertEquals(driver.getCurrentUrl(), url + "administration/attachment-type");
            ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under Attachment Types.");

            // Add a new Attachment Type and verify in the Attachment Type list
            String attachmentTypeName = uniqueNameGenerator.GenerateRandomName(6);
            Assert.assertTrue(attachmentTypesExportControlPage.AddAttachmentTypeAndVerifyInTheAttachmentTypeList(attachmentTypeName));
            ExtentReportListener.getExtentTest().pass("New Attachment Type with name : " + attachmentTypeName + " has been created successfully. Status is : Yes");

            // Navigate back to Dashboard page
            dashboardPage.NavigateBackToDashboardPage();
            ExtentReportListener.getExtentTest().info("User navigated back to dashboard page.");

            // Navigate to Export Control module
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

            // Navigate to attachments
            createExportControlPage.NavigateToAttachments();
            ExtentReportListener.getExtentTest().pass("User navigated to Attachments screen.");

            // Build absolute path from project
            String baseDir = System.getProperty("user.dir");
            String folderPath = JsonDataReader.get(5,"TestFilesFolderPath");

            // Create folder object
            File folder = new File(folderPath);

            if(folder.exists() && folder.isDirectory())
            {
                File[] files = folder.listFiles();
                Assert.assertNotNull(files);

                System.out.println("Total files: " + files.length);

                for (File file : files)
                {
                    if (file.isFile())
                    {
                        // Get file name
                        String fileName = file.getName();
                        System.out.println("File: " + fileName);

                        if(fileName.startsWith("Sample_"))
                        {
                            // Get file path
                            String filePath = Paths.get(baseDir, folderPath, fileName).toString();
                            System.out.println("File Path: " + filePath);

                            // Upload file and verify the attachment
                            createExportControlPage.UploadAnAttachment(filePath);
                            Assert.assertTrue(createExportControlPage.VerifyIfFileIsUploadedSuccessfully(fileName));
                            ExtentReportListener.getExtentTest().pass("User successfully attached file : " + fileName);

                            // Enter Attachment type and description and verify The grouping
                            String description = JsonDataReader.get(5,"FileDescription");

                            Assert.assertTrue(createExportControlPage.EnterAttachmentTypeAndDescriptionAndVerifyTheGrouping(fileName, attachmentTypeName, description));
                            ExtentReportListener.getExtentTest().pass("File Type : " + attachmentTypeName + " and Description : " + description + " added for File : " + fileName + ". Also file is grouped according to file type.");

                            // Delete Attachment and Verify
                            Assert.assertTrue(createExportControlPage.DeleteAttachmentAndVerifyAttachmentDeletedSuccessfully(attachmentTypeName));
                            ExtentReportListener.getExtentTest().pass("File : " + fileName + " Deleted successfully.");
                        }
                        else{
                            System.out.println("Invalid File.");
                        }
                    }
                }
            } else
            {
                System.out.println("Folder not found!");
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