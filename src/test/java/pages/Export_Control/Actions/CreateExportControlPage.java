package pages.Export_Control.Actions;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

import java.time.Duration;


public class CreateExportControlPage extends BasePage {

    private final WebDriverWait wait;

    // Constructor
    public CreateExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ****************************** Sankar Locator*************************************************************

    // Left navigation – Actions toggle button
    By actionsToggleButton = By.xpath("//div[contains(@class,'export-control-nav-block')]//button[@aria-label='Expand Actions']");
    By createExportControlLink = By.xpath("//a[normalize-space()='Create Export Control' and contains(@href,'/export-control/actions')]");
    By saveButton = By.xpath("//button[@aria-label='Save' and normalize-space()='Save']");
    By actionRequiredCrumb = By.xpath("//span[contains(@class,'crumb') and normalize-space()='Action Required']");
    By actionRequiredLink = By.xpath("//div[@id='left-sidebar']//a[contains(@class,'label')][span[normalize-space()='Action Required']]");
    By piNameInput = By.xpath("//input[contains(@id,'dynamic-form-field-input-') and contains(@id,'-PiName')]");
    By submitRadioBtn = By.xpath("//input[@type='radio' and @value='Submit']");
    By createButton = By.xpath("//aside//button[normalize-space()='Create']");
    By createExportControlHeader = By.xpath("//header[contains(@class,'_font-size-medium') and normalize-space()='Create Export Control']");
    By petNameInput = By.xpath("//label[.//div[normalize-space()='Pet name']]/following::input[@type='text'][1]");
    By signOffButton = By.xpath("//button[@aria-label='Sign Off' and normalize-space()='Sign Off']");
    By loadingOverlay = By.xpath("//*[contains(@class,'loading') or contains(@class,'spinner') or contains(@class,'overlay')][not(contains(@style,'display: none'))]");
    By actionConfirmationLabel = By.xpath("//label[@for='action-confirmation']");
    By actionConfirmationCheckbox = By.id("action-confirmation");
    By approveBtnEnabled = By.cssSelector("button[aria-label='Approve']:not([disabled])");
    By approveBtn = By.cssSelector("button[aria-label='Approve']");

    // ****************************** Sankar Functions *************************************************************

    // Dropdown row/cell by visible PI full name (your list is NOT role='option', so use text)
    public By piRowByName(String fullName) {
        return By.xpath("//*[contains(@class,'menu') or @role='listbox' or contains(@class,'Menu')]//*[normalize-space(.)='" + fullName + "'][1]");
    }

