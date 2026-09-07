package pages.Administration.Status_Management;


import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StatusManagement_ExportControlPage extends BasePage{

    public StatusManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    By statusManagementLink = By.xpath("//a[.//span[text()='Status Management']]");
    By statusManagementParent = By.xpath("//a[.//span[text()='Status Management']]");
    By exportControlSubmenu = By.xpath("//a[text()='Export Control'][@href='/administration/status-management']");
    By addStatusButton = By.xpath("//button[text()='Add Status' and contains(@class,'-primary')]");
    By statusNameInput = By.xpath("//div[contains(@class,'modal-content-wrapper')]//input[@type='text']");
    By cancelButton = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[normalize-space()='Cancel']");
    By addButton = By.xpath("//button[text()='Add' and contains(@class,'-primary')]");
    By statusNameEditInput = By.xpath("//label[.//span[text()='Status Name']]//input[@type='text']");
    By saveButton = By.xpath("//button[text()='Save' and contains(@class,'-primary')]");
    By activeDropdownArrow = By.xpath("//div[contains(@class,'_indicatorsContainer_') and contains(@class,'css-1wy0on6')]");
    By optionNo = By.xpath("//div[contains(@class,'menuPortal')]//div[text()='No']");
    By statusManagementHeader = By.xpath("//strong[normalize-space()='Status Management']");
    By addStatusModalHeader = By.xpath("//header[contains(@class,'html-preview-modal-header') and normalize-space()='Add Status']");
    By editStatusModalHeader = By.xpath("//header[contains(@class,'html-preview-modal-header') and normalize-space()='Edit Status']");
    By searchByNameInput = By.xpath("//input[@placeholder='Search by Name']");
    By searchButton      = By.xpath("//button[normalize-space()='Search']");
    By loadMoreButton    = By.xpath("//tr[contains(@class,'load-more-row')]//button[normalize-space()='Load more']");
    By clearSelectionsButton = By.xpath("//button[contains(@class,'_left-margin') and normalize-space()='Clear Selections']");
    By searchByNameInput01 = By.xpath("//input[@placeholder='Search by Name']");
    By statusManagementHeader01 = By.xpath("//strong[@class='section-title-item' and normalize-space()='Status Management']");
    By addStatusHeader = By.xpath("//header[normalize-space()='Add Status']");
    By statusNameLabel = By.xpath("//span[normalize-space()='Status Name']");
    By activeLabel = By.xpath("//span[normalize-space()='Active']");

    //Actions

    public boolean isActiveLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(activeLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStatusNameLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(statusNameLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddStatusHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addStatusHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStatusManagementPageDisplayed01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(statusManagementHeader01));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchByNameCleared() {
        WebElement input = driver.findElement(searchByNameInput01);
        String value = input.getAttribute("value");
        return value == null || value.trim().isEmpty();
    }

    public void clickClearSelectionsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionsButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", button
        );

        button.click();
        pause(1000);
    }

    public void searchStatusByName(String statusName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchByNameInput)
        );
        input.clear();
        input.sendKeys(statusName);

        driver.findElement(searchButton).click();
        pause(1000); // small wait for grid refresh
    }

    public boolean isEditStatusModalDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(editStatusModalHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAddStatusModalDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(addStatusModalHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isStatusManagementPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(statusManagementHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void selectActiveAsNo() {
        WebElement dropdownArrow = driver.findElement(activeDropdownArrow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
        dropdownArrow.click(); // Open the dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noOption = wait.until(ExpectedConditions.elementToBeClickable(optionNo));
        noOption.click(); // Select "No"

        pause(1000);
    }

    public void enterStatusName(String value) {
        WebElement input = driver.findElement(statusNameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusNameInput));

        input.clear();
        input.sendKeys(value);

        pause(1000);
    }

    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        pause(1000);
    }

    public void appendToStatusName(String textToAppend) {
        WebElement input = driver.findElement(statusNameEditInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(statusNameEditInput));

        input.sendKeys(textToAppend); // append only — no clear

        pause(1000);
    }

    public void clickEditButtonForStatus(String statusName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1) Filter the grid to the target status
        searchStatusByName(statusName);

        // 2) Row where Name column contains the status text
        String rowXpath = String.format(
                "//tbody//tr[contains(@class,'item-grid-tr')" +
                        " and .//td[@data-column='name']" +
                        "//*[contains(normalize-space(),'%s')]]",
                statusName
        );

        // 3) Wait for the row to be visible
        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath))
        );

        // 4) Inside that row, find the Edit button
        WebElement editButton = row.findElement(
                By.xpath(".//button[@type='button' and .//i[contains(@class,'fi-edit')]]")
                // or: ".//button[@type='button' and .//div[@aria-label='Edit']]"
        );

        // 5) Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                editButton
        );

        // 6) Wait until clickable, then click
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();

        pause(1000);
    }

    public void clickDeleteButtonForStatus(String statusName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1) Filter grid, so only matching rows show up
        searchStatusByName(statusName);

        // 2) Build row xpath for this status
        String rowXpath = String.format(
                "//tbody//tr[.//td[@data-column='name']" +
                        "//*[normalize-space()='%s' or normalize-space(@data-value)='%s']]",
                statusName, statusName
        );

        // 3) If there is pagination via 'Load more', click until row appears or no more load-more
        while (true) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));
                break; // row found, exit loop
            } catch (TimeoutException e) {
                // try clicking "Load more" if available; otherwise rethrow
                List<WebElement> loadMoreBtns = driver.findElements(loadMoreButton);
                if (loadMoreBtns.isEmpty()) {
                    throw e; // no more pages and row still not there
                }
                loadMoreBtns.get(0).click();
                pause(1000);
            }
        }

        // 4) Row is visible, now get the delete button inside that row
        WebElement row = driver.findElement(By.xpath(rowXpath));
        WebElement deleteButton = row.findElement(
                By.xpath(".//button[@aria-label='Delete item']")
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                deleteButton
        );

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        pause(1000);

        // 5) Handle JS alert if any
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            alertWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss(); // or accept() based on your flow
            pause(1000);
        } catch (TimeoutException e) {
            // No JS alert – likely a custom modal, handle separately if needed
        }
    }

    public void clickAddButton() {
        WebElement button = driver.findElement(addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        pause(1000);
    }

    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();

        pause(1000);
    }

    public void clickAddStatusButton() {
        WebElement button = driver.findElement(addStatusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addStatusButton)).click();

        ExtentReportListener.getExtentTest().pass("Clicked 'Add Status' button successfully");
        pause(1000);
    }

    public void clickStatusManagementExportControl() {
        WebElement parentMenu = driver.findElement(statusManagementParent);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", parentMenu);
        parentMenu.click(); // Expand the menu

        waitForPresence(exportControlSubmenu);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement submenu = wait.until(ExpectedConditions.elementToBeClickable(exportControlSubmenu));
        submenu.click();
        pause(1000);
    }

    public void clickStatusManagementLink() {
        WebElement link = driver.findElement(statusManagementLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(statusManagementLink)).click();

        pause(1000);
    }
}