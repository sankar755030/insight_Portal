package pages.Adobe;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtility;

import java.time.Duration;

public class DeliverablesPage extends BasePage {


    public DeliverablesPage(WebDriver driver) {super(driver);
    }

    // ******** Locators *********

    // Button: Delete Selected
    By addDeliverableOverlay = By.cssSelector("div.add-new-deliverable-overlay");
    By deliverableNameInput = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//label[normalize-space(.)='Deliverable Name']/following::input[@type='text'][1]");

    // Buttons
    By cancelBtn = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//button[normalize-space(.)='Cancel']");
    By topSearchBtn = By.xpath("//button[normalize-space(.)='Add New Deliverable']/preceding::button[normalize-space(.)='Search'][1]");
    By submitBtn = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//button[@type='submit' and normalize-space(.)='Submit']");
    By deliverablesSearchInput = By.cssSelector("input[placeholder^='Search by deliverables']");

    // Top bar "Clear Selections" (left of "Add New Deliverable")
    By clearSelectionsBtn = By.xpath("//button[normalize-space(.)='Clear Selections']");
    By cloneBtn = By.xpath("//button[normalize-space(.)='Clone' and contains(@class,'-small')]");
    By firstCheckbox = By.xpath("(//table[@class='item-grid -sticky']//input[@type='checkbox'])[2]");
    By downloadSelectedBtn = By.xpath("//button[normalize-space(.)='Download Selected']");

    // Reminders dropdown (open it by clicking the control/arrow next to the label)
    By remindersControl = By.xpath("//label[@for='reminderFrequency']/following::div[contains(@class,'select-control')][1]");

    // First recipient row's delete button (next to the Email input)
    By firstRecipientDeleteBtn = By.xpath("//div[contains(@class,'email-row')][1]//button[@aria-label='Delete']");

    By deleteIcon = By.xpath("//div[@aria-label='Delete' and contains(@class,'nav-icon')]");
    By sentForSignaturesButton = By.xpath("//button[@type='button' and @aria-label='Status' and contains(@class,'esign-module__pending')]");
    By editRecipientButton = By.xpath("//td[@data-column='options']//button[@aria-label='Edit Recipient']");
    By cancelButtonemail = By.xpath("//button[@type='button' and @aria-label='Cancel' and normalize-space()='Cancel']");
    By saveButtonemail = By.xpath("//button[@type='button' and @aria-label='Save' and normalize-space()='Save']");
    By recipientEmailInput = By.xpath("//input[@id='email' and @type='text']");
    By closeModalButton = By.xpath("//button[@type='button' and @aria-label='Close modal']");
    By unsignedDocDownloadButton = By.xpath("//div[contains(@class,'esign-module__downloadLink')]//button[contains(text(),'Download') and not(@disabled)]");
    By eSignOptionsMenuButton = By.xpath("//table[.//th//div[normalize-space()='Options']]//tbody//tr[1]//td[last()]//button[not(@disabled)]");

    // --- Locators (keep only one copy) ---
    By deliverableCategoryControl = By.xpath("//form[contains(@class,'addNewDeliverable')]" +
                    "//label[normalize-space()='Deliverable Category']/following::div[contains(@class,'select-control')][1]"
    );

    By deliverableCategoryInput = By.xpath("//form[contains(@class,'addNewDeliverable')]" +
                    "//label[normalize-space()='Deliverable Category']/following::input[contains(@id,'react-select') and contains(@id,'-input')]"
    );

    // "any option" – used for ARROW_DOWN/ENTER fallback check
    By anyReactSelectOption = By.xpath("//div[@role='option'] | //div[contains(@class,'option')]");

    // ************************** Actions ***************************************************************************

    public void scrollUpInPreviewModal() {
        // Locate the scrollable container
        WebElement modalContentWrapper = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.modal-content-wrapper")));

        // Use JavaScript to scroll to the top
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = 0;", modalContentWrapper);

        pause(2000); // optional pause to let UI stabilize
    }

