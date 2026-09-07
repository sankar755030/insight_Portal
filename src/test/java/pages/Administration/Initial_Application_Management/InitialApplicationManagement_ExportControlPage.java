
package pages.Administration.Initial_Application_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UniqueNameGenerator;

import java.time.Duration;

public class InitialApplicationManagement_ExportControlPage extends BasePage {
    UniqueNameGenerator uniqueNameGenerator = new UniqueNameGenerator();

    private final WebDriverWait wait;
    public InitialApplicationManagement_ExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators

    By initialAppMgmtMain = By.xpath("//div[@id='left-sidebar']" + "//span[normalize-space()='Initial Application Management']" + "/ancestor::*[self::a or self::button][1]");
    By initialAppMgmtArrow = By.xpath("//div[@id='left-sidebar']" + "//span[normalize-space()='Initial Application Management']" + "/ancestor::div[contains(@class,'-level-1') and contains(@class,'menu-item')]" + "//button[contains(@class,'toggle-menu-icon-button')]");
    By initialAppMgmtExportControl = By.xpath("//div[contains(@class,'_applicationManagementMenu')]//a[normalize-space()='Export Control']");
    By addInitialApplicationButton = By.xpath("//button[normalize-space()='Add Initial Application']");
    By addButton = By.xpath("//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space()='Add']");
    By modalCancelButton = By.xpath("//div[contains(@class,'ReactModal__Content')]//button[normalize-space()='Cancel']");
    By entityTypeCombobox = By.xpath("//div[contains(@class,'ReactModal__Content--after-open')]" + "//input[@id='entityTypeId' and @role='combobox']");
    By firstEditButton = By.xpath("(//table[contains(@class,'item-grid')]//tbody//button[normalize-space()='Edit'])[1]");
    By inlineCancelButton = By.xpath("(//tr[contains(@class,'item-grid-tr')]//button[@aria-label='Undo item'])[1]");
    By inlineSaveButton = By.xpath("(//tr[contains(@class,'item-grid-tr')]//button[@aria-label='Save item'])[1]");
    By initialApplicationBreadcrumb = By.xpath("//span[@class='crumb' and normalize-space()='Initial Application Management']");
    By exportControlBreadcrumb = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='Export Control']");
    By actionNameLabel = By.xpath("//label[@for='name' and contains(normalize-space(),'Action Name')]");

    //Actions
    public boolean isActionNameLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(actionNameLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExportControlBreadcrumbDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(exportControlBreadcrumb));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInitialApplicationBreadcrumbDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(initialApplicationBreadcrumb));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickInlineCancel() {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(inlineCancelButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);
        cancel.click();
        pause(500);
    }

    public void clickInlineSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(inlineSaveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", save);
        save.click();
        pause(800);
    }

    public void clickFirstEditButton() {
        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstEditButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", editBtn
        );

        editBtn.click();
    }

    public void clickAddButton() {

        // Step 1: Wait & scroll into view
        WebElement add = wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", add);

        // Step 2: Click Add
        add.click();

        // Step 3: Pause (your standard)
        pause(1000);
    }

    public void selectEntityType() {

        // Wait until the combobox is visible in the active modal
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(entityTypeCombobox)
        );

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        // Use JS click to bypass any overlay / animation issues
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", input);

        // Type and select "Export Control"
        input.clear();
        input.sendKeys("Export Control");
        pause(500);
        input.sendKeys(Keys.ENTER);
    }

    public String enterRandomActionAndSelectEntity() {

        // Step 1: Generate random name using your BasePage method
        String randomName = uniqueNameGenerator.GenerateRandomName(6);

        // Step 2: Enter Action Name
        WebElement actionNameInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", actionNameInput);

        actionNameInput.click();
        actionNameInput.clear();
        actionNameInput.sendKeys(randomName);

        // Step 3: Select Entity Type = Export Control (React-Select)
        selectEntityType();

        return randomName;
    }

    public void clickCancel() {
        driver.findElement(modalCancelButton).click();
    }

    public void clickAddInitialApplication() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addInitialApplicationButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();
        pause(800);
    }

    public void openInitialApplicationManagementExportControl() {

        // STEP 1 – Click "Initial Application Management" (link or button)
        WebElement main = wait.until(
                ExpectedConditions.elementToBeClickable(initialAppMgmtMain));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", main);
        main.click();
        pause(800); // allow page / submenu to render

        // STEP 2 – If Export Control not visible yet, try expanding via arrow
        if (driver.findElements(initialAppMgmtExportControl).isEmpty()) {
            java.util.List<WebElement> arrows = driver.findElements(initialAppMgmtArrow);
            if (!arrows.isEmpty()) {
                WebElement arrow = wait.until(
                        ExpectedConditions.elementToBeClickable(initialAppMgmtArrow));
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", arrow);
                arrow.click();
                pause(800);
            }
        }

        // STEP 3 – Click "Export Control"
        WebElement exportControl = wait.until(
                ExpectedConditions.elementToBeClickable(initialAppMgmtExportControl));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", exportControl);
        exportControl.click();
        pause(1000);
    }
}