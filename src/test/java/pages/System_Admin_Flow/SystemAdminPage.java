package pages.System_Admin_Flow;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class SystemAdminPage extends BasePage {

    public final WebDriverWait wait;

    public SystemAdminPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //locator

    // First Record Number link in the Action Required grid
    By firstRecordNumberLink = By.xpath("(//table[contains(@class,'item-grid')]//tbody" + "//td[@data-column='_exportControlNumber']//a)[1]");
    By approveButton = By.xpath("//button[@type='button' and @aria-label='Approve']");
    By commentsButton = By.xpath("//span[normalize-space()='Comments']/parent::div");
    By commentsEditor = By.xpath("//div[contains(@class,'fr-element') and contains(@class,'fr-view') and @contenteditable='true']");
    // Comments modal – "Comment" button
    By commentModalButton = By.xpath("//button[contains(@class,'comment-btn') and normalize-space()='Comment']");
    // User dropdown (Mohan C)
    By userDropdown = By.xpath("//div[contains(@class,'current-user-name')]");
    // Logout link
    By logoutLink = By.xpath("//a[span[normalize-space()='Logout']]");
    By recordNumberValue = By.xpath("//dt[normalize-space()='Record #:']/following-sibling::dd");
    By valueFieldInput = By.xpath("//label[normalize-space()='Record Number']/following-sibling::input[@aria-label='Value']");
    By yourNameDiv = By.xpath("//div[contains(@class,'fr-element') and normalize-space()='Your Name']");
    By genderDiv = By.xpath("//div[contains(@class,'fr-element') and normalize-space()='Gender']");
    By personnelExclusionValue = By.xpath("//dd[contains(@class,'_dark-text') and normalize-space()='Personnel Exclusion']");
    By commentsLabel = By.xpath("//span[contains(@class,'_font-size-base') and normalize-space()='Comments']");

    By statusUnderReviewValue = By.xpath("//dt[normalize-space()='Status:']/following-sibling::dd[@title='Under Review' and normalize-space()='Under Review']");
    By notesSectionTitle = By.xpath("//span[contains(@class,'toggleable-section-title') and normalize-space()='Notes']");
    By commentButton = By.xpath("//button[contains(@class,'comment-btn') and normalize-space()='Comment']");

    //method
    public boolean isCommentButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(commentButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCommentsLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(commentsLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPersonnelExclusionDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(personnelExclusionValue));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGenderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(genderDiv));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isYourNameDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(yourNameDiv));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNotesSectionTitleDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(notesSectionTitle)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStatusUnderReviewDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(statusUnderReviewValue)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Record Number link in results grid – dynamic by record number text
    public By recordNumberLink(String recordNumber) {
        return By.xpath("//table[contains(@class,'item-grid')]//tbody//tr" + "//td[@data-column='_exportControlNumber']" + "//a[span[normalize-space()='" + recordNumber + "']]");
    }

    public void clickRecordNumber(String recordNumber) {

        By locator = recordNumberLink(recordNumber);

        WebElement link = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        // Wait for clickability AFTER scroll
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            link.click();
        } catch (Exception e) {
            // Fallback – JS click for React grids
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", link);
        }

        pause(1000);
    }

    public void enterValueField(String record) {
//        WebElement input = driver.findElement(valueFieldInput);
//
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(valueFieldInput));
//
//        input.clear();
//        input.sendKeys(value);

        waitForPresence(valueFieldInput);
        type(valueFieldInput, record);

        pause(2000);   // follow your standard pause pattern
    }

    public String getRecordNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) {   // small retry for stale element
            try {
                WebElement record = wait.until(
                        ExpectedConditions.refreshed(
                                ExpectedConditions.visibilityOfElementLocated(recordNumberValue)
                        )
                );

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", record);

                pause(500);

                String text = record.getText().trim();
                if (!text.isEmpty()) {
                    return text;
                }
            } catch (StaleElementReferenceException e) {
                // retry with a fresh element
                if (i == 2) {
                    throw e;   // after 3 tries, let it fail
                }
            }
        }

        throw new RuntimeException("Record # not found or empty");
    }

    public void clickLogout() {
        // Step 1: Click dropdown
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(userDropdown)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
        dropdown.click();
        pause(500);

        // Step 2: Click Logout
        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink)
        );
        logout.click();
        pause(1000);
    }

    public void enterCommentInModal(String commentText) {
        WebElement editor = wait.until(
                ExpectedConditions.visibilityOfElementLocated(commentsEditor));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", editor);

        editor.click();
        // Clear any existing text
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editor.sendKeys(Keys.DELETE);

        // Type new comment
        editor.sendKeys(commentText);

        pause(1000);
    }

    public void clickCommentButtonOnModal() {
        WebElement commentBtn = wait.until(
                ExpectedConditions.elementToBeClickable(commentModalButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", commentBtn);

        commentBtn.click();

        pause(1000);
    }

    public void clickComments() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement comments = wait.until(
                ExpectedConditions.elementToBeClickable(commentsButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", comments);

        comments.click();

        pause(1000);
    }

    public void clickApproveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement approveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(approveButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", approveBtn);

        approveBtn.click();

        pause(1000);
    }

    public void clickFirstRecordNumber() {
        WebElement firstRecord = wait.until(
                ExpectedConditions.elementToBeClickable(firstRecordNumberLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", firstRecord);

        firstRecord.click();

        pause(1000);
    }
}