    public void clickESignOptionsMenu() {
        WebElement menuBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(eSignOptionsMenuButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", menuBtn);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(menuBtn));

        menuBtn.click();
        pause(1500);
    }

    public void clickUnsignedDocDownloadButton() {
        WebElement downloadBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(unsignedDocDownloadButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", downloadBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(downloadBtn));

        downloadBtn.click();
        pause(2000);
    }

    public void clickCloseModalButton() {
        WebElement closeBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(closeModalButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", closeBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(closeBtn));

        closeBtn.click();
        pause(2000);
    }

    public void setRecipientEmail(String email) {
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(recipientEmailInput));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", emailInput);

        emailInput.clear();
        emailInput.sendKeys(email);

        pause(1000);
    }

    public void clickSaveButton() {
        WebElement saveBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButtonemail));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saveBtn));

        saveBtn.click();
        pause(2000);
    }

    public void clickCancelButton() {
        WebElement cancelBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(cancelButtonemail));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cancelBtn));

        cancelBtn.click();
        pause(2000);
    }

    public void clickEditRecipientButton() {
        WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(editRecipientButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();
        pause(2000);
    }

    public void clickSentForSignaturesButton() {
        WebElement sentButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(sentForSignaturesButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sentButton);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sentButton));

        sentButton.click();
        pause(2000);
    }

    public void clickDeleteIcon() {
        WebElement deleteElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(deleteIcon));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteElement);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deleteElement));

        deleteElement.click();
        pause(2000);
    }

    public void clickOnSendButton(){
        BrowserUtility.click(driver,By.xpath("//button[@id='sendButton']"),"Send Button");
    }

    public void clickFirstRecipientDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(firstRecipientDeleteBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }

    public void selectReminderFrequency(String frequency) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1) Open the dropdown
        WebElement control = wait.until(ExpectedConditions.visibilityOfElementLocated(remindersControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        wait.until(ExpectedConditions.elementToBeClickable(control));
        try {
            control.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
        }

        // 2) Pick the option (menu is rendered in a portal, so search globally)
        By optionLocator = reminderOption(frequency);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        try {
            option.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
        pause(600);
    }

    public void selectReminderEveryDay() {
        selectReminderFrequency("Every day");
    }

    public void clickDownloadSelected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(downloadSelectedBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }

    public void tickFirstCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(firstCheckbox));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        pause(500);
    }

    public void clickClone() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cloneBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }

    public void clickClearSelections() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionsBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(500);
    }

    public void typeDeliverablesSearch(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverablesSearchInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.clear();
        input.sendKeys(text);
        pause(300);
    }

    public void clickSubmitOnOverlay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addDeliverableOverlay));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        // overlay should close after submit
        wait.until(ExpectedConditions.invisibilityOfElementLocated(addDeliverableOverlay));
        pause(500);
    }

    public String typeDeliverableNameUnique(String base) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addDeliverableOverlay));

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableNameInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        String unique = base + "_" + System.currentTimeMillis();
        input.sendKeys(unique);
        pause(300);
        return unique;
    }

    public void selectDeliverableCategory(String categoryText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // open control
        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(deliverableCategoryControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        // focus input & type filter
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableCategoryInput));
        input.clear();
        input.sendKeys(categoryText);

        // ---- Strategy A: click exact option if it appears
        try {
            WebElement exact = wait.until(ExpectedConditions.visibilityOfElementLocated(reactSelectOptionExact(categoryText)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exact);
            pause(300);
            return;
        } catch (TimeoutException ignore) {
            // fall through
        }

        try {
            // ensure menu is open (some skins close after typing)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
            // wait briefly for any option to appear; if not, still try keys
            try { wait.until(ExpectedConditions.presenceOfElementLocated(anyReactSelectOption)); }
            catch (TimeoutException ignored) { /* menu may not expose option DOM; keys still work */ }

            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);
            pause(300);
        } catch (Exception e) {
            // ---- Strategy C: one retry from the top (handles flicker)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
            input.clear();
            input.sendKeys(categoryText);
            WebElement exact2 = wait.until(ExpectedConditions.visibilityOfElementLocated(reactSelectOptionExact(categoryText)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exact2);
            pause(300);
        }
    }

    public void clickCancelOnOverlay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(addDeliverableOverlay));
        pause(500);
    }

    public void clickTopSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(topSearchBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(800);
    }

    public By categoryToggleBy(String category) {
        return By.xpath(
                "//span[contains(@class,'title-text')][contains(normalize-space(),'" + category + "')]"
                        + "/ancestor::div[contains(@class,'toggleable-title')]"
                        + "//button[contains(@class,'content-toggler-button')]"
        );
    }

    public By deliverableLinkExact(String category, String deliverableName) {
        return By.xpath(
                "//span[contains(@class,'title-text')][contains(normalize-space(),'" + category + "')]"
                        + "/ancestor::div[contains(@class,'collapsible-bar')]"
                        + "//td[@data-column='col_204' and @data-value='" + deliverableName + "']//a"
        );
    }

    public void clickDeliverableExact(String category, String deliverableName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Expand category if collapsed
        By toggleBy = categoryToggleBy(category);
        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(toggleBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", toggle);
        String expanded = toggle.getAttribute("aria-expanded");
        if (expanded == null || expanded.equalsIgnoreCase("false")) {
            toggle.click();
            wait.until(ExpectedConditions.attributeToBe(toggle, "aria-expanded", "true"));
        }

        // Step 2: Click deliverable link by data-value
        By linkBy = deliverableLinkExact(category, deliverableName);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        try {
            link.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            link = wait.until(ExpectedConditions.visibilityOfElementLocated(linkBy));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(600);
    }

    public By reactSelectOptionExact(String text) {
        return By.xpath(
                // look anywhere on the page for a react-select option with exact text
                "//div[@role='option' and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'option') and normalize-space()='" + text + "']"
        );
    }

    public By reminderOption(String text) {
        return By.xpath(
                "(//div[@role='option' and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'select-option') and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'select__option') and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'_option_') and normalize-space()='" + text + "'])[1]"
        );
    }
}