    public void clickApprove() {
        WebElement approve = wait.until(ExpectedConditions.elementToBeClickable(approveBtnEnabled));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", approve);
        try {
            approve.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approve);
        }
        pause(1500);
    }

    public void confirmAndWaitForApproveEnabled() {

        WebElement cb = wait.until(ExpectedConditions.visibilityOfElementLocated(actionConfirmationCheckbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cb);

        // Click checkbox (sometimes input click is flaky; label click works too)
        if (!cb.isSelected()) {
            try {
                cb.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb);
            }
        }

        // Validate checkbox is really selected
        wait.until(driver -> driver.findElement(actionConfirmationCheckbox).isSelected());

        // Now wait until Approve is enabled (disabled attribute removed)
        wait.until(ExpectedConditions.presenceOfElementLocated(approveBtnEnabled));

        pause(1000);
    }

    public void selectChiefApprovalConfirmation() {
        WebElement cb = wait.until(ExpectedConditions.presenceOfElementLocated(actionConfirmationCheckbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cb);

        // Click label is more reliable than clicking the input in many UIs
        WebElement lbl = wait.until(ExpectedConditions.elementToBeClickable(actionConfirmationLabel));
        try { lbl.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lbl); }

        // Ensure it's truly selected
        wait.until(driver -> driver.findElement(actionConfirmationCheckbox).isSelected());
        pause(500);
    }

    public void clickSignOffButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement signOffBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(signOffButton)
            );
            this.scrollIntoView01(signOffBtn);

            try {
                signOffBtn.click();
            } catch (Exception e) {
                this.jsClick01(signOffBtn);
            }

            pause(500);
        } catch (Exception e) {
            throw e;
        }
    }

    public void enterPetName(String positiveSearchText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(petNameInput));
            this.scrollIntoView01(input);

            input.clear();
            input.sendKeys(positiveSearchText);

            pause(500);
        } catch (Exception e) {
            throw e;
        }
    }

    public void clickSubmitRadioButton() {

        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(submitRadioBtn));

        this.scrollIntoView01(radio);

        try {
            radio.click();
        } catch (Exception e) {
            this.jsClick01(radio);
        }

        pause(500);
    }

    public boolean isActionRequiredCrumbDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(actionRequiredCrumb));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCreateExportControlHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(createExportControlHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickActionRequired() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionRequiredLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();

        pause(1000);
    }

    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        saveBtn.click();
        pause(1000);
    }

    public By piOptionByText(String fullName) {
        // Keep it generic for react-select style options
        return By.xpath("//div[contains(@class,'menu') or contains(@class,'select')]"
                + "//div[normalize-space()='" + fullName + "']");
    }

    public void clickCreateExportControl() {

        // 1) Scroll and open Actions dropdown
        WebElement actionsToggle = wait.until(ExpectedConditions.elementToBeClickable(actionsToggleButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", actionsToggle);
        actionsToggle.click();
        pause(2000);

        // 2) Wait for and click 'Create Export Control'
        WebElement createExportControl = wait.until(ExpectedConditions.elementToBeClickable(createExportControlLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", createExportControl);
        createExportControl.click();

        // 3) Small pause for navigation to complete
        pause(2000);
    }

    public void selectPiName(String searchText, String fullNameToSelect) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1) Focus input (use visibility, NOT clickable; input is opacity:0)
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(piNameInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        // Safe click to focus
        try { input.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input); }

        // 2) Clear + type
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(searchText);

        // Small wait to let dropdown options appear
        pause(800);

        // 3) Click the PI row by name text (NOT role='option')
        WebElement rowText = wait.until(ExpectedConditions.visibilityOfElementLocated(piRowByName(fullNameToSelect)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", rowText);

        try { rowText.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", rowText); }

        pause(800);
    }

    public void clickCreateButton() {
        WebElement createBtn = wait.until(
                ExpectedConditions.elementToBeClickable(createButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", createBtn);
        createBtn.click();
        pause(1000);
    }

    // **************************** Sahil Code ************************************************************************

    // General Locators
    By buttonActions = By.xpath("//button[text()='Actions']");
    By linkCreateExportControl = By.xpath("//a[text()='Create Export Control']");
    By linkInitialReview = By.xpath("//div[text()='Initial Review (IR)']/..");

    // Create Export Control page locators
    By inputExportControlRequest = By.xpath("//input[@value='ExportControlRequest']");
    By inputSelectPI = By.xpath("//div[text()='Start typing to search...']/following::div/input");
    By buttonCreate = By.xpath("//button[text()='Create']");

    // Export Control Details locators
    By exportControlRecordNo = By.xpath("//dt[text()='Record #']/../dd");
    By exportControlPI = By.xpath("//dt[text()='PI']/../dd");
    By exportControlType = By.xpath("//dt[text()='Type']/../dd");
    By exportControlStatus = By.xpath("//dt[text()='Status']/../dd");

    By lblExportControlSuccessfulCreation = By.xpath("//div[text()='Record has been created successfully.']");
    By lblExportControlRecordNum = By.xpath("//dt[text()='Record #']");
    By lblSubmissionChecklist = By.xpath("//span[text()='Submission Checklist']");

    //Attachment Locators
    By linkAttachments = By.xpath("//a[text()='Attachments']");
    By inputSearchAttachment = By.xpath("//input[@placeholder='Search by attachments...']");
    By buttonSearch = By.xpath("//button[text()='Search']");
    By fileInput = By.xpath("//input[@type='file']");

    By inputAttachmentType = By.xpath("//td[@data-column='exportControlAttachmentCategoryId']/div//input");
    By textareaAttachmentDescription = By.xpath("//td[@data-column='description']/textarea");
    By buttonDeleteAttachment = By.xpath("//button[@aria-label='Delete attachment']");
    By buttonOK = By.xpath("//button[text()='OK']");

    //People Locators
    By linkPeople = By.xpath("//a[text()='People']");
    By buttonAddNewPeople = By.xpath("//button[text()=' Add New People']");
    By inputSearchForUsers = By.xpath("(//div[text()='Search for users']/following::div/input)[1]");
    By buttonAdd = By.xpath("//button[text()='Add']");

    By buttonAddExternalPeople = By.xpath("//button[text()=' Add External People']");
    By inputFirstName = By.xpath("//input[@placeholder='First Name']");
    By inputLastName = By.xpath("//input[@placeholder='Last Name']");
    By inputAddExternalAffiliation = By.xpath("//input[@placeholder='Add External Affiliation']");
    By inputExternalAffiliation = By.xpath("(//div[text()='External Affiliation']/following::input)[1]");
    By userAdditionMsg = By.xpath("//div[text()='External People successfully added.']");

    // Functions
    public void NavigateToCreateExportControlPage() {
        waitForPresence(buttonActions);
        click(buttonActions);
        pause(1000);

        waitForPresence(linkCreateExportControl);
        click(linkCreateExportControl);
        pause(3000);
    }

    public void CreateExportControl(String pi) {
        // Select Export Control Type
        waitForPresence(inputExportControlRequest);
        click(inputExportControlRequest);
        pause(1000);

        // Select PI Name
        type(inputSelectPI, pi);
        pause(3000);
        driver.findElement(By.xpath("(//div[text()='" + pi + "']/..)[1]")).click();
        pause(2000);

        // Click Create button
        click(buttonCreate);
        pause(2000);

        waitForPresence(lblExportControlSuccessfulCreation);
    }

    public void CreateExportControlRequest(String pi, String requestType) {
        // Select Export Control Type

        driver.findElement(By.xpath("//span[text()='" + requestType +"']/../input")).click();
        pause(1000);

        // Select PI Name
        type(inputSelectPI, pi);
        pause(3000);
        driver.findElement(By.xpath("//div[text()='" + pi + "']/..")).click();
        pause(2000);

        // Click Create button
        click(buttonCreate);
        pause(2000);

        waitForPresence(lblExportControlSuccessfulCreation);
    }

    public boolean VerifyExportControlIsCreatedSuccessfully() {
        pause(5000);
        waitForPresence(lblExportControlRecordNum);
        return driver.findElement(lblExportControlRecordNum).isDisplayed();
    }

    public String GetExportControlRecordNumber() {
        pause(2000);
        return driver.findElement(exportControlRecordNo).getText();
    }

    public boolean VerifyCreatedFormIsVisible(String formName) {
        return driver.findElement(By.xpath("//a[text()='" + formName + "']")).isDisplayed();
    }

    public boolean VerifyInstructionsAssociatedWithFormIsVisibleUnderExportControl(String formName, String formInst) {
        boolean result = false;

        // Select the form
        driver.findElement(By.xpath("//a[text()='" + formName + "']")).click();
        pause(2000);

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public void NavigateToAttachments() {
        click(linkAttachments);
        pause(2000);
    }

    public void NavigateToPeople() {
        click(linkPeople);
        pause(2000);
    }

    public void UploadAnAttachment(String filePath) {
        pause(2000);

        WebElement uploadElement = driver.findElement(fileInput);

        // Directly send file path (bypasses drag/drop UI)
        uploadElement.sendKeys(filePath);
        pause(3000);
    }

    public boolean VerifyIfFileIsUploadedSuccessfully(String name) {
        pause(3000);

        return driver.findElement(By.xpath("//div[text()='" + name + "']")).isDisplayed();
    }

    public boolean EnterAttachmentTypeAndDescriptionAndVerifyTheGrouping(String fileName, String typeName, String desc) {
        boolean result = false;

        // Select Attachment type
        click(inputAttachmentType);
        pause(1000);

        driver.findElement(By.xpath("//div[text()='" + typeName + "']")).click();
        pause(1000);

        // Enter Attachment Description
        type(textareaAttachmentDescription, desc);
        pause(3000);

        // Search for the attachment
        driver.findElement(inputSearchAttachment).clear();
        type(inputSearchAttachment, fileName);
        click(buttonSearch);
        pause(3000);

        // Verify of attachment group is visible
        if(driver.findElement(By.xpath("//span[text()='" + typeName + "']")).isDisplayed() && driver.findElement(By.xpath("//span[text()='1']")).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean DeleteAttachmentAndVerifyAttachmentDeletedSuccessfully(String typeName) {
        boolean result = false;

        // Expand the group
        driver.findElement(By.xpath("//span[text()='" + typeName + "']")).click();
        pause(2000);

        click(buttonDeleteAttachment);
        if(driver.findElement(By.xpath("//div[text()='Are you sure you want to delete this attachment?']")).isDisplayed())
        {
            click(buttonOK);
            pause(2000);

            if(driver.findElement(By.xpath("//div[text()='Document was successfully deleted.']")).isDisplayed())
            {
                result = true;
                pause(5000);

                driver.findElement(inputSearchAttachment).clear();
                click(buttonSearch);
                pause(3000);

                //click(buttonClearSelections);
                driver.navigate().refresh();
                pause(2000);
            }
        }
        return result;
    }

    public boolean VerifyInstructionsForPeople(String formInst) {
        boolean result = false;

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public boolean VerifyInstructionsForAttachments(String formInst) {
        boolean result = false;

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public boolean VerifyInstructionsForReviewLetter(String formInst) {
        boolean result = false;

        // Click on Initial Review link
        click(linkInitialReview);
        pause(5000);

        // Verify Instructions
        if(driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).isDisplayed())
        {
            String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
            if(Objects.equals(name, formInst))
            {
                result=true;
            }
        }
        else {
            // Click on Instructions link
            driver.findElement(By.xpath("//span[text()='Instructions']")).click();
            pause(3000);

            String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
            if(Objects.equals(name, formInst))
            {
                result=true;
            }
        }
        return result;
    }

    public boolean VerifyExportControlDetailsOnPeoplePage(String recordNum, String piName, String ecType, String ecStatus) {
        boolean result = false;

        String recordNo = driver.findElement(exportControlRecordNo).getText();
        String pi = driver.findElement(exportControlPI).getText();
        String type = driver.findElement(exportControlType).getText();
        String status = driver.findElement(exportControlStatus).getText();

        if(Objects.equals(recordNum, recordNo) && Objects.equals(piName, pi) && Objects.equals(ecType, type) && Objects.equals(ecStatus, status))
        {
            result=true;
        }
        return result;
    }

    public boolean VerifyUserIsAutomaticallyAddedAsASubmitterInStaffList(String userName) {
        return driver.findElement(By.xpath("//div[text()='" + userName + "']")).isDisplayed();
    }

    public boolean VerifyUserIsAbleToAddExternalPeopleWithNewExternalAffiliation(String firstName, String lastName, String newExternalAffiliation) {
        boolean result = false;

        // Enter external people details
        type(inputFirstName, firstName);
        type(inputLastName, lastName);
        type(inputAddExternalAffiliation, newExternalAffiliation);
        pause(3000);

        click(buttonAdd);
        pause(2000);

        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean VerifyNewExternalPeopleIsVisibleInTheList(String name, String org, String type) {
        boolean result = false;

        By elementUserName = By.xpath("//div[text()='" + name + "']");
        waitForPresence(elementUserName);

        By elementOrgName = By.xpath("//div[text()='" + org + "']");
        waitForPresence(elementUserName);

        By elementTypeName = By.xpath("//div[text()='" + type + "']");
        waitForPresence(elementUserName);

        if(driver.findElement(elementUserName).isDisplayed() && driver.findElement(elementOrgName).isDisplayed() && driver.findElement(elementTypeName).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyExistingExternalPeopleIsVisibleInTheList(String name, String type) {
        boolean result = false;

        By elementUserName = By.xpath("//div[text()='" + name + "']");
        waitForPresence(elementUserName);

        By elementTypeName = By.xpath("//div[text()='" + type + "']");
        waitForPresence(elementUserName);

        if(driver.findElement(elementUserName).isDisplayed() && driver.findElement(elementTypeName).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyBothOrganizationAndTypeFieldsAreDisabledForNewlyAddedExternalPeople(String orgName, String typeName) {
        boolean result = false;

        String orgClassName = driver.findElement(By.xpath("//div[text()='"+ orgName + "']/../..")).getAttribute("class");
        String typeClassName = driver.findElement(By.xpath("//div[text()='"+ typeName + "']/../..")).getAttribute("class");

        if(orgClassName.contains("_controlDisabled") && typeClassName.contains("_controlDisabled"))
        {
            result = true;
        }

        return result;
    }

    public boolean VerifyUserIsAbleToAddExternalPeopleWithExistingExternalAffiliation(String firstName, String lastName, String existingExternalAffiliation) {
        boolean result = false;

        // Enter external people details
        type(inputFirstName, firstName);
        type(inputLastName, lastName);

        click(inputExternalAffiliation);
        type(inputExternalAffiliation, existingExternalAffiliation);
        pause(2000);
        driver.findElement(By.xpath("//div[text()='" + existingExternalAffiliation + "']")).click();
        pause(2000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='External People successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean DeleteNewlyAddedPeople(String name) {
        boolean result = false;

        driver.findElement(By.xpath("//td[@data-value='" + name + "']/..//button")).click();
        pause(2000);

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Accept the alert (click 'OK')
        alert.accept();
        pause(2000);

        By userDeletionMsg = By.xpath("//div[text()='People deleted successfully.']");
        waitForPresence(userDeletionMsg);

        if(driver.findElement(userDeletionMsg).isDisplayed())
        {
            pause(3000);

            List<WebElement> elements = driver.findElements(By.xpath("//div[text()='" + name + "']"));

            if (!elements.isEmpty()) {
                elements.get(0).isDisplayed();
            }
            {
                result = true;
            }
        }

        return result;
    }

    public void NavigateToAddNewInternalPeopleSection() {
        click(buttonAddNewPeople);
        pause(1000);
    }

    public void NavigateToAddNewExternalPeopleSection() {
        click(buttonAddNewPeople);
        pause(1000);

        click(buttonAddExternalPeople);
        pause(1000);
    }

    public boolean VerifyUserIsAbleToAddExistingExternalPeople(String existingExternalPeople) {
        boolean result = false;

        // Select Existing External People
        click(inputSearchForUsers);
        type(inputSearchForUsers, existingExternalPeople);
        pause(2000);

        By elementUser = By.xpath("//div[text()='" + existingExternalPeople + "']");
        click(elementUser);

        pause(2000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='External People successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean VerifyUserIsAbleToAddInternalPeople(String existingInternalPeople) {
        boolean result = false;

        // Select Existing Internal People
        click(inputSearchForUsers);
        type(inputSearchForUsers, existingInternalPeople);
        pause(2000);

        By elementUser = By.xpath("//div[text()='" + existingInternalPeople + "']");
        click(elementUser);

        pause(2000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='People was successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean VerifyNewInternalPeopleIsVisibleInTheList(String name, String org) {
        boolean result = false;

        By elementUserName = By.xpath("//div[text()='" + name + "']");
        waitForPresence(elementUserName);

        By elementOrgName = By.xpath("//div[text()='" + org + "']");
        waitForPresence(elementOrgName);

        if(driver.findElement(elementUserName).isDisplayed() && driver.findElement(elementOrgName).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyBothOrganizationAndTypeFieldsAreEnabledForNewlyAddedInternalPeople(String orgName) {
        boolean result = false;

        driver.findElement(By.xpath("//div[text()='"+ orgName + "']/../..")).click();
        String orgClassName = driver.findElement(By.xpath("//div[text()='"+ orgName + "']/../..")).getAttribute("class");

        pause(2000);

        driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).click();
        String typeClassName = driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).getAttribute("class");

        if(orgClassName.contains("_controlMenuExpanded") && typeClassName.contains("_controlMenuExpanded"))
        {
            result = true;
        }

        return result;
    }

    public void SelectNewlyCreatedPeopleTypeForTheAddedInternalPeople(String typeName) {
        driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).click();
        pause(2000);

        By peopleType = By.xpath("//div[text()='" + typeName + "']");
        waitForElement(peopleType);

        if(driver.findElement(peopleType).isDisplayed())
        {
            click(peopleType);
            pause(5000);
        }
    }

    public boolean VerifySubmissionChecklistMsgBeforeCompletion(String msg) {
        boolean result = false;
        click(lblSubmissionChecklist);
        pause(2000);

        By submissionChecklistInnerText = By.xpath("(//div[@class='submission-checklist-list-inner'])[1]");
        waitForPresence(submissionChecklistInnerText);

        String submissionMsg = driver.findElement(submissionChecklistInnerText).getText();
        if(Objects.equals(msg, submissionMsg))
        {
            result = true;
        }
        return result;
    }

    public void AssignInternalUserRole(String roleName) {
        driver.findElement(By.xpath("(//td[@data-column='roleId'])[2]/div/div")).click();
        pause(2000);

        By role = By.xpath("//div[text()='" + roleName + "']");
        waitForElement(role);

        if(driver.findElement(role).isDisplayed())
        {
            click(role);
            pause(5000);
        }
    }

    public boolean VerifySubmissionChecklistMsgAfterCompletion(String msg) {
        boolean result = false;

        By submissionChecklistSuccessText = By.xpath("(//li[@class='_successMessage_1jkn8_22'])[1]/span");
        waitForPresence(submissionChecklistSuccessText);

        String submissionMsg = driver.findElement(submissionChecklistSuccessText).getText();
        if(Objects.equals(msg, submissionMsg))
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyChecklistMsgIfInternalUserIsAlsoAssignedPIRole(String msg, String typeName, String roleName) {
        boolean result = false;

        // Assign Type
        driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).click();
        pause(2000);

        By peopleType = By.xpath("(//div[text()='" + typeName + "'])[2]");
        waitForElement(peopleType);

        if(driver.findElement(peopleType).isDisplayed())
        {
            click(peopleType);
            pause(5000);
        }

        // Assign Role
        driver.findElement(By.xpath("(//td[@data-column='roleId'])[2]/div/div")).click();
        pause(2000);

        By role = By.xpath("(//div[text()='" + roleName + "'])[2]");
        waitForElement(role);

        if(driver.findElement(role).isDisplayed())
        {
            click(role);
            pause(5000);
        }

        By submissionChecklistErrText = By.xpath("(//div[@class='submission-checklist-list-inner'])[1]");
        waitForPresence(submissionChecklistErrText);

        String submissionMsg = driver.findElement(submissionChecklistErrText).getText();
        if(Objects.equals(msg, submissionMsg))
        {
            result = true;
        }
        return result;
    }
}