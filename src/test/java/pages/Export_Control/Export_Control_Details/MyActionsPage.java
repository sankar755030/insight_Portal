package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyActionsPage extends BasePage {
    private final WebDriverWait wait;

    public MyActionsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // **************************** Locators *******************************************

    // Already have:
    By openDropdownPanel = By.xpath("//div[contains(@class,'menu') and contains(@class,'select')]");
    By submitterInput = By.xpath("//label[normalize-space()='Submitter']/following::input[contains(@id,'react-select')][1]");
    String submitterOptionXpath = "//div[contains(@id,'react-select') and contains(@id,'listbox')]" + "//div[contains(@class,'option') and contains(normalize-space(),'OPTION_TEXT')]";
    By reviewerDropdown = By.xpath("//label[normalize-space()='Reviewer']/following::div[contains(@class,'select-control')][1]");
    By reviewerInput = By.xpath("//label[normalize-space()='Reviewer']/following::input[contains(@id,'react-select')][1]");
    String reviewerOptionXpath = "//div[contains(@id,'react-select') and contains(@id,'listbox')]" + "//div[contains(@class,'option') and contains(normalize-space(),'OPTION_TEXT')]";
    By actionRequiredLink = By.xpath("//div[contains(@class,'export-control-nav-block')]//a" + "[contains(@class,'label') and normalize-space()='Action Required']");
    By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[1]");

    // buttons
    By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    By firstRecordNumberLink = By.xpath("//table[contains(@class,'item-grid')]//tbody/tr[1]" + "//td[@data-column='_exportControlNumber']//a");
    By clearSelectionButton = By.xpath("//button[@type='button' and contains(.,'Clear Selections')]");

    By actionRequiredBreadcrumb = By.xpath("//span[contains(@class,'crumb') and contains(@class,'_font-bold') and normalize-space()='Action Required']");
    By reviewerLabel = By.xpath("//label[normalize-space()='Reviewer']");
    By recordNumberLabel = By.xpath("//label[normalize-space()='Record Number']");
    By submitterLabel = By.xpath("//label[normalize-space()='Submitter']");
    By piNameValue = By.xpath("//dt[normalize-space()='PI:']/following-sibling::dd[@title='Chandra, Mohan' and normalize-space()='Chandra, Mohan']");

    // ****************** Functions ******************************************************

    public boolean isPINameDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(piNameValue)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReviewerLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(reviewerLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRecordNumberLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(recordNumberLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean VerifyRecordIsDisplayed(String recordNum) {
        By recordNumber = By.xpath("//dd[text()='" + recordNum + "']");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(recordNumber)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmitterLabelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(submitterLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isActionRequiredBreadcrumbDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement breadcrumb = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(actionRequiredBreadcrumb)
            );
            return breadcrumb.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectSubmitter(String searchText, String optionToSelect) {

        // We assume clickSubmitterFilter() already called from test

        // Step 1: Type submitter code
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(submitterInput));
        input.click();
        input.clear();
        input.sendKeys(searchText);
        pause(1000);

        // Step 2: Try to click the suggestion from dropdown
        By dynamicOption = By.xpath(submitterOptionXpath.replace("OPTION_TEXT", optionToSelect));

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement option = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(dynamicOption));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", option);
            option.click();
        } catch (TimeoutException e) {
            // Fallback for "traditional" behaviour => just confirm with ENTER
            input.sendKeys(Keys.ENTER);
        }

        pause(1000);
    }

    public void clickSubmitterFilter() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(openDropdownPanel));
        } catch (TimeoutException e) {
            // ignore if no open dropdown
        }

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(submitterInput));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        try {
            input.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
        }

        pause(500);
    }

    public void selectReviewer(String searchText, String optionToSelect) {

        // Step 1: Open the dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(reviewerDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
        dropdown.click();
        pause(500);

        // Step 2: Type reviewer code
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(reviewerInput));
        input.click();
        input.clear();
        input.sendKeys(searchText);
        pause(1000);

        // Step 3: Try to click the suggestion from the popup
        By dynamicOption = By.xpath(reviewerOptionXpath.replace("OPTION_TEXT", optionToSelect));

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement option = shortWait.until(ExpectedConditions
                    .visibilityOfElementLocated(dynamicOption));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", option);
            option.click();
        } catch (TimeoutException e) {
            // Fallback for “traditional” behaviour:
            // listbox not found / auto-select on enter -> just hit ENTER
            input.sendKeys(Keys.ENTER);
        }

        // Optional: click somewhere to blur the field if needed
        // driver.findElement(By.xpath("//label[normalize-space()='Record Number']")).click();

        pause(1000);
    }

    public void enterRecordNumber(String recordNo) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(recordNumberInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(recordNo);
        pause(500);
    }

    public void clickClearSelections() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(2000);
    }

    public void clickActionRequiredLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionRequiredLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();

        pause(1500);   // after-click pause
    }

    public void clickFirstRecordNumberLink() {
        WebElement link = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(firstRecordNumberLink));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        pause(1500);
    }
}