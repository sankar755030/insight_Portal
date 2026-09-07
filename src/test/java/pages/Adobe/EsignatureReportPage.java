package pages.Adobe;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class EsignatureReportPage extends BasePage {

    public EsignatureReportPage(WebDriver driver) {super(driver);
    }

    //*****  Locator   ***************

    By signReasonDropdown = By.id("signReasonFormControlDropdown");
    By continueBtn = By.xpath("//button[contains(@class,'click-to-accept-tou') and normalize-space(.)='Continue']");
    By signatureInput = By.xpath("//input[@placeholder='Type your signature here' and contains(@class,'signature-type-name')]");
    By applyBtn = By.xpath("//button[contains(@class,'apply') and normalize-space(.)='Apply']");
    By okBtn = By.xpath("//button[contains(@class,'state-next') and normalize-space(.)='OK']");
    By clickToSignBtn = By.xpath("//button[contains(@class,'btn') and contains(normalize-space(.),'Click to Sign')]");
    By downloadAgreementButton = By.xpath("//button[@data-test-id='download-agreement-button']");
    By optionsDropdownLink = By.xpath("//a[contains(@class,'esign-options') and contains(@class,'dropdown-toggle') and normalize-space(.)='Options']");
    By fromInput = By.id("dateFrom");
    By toInput   = By.id("dateTo");
    By reportTypeControl = By.xpath("//label[@for='reportType']/following::div[contains(@class,'select-control')][1]");
    By searchBtn = By.xpath("//form[contains(@class,'base-search-form')]//button[@type='submit' and normalize-space()='Search']");
    By clearBtn  = By.xpath("//form[contains(@class,'base-search-form')]//button[@type='button' and normalize-space()='Clear']");
    By downloadBtn = By.xpath("//button[contains(@class,'-primary') and .//span[normalize-space()='Download']]");

    // Expand/collapse: Reports and Utilities (left menu)
    By reportsAndUtilitiesBtn = By.xpath("//nav[@id='left-panel-content']//button[normalize-space(.)='Reports and Utilities']");

    // Link: E-Signature Reports
    By eSignatureReportsLink = By.xpath("//nav[@id='left-panel-content']//a[@href='/agreements/admin/e-signature-report']//span[normalize-space(.)='E-Signature Reports']/ancestor::a");

    // Page header (assertion)
    By eSignatureReportHeader = By.xpath("//strong[contains(@class,'section-title-item') and normalize-space(.)='E-Signature Report']");

    // Locator (control – use this to open the dropdown)
    By reportTypeControl0101 = By.xpath("//div[contains(@class,'form-base-reportType')]//div[contains(@class,'select-control')]");
    By continueButton = By.xpath("//button[contains(@class,'btn-primary') and normalize-space(.)='Continue']");
    By closeButton = By.xpath("//button[contains(@class,'btn-secondary') and contains(@class,'cancel') and normalize-space(.)='Close']");
    By signaturePreviewModalTitle = By.xpath("//h1[contains(@class,'modal-title') and normalize-space(.)='Signature Preview']");
    By clickToSignButton = By.xpath("//button[contains(@class,'click-to-esign') and normalize-space(.)='Click to Sign']");
    By signatureModalTitle = By.xpath("//h1[contains(@class,'modal-title') and normalize-space(.)='Signature Preview']");

    // 1) Close icon (fi-remove) - best to anchor to close button if available
    By closeIcon = By.xpath("//button[@aria-label='Close modal']//i[contains(@class,'fi-remove')]");

    // 2) Status button: Sent For Signatures
    By sentForSignaturesStatus = By.xpath("//button[@aria-label='Status' and contains(normalize-space(.),'Sent For Signatures')]");

    // 3) Signature Status heading
    By signatureStatusHeading = By.xpath("//h3[normalize-space(.)='Signature Status']");

    // 4) Edit Recipient Info modal header
    By editRecipientInfoHeader = By.xpath("//header[contains(@class,'html-preview-modal-header') and normalize-space(.)='Edit Recipient Info']");

    // 5) Project Period label
    By projectPeriodLabel = By.xpath("//dt[normalize-space(.)='Project Period:']");
    By eSignReportHeader01 = By.xpath("//strong[@class='section-title-item' and normalize-space()='E-Signature Report']");
    By reportTypeLabel = By.xpath("//label[@for='reportType' and normalize-space()='Report Type']");

    //***************************  Action   *******************************************************

    public boolean isESignatureReportHeaderDisplayed01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(eSignReportHeader01)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReportTypeLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(reportTypeLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isESignatureReportHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(eSignatureReportHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openESignatureReport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement reportsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(reportsAndUtilitiesBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", reportsBtn);

        // Expand if collapsed (button has aria-expanded on your menu buttons)
        String expanded = reportsBtn.getAttribute("aria-expanded");
        if (!"true".equalsIgnoreCase(expanded)) {
            try { reportsBtn.click(); }
            catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reportsBtn); }
        }

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(eSignatureReportsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        try { link.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link); }
    }

    public boolean isCloseIconDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(closeIcon)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isSentForSignaturesStatusDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(sentForSignaturesStatus)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isSignatureStatusHeadingDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(signatureStatusHeading)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isEditRecipientInfoHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(editRecipientInfoHeader)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isProjectPeriodLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(projectPeriodLabel)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDownloadAgreementButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(downloadAgreementButton)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectSignReason(String signReasonText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Ensure Signature Preview modal is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(signatureModalTitle));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(signReasonDropdown));

        // Scroll to avoid overlay issues
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        Select select = new Select(dropdown);

        // Select using visible text from JSON (trim-safe)
        select.selectByVisibleText(" " + signReasonText);

        // Verify correct option is selected
        wait.until(driver -> {
            String selectedText = new Select(driver.findElement(signReasonDropdown))
                    .getFirstSelectedOption()
                    .getText()
                    .trim();
            return selectedText.equals(signReasonText);
        });
    }

    public boolean isClickToSignButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(clickToSignButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignaturePreviewTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(signaturePreviewModalTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCloseButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOptionsDropdownDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(optionsDropdownLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        btn.click();
    }

    public void clickSignaturePlaceholderUsingJs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[aria-label='Click to sign required']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"div[aria-label='Click to sign required']\").click();");
    }

    public void enterSignatureText(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(signatureInput));
        input.clear();
        input.sendKeys(text);
    }

    public void clickApply() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(applyBtn)).click();
    }

    public void clickSignReasonDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(signReasonDropdown)).click();
    }

    public void clickOk() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(okBtn)).click();
    }

    public void clickClickToSign() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(clickToSignBtn)).click();
    }

    public boolean isContinueButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method (same pattern you asked for)
    public void selectCompleteReportType00201() {
        pause(3000);

        WebElement control = waitClickable(reportTypeControl0101, 30);
        scrollIntoView(control);
        try { control.click(); } catch (Exception e) { jsClick(control); }

        WebElement option = waitVisible(reportTypeOption("Complete Signature Report"), 15);
        scrollIntoView(option);
        try { option.click(); } catch (Exception e) { jsClick(option); }

    }

    public void clickDownload() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(downloadBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }

    // ===== Helpers =====
    private WebElement waitClickable(By locator, int sec) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(sec))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitVisible(By locator, int sec) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(sec))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    private void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    private static String fmt(LocalDate d) {
        return d.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH));
    }

    private void setDate(By input, String value) {
        WebElement el = waitVisible(input, 15);
        scrollIntoView(el);
        try {
            waitClickable(input, 10).click();
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            el.sendKeys(value);
            el.sendKeys(Keys.TAB);
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value=arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                            "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                    el, value);
        }
    }

    public String[] enterRandomDateRange() {
        LocalDate today = LocalDate.now();
        LocalDate from  = today.minusDays(ThreadLocalRandom.current().nextInt(10, 15)); // 10–14 days ago
        LocalDate to    = from.plusDays(ThreadLocalRandom.current().nextInt(1, 6));     // +1–5 days
        if (to.isAfter(today)) to = today;

        setDate(fromInput, fmt(from));
        pause(3000);
        setDate(toInput, fmt(to));
        return new String[] { fmt(from), fmt(to) };
    }

    public void selectPendingReportType() {
        pause(3000);

        WebElement control = waitClickable(reportTypeControl, 30);
        scrollIntoView(control);
        try { control.click(); } catch (Exception e) { jsClick(control); }

        WebElement option = waitVisible(reportTypeOption("Pending Signature Report"), 15);
        scrollIntoView(option);
        try { option.click(); } catch (Exception e) { jsClick(option); }
    }

    public void clickSearch() {
        pause(3000);

        WebElement btn = waitClickable(searchBtn, 30);
        scrollIntoView(btn);
        try { btn.click(); } catch (Exception e) { jsClick(btn); }
    }

    public void clickClear() {
        pause(3000);

        WebElement btn = waitClickable(clearBtn, 30);
        scrollIntoView(btn);
        try { btn.click(); } catch (Exception e) { jsClick(btn); }
    }

    public By reportTypeOption(String text) {
        return By.xpath("(//div[@role='option' or contains(@class,'select__option') or contains(@class,'_option_')][normalize-space()='" + text + "'])[1]");
    }
}