package pages.Administration.Workflow_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionNamePage extends BasePage {

    public ActionNamePage(WebDriver driver) {
        super(driver);
    }

    // Variables
    String editBtnForActionNameXpath = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//td[@data-column='_actions']//button[normalize-space(text())='Edit']";
    String saveBtnForActionNameXpath = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//button[normalize-space(text())='Save']";
    String nameInputInRowXpath = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//td[@data-column='name']//input";
    String cancelBtnForActionNameXpath = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//td[@data-column='_actions']//button[(normalize-space(.)='Cancel' or @aria-label='Undo item')]";
    String editBtnAfterCancelXpath = "//tr[.//td[@data-column='name' and @data-value='%s']]" + "//td[@data-column='_actions']//button[normalize-space(.)='Edit']";
    String saveBtnForActionNameXpathAction = "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" + "//td[@data-column='_actions']//button[(normalize-space(.)='Save' or @aria-label='Save item')]";
    String editBtnAfterSaveXpath = "//tr[.//td[@data-column='name' and @data-value='%s']]" + "//td[@data-column='_actions']//button[normalize-space(.)='Edit']";

    // Locators
    By workflowManagementLink = By.xpath("//nav//a[(normalize-space(.)='Workflow Management' or .//span[normalize-space(.)='Workflow Management'])" + " and contains(@href,'/administration/workflow-management')]");
    By actionNameScope3 = By.xpath("//nav//a[normalize-space(.)='Action name' " + "and contains(@href,'/administration/workflow-management') " + "and contains(@href,'/scopeId/3/') " + "and contains(@href,'/action-names')]");
    By addNewButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Add New']");
    By nameInputField = By.xpath("//input[@id='name' and contains(@class,'default-input') and @type='text']");
    By historyTitleInput = By.xpath("//input[@id='historyTitle' and contains(@class,'default-input') and @type='text']");
    By cancelButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-unstyled') and normalize-space(text())='Cancel']");
    By addButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Add']");
    By actionNameHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(.),'Action Name')]");
    By actionNameLabel = By.xpath("//label[@for='name']");
    By historyTitleLabel = By.xpath("//label[@for='historyTitle']");
    By actionNameLink = By.xpath("//a[normalize-space(.)='Action name' and contains(@href,'/scopeId/5/action-names')]");

    // Action
    public void clickActionNameLink() {
        WebElement link = driver.findElement(actionNameLink);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", link
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(actionNameLink));

        link.click();
        pause(1000);
    }

    public boolean isActionNameLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(actionNameLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isActionNameHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(actionNameHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSaveForActionName(String actionName) {
        By saveLocator = By.xpath(String.format(saveBtnForActionNameXpathAction, actionName, actionName));
        By editAfterSave = By.xpath(String.format(editBtnAfterSaveXpath, actionName));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        try {
            saveBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        // Verify row exited edit mode
        wait.until(ExpectedConditions.presenceOfElementLocated(editAfterSave));
        pause(1000);
    }

    public void clickCancelForActionName(String actionName) {
        By cancelLocator = By.xpath(String.format(cancelBtnForActionNameXpath, actionName, actionName));
        By editAfterCancel = By.xpath(String.format(editBtnAfterCancelXpath, actionName));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);

        try {
            cancelBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelBtn);
        }

        // Verify the row exited edit mode (Edit button reappears)
        wait.until(ExpectedConditions.presenceOfElementLocated(editAfterCancel));
        pause(1000);
    }

    public void clickEditForActionName(String actionName) {
        By editLocator = By.xpath(String.format(editBtnForActionNameXpath, actionName, actionName));
        By saveLocator = By.xpath(String.format(saveBtnForActionNameXpath, actionName, actionName));
        By nameInputInRow = By.xpath(String.format(nameInputInRowXpath, actionName, actionName));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", editBtn);

        try {
            editBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);
        }

        // Row should switch to edit mode: wait for Save button or input to appear
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(saveLocator),
                ExpectedConditions.presenceOfElementLocated(nameInputInRow)
        ));

        pause(1000);
    }

    public void clickAddButton() {
        WebElement button = driver.findElement(addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton));

        button.click();
        pause(1000);
    }

    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));

        button.click();
        pause(1000);
    }

    public void enterHistoryTitle(String title) {
        WebElement input = driver.findElement(historyTitleInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(historyTitleInput));

        input.clear();
        input.sendKeys(title);

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

        button.click();
        pause(1000);
    }

    public void clickActionNameScope3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionNameScope3));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management/scopeId/3/action-names"));
        pause(600);
    }

    public void clickWorkflowManagementaction() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management"));
        pause(600);
    }
}