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
import pages.Administration.People_Management.PeopleManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;
import utils.WaitUtility;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_251470_Staff_Management_Admin {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PeopleManagement_ExportControlPage peopleManagementExportControlPage;
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
        peopleManagementExportControlPage = new PeopleManagement_ExportControlPage(driver);
        waitUtility = new WaitUtility(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
    }

    @Test
    public void StaffManagement_Admin()
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

            // Navigate to Export Control under People Management
            waitUtility.waitUntilPageLoad(driver, 120);
            peopleManagementExportControlPage.NavigateToPeopleManagementExportControlPage();
            Assert.assertEquals(driver.getCurrentUrl(), url + "administration/people-management");
            ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under People Management.");

            // Add a new People Type and verify in the People Management list
            String peopleTypeName = uniqueNameGenerator.GenerateRandomName(6);
            Assert.assertTrue(peopleManagementExportControlPage.AddPeopleTypeAndVerifyInThePeopleManagementList(peopleTypeName));
            ExtentReportListener.getExtentTest().pass("New People Type with name : " + peopleTypeName + " has been created successfully. Status is : Active and default role assigned to it is : General");

            // Add a new role and assign to the created people type
            String role = uniqueNameGenerator.GenerateRandomName(6);
            Assert.assertTrue(peopleManagementExportControlPage.AddNewRoleToPeopleTypeAndVerifyInList(peopleTypeName, role));
            ExtentReportListener.getExtentTest().pass("New Role with name : " + role + " has been assigned to people type: " + peopleTypeName);

            // Edit People Type Name and Verify
            String newTypeName = uniqueNameGenerator.GenerateRandomName(6);

            Assert.assertTrue(peopleManagementExportControlPage.EditPeopleTypeNameAndVerifyInList(newTypeName));
            ExtentReportListener.getExtentTest().pass("People Type Name changed successfully to : " + newTypeName);

            // Deactivate New People Type and Verify
            Assert.assertTrue(peopleManagementExportControlPage.DeactivatePeopleTypeAndVerifyStatusInList());
            ExtentReportListener.getExtentTest().pass("People Type Name : " + newTypeName + " deactivated successfully and status changed to : No");

            // Edit Role Name and Verify
            String newRoleName = uniqueNameGenerator.GenerateRandomName(6);

            Assert.assertTrue(peopleManagementExportControlPage.EditRoleNameAndVerifyInList(newRoleName));
            ExtentReportListener.getExtentTest().pass("Role Name changed successfully to : " + newRoleName);

            // Deactivate new role name and verify
            Assert.assertTrue(peopleManagementExportControlPage.DeactivateAssociatedRoleAndVerifyInList());
            ExtentReportListener.getExtentTest().pass("New Role with name : " + newRoleName + " has been deactivated successfully for people type : " + newTypeName);

            // Try to deactivate all associated roles and verify error message
            String msg = JsonDataReader.get(4,"RoleDeactivationErrorMessage");

            Assert.assertTrue(peopleManagementExportControlPage.TryToDeactivateAllRolesAndVerifyCorrectErrorMessageIsDisplayed(msg));
            ExtentReportListener.getExtentTest().pass("Upon trying to deactivate all associated roles, user is getting an alert with message :  " + msg);
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