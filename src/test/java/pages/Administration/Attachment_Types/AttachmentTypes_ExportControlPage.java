package pages.Administration.Attachment_Types;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import utils.UniqueNameGenerator;

public class AttachmentTypes_ExportControlPage extends BasePage {

    private final WebDriverWait wait;
    UniqueNameGenerator uniqueNameGenerator = new UniqueNameGenerator();

    // Constructor
    public AttachmentTypes_ExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // *********************** Sankar Locators **********************************************

    By addAttachmentTypeButton = By.xpath("//button[normalize-space()='Add Attachment Type']");
    By typeNameInput = By.xpath("//span[normalize-space()='Type Name']/following::input[@type='text'][1]");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By addButton = By.xpath("//button[normalize-space()='Add']");
    By firstEditButton = By.xpath("//table//tr[1]//button[.//div[contains(@class,'_icon-edit-grid')]]");
    By typeNameInput01 = By.xpath("//label[span[normalize-space()='Type Name']]//input");
    By saveButton01 = By.xpath("//button[normalize-space()='Save']");
    By cancelButton01 = By.xpath("//button[normalize-space()='Cancel']");
    By attachmentTypesToggle = By.xpath("//span[normalize-space()='Attachment Types']" + "/ancestor::div[contains(@class,'menu-item-holder')]" +"//button[contains(@class,'toggle-menu-icon-button')]");
    By attachmentTypesExportControl = By.xpath("//div[contains(@class,'toggleable-menu-children') and contains(@class,'-opened')]" + "//span[normalize-space()='Export Control']/parent::a");
    By attachmentTypeTitle = By.xpath("//strong[contains(text(),'Attachment Type')]");
    By addAttachmentTypeHeader = By.xpath("//header[contains(text(),'Add Attachment Type')]");
    By typeNameLabel = By.xpath("//span[normalize-space()='Type Name']");
    By activeLabel = By.xpath("//span[normalize-space()='Active']");

    // ********************* Sahil Locators *****************************************

    By linkAttachmentTypes = By.xpath("//span[text()='Attachment Types']/..");
    By linkExportControl = By.xpath("(//a[@href='/administration/attachment-type'])[2]");

    By buttonAddAttachmentType = By.xpath("//button[text()='Add Attachment Type']");
    By inputTypeName = By.xpath("//span[text()='Type Name']/..//input");
    By buttonAdd = By.xpath("//button[text()='Add']");

    // Functions
    public boolean isActiveLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(activeLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTypeNameLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(typeNameLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddAttachmentTypeHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addAttachmentTypeHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAttachmentTypeSectionDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(attachmentTypeTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void NavigateToAttachmentTypesExportControlPage() {

        //Click on Attachment Types navigation link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkAttachmentTypes));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();

        //waitForPresence(linkAttachmentTypes);
        //click(linkAttachmentTypes);
        pause(5000);

        //Click on Export Control under People Management
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }

    public boolean AddAttachmentTypeAndVerifyInTheAttachmentTypeList(String typeName) {
        boolean result = false;

        // Click on Add Attachment Type button
        click(buttonAddAttachmentType);

        // Enter Attachment Type name
        type(inputTypeName, typeName);

        // Click Add button
        click(buttonAdd);
        pause(5000);

        int noOfRows = driver.findElements(By.xpath("//tbody/tr")).size();

        for (int i = 1; i <= noOfRows; i++)
        {
            String name = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div/div")).getText();

            if(Objects.equals(name, typeName))
            {
                result=true;
                break;
            }
        }

        return result;
    }

    // ***************************** Sankar Functions **********************************

    public void expandAttachmentTypes() {
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(attachmentTypesToggle));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", toggle);
        toggle.click();
        pause(1000);
    }

    public void clickAttachmentTypesExportControl() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(attachmentTypesExportControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        pause(1000);
    }

    public void appendTypeNameWithTest() {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(typeNameInput01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input
        );

        input.click();

        String existing = input.getAttribute("value");
        input.clear();
        input.sendKeys(existing + "Test");

        pause(1000);
    }

    public void clickCancel() {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton01));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancel);
        cancel.click();
        pause(800);
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton01));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", save);
        save.click();
        pause(1000);
    }

    public void clickFirstEditButton() {

        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstEditButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editBtn);

        editBtn.click();
        pause(1000);
    }

    public void enterRandomTypeName() {
        String randomName = uniqueNameGenerator.GenerateRandomName(6);

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(typeNameInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(randomName);

        pause(1000);
    }

    public void clickCancelButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickAddButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickAddAttachmentType() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addAttachmentTypeButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }
}