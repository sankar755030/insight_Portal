package pages.Administration.Workflow_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtility;
import utils.WaitUtility;

import java.time.Duration;
import java.util.List;

public class WorkflowsPage extends BasePage {

    public WorkflowsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By workflowManagementLink = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
    By exportControlWorkflowsLink = By.xpath("//a[contains(@href, 'workflows-export-control') and text()='Workflows']");
    By addNewButton = By.xpath("//button[@type='button' and contains(@class,'-primary') and text()='Add New']");
    By nameInputField = By.xpath("//input[@id='name' and @type='text']");
    By cancelButton = By.xpath("//button[normalize-space(text())='Cancel']");
    By saveButton = By.xpath("//div[contains(@class,'buttons-cell')]//button[normalize-space()='Save']");
    By updateButton = By.xpath("//button[normalize-space(text())='Update']");
    By workflowGrid = By.cssSelector("div.panel.-table, div.list, table");
    By editControls = By.xpath(".//a[normalize-space()='Edit' or @title='Edit']" + " | .//button[normalize-space()='Edit' or @title='Edit' or .//i[contains(@class,'edit')]]");
    By workflowsHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(.),'Workflows')]");
    By nameLabel = By.xpath("//label[@for='name']");
    By recordTypeLabel = By.xpath("//label[@for='additionalProps.exportControlOfficeId']");
    By transactionTypeLabel = By.xpath("//label[@for='additionalProps.transactionTypeId']");
    By exportControlStatusLabel = By.xpath("//label[@for='additionalProps.exportControlStatusId']");
    By emailFromLabel = By.xpath("//label[@for='emailFrom']");
    By workflowsLink = By.xpath("//a[contains(@class,'_menuLink') and normalize-space(.)='Workflows' and contains(@href,'/administration/workflow-management/scopeId/5/workflowType/1/workflows')]");

    //Actions
    public void clickWorkflowsLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(1000);
    }

    public boolean isNameLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(nameLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRecordTypeLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(recordTypeLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTransactionTypeLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTypeLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExportControlStatusLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(exportControlStatusLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailFromLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFromLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWorkflowsHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(workflowsHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFirstEdit() {
        WebElement grid = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(workflowGrid));

        // Wait until at least one Edit is present
        List<WebElement> edits = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> {
                    List<WebElement> els = grid.findElements(editControls);
                    return (els != null && !els.isEmpty()) ? els : null;
                });

        // Pick the first *displayed* one
        assert edits != null;
        WebElement first = edits.stream().filter(WebElement::isDisplayed).findFirst().orElse(edits.get(0));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(first));

        try {
            first.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
        }

        pause(2000);
    }

    public void clickUpdateButton() {
        WebElement button = driver.findElement(updateButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }

    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }

    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }

    public void selectOptionFromDropdown(String dropDownName,String optionToSelect){
        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);

    }

    public void selectOptionFromDropdownExportControlStatus(String dropDownName, String optionToSelect){
        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);
        BrowserUtility.click(driver, By.xpath(dropdownXpath), dropDownName); // collapse

    }

    public void enterName(String name) {
        WebElement input = driver.findElement(nameInputField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));

        input.clear();
        input.sendKeys(name);
        pause(1000);
    }

    public void clickAddNewButton() {
        WebElement button = driver.findElement(addNewButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addNewButton));

        button.click();
        pause(1000);
    }

    public void clickExportControlWorkflows() {
        WebElement link = driver.findElement(exportControlWorkflowsLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(exportControlWorkflowsLink));

        link.click();
        pause(1000);
    }

    public void clickWorkflowManagementLink() {
        WebElement link = driver.findElement(workflowManagementLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));

        link.click();
        pause(1000);
    }
}