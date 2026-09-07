package pages.Administration.Form_Visibility;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormsVisibility_ExportControlPage extends BasePage {

    private final WebDriverWait wait;

    public FormsVisibility_ExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators

    By activeRulesModal = By.xpath("//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]");
    By formInput = By.xpath("//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//input[@id='dataSourceKey']");
    By accessInput = By.xpath("//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//input[@id='access']");
    By queryBuilderInput = By.xpath("//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//div[contains(text(),'Query Builder')]/following::input[@role='combobox'][1]");
    By formVisibilityToggleButton = By.xpath("//button[contains(@class,'toggle-menu-icon-button') and contains(@aria-label,'Form Visibility')]");
    By exportControlLink = By.xpath("//a[contains(@href,'/administration/form-visibility/export-control') and contains(@class,'label')]");
    By addRuleButton = By.xpath("//button[normalize-space()='Add rule']");
    By rulesModal = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]");
    By addRuleInsideModalButton = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Rule']");
    By removeRuleButton = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[@aria-label='Remove rule']");
    By addGroupButton = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[contains(@class,'-primary')][div/i[contains(@class,'fi-add')]][contains(.,'Group')]");
    By removeGroupButton = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[@aria-label='Remove group']");
    By migrationButton = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Migration']");
    By cancelButton = By.xpath("//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Cancel']");
    By firstEditButton = By.xpath("(//table//button[normalize-space()='Edit'])[1]");
    By cancelButton01 = By.xpath("//button[@type='button' and normalize-space()='Cancel']");
    By saveButton01 = By.xpath("//button[@type='button' and normalize-space()='Save']");
    By formVisibilityExportControlHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(.),'Form Visibility') and contains(normalize-space(.),'Export Control')]");
    By formLabel = By.xpath("//label[@for='dataSourceKey']");
    By accessLabel = By.xpath("//label[@for='access']");
    By queryBuilderSection = By.xpath("//div[normalize-space()='Query Builder']");
    By ruleButton = By.xpath("//button[normalize-space()='Rule']");
    By groupButton = By.xpath("//button[normalize-space()='Group']");
    By migrationButton1 = By.xpath("//button[normalize-space()='Migration']");

    //Actions
    public boolean isRuleButtonDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ruleButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGroupButtonDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(groupButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMigrationButtonDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(migrationButton1)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFormLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(formLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccessLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(accessLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isQueryBuilderSectionDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(queryBuilderSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFormVisibilityExportControlHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(formVisibilityExportControlHeader)
            );
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSaveButton() {

        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", save);

        save.click();
        pause(1000);
    }

    public void clickCancelButton() {

        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();
        pause(1000);
    }

    public void clickFirstEditButton() {

        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstEditButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", editBtn);

        editBtn.click();
        pause(1000);
    }

    public void clickCancel() {

        WebElement cancel = wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();

        pause(800);
    }

    public void clickMigration() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(migrationButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(800);
    }

    public void clickRemoveGroup() {

        WebElement removeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(removeGroupButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", removeBtn);

        removeBtn.click();

        pause(800);
    }

    public void clickAddGroup() {

        WebElement groupBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addGroupButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", groupBtn);

        groupBtn.click();

        pause(800);
    }

    public void clickRemoveRule() {

        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(removeRuleButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", deleteBtn);

        deleteBtn.click();

        pause(800);
    }

    public void clickAddRuleInsideModal() {

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addRuleInsideModalButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", button);

        button.click();

        pause(800);
    }

    // Step 1: Select Form by typing and pressing ENTER
    public void selectForm(String formName) {

        // Ensure modal is visible
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(activeRulesModal));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", modal);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(formInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        pause(300);

        // Type and select via ENTER
        input.clear();
        input.sendKeys(formName);
        pause(300);
        input.sendKeys(Keys.ENTER);

        pause(800);
    }

    // Step 2: Select Access by typing and pressing ENTER
    public void selectAccess(String accessType) {

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(accessInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        pause(300);

        input.clear();
        input.sendKeys(accessType);
        pause(300);
        input.sendKeys(Keys.ENTER);

        pause(800);
    }

    // Step 3: Select Query Builder by typing and pressing ENTER
    public void selectQueryBuilder(String builderOption) {

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(queryBuilderInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        pause(300);

        input.clear();
        input.sendKeys(builderOption);
        pause(300);
        input.sendKeys(Keys.ENTER);

        pause(800);
    }

    // Wait for modal (you already have something like this, keep it)
    public void waitForFormVisibilityModal() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(rulesModal));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", modal);
        pause(800);
    }

    public void openExportControlUnderFormVisibility() {

        // 1) Make sure Form Visibility section is expanded
        WebElement toggleBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(formVisibilityToggleButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        String expanded = toggleBtn.getAttribute("aria-expanded");
        // Only click if it's NOT already expanded
        if (expanded == null || expanded.equalsIgnoreCase("false")) {
            toggleBtn.click();
            pause(500);
        }

        // 2) Click Export Control link
        WebElement exportControl = wait.until(
                ExpectedConditions.elementToBeClickable(exportControlLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", exportControl);

        exportControl.click();
        pause(1000);
    }

    public void clickAddRuleButton() {

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addRuleButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
        button.click();

        pause(1000);
    }
}