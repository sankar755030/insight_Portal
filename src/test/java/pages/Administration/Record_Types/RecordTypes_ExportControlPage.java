package pages.Administration.Record_Types;
import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.NoSuchElementException;


public class RecordTypes_ExportControlPage extends BasePage {

    public RecordTypes_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    By exportControlLink = By.xpath("//a[text()='Export Control'][@href='/administration/record-types']");
    By addRecordTypeLink = By.xpath("//a[@class='_link_ogtko_1' and text()='Add Record Type']");
    By recordTypeInput = By.xpath("//label[text()='Enter Record Type']/following::input[contains(@class,'text-input') and @id='refMeaning']");
    By createButton = By.xpath("//button[@type='button' and contains(text(), 'Create')]");
    By cancelButton = By.xpath("//div[contains(@class,'buttons-cell')]//a[normalize-space()='Cancel' and contains(@href,'/administration/record-types')]");
    By addCategoryLink = By.xpath("//a[@class='_link_ogtko_1' and text()='Add Category']");
    By searchByNameInput = By.xpath("//input[@placeholder='Search by Name']");
    By searchButton = By.xpath("//button[@type='button' and text()='Search']");
    By clearSelectionsButton = By.xpath("//button[@type='button' and text()='Clear Selections']");
    By refMeaningInput = By.xpath("//input[contains(@class,'default-input') and @type='text']");
    By moduleDropdownArrow = By.xpath("//div[contains(@class,'select-dropdown-indicator')]");
    By recordTypesLink02 = By.xpath("//div[contains(@class,'-level-1') and contains(@class,'menu-item')]//a[contains(@class,'label') and contains(@href,'/administration/record-types')]//span[normalize-space()='Record Types']");
    By recordTypesToggle = By.xpath("//div[contains(@class,'-level-1') and contains(@class,'menu-item')][.//span[normalize-space()='Record Types']]//div[contains(@class,'menu-item-side-element')]//button[contains(@class,'toggle-menu-icon-button')]");
    By recordTypesExportControl = By.xpath("//div[contains(@class,'-level-1') and contains(@class,'menu-item')][.//span[normalize-space()='Record Types']]" + "/following-sibling::div[contains(@class,'toggleable-menu-children')]" + "//a[contains(@class,'label')]//span[normalize-space()='Export Control']");
    By firstRowEditButton = By.xpath("//table[contains(@class,'item-grid')]//tbody/tr[1]" + "//td[@data-column='_actions']//button[.//div[@aria-label='Edit']]");
    By editRecordTypeCancelButton = By.xpath("//div[contains(@class,'ReactModalPortal')]//button[normalize-space()='Cancel']");
    By editRecordTypeSaveButton = By.xpath("//div[contains(@class,'ReactModalPortal')]//button[normalize-space()='Save']");
    By recordTypesHeader = By.xpath("//strong[normalize-space()='Record Types']");
    By createRecordTypeHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='Create Record Type']");
    By createCategoryHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='Create Category']");
    By editRecordTypeHeader = By.xpath("//header[contains(text(),'Edit Record Type')]");
    By recordTypesSectionTitle = By.xpath("//strong[contains(text(),'Record Types')]");

    //Actions

    public boolean isEditRecordTypeHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(editRecordTypeHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRecordTypesSectionDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(recordTypesSectionTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCreateCategoryPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(createCategoryHeader));
            return true;
        } catch (
                TimeoutException e) {
            return false;
        }
    }

    public boolean isCreateRecordTypePageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(createRecordTypeHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isRecordTypesPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(recordTypesHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickSaveOnEditRecordTypeModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(editRecordTypeSaveButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        saveBtn.click();
        pause(1000);   // your pattern
    }

    public void clickFirstRecordTypeEditIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstRowEditButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", editBtn);

        editBtn.click();
        pause(1000);
    }

    public void clickCancelOnEditRecordTypeModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cancelBtn = wait.until(
                ExpectedConditions.elementToBeClickable(editRecordTypeCancelButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);

        cancelBtn.click();
        pause(1000);
    }

    public void openRecordTypesExportControl() {
        // ensure the parent row is in view
        WebElement parent = driver.findElement(recordTypesLink02);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", parent);

        // if children are collapsed, expand
        WebElement toggle = driver.findElement(recordTypesToggle);
        if (toggle.getAttribute("aria-expanded") != null && toggle.getAttribute("aria-expanded").equals("false")) {
            toggle.click();
        } else {
            // some UIs don’t set aria-expanded; click if children not visible
            try {
                driver.findElement(recordTypesExportControl); // present but might be hidden
            } catch (NoSuchElementException e) {
                toggle.click();
            }
        }

        // wait for the submenu item to be visible & clickable, then click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        By visibleChild = By.xpath(
                "//div[contains(@class,'-level-1') and contains(@class,'menu-item')][.//span[normalize-space()='Record Types']]"
                        + "/following-sibling::div[contains(@class,'toggleable-menu-children')]"
                        + "//a[contains(@class,'label')]//span[normalize-space()='Export Control']/.."
        );
        WebElement child = wait.until(ExpectedConditions.elementToBeClickable(visibleChild));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", child);
        child.click();

        pause(500); // optional, matches your pattern
    }

    public void selectModuleAsExportControl() {
        WebElement dropdownArrow = driver.findElement(moduleDropdownArrow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
        pause(1000); // Stabilization wait

        dropdownArrow.click(); // Open dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for dropdown options to render
        String moduleOptionXpath = "//div[text()='%s']";
        By exportControlOption = By.xpath(String.format(moduleOptionXpath, "Export Control"));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(exportControlOption));
        option.click(); // Select the option

        ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");
        pause(1000); // Optional post-click wait
    }

    public void enterRefMeaning(String value) {
        WebElement input = driver.findElement(refMeaningInput);
        input.clear();
        input.sendKeys(value);

    }

    public void selectCategory(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 1️⃣ Ensure we are on the Create Category page
        wait.until(ExpectedConditions.urlContains("/administration/record-types/create-category"));
        By controlBy = By.xpath("//label[contains(normalize-space(),'Select Record Type')]" + "/following::div[contains(@class,'select-control')][1]");
        WebElement control = wait.until(ExpectedConditions.visibilityOfElementLocated(controlBy));

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        pause(600);

        // 3️⃣ Click to open dropdown (with retry)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(control)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
        }
        pause(600);

        // 4️⃣ Type the category name in the combobox
        By inputBy = By.xpath(
                "//label[contains(normalize-space(),'Select Record Type')]" +
                        "/following::input[@role='combobox'][1]"
        );
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputBy));
        input.clear();
        input.sendKeys(categoryName);
        pause(600);

        // 5️⃣ Pick the option from the dropdown list
        By optionBy = By.xpath(
                String.format("//div[contains(@class,'option') and normalize-space()='%s']", categoryName)
        );
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionBy));
        option.click();

        pause(800);
    }

    public void clickAddCategoryLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait until the link is present & clickable
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addCategoryLink));

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        pause(800); // small buffer

        try {
            // Normal click first
            link.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // If something is on top (loader/header), retry with JS click
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            } catch (Exception ignored) {
                // Last fallback: re-find and JS click
                try {
                    WebElement freshLink =
                            wait.until(ExpectedConditions.elementToBeClickable(addCategoryLink));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", freshLink);
                } catch (Exception ignoredAgain) {
                    // Swallow – navigation likely already happened
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            // Page updated between find & click – re-locate and click via JS
            try {
                WebElement freshLink =
                        wait.until(ExpectedConditions.elementToBeClickable(addCategoryLink));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", freshLink);
            } catch (Exception ignored) {
                // do nothing – if it still fails, we don't want test to die here
            }
        }

        // Give time for navigation / modal to appear
        pause(2000);
    }

    public void clickClearSelectionsButton() {
        WebElement button = driver.findElement(clearSelectionsButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        pause(1000); // Wait after scroll
        button.click();
        pause(2000); // Wait for reset action
        ExtentReportListener.getExtentTest().pass("Clicked 'Clear Selections' button successfully");
    }

    public void clickSearchButton() {
        WebElement button = driver.findElement(searchButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        pause(1000); // Wait after scroll
        button.click();
        pause(2000); // Wait for search results
        ExtentReportListener.getExtentTest().pass("Clicked 'Search' button successfully");
    }

    public void searchRecordByName(String name) {
        WebElement input = driver.findElement(searchByNameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000); // Wait after scroll
        input.clear();
        input.sendKeys(name);
        pause(2000); // Optional wait to let results filter
        ExtentReportListener.getExtentTest().pass("Entered '" + name + "' in 'Search by Name' input field");
    }

    public void clickCancelButton() {
        WebElement cancel = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancel);
        pause(1000); // Optional scroll wait

        cancel.click();
        pause(3000); // Wait for navigation

        ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button and navigated back to Record Types list");
    }

    public void clickCreateButton() {
        WebElement button = driver.findElement(createButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        pause(1000); // Optional scroll wait

        button.click();
        pause(3000); // Wait for action or navigation

        ExtentReportListener.getExtentTest().pass("Clicked 'Create' button successfully");
    }

    public void enterRecordType(String recordTypeName) {
        WebElement input = driver.findElement(recordTypeInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000); // Wait after scroll
        input.clear(); // Clear if anything prefilled
        input.sendKeys(recordTypeName); // Enter the value
        pause(1000); // Optional wait
        ExtentReportListener.getExtentTest().pass("Entered '" + recordTypeName + "' into Record Type input field");
    }

    public void clickAddRecordTypeLink() {
        WebElement link = driver.findElement(addRecordTypeLink);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", link); // Scroll into view
        pause(1000); // Optional wait after scroll
        link.click();
        pause(3000); // Wait for navigation
    }

    public void clickExportControlUnderRecordTypes() {
        waitForPresence(exportControlLink);
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000);
        link.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlContains("record-types"));

        // Assertion inside method (optional)
        Assert.assertTrue(driver.getCurrentUrl().contains("record-types"), "Export Control page did not load properly");
    }
}