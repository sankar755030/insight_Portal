package pages.Administration.Workflow_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtility;
import utils.WaitUtility;

import java.time.Duration;

public class AncillaryWorkflowsPage extends BasePage {

    public AncillaryWorkflowsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By addNewButton = By.xpath("//button[@type='button' and normalize-space()='Add New']");
    By cancelButton = By.xpath("//button[normalize-space(text())='Cancel']");
    By saveButton = By.xpath("//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Save']");
    By nameInputFieldAnc = By.xpath("//input[@id='name' and @type='text']");
    By updateButtonAnc = By.xpath("//button[normalize-space(text())='Update']");
    By workflowManagementLink = By.xpath("//nav//a[(normalize-space(.)='Workflow Management' or .//span[normalize-space(.)='Workflow Management'])" + " and contains(@href,'/administration/workflow-management')]");
    By ancillaryWorkflowsScope3 = By.xpath("//nav//a[normalize-space(.)='Ancillary Workflows' " + "and contains(@href,'/administration/workflow-management') " + "and contains(@href,'/scopeId/3/') " + "and contains(@href,'/workflowType/2/') " + "and contains(@href,'/workflows')]");
    By lastRow = By.xpath("(//table//tr[.//td[@data-column='name']])[last()]");
    By editBtnInRow = By.xpath(".//td[@data-column='_actions']//button[@type='button' and normalize-space()='Edit']");
    By lastEditDirect = By.xpath("(//td[@data-column='_actions']//button[@type='button' and normalize-space()='Edit'])[last()]");
    By ancillaryWorkflowsHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(.),'Ancillary Workflows')]");
    By nameLabel = By.xpath("//label[@for='name']");
    By triggeringRuleLabel = By.xpath("//label[@for='triggeringRuleId']");
    By ancillaryWorkflowsLink = By.xpath("//a[normalize-space(.)='Ancillary Workflows' " + "and contains(@href,'/scopeId/5/workflowType/2/workflows')]");

    // Action
    public void clickAncillaryWorkflowsLink() {
        WebElement link = driver.findElement(ancillaryWorkflowsLink);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", link
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ancillaryWorkflowsLink));

        link.click();
        pause(1000);
    }

    public boolean isNameLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(nameLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTriggeringRuleLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(triggeringRuleLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAncillaryWorkflowsHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(ancillaryWorkflowsHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLastEdit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        // Prefer row-scoped
        try {
            WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(lastRow));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", row);
            WebElement edit = row.findElement(editBtnInRow);
            wait.until(ExpectedConditions.elementToBeClickable(edit));
            try { edit.click(); } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", edit);
            }
        } catch (TimeoutException te) {
            // Fallback to direct last Edit
            WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(lastEditDirect));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", edit);
            try { edit.click(); } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", edit);
            }
        }

        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("name")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and normalize-space()='Update']"))
        ));
        pause(500);
    }

    public void clickAncillaryWorkflowsScope3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(ancillaryWorkflowsScope3));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management/scopeId/3/workflowType/2/workflows"));
        pause(600);
    }

    public void clickWorkflowManagement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management"));
        pause(600);
    }

    public void clickUpdateButtonAnc() {
        WebElement button = driver.findElement(updateButtonAnc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }

    public void enterNameAnc(String name) {
        WebElement input = driver.findElement(nameInputFieldAnc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputFieldAnc));

        input.clear();
        input.sendKeys(name);
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

    public void selectOptionFromDropdownAncillary(String dropDownName, String optionToSelect){
        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);

    }

    public void clickAddNew() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addNewButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();

        // Confirm something opened/changed
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role='dialog' or @aria-modal='true' or contains(@class,'modal')]")),
                ExpectedConditions.urlMatches(".*(/new|/create|/add).*")));
        pause(800);
    }
}