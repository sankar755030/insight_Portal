package pages.Administration.Transaction_Types;

import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransactionTypes_ExportControlPage extends BasePage {

    public TransactionTypes_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    By addNewTransactionTypeLink = By.xpath("//a[@href='/administration/transaction-types/transaction-new' and normalize-space(text())='Add new']");
    By transactionTypeInput = By.xpath("//input[@id='name' and contains(@class,'text-input') and contains(@class,'default-input')]");
    By searchByNameInput = By.xpath("//input[@placeholder='Search by Name' and contains(@class,'text-input') and contains(@class,'default-input')]");
    By activeCheckbox = By.id("isActive");
    By transactionTypesLink = By.xpath("//a[@href='/administration/transaction-types']//span[normalize-space()='Transaction Types']");
    By cancelLink = By.xpath("//a[contains(@href,'/administration/transaction-types') and contains(text(),'Cancel')]");
    By transactionTypesHeader = By.xpath("//strong[normalize-space()='Transaction Types']");
    By transactionTypeBreadcrumb = By.xpath("//span[contains(@class,'crumb') and contains(@class,'_font-bold') and normalize-space()='Transaction Type']");

    //Actions
    public boolean isTransactionTypeCreatePageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTypeBreadcrumb));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isTransactionTypesPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTypesHeader));
            return true;
        } catch (
                TimeoutException e) {
            return false;
        }
    }

    public void clickCancel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelLink));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();

        pause(1000); // Your BasePage pause pattern
    }

    public void checkActiveCheckbox() {
        WebElement checkbox = driver.findElement(activeCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
        pause(1000);

        if (!checkbox.isSelected()) {
            checkbox.click();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");
        } else {
            ExtentReportListener.getExtentTest().info("'Active' checkbox was already checked");
        }

        pause(1000);
    }

    public void enterSearchByName(String name) {
        WebElement input = driver.findElement(searchByNameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000);
        input.clear();
        input.sendKeys(name);
        pause(1000);
        ExtentReportListener.getExtentTest().pass("Entered '" + name + "' into Search by Name input field");
    }

    public String generateUniqueTransactionTypeName() {
        return "Test " + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
    }

    public void enterTransactionType(String transactionName) {
        WebElement input = driver.findElement(transactionTypeInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000);
        input.clear();
        input.sendKeys(transactionName);
        pause(1000);
        ExtentReportListener.getExtentTest().pass("Entered '" + transactionName + "' into Transaction Type input field");
    }

    public void clickAddNewTransactionType() {
        WebElement addNewLink = driver.findElement(addNewTransactionTypeLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addNewLink);
        pause(1000);
        addNewLink.click();
        pause(1000);
    }

    public void clickTransactionTypesLink() {
        WebElement link = driver.findElement(transactionTypesLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000);
        link.click();
        pause(1000);
    }
}