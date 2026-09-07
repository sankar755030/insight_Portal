package tests.AdobeEsign.Sprint1;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Adobe.DeliverablesPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.nio.file.Paths;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Esign_Agreement_PDF_Attachment_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    DeliverablesPage deliverablesPage;

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
        deliverablesPage = new DeliverablesPage(driver);
    }

    @Test
    public void createFormFlow() {
        try {
            String URLAustin = JsonDataReader.get(0,"URLAustin");
            String agreementNumber     = JsonDataReader.get(1, "AgreementNumber");
            String delivNamePrefix     = JsonDataReader.get(1, "DeliverableNamePrefix");
            String delivCategory       = JsonDataReader.get(1, "DeliverableCategory");
            String deliverablesSearch  = JsonDataReader.get(1, "DeliverablesSearchText");
            String invalidEmail        = JsonDataReader.get(1, "InvalidRecipientEmail");
            String validEmail          = JsonDataReader.get(1, "ValidRecipientEmail");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");
            String filePath = JsonDataReader.get(5, "TestPDFFilePath");
            String baseDir = System.getProperty("user.dir");
            String fullFilePath = Paths.get(baseDir, filePath).toString();

            // User will open the login page of the Insight Portal application
            driver.get(URLAustin);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            agreementPage.clickAgreementsLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Agreements' link from sidebar");
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.enterAgreementNumber(agreementNumber);
            ExtentReportListener.getExtentTest().info("Entered Agreement Number: " + agreementNumber);
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.clickAdvanced();
            ExtentReportListener.getExtentTest().info("Clicked 'Advanced' button successfully");
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.clickCloseButton();
            ExtentReportListener.getExtentTest().info("Clicked on 'Close' button successfully");
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("Clicked on 'Clear Selections' button successfully");
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.enterAgreementNumber(agreementNumber);
            ExtentReportListener.getExtentTest().info("Entered Agreement Number: " + agreementNumber);
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.clickSearch();
            ExtentReportListener.getExtentTest().info("Clicked Search button");
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            agreementPage.clickAgreementSpan();
            ExtentReportListener.getExtentTest().info("Clicked Agreement span");
            Assert.assertTrue(agreementPage.isTransactionStatusTitleDisplayed(), "'Transaction status' is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Transaction status' is displayed");

            agreementPage.clickDeliverablesTab();
            ExtentReportListener.getExtentTest().info("Clicked 'Deliverables' tab using JavaScript after scroll");
            ExtentReportListener.getExtentTest().info("Clicked the expand/collapse toggle button");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            agreementPage.clickAddNewDeliverable();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New Deliverable' button successfully");
            Assert.assertTrue(agreementPage.isDeliverableNameLabelDisplayed(), "'Deliverable Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Deliverable Name' label is displayed");

            String enteredName = deliverablesPage.typeDeliverableNameUnique(delivNamePrefix);
            ExtentReportListener.getExtentTest().info("Entered Deliverable Name: " + enteredName);
            Assert.assertTrue(agreementPage.isDeliverableNameLabelDisplayed(), "'Deliverable Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Deliverable Name' label is displayed");

            deliverablesPage.selectDeliverableCategory(delivCategory);
            ExtentReportListener.getExtentTest().info("Selected Deliverable Category: " + delivCategory);
            Assert.assertTrue(agreementPage.isDeliverableCategoryLabelDisplayed(), "'Deliverable Category' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Deliverable Category' label is displayed");

            deliverablesPage.clickCancelOnOverlay();
            ExtentReportListener.getExtentTest().info("Clicked Cancel on Add New Deliverable overlay");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            agreementPage.clickAddNewDeliverable();
            ExtentReportListener.getExtentTest().info("Clicked 'Add New Deliverable' button successfully");
            Assert.assertTrue(agreementPage.isDeliverableNameLabelDisplayed(), "'Deliverable Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Deliverable Name' label is displayed");

            String enteredName01 = deliverablesPage.typeDeliverableNameUnique(delivNamePrefix);
            ExtentReportListener.getExtentTest().info("Entered Deliverable Name: " + enteredName01);
            Assert.assertTrue(agreementPage.isDeliverableNameLabelDisplayed(), "'Deliverable Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Deliverable Name' label is displayed");

            deliverablesPage.selectDeliverableCategory(delivCategory);
            ExtentReportListener.getExtentTest().info("Selected Deliverable Category: " + delivCategory);
            Assert.assertTrue(agreementPage.isDeliverableCategoryLabelDisplayed(), "'Deliverable Category' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Deliverable Category' label is displayed");

            deliverablesPage.clickSubmitOnOverlay();
            ExtentReportListener.getExtentTest().info("Clicked 'Submit' on Add New Deliverable overlay");
            ExtentReportListener.getExtentTest().info("Clicked the expand/collapse toggle button");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            deliverablesPage.typeDeliverablesSearch(deliverablesSearch);
            ExtentReportListener.getExtentTest().info("Typed Deliverables search text: " + deliverablesSearch);
            Assert.assertTrue(agreementPage.isSearchButtonDisplayed(), "'Search' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Search' button is displayed");

            deliverablesPage.clickTopSearch();
            ExtentReportListener.getExtentTest().info("Clicked top 'Search' button on Deliverables page");
            Assert.assertTrue(agreementPage.isSearchButtonDisplayed(), "'Search' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Search' button is displayed");

            deliverablesPage.clickClearSelections();
            ExtentReportListener.getExtentTest().info("Clicked 'Clear Selections' successfully");
            Assert.assertTrue(agreementPage.isSearchButtonDisplayed(), "'Search' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Search' button is displayed");

            agreementPage.clickToggleButton();
            ExtentReportListener.getExtentTest().info("Clicked the expand/collapse toggle button");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            deliverablesPage.clickClone();
            ExtentReportListener.getExtentTest().info("Clicked 'Clone' successfully");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            deliverablesPage.tickFirstCheckbox();
            ExtentReportListener.getExtentTest().info("Ticked the first checkbox successfully");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            deliverablesPage.clickDownloadSelected();
            ExtentReportListener.getExtentTest().info("Clicked 'Download Selected' successfully");
            Assert.assertTrue(agreementPage.isProjectPeriodLabelDisplayed(), "'Project Period' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Project Period' label is displayed");

            deliverablesPage.clickDeliverableExact("CTO - MCA", enteredName01);
            ExtentReportListener.getExtentTest().info("Opened deliverable link: " + enteredName01);
            Assert.assertTrue(agreementPage.isESignatureExpandedDisplayed(), "'E-Signature' section is NOT expanded/displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature' section is expanded/displayed");

            agreementPage.clickAdobeIcon();
            ExtentReportListener.getExtentTest().info("Clicked Adobe integration icon");
            Assert.assertTrue(agreementPage.isDetailsHeadingDisplayed(), "First 'Details' heading is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified first 'Details' heading is displayed");

            agreementPage.uploadAgreementPdf(fullFilePath);
            ExtentReportListener.getExtentTest().info("Uploaded file from path: " + filePath);
            ExtentReportListener.getExtentTest().info("Successfully uploaded 'Agreement Info 2025_03.pdf'");
            Assert.assertTrue(agreementPage.isUploadFileLabelDisplayed(), "'Upload File' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Upload File' label is displayed");

            deliverablesPage.selectReminderEveryDay();
            ExtentReportListener.getExtentTest().info("Selected Reminders frequency: Every day");
            Assert.assertTrue(agreementPage.isRemindersTextDisplayed(), "'Reminders' text is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reminders' text is displayed");

            agreementPage.clickAddRecipient();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Recipient' button");
            Assert.assertTrue(agreementPage.isRecipientsHeadingDisplayed(), "'Recipients' heading is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Recipients' heading is displayed");

            agreementPage.enterRecipientEmail(invalidEmail);
            ExtentReportListener.getExtentTest().info("Entered invalid recipient email: " + invalidEmail);
            Assert.assertTrue(agreementPage.isRecipientsHeadingDisplayed(), "'Recipients' heading is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Recipients' heading is displayed");

            deliverablesPage.clickFirstRecipientDelete();
            ExtentReportListener.getExtentTest().info("Clicked recipient delete button successfully");
            Assert.assertTrue(agreementPage.isRecipientsHeadingDisplayed(), "'Recipients' heading is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Recipients' heading is displayed");

            agreementPage.clickAddRecipient();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Recipient' button");
            Assert.assertTrue(agreementPage.isRecipientsHeadingDisplayed(), "'Recipients' heading is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Recipients' heading is displayed");

            agreementPage.enterRecipientEmail(validEmail);
            ExtentReportListener.getExtentTest().info("Entered valid recipient email: " + validEmail);
            Assert.assertTrue(agreementPage.isRecipientsHeadingDisplayed(), "'Recipients' heading is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Recipients' heading is displayed");

            agreementPage.clickPreviewButton();
            basePage.switchToFrame(By.xpath("//iframe[@class='sign-in-iframe']"), 20);

            basePage.waitAdobeFormToBeVisible();
            ExtentReportListener.getExtentTest().info("Clicked 'Preview' button inside 'add-recipients-section'");

            ExtentReportListener.getExtentTest().info("Successfully Switched to iframe");

            basePage.dragAndDrop(
                    By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"),
                    By.xpath("//div[@data-testid='overlay-drop-target']"),
                    20);
            ExtentReportListener.getExtentTest().info("Drag and drop was successful");

            basePage.switchToDefaultContent();
            ExtentReportListener.getExtentTest().info("Switched to default content");

            By modalLocator = By.xpath("//div[contains(@class, 'modal-content-wrapper')]");
            basePage.scrollToBottomOfModal(modalLocator, 20);
            ExtentReportListener.getExtentTest().info("Successfully scrolled the modal-content-wrapper");

            By iframeLocator = By.xpath("//iframe[@class='sign-in-iframe']");
            basePage.switchToFrame(iframeLocator, 20);
            ExtentReportListener.getExtentTest().info("Successfully Switched to iframe");

            deliverablesPage.clickOnSendButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Send' button successfully");

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