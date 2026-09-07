package tests.AdobeEsign.Sprint2;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.*;
import pages.Adobe.*;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Esign_Review_and_AddSign__Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    EsignatureReportPage esignatureReportpage;

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
        esignatureReportpage = new EsignatureReportPage(driver);
    }

    @Test
    public void createFormFlow() {
        try {
            String ESignLink = JsonDataReader.get(0,"ESignLink");
            String signText = JsonDataReader.get(0, "ESignSignatureText");
            String signReasonText = JsonDataReader.get(1, "SignReasonApproveText");

            // User will open the login page of the Insight Portal application
            driver.get(ESignLink);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");
            basePage.pause(20000);

            Assert.assertTrue(esignatureReportpage.isContinueButtonDisplayed(), "'Continue' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Continue' button is displayed");

            esignatureReportpage.clickContinue();
            ExtentReportListener.getExtentTest().info("User clicked the 'Continue' button");
            Assert.assertTrue(esignatureReportpage.isOptionsDropdownDisplayed(), "'Options' dropdown is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Options' dropdown is displayed");

            esignatureReportpage.clickSignaturePlaceholderUsingJs();
            ExtentReportListener.getExtentTest().info("User clicked on the signature placeholder");
            Assert.assertTrue(esignatureReportpage.isCloseButtonDisplayed(), "'Close' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Close' button is displayed");

            esignatureReportpage.enterSignatureText(signText);
            ExtentReportListener.getExtentTest().info("User entered signature text: " + signText);
            Assert.assertTrue(esignatureReportpage.isCloseButtonDisplayed(), "'Close' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Close' button is displayed");

            esignatureReportpage.clickApply();
            ExtentReportListener.getExtentTest().info("User clicked the 'Apply' button");
            Assert.assertTrue(esignatureReportpage.isSignaturePreviewTitleDisplayed(), "'Signature Preview' modal title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Signature Preview' modal title is displayed");

            esignatureReportpage.clickSignReasonDropdown();
            ExtentReportListener.getExtentTest().info("User clicked on the 'Sign Reason' dropdown");
            Assert.assertTrue(esignatureReportpage.isSignaturePreviewTitleDisplayed(), "'Signature Preview' modal title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Signature Preview' modal title is displayed");

            esignatureReportpage.selectSignReason(signReasonText);
            ExtentReportListener.getExtentTest().info("Selected signing reason '" + signReasonText + "' successfully");
            Assert.assertTrue(esignatureReportpage.isSignaturePreviewTitleDisplayed(), "'Signature Preview' modal title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Signature Preview' modal title is displayed");

            esignatureReportpage.clickOk();
            ExtentReportListener.getExtentTest().info("User clicked the 'OK' button");
            Assert.assertTrue(esignatureReportpage.isClickToSignButtonDisplayed(), "'Click to Sign' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Click to Sign' button is displayed");

            esignatureReportpage.clickClickToSign();
            ExtentReportListener.getExtentTest().info("User clicked the 'Click to Sign' button");
            Assert.assertTrue(esignatureReportpage.isDownloadAgreementButtonDisplayed(), "'Download' agreement button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Download' agreement button is displayed");

            esignatureReportpage.clickDownload();
            ExtentReportListener.getExtentTest().pass("User clicked the 'Download' button after signing the document");

        } catch (Exception e) {
            // The user logs any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}