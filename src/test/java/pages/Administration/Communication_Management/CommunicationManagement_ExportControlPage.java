package pages.Administration.Communication_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommunicationManagement_ExportControlPage extends BasePage {
    private final WebDriverWait wait;
    public CommunicationManagement_ExportControlPage(WebDriver driver) {

        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locator
    By noticeGroupControl = By.xpath("//div[contains(@class,'form-cell') and contains(@class,'-group')]" + "//div[contains(@class,'select-control')]");
    By addNewTemplateModal = By.xpath("//div[contains(@class,'modal-content-wrapper')]" + "[.//strong[normalize-space()='Add New Template']]");
    By communicationManagementToggle = By.xpath("//button[@aria-label='Expand Communication Management' or @aria-label='Collapse Communication Management']");
    By communicationExportControlLink = By.xpath("//div[contains(@class,'-level-2') and .//span[normalize-space()='Export Control']]" + "//a[contains(@href,'/administration/communication-management/export-control')]");
    By addNewTemplateButton = By.xpath("//button[@class='button -primary' and normalize-space()='Add New Template']");
    By templateNameInput = By.id("name");
    By reminderFrequencyInput = By.xpath("//div[contains(@class,'modal-content-wrapper')]" + "[.//strong[normalize-space()='Add New Template']]" + "//input[@id='reminderSettings.intervalDays']");
    By createTemplateButton = By.xpath("//button[@type='submit' and contains(@class,'-primary') and normalize-space()='Create Template']");
    By cancelTemplateButton = By.xpath("//div[contains(@class,'_flexJustifyEnd')]/button[normalize-space()='Cancel']");
    By layoutControl = By.xpath("//div[contains(@class,'form-cell') and contains(@class,'-layout-id')]" + "//div[contains(@class,'select-control')]");
    By notificationTypeInput = By.id("id");
    By cancelButton = By.xpath("//div[contains(@class,'_bottom-action-panel')]//button[normalize-space()='Cancel']");
    By saveButton = By.xpath("//div[contains(@class,'_bottom-action-panel')]//button[normalize-space()='Save']");
    By notificationsButton = By.xpath("//button[contains(@class,'button') and normalize-space()='Notifications']");
    By notificationsMenuLink = By.xpath("//a[contains(@class,'label') and @href='/export-control/notifications']//span[normalize-space()='Notifications']");
    By firstPlusButton = By.xpath("(//button[contains(@class,'plus-button')])[1]");
    By collapseButton = By.xpath("(//button[@aria-label='Collapse details'])[1]");
    By communicationManagementCrumb = By.xpath("//span[contains(@class,'crumb') and normalize-space()='Communication Management']");
    By addNewTemplateTitle = By.xpath("//strong[contains(text(),'Add New Template')]");
    By noticeGroupLabel = By.xpath("//label[@for='group' and contains(normalize-space(),'Notice Group')]");
    By layoutLabel = By.xpath("//label[@for='layoutId' and contains(normalize-space(),'Layout')]");
    By reminderFrequencyLabel = By.xpath("//label[@for='reminderSettings.intervalDays' and contains(normalize-space(),'Reminder Frequency')]");
    By notificationTypeLabel = By.xpath("//label[@for='id' and normalize-space()='Notification Type']");
    By notificationsScreenTitle = By.xpath("//span[contains(@class,'screen-title') and normalize-space()='Notifications']");

    //Action
    public boolean isNotificationsScreenTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(notificationsScreenTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNotificationTypeLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(notificationTypeLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReminderFrequencyLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(reminderFrequencyLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLayoutLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(layoutLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoticeGroupLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(noticeGroupLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddNewTemplateTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addNewTemplateTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCommunicationManagementCrumbDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(communicationManagementCrumb));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCollapseButton() {

        WebElement minusBtn = wait.until(
                ExpectedConditions.elementToBeClickable(collapseButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", minusBtn
        );

        minusBtn.click();

        pause(800);
    }

    public void clickFirstPlusButton() {
        WebElement plusBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstPlusButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", plusBtn);

        plusBtn.click();

        pause(1000);
    }

    public void clickNotificationsMenu() {
        WebElement notifMenu = wait.until(
                ExpectedConditions.elementToBeClickable(notificationsMenuLink)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", notifMenu
        );

        notifMenu.click();

        pause(1000); // small wait for Notifications page to load
    }

    public void clickNotificationsButton() {
        WebElement notifBtn = wait.until(
                ExpectedConditions.elementToBeClickable(notificationsButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", notifBtn
        );

        notifBtn.click();

        // Optional small pause so tab content loads properly
        pause(1000);
    }

    // Step 1 – Select Notification Type: "Export Control Approved"
    public void selectNotificationType(String notificationTypeText) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(notificationTypeInput));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(notificationTypeText);

        // Option from react-select dropdown
        By optionLocator = By.xpath(
                "//div[contains(@class,'menu')]//div[normalize-space()='" + notificationTypeText + "']");

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();

        pause(1000);
    }

    // Step 2 – Click Cancel button
    public void clickCancelButton() {
        WebElement cancel = wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();
        pause(1000);
    }

    // Step 3 – Click Save button
    public void clickSaveButton() {
        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", save);

        save.click();
        pause(1000);
    }

    public void selectLayout(String layoutName) {

        WebElement control = wait.until(
                ExpectedConditions.visibilityOfElementLocated(layoutControl));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        WebElement input = control.findElement(By.xpath(".//input[@id='layoutId']"));
        input.clear();
        input.sendKeys(layoutName);   // "Green"

        pause(600);

        input.sendKeys(Keys.ENTER);
        pause(800);
    }

    // Step 2: Select Notice Group (e.g. "ExportControl Workflow")
    // This method stays fully inside the modal and does NOT click outside
    public void selectNoticeGroup(String searchText) {

        // 1) Click the Notice Group control box to open the dropdown
        WebElement control = wait.until(
                ExpectedConditions.visibilityOfElementLocated(noticeGroupControl));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        // 2) Type into the Notice Group input
        WebElement input = control.findElement(By.xpath(".//input[@id='group']"));
        input.clear();
        input.sendKeys(searchText);   // e.g., "ExportControl Workflow"

        // Small wait to let React-Select filter options
        pause(600);

        // 3) If "No results found" appears, close dropdown and return
        By noResults = By.xpath(
                "//div[contains(@class,'menu') or contains(@class,'-menu')]//*[contains(text(),'No results found')]"
        );
        if (!driver.findElements(noResults).isEmpty()) {
            System.out.println("Notice Group: No results found for '" + searchText + "'");
            input.sendKeys(Keys.ESCAPE);   // Close only the dropdown, NOT the modal
            pause(500);
            return;
        }

        // 4) Press ENTER to select the highlighted option
        input.sendKeys(Keys.ENTER);

        // Optional: tiny pause so selected value appears
        pause(600);
    }

    // Step 6: Click Cancel (close modal)
    public void clickCancelTemplateButton() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(cancelTemplateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();

        pause(800);
    }

    // Step 5: Click Create Template
    public void clickCreateTemplateButton() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(createTemplateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();

        pause(1000);
    }

    // Step 4: Set Reminder Frequency (days) – inside the modal only
    public void setReminderFrequency(String days) {

        // Ensure modal is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(addNewTemplateModal));

        // Find the Reminder Frequency input inside the modal
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(reminderFrequencyInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input
        );

        input.click();
        input.clear();
        input.sendKeys(days);   // e.g., "1"

        pause(500);
    }

    // Step 1: Set Template Name
    public void setTemplateName(String templateName) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(templateNameInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input
        );

        input.clear();
        input.sendKeys(templateName);

        pause(500);
    }

    public void clickAddNewTemplate() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addNewTemplateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();

        pause(800); // your standard pause
    }

    public void clickCommunicationExportControl() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(communicationExportControlLink)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", link
        );

        link.click();
        pause(800);
    }

    public void expandCommunicationManagement() {

        WebElement toggle = wait.until(
                ExpectedConditions.elementToBeClickable(communicationManagementToggle)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", toggle
        );

        // Only click if it is closed
        String expanded = toggle.getAttribute("aria-expanded");

        if (expanded != null && expanded.equals("false")) {
            toggle.click();
            pause(800);
        }
    }
}