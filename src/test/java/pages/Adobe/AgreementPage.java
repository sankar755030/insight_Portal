package pages.Adobe;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgreementPage extends BasePage {

    public AgreementPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By leftSidebar = By.cssSelector("div[class*='administrationSubmenu'][class*='current-module']");
    By exportControlLink = By.cssSelector("a[href='/administration/forms-management-export-control']");
    By agreementsLink = By.xpath("//a[contains(text(),'Agreements')]");
    By agreementNumberInput = By.xpath("//input[@id='agreementNumber']");
    By searchButton = By.xpath("//button[text()='Search']");
    By agreementSpan = By.xpath("//span[text()='2025A023110']");
    By deliverablesTab = By.xpath("//a[@href='/agreements/2025A023110/latest/deliverables']//span[text()='Deliverables']");
    By toggleButton = By.xpath("//button[@type='button' and @aria-label='Expand/collapse' and contains(@class, 'toggle-button')]");
    By adobeIcon = By.xpath("//img[@alt='adobe-icon']");
    By addRecipientButton = By.xpath("//button[text()='Add Recipient']");
    By emailInput = By.xpath("//input[@placeholder='Email' and @type='email']");
    By previewButton = By.xpath("//div[contains(@class, 'add-recipients-section')]//button[normalize-space(text())='Preview']");
    By administrationLink = By.xpath("//a[contains(@href, '/administration') and contains(., 'Administration')]");
    By formsManagementLink = By.xpath("//a[contains(@href, '/administration/forms-management-agreements') and contains(., 'Forms Management')]");
    By searchByNameInput = By.xpath("//input[@type='text' and @placeholder='Search by Name' and @maxlength='300' and @value='']");
    By searchButton01 = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-primary') and text()='Search']");
    By clearSelectionsButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-unstyled') and text()='Clear Selections']");
    By addNewLink = By.xpath("//a[@href='/administration/forms-management-export-control/new' and text()='Add new']");
    By nameInputField = By.id("name");
    By descriptionTextArea = By.id("description");
    By categoryDropdown = By.id("category");  // Input element to activate dropdown
    By generalOption = By.xpath("//div[contains(@class,'option') and text()='General']");
    By categorySequenceNoInput = By.id("categorySequenceNo");
    By createButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-primary') and text()='Create']");
    By hiddenFileInput = By.xpath("//div[contains(@class,'_fileUploader')]/input[@type='file']");
    By closeButton = By.xpath("//button[normalize-space(text())='Close']");
    By advancedBtn = By.xpath("//form[contains(@class,'agrAgreementsLandingSearchForm')]//button[normalize-space(.)='Search']/following-sibling::button[contains(.,'Advanced')]");
    By advancedBtnAlt = By.xpath("//form[contains(@class,'agrAgreementsLandingSearchForm')]//button[contains(normalize-space(.),'Advanced (')]");
    By addNewDeliverableBtn = By.xpath("//button[contains(@class,'add-deliverable-button') and normalize-space(.)='Add New Deliverable']");
    By typeControlnew01 = By.xpath("//label[@for='type']/following::div[contains(@class,'select-control')][1]");
    By formsManagementHeader = By.xpath("//strong[normalize-space()='Forms Management']");
    By formsManagementSearchResultRow = By.xpath("//table//tr[contains(@class,'item-grid-tr')]");
    By newFormBreadcrumbHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='New form']");
    By formVersionsHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(),'Form Versions')]");
    By version1BreadcrumbHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='Version 1']");
    By dashboardNotificationsHeader = By.xpath("//*[normalize-space()='Dashboard Notifications - Summary']");
    By allAgreementsProposalsTitle = By.xpath("//strong[contains(@class,'section-title-item') and normalize-space(.)='All Agreements/Proposals']");
    By transactionStatusTitle = By.xpath("//div[normalize-space(.)='Transaction status' and contains(@class,'statusBarTitle')]");
    By deliverableNameLabel = By.xpath("//label[normalize-space(.)='Deliverable Name']");
    By deliverableCategoryLabel = By.xpath("//label[normalize-space(.)='Deliverable Category']");
    By eSignatureToggleButtonExpanded = By.xpath("//button[contains(@class,'content-toggler-button') and normalize-space(.)='E-Signature' and @aria-expanded='true']");
    By detailsHeading = By.xpath("(//h3[normalize-space(.)='Details'])[1]");
    By uploadFileLabel = By.xpath("//label[contains(@class,'form-label') and normalize-space(.)='Upload File']");
    By remindersText = By.xpath("//strong[normalize-space(.)='Reminders']");
    By recipientsHeading = By.xpath("//h3[normalize-space(.)='Recipients']");
    By searchButtona = By.xpath("//button[@type='submit' and contains(@class,'-primary') and normalize-space(.)='Search']");
    By projectPeriodLabel = By.xpath("//dt[normalize-space(.)='Project Period:']");


    // ******** Actions *********
    public boolean isProjectPeriodLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(projectPeriodLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtona)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAllAgreementsProposalsTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(allAgreementsProposalsTitle)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isTransactionStatusTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(transactionStatusTitle)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDeliverableNameLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableNameLabel)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDeliverableCategoryLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableCategoryLabel)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isESignatureExpandedDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(eSignatureToggleButtonExpanded)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDetailsHeadingDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(detailsHeading)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isUploadFileLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(uploadFileLabel)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isRemindersTextDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(remindersText)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isRecipientsHeadingDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(recipientsHeading)).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDashboardNotificationsSummaryDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboardNotificationsHeader));
            return header.isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isVersion1PageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(version1BreadcrumbHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFormVersionsPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(formVersionsHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isNewFormPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(newFormBreadcrumbHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSearchResultDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(formsManagementSearchResultRow));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFormsManagementPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(formsManagementHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void selectTypeAsExportControlRequestnew01() {
        WebElement control = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(typeControlnew01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", control);

        control.click();

        WebElement option = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'menu')]//div[normalize-space()='Test1']")));
        option.click();

        pause(1000);
    }

    public void clickAddNewDeliverable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addNewDeliverableBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickAdvanced() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By target = advancedBtn;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(target));
        } catch (TimeoutException e) {
            target = advancedBtnAlt; // fall back if text changed (e.g., Advanced (1))
            wait.until(ExpectedConditions.presenceOfElementLocated(target));
        }

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(target));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickCloseButton() {
        WebElement button = driver.findElement(closeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(closeButton));

        button.click();
        pause(1000);
    }

    public void uploadAgreementPdf(String filePath) {
        WebElement uploadInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(hiddenFileInput));
        uploadInput.sendKeys(filePath);
        ExtentReportListener.getExtentTest().pass("Uploaded file: " + filePath);
    }

    public void clickVersion1Link() {
        By version1Link = By.linkText("Version 1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Optional: wait for loading overlays to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".toastify, .loading-spinner")));

            // Ensure element exists in DOM first
            wait.until(ExpectedConditions.presenceOfElementLocated(version1Link));

            // Wait until it is visible and clickable
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(version1Link));

            // Scroll and JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);


        } catch (Exception e) {
            throw new RuntimeException(" Unable to click 'Version 1' link: " + e.getMessage(), e);
        }
    }

    public void clickCreateButton() {
        WebElement button = driver.findElement(createButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);  // Optional: ensure visibility
        pause(1000);
        button.click();
        pause(3000);  // Wait for navigation or action to complete
    }

    public void enterCategorySequenceNo(String number) {
        WebElement input = driver.findElement(categorySequenceNoInput);
        input.clear();             // Clear existing value
        input.sendKeys(number);    // Enter the desired number
        pause(1000);               // Optional wait
    }

    public void selectCategoryAsGeneral() {
        WebElement dropdown = driver.findElement(categoryDropdown);

        // Step 1: Click to expand the dropdown
        dropdown.click();
        pause(1000);  // Wait for options to load

        // Step 2: Select the "General" option
        WebElement option = driver.findElement(generalOption);
        option.click();
        pause(1000);  // Allow selection to register
    }

    public void enterDescription(String text) {
        WebElement textarea = driver.findElement(descriptionTextArea);
        textarea.clear();           // Optional: clears any existing text
        textarea.sendKeys(text);    // Sends the input text
        pause(1000);                // Optional wait
    }

    public void enterName(String name) {
        WebElement input = driver.findElement(nameInputField);
        input.clear();  // Optional: clears existing text
        input.sendKeys(name);
        pause(1000);  // Optional: wait after entering
    }

    public void clickAddNewLink() {
        WebElement link = driver.findElement(addNewLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link); // Optional scroll
        pause(1000); // Wait after scroll
        link.click();
        pause(3000); // Wait for navigation
    }

    public void scrollSidebarToExportControlAndClick() {
        WebElement sidebar = driver.findElement(leftSidebar);
        WebElement target = driver.findElement(exportControlLink);

        // Scroll the left sidebar container itself
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
                sidebar, target
        );

        pause(1500);  // Let scroll complete
        target.click();
        pause(5000);  // Wait for new content to load
    }

    public void clickClearSelectionsButton() {
        click(clearSelectionsButton);  // Uses your reusable click() method
        pause(5000); // Optional wait after click
    }

    public void clickSearchButton() {
        click(searchButton01);  // Assuming click() is your basePage reusable method
        pause(5000); // Optional wait after click
    }

    public void enterSearchText(String name) {
        type(searchByNameInput, name);  // Assuming you have a `type()` wrapper method in basePage
        pause(5000); // Optional wait after typing
    }

    public void clickFormsManagementLink() {
        click(formsManagementLink);
        pause(5000);
    }

    public void clickAdministrationLink() {
        waitForPresence(administrationLink);
        WebElement adminElement = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(administrationLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminElement);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(adminElement));

        adminElement.click();
        pause(5000);
    }

    public void clickAgreementsLink() {
        WebElement link = new WebDriverWait(driver, Duration.ofSeconds(240))
                .until(ExpectedConditions.visibilityOfElementLocated(agreementsLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(link));

        link.click();
        pause(5000);
    }

    public void enterAgreementNumber(String number) {
        WebElement element = waitForElement50(agreementNumberInput);
        element.sendKeys(number);
        pause(5000);
    }

    public void clickSearch() {
        click(searchButton);
        pause(5000);
    }

    public void clickAgreementSpan() {
        click(agreementSpan);
        pause(5000);
    }

    public void clickDeliverablesTab() {
        scrollAndJsClick(deliverablesTab, 20);
        pause(5000);
    }

    public void clickToggleButton() {
        click(toggleButton);
        pause(5000);
    }

    public void clickAdobeIcon() {
        click(adobeIcon);
        pause(5000);
    }

    public void clickAddRecipient() {
        click(addRecipientButton);
        pause(5000);
    }

    public void enterRecipientEmail(String email) {
        type(emailInput, email);
        pause(5000);
    }

    public void clickPreviewButton() {
        click(previewButton);
        pause(10000);
    }
}