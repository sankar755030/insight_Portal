package tests.AdobeEsign.Sprint3;

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
import pages.Adobe.*;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class EsignatureReport {
    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    EsignatureReportPage esignatureReportpage;
    DashboardPage dashboardPage;

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
    public void AdobeEsignatureReportpageAdobeFlow () {
        try {
            String URLAustin = JsonDataReader.get(0,"URLAustin");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            // User will open the login page of the Insight Portal application
            driver.get(URLAustin);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");
            AgreementPage agreementPage = new AgreementPage(driver);

            agreementPage.clickAgreementsLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Agreements' link from sidebar");
            Assert.assertTrue(agreementPage.isAllAgreementsProposalsTitleDisplayed(), "'All Agreements/Proposals' title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All Agreements/Proposals' title is displayed");

            esignatureReportpage.openESignatureReport();
            ExtentReportListener.getExtentTest().info("Opened Reports and Utilities > E-Signature Reports successfully");
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

            // Dates
            String[] range = esignatureReportpage.enterRandomDateRange();
            ExtentReportListener.getExtentTest().info("Entered From: " + range[0] + "  To: " + range[1]);
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed01(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

            // Report Type
            esignatureReportpage.selectPendingReportType();
            ExtentReportListener.getExtentTest().info("Selected Report Type: Pending Signature Report");
            Assert.assertTrue(esignatureReportpage.isReportTypeLabelDisplayed(), "'Report Type' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Report Type' label is displayed");

            // Search
            esignatureReportpage.clickSearch();
            ExtentReportListener.getExtentTest().info("Clicked Search");
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed01(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

            // Clear
            esignatureReportpage.clickClear();
            ExtentReportListener.getExtentTest().info("Clicked Clear");
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed01(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

            // Dates
            String[] range01 = esignatureReportpage.enterRandomDateRange();
            ExtentReportListener.getExtentTest().info("Entered From: " + range01[0] + "  To: " + range01[1]);
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed01(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

            // Call in @Test
            esignatureReportpage.selectCompleteReportType00201();
            ExtentReportListener.getExtentTest().info("Selected 'Complete Signature Report' from Report Type dropdown successfully");
            Assert.assertTrue(esignatureReportpage.isReportTypeLabelDisplayed(), "'Report Type' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Report Type' label is displayed");

            // Search
            esignatureReportpage.clickSearch();
            ExtentReportListener.getExtentTest().info("Clicked Search");
            Assert.assertTrue(esignatureReportpage.isReportTypeLabelDisplayed(), "'Report Type' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Report Type' label is displayed");

            // Clear
            esignatureReportpage.clickClear();
            ExtentReportListener.getExtentTest().info("Clicked Clear");
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed01(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

            esignatureReportpage.clickDownload();
            ExtentReportListener.getExtentTest().info("Clicked Download");
            Assert.assertTrue(esignatureReportpage.isESignatureReportHeaderDisplayed01(), "'E-Signature Report' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'E-Signature Report' header is displayed");

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