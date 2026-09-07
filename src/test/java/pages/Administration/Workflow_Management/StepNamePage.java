package pages.Administration.Workflow_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepNamePage extends BasePage {

    String editButtonForStepNameXpath = "//tr[.//td[@data-column='name' and @data-value='%s']]//button[normalize-space(text())='Edit']";
    String cancelBtnForStepNameXpath = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//td[@data-column='_actions']//button[normalize-space(text())='Cancel']";
    String saveBtnForStepNameXpath = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//td[@data-column='_actions']//button[(normalize-space(text())='Save' or @aria-label='Save item')]";

    public StepNamePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By workflowManagementLinkStepName = By.xpath("//nav//a[(normalize-space(.)='Workflow Management' or .//span[normalize-space(.)='Workflow Management'])" + " and contains(@href,'/administration/workflow-management')]");
    By addNewButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Add New']");
    By nameInputField = By.xpath("//input[@id='name' and contains(@class,'default-input') and @type='text']");
    By cancelButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-unstyled') and normalize-space(text())='Cancel']");
    By nameInputFieldUni = By.xpath("//input[@id='name' and contains(@class,'default-input') and @type='text']");
    By addButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Add']");
    By exportControlStepNameLink = By.xpath("//button[contains(@class,'label') and normalize-space(.)='Export Control']" + "/ancestor::div[contains(@class,'-level-1')][1]" + "/following-sibling::div[contains(@class,'toggleable-menu-children')][1]" + "//a[normalize-space(.)='Step name' and contains(@href,'/scopeId/3/step-names')]");
    By stepNameHeader = By.xpath("//header[contains(@class,'_font-size-medium') and normalize-space()='Step Name']");
    By stepNameLabel = By.xpath("//label[@for='name' and contains(normalize-space(.),'Step name')]");
    By exportControlHeaderBtn = By.xpath("//button[contains(@class,'label') and contains(normalize-space(.),'ExportControl')]");
    By stepNameLink = By.xpath("//a[contains(@href,'/administration/workflow-management/scopeId/5/step-names') and normalize-space(.)='Step name']");

    //Actions
    public void clickExportControlStepName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement step = wait.until(ExpectedConditions.elementToBeClickable(stepNameLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", step);
        try { step.click(); } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", step);
        }
        pause(1200);
    }

    public boolean isStepNameLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(stepNameLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStepNameHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(stepNameHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSaveForStepName(String stepName) {
        By saveLocator = By.xpath(String.format(saveBtnForStepNameXpath, stepName, stepName));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        try {
            saveBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        // Post-save sanity: row returns to view mode with 'Edit' button visible
        By editAfterSave = By.xpath(String.format(
                "//tr[.//td[@data-column='name' and @data-value='%s']]//button[normalize-space(text())='Edit']",
                stepName
        ));
        wait.until(ExpectedConditions.presenceOfElementLocated(editAfterSave));

        pause(1000);
    }

    public void clickCancelForStepName(String stepName) {
        By cancelLocator = By.xpath(String.format(cancelBtnForStepNameXpath, stepName, stepName));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cancelLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        pause(1000);
    }

    public void clickEditButtonForStepName(String stepName) {
        By editButtonLocator = By.xpath(String.format(editButtonForStepNameXpath, stepName));

        WebElement button = driver.findElement(editButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(editButtonLocator));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }

    public void clickAddButton() {
        WebElement button = driver.findElement(addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }

    public String enterUniqueName(String baseName) {
        String uniqueName = baseName + "_" + System.currentTimeMillis();

        WebElement input = driver.findElement(nameInputFieldUni);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputFieldUni));

        input.clear();
        input.sendKeys(uniqueName);

        pause(1000);
        return uniqueName; // return so the test can reuse it if needed
    }

    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
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

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }

    public void clickWorkflowManagementStepName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLinkStepName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management"));
        pause(600);
    }
}