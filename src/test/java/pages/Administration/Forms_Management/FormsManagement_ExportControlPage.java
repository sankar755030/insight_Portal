package pages.Administration.Forms_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FormsManagement_ExportControlPage extends BasePage {

    // Constructor
    public FormsManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // ********************* Sankar Locators **********************************

    By addRootLevelQuestionButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(., 'Add root level question')]");
    By radioButtonGroupCard = By.xpath("//div[contains(@class,'possible-question') and .//text()[contains(., 'Radio button group')]]");
    By radioOption1Input = By.xpath("//input[@class='value-input text-input default-input' and @value='Option1']");
    By addOptionButton = By.xpath("//button[@type='button' and div/i[contains(@class, 'fi-add')] and contains(., 'Add option')]");
    By radioOption2Input = By.xpath("//input[@class='value-input text-input default-input' and @value='Option2']");
    By readOnlyCheckbox = By.xpath("//label[contains(normalize-space(), 'Read only')]/input[@type='checkbox']");
    By helpTextArea = By.xpath("//span[text()='Help text:']/following-sibling::textarea");
    By applyButton = By.xpath("//button[contains(@class,'-primary') and contains(@class,'-submission') and contains(@class,'-small') and text()='Apply']");
    By previewLink = By.xpath("//a[contains(@href, '/preview') and text()='Preview']");
    By closePreviewLink = By.xpath("//a[contains(@href, '/edit') and text()='Close preview']");
    By saveButton = By.xpath("//button[@type='button' and contains(@class, '-submission') and text()='Save']");
    By addChildQuestionButton = By.xpath("//button[normalize-space()='Add child question']");
    By moveButton = By.xpath("//button[normalize-space()='Move']");
    By cancelMovingButton = By.xpath("//button[normalize-space()='Cancel moving']");
    By editButton = By.xpath("//button[normalize-space()='Edit']");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By removeButton = By.xpath("//button[normalize-space()='Remove']");
    By undoButton = By.xpath("//button[normalize-space()='Undo']");
    By formManagementLink = By.xpath("//a[@class='_link_ogtko_1' and contains(@href, '/forms-management-export-control') and normalize-space()='Form Management']");
    By testFormLink = By.xpath("//a[contains(@class, '_link_ogtko_1') and contains(@href, '/administration/forms-management-export-control/edit/')]");
    By editDescriptionButton = By.xpath("//button[normalize-space()='Edit description']");
    By cancelButtononversionedit = By.xpath("//button[normalize-space()='Cancel']");
    By versionTextArea = By.xpath("(//textarea[contains(@class,'default-input')])[last()]");
    By saveButtonsmall = By.xpath("//button[@class='button -primary -small' and normalize-space()='Save']");
    By addNewButton = By.xpath("//button[@class='button -primary -small' and normalize-space()='Add new']");
    By cancelButtonaddnewcancel = By.xpath("//button[@class='button -small -unstyled' and normalize-space()='Cancel']");
    By saveButtonassert = By.xpath("//button[contains(@class,'-submission') and normalize-space()='Save']");
    By previewBreadcrumbHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='Preview']");
    By selectQuestionTypeModalHeader = By.xpath("//header[contains(@class,'modal-header') and normalize-space(.)='Select the type of question to add']");

    // ********************************* Sahil Locators *******************************************************************

    By linkFormsManagement = By.xpath("//span[text()='Forms Management']/..");
    By linkExportControl = By.xpath("//a[contains(@href, 'forms-management-export-control')]");
    By linkAddNew = By.xpath("//a[text()='Add new']");
    By inputName = By.id("name");
    By textareaDescription = By.id("description");
    By inputType = By.id("type");
    By inputCategory = By.id("category");
    By inputCategorySeqNo = By.id("categorySequenceNo");
    By buttonCreate = By.xpath("//button[text()='Create']");
    By buttonChangeActiveVersion = By.xpath("//button[text()='Change active version']");
    By buttonActivate = By.xpath("//button[text()='Activate']");
    By linkActiveVersion = By.xpath("//span[text()='Active']/../../../a");
    By inputInstructions = By.xpath("//div[@class='fr-wrapper show-placeholder']/div");
    By buttonSave = By.xpath("//button[text()='Save']");
    By radioButtonGroupTitle = By.xpath("//div[@class='question-name' and normalize-space()='Radio button group']");

    // *********** Sankar Actions ************************************************
    public void clickRadioButtonGroupTitle() {
        waitForPresence(radioButtonGroupTitle);

        WebElement radioTitle = driver.findElement(radioButtonGroupTitle);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                radioTitle
        );

        pause(1000);   // Let the scroll complete
        radioTitle.click();
        pause(3000);   // Let the modal / next UI render
    }

    public boolean isSelectQuestionTypeModalDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectQuestionTypeModalHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPreviewPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(previewBreadcrumbHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSaveButtonDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(saveButtonassert));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickCancelButtonaddnewcancel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cancelButtonaddnewcancel));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel' button: " + e.getMessage(), e);
        }
    }

    public void clickAddNewButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addNewButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Add new' button: " + e.getMessage(), e);
        }
    }

    public void clickSaveButtonsmall() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButtonsmall));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Save' button: " + e.getMessage(), e);
        }
    }

    public void enterVersionText(String versionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(versionTextArea));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", textarea);
            pause(300);
            textarea.clear();
            textarea.sendKeys(versionName);
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter text into version description box: " + e.getMessage(), e);
        }
    }

    public void clickCancelButtononversionedit01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cancelButtononversionedit));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel' button: " + e.getMessage(), e);
        }
    }

    public void clickEditDescriptionButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(editDescriptionButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Edit description' button: " + e.getMessage(), e);
        }
    }

    public void clickTestFormLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(testFormLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
            pause(300);
            link.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on the test form link: " + e.getMessage(), e);
        }
    }

    public void clickFormManagementLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(formManagementLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
            pause(300);
            link.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Form Management' link: " + e.getMessage(), e);
        }
    }

    public void clickOutsidePopupByOffset() {
        try {
            Actions actions = new Actions(driver);
            actions.moveByOffset(50, 50).click().perform(); // Click on top-left part of the screen
            pause(500); // Allow popup to close
        } catch (Exception e) {
            throw new RuntimeException("Failed to click outside popup using offset: " + e.getMessage(), e);
        }
    }

    public void clickUndoButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement undoBtn = wait.until(ExpectedConditions.elementToBeClickable(undoButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", undoBtn);
            pause(300);
            undoBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Undo' button: " + e.getMessage(), e);
        }
    }

    public void clickRemoveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", removeBtn);
            pause(300);
            removeBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Remove' button: " + e.getMessage(), e);
        }
    }

    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);
            pause(300);
            cancelBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel' button: " + e.getMessage(), e);
        }
    }

    public void clickEditButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
            pause(300);
            editBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Edit' button: " + e.getMessage(), e);
        }
    }

    public void clickCancelMovingButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelMovingButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);
            pause(300);
            cancelBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel moving' button: " + e.getMessage(), e);
        }
    }

    public void clickMoveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement moveBtn = wait.until(ExpectedConditions.elementToBeClickable(moveButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", moveBtn);
            pause(300); // optional wait
            moveBtn.click();
            pause(500); // wait for any move dialog/behavior
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Move' button: " + e.getMessage(), e);
        }
    }

    public void clickAddChildQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addChildQuestionButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
            pause(300); // optional
            button.click();
            pause(500); // wait for action
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Add child question' button: " + e.getMessage(), e);
        }
    }

    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        button.click();
        pause(3000); // Optional: wait for the form save to complete
    }

    public void clickClosePreviewLink() {
        WebElement link = driver.findElement(closePreviewLink);
        link.click();
        pause(3000); // Optional wait for the editor to reload
    }

    public void clickPreviewLink() {
        WebElement link = driver.findElement(previewLink);
        link.click();
        pause(3000); // Optional wait for preview page to load
    }

    public void clickApplyButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(applyButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Apply' button: " + e.getMessage(), e);
        }
    }

    public void enterHelpText(String text) {
        WebElement helpTextField = driver.findElement(helpTextArea);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", helpTextField);
        helpTextField.clear(); // Optional: clear existing text
        helpTextField.sendKeys(text);
        pause(500); // Optional: slight wait after entering text
    }

    public void checkReadOnly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(readOnlyCheckbox));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
            pause(300); // Optional pause

            if (!checkbox.isSelected()) {
                checkbox.click(); // or JS click if needed
                pause(500); // Optional post-click pause
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not check 'Read only': " + e.getMessage(), e);
        }
    }

    public void enterRadioOption2Text(String text) {
        WebElement input = driver.findElement(radioOption2Input);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        input.clear();
        input.sendKeys(text);
        pause(1000);  // Optional delay
    }

    public void clickAddOptionButton() {
        WebElement addButton = driver.findElement(addOptionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
        pause(1000);  // Optional scroll wait
        addButton.click();
        pause(2000);  // Allow new input field to load
    }

    public void enterRadioOption1Text(String text) {
        WebElement input = driver.findElement(radioOption1Input);
        input.clear();
        input.sendKeys(text);
        pause(1000);  // Let input settle
    }

    public void clickRadioButtonGroupOption() {
        waitForPresence(radioButtonGroupCard);

        WebElement radioOption = driver.findElement(radioButtonGroupCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioOption);
        pause(1000);  // Let the scroll complete
        radioOption.click();
        pause(3000);  // Let the new field render
    }

    public void clickAddRootLevelQuestionButton() {
        waitForPresence(addRootLevelQuestionButton);
        WebElement button = driver.findElement(addRootLevelQuestionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);  // Optional: make visible
        pause(1000);  // Wait after scroll
        button.click();
        pause(3000);  // Wait for action completion
    }

    // ************************************************* Sahil Functions ****************************************

    public void NavigateToFormsManagementExportControlPage() {
        //Click on Forms Management navigation link

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkFormsManagement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();

        //waitForPresence(linkFormsManagement);
        //click(linkFormsManagement);
        pause(2000);

        //Click on Export Control under Forms Management
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }

    public void ClickAddNewLink() {
        click(linkAddNew);
        pause(2000);
    }

    public boolean VerifyUserIsOnNewFormPage() {
        boolean result = false;
        String name = driver.findElement(By.xpath("//div[@class='simple-bread-crumbs']//span[3]")).getText();
        if(Objects.equals(name, "New form"))
        {
            result=true;
        }
        return result;
    }

    public void CreateNewForm(String formName, String desc, String formType, String category, String seqNo) {
        // Generate a new name and enter it in the name field
        type(inputName, formName);

        //Enter description
        type(textareaDescription, desc);

        //Select type
        type(inputType, formType);
        pause(1000);
        driver.findElement(By.xpath("//div[text()='" + formType + "']")).click();
        pause(2000);

        //Enter category
        type(inputCategory, category);
        pause(1000);
        driver.findElement(By.xpath("//div[text()='" + category + "']")).click();
        pause(2000);

        //Enter category seq no
        type(inputCategorySeqNo, seqNo);
        pause(1000);

        //Click on Create button
        click(buttonCreate);
        pause(2000);
    }

    public boolean VerifyFormIsCreatedSuccessfully(String formName) {
        boolean result = false;

        pause(5000);

        String name = driver.findElement(By.xpath("//div[@class='simple-bread-crumbs']//span[3]")).getText();
        String versionNo = driver.findElement(By.xpath("//header[text()='Form Versions']/span")).getText();
        String createdOn = driver.findElement(By.xpath("//dt[text()='Created on:']/following::dd/span")).getText();

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format the date in MM/DD/YY format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formattedDate = currentDate.format(formatter);

        if(Objects.equals(name, formName) && Objects.equals(versionNo, "1") && Objects.equals(createdOn, formattedDate))
        {
            result=true;
        }
        return result;
    }

    public boolean ChangeFormStatusToActiveAndVerifyStatus() {
        boolean result = false;

        click(buttonChangeActiveVersion);
        pause(3000);

        click(buttonActivate);
        pause(3000);

        String activeOn = driver.findElement(By.xpath("(//dt[text()='Activated on:']/following::dd/span)[1]")).getText();
        String status = driver.findElement(By.xpath("//span[text()='Active']")).getText();

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format the date in MM/DD/YY format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formattedDate = currentDate.format(formatter);

        if(Objects.equals(status, "Active") && Objects.equals(activeOn, formattedDate))
        {
            result=true;
        }
        return result;
    }

    public void ClickOnTheActiveVersion() {
        click(linkActiveVersion);
        pause(2000);
    }

    public boolean AddInstructionsAndVerifyItIsSavedSuccessfully(String instructions, String formName) {
        boolean result = false;
        pause(2000);

        waitForPresence(inputInstructions);
        click(inputInstructions);
        pause(5000);

        type(inputInstructions, instructions);
        click(buttonSave);
        pause(2000);

        driver.findElement(By.xpath("//a[text()='" + formName + "']")).click();
        pause(2000);

        click(linkActiveVersion);
        pause(2000);

        String inst = driver.findElement(By.xpath("//div[@class='fr-element fr-view']")).getText();
        if (Objects.equals(inst, ""))
        {
            type(inputInstructions, instructions);
            click(buttonSave);
            pause(2000);

            result = true;
        } else if (Objects.equals(inst, instructions))
        {
            click(buttonSave);
            pause(2000);

            result = true;
            pause(2000);
        }
        return result;
    }
}