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
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;
import utils.WaitUtility;

import java.time.Duration;
import java.util.Objects;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_247367_Staff_Component_Integration_EndUser {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PeopleManagement_ExportControlPage peopleManagementExportControlPage;
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
        peopleManagementExportControlPage = new PeopleManagement_ExportControlPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        waitUtility = new WaitUtility(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
    }

    @Test
    public void Staff_Component_Integration_EndUser()
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
            String role = peopleManagementExportControlPage.GetRoleName();
            ExtentReportListener.getExtentTest().pass("New People Type with name : " + peopleTypeName + " has been created successfully. Status is : Active and default role assigned to it is : " + role);

            // Verify if External People Type Exist
            String defaultType = JsonDataReader.get(7,"DefaultPeopleType");

            boolean answer = peopleManagementExportControlPage.VerifyIfExternalPeopleTypeExist(defaultType);
            System.out.println("Default People Type exist? : " + answer);

            if(answer)
            {
                // Check the status, if Active(Yes) do nothing, else, enable it
                String defaultTypeStatus = peopleManagementExportControlPage.GetStatusOfPeopleType();
                System.out.println("Default People Type Status? : " + defaultTypeStatus);

                if(!Objects.equals(defaultTypeStatus, "Yes"))
                {
                    Assert.assertTrue(peopleManagementExportControlPage.ActivateRoleAndVerifyInList());
                    ExtentReportListener.getExtentTest().pass("Role : External exist and is Activated");
                    System.out.println("Default People Type Status Changed to Active.");
                }
                else{
                    ExtentReportListener.getExtentTest().info("Role : External exist and Active by default.");
                }
            }
            else
            {
                Assert.assertTrue(peopleManagementExportControlPage.AddPeopleTypeAndVerifyInThePeopleManagementList("External"));
                ExtentReportListener.getExtentTest().info("Role : External is created and is Active by default.");
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
            String type = JsonDataReader.get(3,"ExportControlType");
            String status = JsonDataReader.get(3,"Status");

            createExportControlPage.CreateExportControl(piName);
            Assert.assertTrue(createExportControlPage.VerifyExportControlIsCreatedSuccessfully());
            String recordNo = createExportControlPage.GetExportControlRecordNumber();
            ExtentReportListener.getExtentTest().pass("Export Control created successfully with Record Number : " + recordNo);

            // Navigate to People Section
            createExportControlPage.NavigateToPeople();
            ExtentReportListener.getExtentTest().info("User navigated to People section.");

            // Verify Export Control Details under People Section
            Assert.assertTrue(createExportControlPage.VerifyExportControlDetailsOnPeoplePage(recordNo, piName, type, status));
            ExtentReportListener.getExtentTest().pass("Created Export Control has PI : " + piName + " with Type : " + type + " and Status : " + status);

            // Verify user is automatically added as a submitter in the staff list
            Assert.assertTrue(createExportControlPage.VerifyUserIsAutomaticallyAddedAsASubmitterInStaffList(piName));
            ExtentReportListener.getExtentTest().pass("User : " + piName + " is automatically added as a submitter in the staff list.");

            //********************************** Add New External People Scenarios *******************************

            for(int i=1; i<=3; i++)
            {
                String firstName = uniqueNameGenerator.GenerateRandomName(6);
                String lastName = uniqueNameGenerator.GenerateRandomName(6);
                String fullName = lastName + ", " + firstName;

                // Navigate to Add New External People Section
                createExportControlPage.NavigateToAddNewExternalPeopleSection();
                ExtentReportListener.getExtentTest().info("User navigated to Add New External People section.");

                if(i==1)
                {
                    //********************************** Add New External People with New External Affiliation Scenarios **********************

                    // Verify user is able to add external people with New External Affiliation
                    String newExtAff = JsonDataReader.get(7,"NewExternalAffiliation");

                    Assert.assertTrue(createExportControlPage.VerifyUserIsAbleToAddExternalPeopleWithNewExternalAffiliation(firstName, lastName, newExtAff));
                    ExtentReportListener.getExtentTest().pass("User is able to add new external people : " + lastName + " " + firstName + " with new affiliation : " + newExtAff);

                    // Verify new external people is visible in the list
                    Assert.assertTrue(createExportControlPage.VerifyNewExternalPeopleIsVisibleInTheList(fullName, newExtAff, defaultType));
                    ExtentReportListener.getExtentTest().pass("New external people : " + fullName + " is visible under people list with new affiliation : " + newExtAff);

                    // Verify If Both Organization & Type fields are disabled for every newly added external people
                    Assert.assertTrue(createExportControlPage.VerifyBothOrganizationAndTypeFieldsAreDisabledForNewlyAddedExternalPeople(newExtAff, defaultType));
                    ExtentReportListener.getExtentTest().pass("Both Organization and Type fields are disabled for New external people : " + fullName + " with new affiliation : " + newExtAff);

                    // Delete External People
                    Assert.assertTrue(createExportControlPage.DeleteNewlyAddedPeople(fullName));
                    ExtentReportListener.getExtentTest().pass("New external people : " + fullName + " is Deleted successfully.");
                }
                else if(i==2)
                {
                    //********************************** Add New External People with Existing External Affiliation Scenarios **********************

                    // Verify user is able to add external people with Existing External Affiliation
                    String existingExtAff = JsonDataReader.get(7,"ExistingExternalAffiliation");

                    Assert.assertTrue(createExportControlPage.VerifyUserIsAbleToAddExternalPeopleWithExistingExternalAffiliation(firstName, lastName, existingExtAff));
                    ExtentReportListener.getExtentTest().pass("User is able to add new external people : " + lastName + " " + firstName + " with existing affiliation : " + existingExtAff);

                    // Verify new external people is visible in the list
                    Assert.assertTrue(createExportControlPage.VerifyNewExternalPeopleIsVisibleInTheList(fullName, existingExtAff, defaultType));
                    ExtentReportListener.getExtentTest().pass("New external people : " + fullName + " is visible under people list with existing affiliation : " + existingExtAff);

                    // Verify If Both Organization & Type fields are disabled for every newly added external people
                    Assert.assertTrue(createExportControlPage.VerifyBothOrganizationAndTypeFieldsAreDisabledForNewlyAddedExternalPeople(existingExtAff, defaultType));
                    ExtentReportListener.getExtentTest().pass("Both Organization and Type fields are disabled for New external people : " + fullName + " with existing affiliation : " + existingExtAff);

                    // Delete External People
                    Assert.assertTrue(createExportControlPage.DeleteNewlyAddedPeople(fullName));
                    ExtentReportListener.getExtentTest().pass("New external people : " + fullName + " is Deleted successfully.");
                }
                else
                {
                    //********************************** Add New Existing External People Scenarios **********************

                    String existingExternalPeople = JsonDataReader.get(7,"ExistingExternalPeople");
                    String existingUserExternalAffiliation = JsonDataReader.get(7,"ExistingUserExternalAffiliation");

                    // Verify user is able to add existing external people
                    Assert.assertTrue(createExportControlPage.VerifyUserIsAbleToAddExistingExternalPeople(existingExternalPeople));
                    ExtentReportListener.getExtentTest().pass("User is able to add new existing external people : " + existingExternalPeople);

                    // Verify new existing external people is visible in the list
                    Assert.assertTrue(createExportControlPage.VerifyExistingExternalPeopleIsVisibleInTheList(existingExternalPeople, defaultType));
                    ExtentReportListener.getExtentTest().pass("New existing external people : " + existingExternalPeople + " is visible under people list with existing affiliation : " + existingUserExternalAffiliation);

                    // Verify If Both Organization & Type field is disabled for newly added external people
                    Assert.assertTrue(createExportControlPage.VerifyBothOrganizationAndTypeFieldsAreDisabledForNewlyAddedExternalPeople(existingUserExternalAffiliation, defaultType));
                    ExtentReportListener.getExtentTest().pass("Both Organization and Type fields are disabled for New existing external people : " + existingExternalPeople + " with existing affiliation : " + existingUserExternalAffiliation);

                    // Delete Existing External People
                    Assert.assertTrue(createExportControlPage.DeleteNewlyAddedPeople(existingExternalPeople));
                    ExtentReportListener.getExtentTest().pass("New existing external people : " + existingExternalPeople + " is deleted successfully.");
                }
            }

            //********************************** Add New Internal People Scenarios *******************************

            // Navigate to Add New Internal People Section
            createExportControlPage.NavigateToAddNewInternalPeopleSection();
            ExtentReportListener.getExtentTest().info("User navigated to Add New Internal People section.");

            String internalPeople = JsonDataReader.get(7,"InternalPeople");
            String internalPeopleAffiliation = JsonDataReader.get(7,"InternalPeopleAffiliation");

            // Verify if user is able to add new internal people
            Assert.assertTrue(createExportControlPage.VerifyUserIsAbleToAddInternalPeople(internalPeople));
            ExtentReportListener.getExtentTest().pass("User is able to add new internal people : " + internalPeople);

            // Verify new internal people is visible in the list
            Assert.assertTrue(createExportControlPage.VerifyNewInternalPeopleIsVisibleInTheList(internalPeople, internalPeopleAffiliation));
            ExtentReportListener.getExtentTest().pass("New internal people : " + internalPeople + " is visible under people list with affiliation : " + internalPeopleAffiliation);

            // Verify If Both Organization & Type fields are enabled and editable for every newly added internal people
            Assert.assertTrue(createExportControlPage.VerifyBothOrganizationAndTypeFieldsAreEnabledForNewlyAddedInternalPeople(internalPeopleAffiliation));
            ExtentReportListener.getExtentTest().pass("Both Organization and Type fields are enabled for New Internal people : " + internalPeople + " with affiliation : " + internalPeopleAffiliation);

            // Select the newly created people type for the added internal user
            createExportControlPage.SelectNewlyCreatedPeopleTypeForTheAddedInternalPeople(peopleTypeName);
            ExtentReportListener.getExtentTest().info("Newly created people type : " + peopleTypeName + " is added for new internal people : " + internalPeople);

            // Verify Submission Checklist text before completion
            String submissionChecklistMsgBeforeCompletion = JsonDataReader.get(7,"SubmissionChecklistMsgBeforeCompletion");

            Assert.assertTrue(createExportControlPage.VerifySubmissionChecklistMsgBeforeCompletion(submissionChecklistMsgBeforeCompletion));
            ExtentReportListener.getExtentTest().pass("Submission checklist message visible before completion is  : " + submissionChecklistMsgBeforeCompletion);

            // Use the role assigned to people type for the added internal user
            createExportControlPage.AssignInternalUserRole(role);
            ExtentReportListener.getExtentTest().info("Role : " + role + " assigned to new internal people : " + internalPeople);

            // Verify Submission Checklist text after completion
            String submissionChecklistMsgAfterCompletion = JsonDataReader.get(7,"SubmissionChecklistMsgAfterCompletion");

            Assert.assertTrue(createExportControlPage.VerifySubmissionChecklistMsgAfterCompletion(submissionChecklistMsgAfterCompletion));
            ExtentReportListener.getExtentTest().pass("Submission checklist message visible after completion is  : " + submissionChecklistMsgAfterCompletion);

            // Verify checklist message upon assigning PI role to the new internal user
            String multiplePIRolesMsg = JsonDataReader.get(7,"MultiplePIRolesMsg");
            String submitterType = JsonDataReader.get(7,"SubmitterType");
            String submitterRole = JsonDataReader.get(7,"SubmitterRole");

            Assert.assertTrue(createExportControlPage.VerifyChecklistMsgIfInternalUserIsAlsoAssignedPIRole(multiplePIRolesMsg, submitterType, submitterRole));
            ExtentReportListener.getExtentTest().pass("Submission checklist message if the internal person is also assigned with PI role  : " + multiplePIRolesMsg);
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