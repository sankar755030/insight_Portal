package pages.Administration.Workflow_Management;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


public class RulesPage extends BasePage {

    private final WebDriverWait wait;

    public RulesPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Variables

    String MENU_XPATH = "//div[contains(@class,'select__menu') or contains(@class,'select-menu-outer')]";
    String OPTION_BY_TEXT = "(//div[(contains(@class,'select__option') or contains(@class,'select-option') or @role='option') " + " and normalize-space()='%s'])[1]";
    String ARROW_BY_LABEL =
            "(" +
                    " (//*[self::label or self::div or self::span][normalize-space()='%s']" +
                    "   /ancestor::div[contains(@class,'form') or contains(@class,'row') or contains(@class,'column')][1]" +
                    "   //div[contains(@class,'select-dropdown-indicator') or contains(@class,'_indicatorsContainer')]" +
                    " )" +
                    " | " +
                    " (//*[self::label or self::div or self::span][normalize-space()='%s']" +
                    "   /following::div[contains(@class,'select-dropdown-indicator') or contains(@class,'_indicatorsContainer')][1]" +
                    " )" +
                    ")";
    // Locators

    By workflowManagementLinkRules = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
    By addRuleButton = By.xpath("//button[normalize-space(text())='Add rule']");
    By nameInputField = By.xpath("//input[@id='name' and @type='text']");
    By addRuleButtonRule = By.xpath("//button[normalize-space()='Rule' or normalize-space(text())='Add Rule']");
    By removeRuleButton = By.xpath("//button[@aria-label='Remove rule']");
    By addGroupButton = By.xpath("//button[normalize-space()='Group' or normalize-space(text())='Add Group']");
    By removeGroupButton = By.xpath("//button[@aria-label='Remove group']");
    By migrationButton = By.xpath("//button[normalize-space()='Migration']");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By rulesLink = By.xpath("//a[text()='Rules'][@href='/administration/workflow-management/scopeId/5/rules']");
    By workflowsCancelButton = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[@type='button' and normalize-space()='Cancel']");
    By rulesHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(.),'Rules')]");
    By ruleNameLabel = By.xpath("//label[@for='name']");
    By queryBuilderSection = By.xpath("//div[normalize-space()='Query Builder']");
    By rulesLink01 = By.xpath("//a[normalize-space(.)='Rules' " + "and contains(@href,'/administration/workflow-management/scopeId/5/rules')]");

    //Actions
    public void clickRulesLink01() {
        WebElement link = driver.findElement(rulesLink01);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", link
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(rulesLink01));

        link.click();
        pause(1000);
    }

    public boolean isQueryBuilderSectionDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(queryBuilderSection));
            return section.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRuleNameLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameLabel));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRulesHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(rulesHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickWorkflowsCancelButton() {
        WebElement cancelBtn = wait.until(
                ExpectedConditions.elementToBeClickable(workflowsCancelButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancelBtn
        );

        cancelBtn.click();
        pause(1000);
    }

    public void selectQueryBuilderOperator(String labelText, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        By arrow = By.xpath(String.format(ARROW_BY_LABEL, labelText, labelText));
        List<WebElement> arrows = driver.findElements(arrow);
        if (arrows.isEmpty()) {
            throw new NoSuchElementException("Could not find dropdown indicator near label: " + labelText);
        }

        WebElement arrowEl = arrows.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", arrowEl);

        // Open menu (retry if needed)
        arrowEl.click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_XPATH)));
        } catch (org.openqa.selenium.TimeoutException e) {
            arrowEl.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_XPATH)));
        }

        // Pick the option (e.g., AND)
        By option = By.xpath(String.format(OPTION_BY_TEXT, optionText));
        WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        wait.until(ExpectedConditions.elementToBeClickable(opt));
        try {
            new Actions(driver).moveToElement(opt).pause(Duration.ofMillis(80)).click().perform();
        } catch (Exception ignored) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
        }

        // Let the menu close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MENU_XPATH)));
        pause(300);
    }

    public void clickEditButtonForRule(String ruleName) {

        pause(5000);

        String editButtonXpath = "//td[@data-column='name' and normalize-space(text())='" + ruleName + "']" +
                "/following-sibling::td//button[normalize-space()='Edit']";

        By editButton = By.xpath(editButtonXpath);

        WebElement button = driver.findElement(editButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(editButton));

        pause(1000);
        button.click();
    }

    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        pause(1000);
        button.click();
    }

    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));

        pause(1000);
        button.click();
    }

    public void clickOKButton()
    {
        driver.findElement(By.xpath("//button[text()='OK']")).click();
        pause(2000);
    }

    public void clickMigrationButton() {
        WebElement button = driver.findElement(migrationButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(migrationButton));

        pause(1000);
        button.click();
    }

    public void clickRemoveGroupButton() {
        WebElement button = driver.findElement(removeGroupButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeGroupButton));

        pause(1000);
        button.click();
    }

    public void clickAddGroupButton() {
        WebElement button = driver.findElement(addGroupButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addGroupButton));

        pause(1000);
        button.click();
    }

    public void clickRemoveRuleButton() {
        WebElement button = driver.findElement(removeRuleButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeRuleButton));

        pause(1000);
        button.click();
    }

    public void clickAddRuleButtonRule() {
        WebElement button = driver.findElement(addRuleButtonRule);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addRuleButtonRule));

        pause(1000);
        button.click();
    }

    public String enterUniqueNameWithTestPrefix() {
        String uniqueName = "Test_" + System.currentTimeMillis(); // e.g., Test_1723465923456

        WebElement input = driver.findElement(nameInputField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));

        input.clear();
        input.sendKeys(uniqueName);

        pause(1000);

        return uniqueName; // return so test can log or use later
    }

    public void clickAddRuleButton() {
        WebElement button = driver.findElement(addRuleButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addRuleButton));

        pause(1000);
        button.click();
    }

    public void clickRulesLink() {
        WebElement link = driver.findElement(rulesLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000);
        link.click();
    }

    public void clickWorkflowManagementLinkRules() {
        WebElement link = driver.findElement(workflowManagementLinkRules);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLinkRules));

        link.click();
        pause(1000);
    }
}