package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotesPage extends BasePage {

    public NotesPage(WebDriver driver) {

        super(driver);
    }

    //Locators
    By notesSectionHeader = By.xpath("//header[.//span[normalize-space()='Notes']]");
    By notesTextArea = By.xpath("//div[contains(@class,'toggleable-section')]" + "[.//span[@class='toggleable-section-title' and normalize-space()='Notes']]" + "//textarea");
    By notesAddButton = By.xpath("//div[contains(@class,'toggleable-section')]" + "[.//span[@class='toggleable-section-title' and normalize-space()='Notes']]" + "//div[contains(@class,'note-form-buttons')]//button[normalize-space()='Add']");
    By addNoteButton = By.xpath("//button[contains(@class,'button') and .//div[@aria-label='Add'] and normalize-space()='Add Note']");
    By notesToggleHeader = By.xpath("//header[.//span[@class='toggleable-section-title' and normalize-space()='Notes']]");
    By notesSectionTitle = By.xpath("//span[contains(@class,'toggleable-section-title') and normalize-space()='Notes']");

    //Actions
    public boolean isNotesSectionDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(notesSectionTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNotesSection01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement header = wait.until(
                ExpectedConditions.elementToBeClickable(notesToggleHeader)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", header);

        header.click();

        pause(800);
    }

    public void clickAddNoteButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement addNoteBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addNoteButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addNoteBtn);

        wait.until(ExpectedConditions.elementToBeClickable(addNoteBtn));

        addNoteBtn.click();
        pause(1000);
    }

    public void enterNotesAndClickAdd(String noteText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1) Get textarea
        WebElement noteBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(notesTextArea)
        );

        // 2) Scroll textarea into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", noteBox);

        // 3) Click + type text
        noteBox.click();
        noteBox.clear();
        noteBox.sendKeys(noteText);
        pause(800);

        // 4) Find Add button (no clickable wait)
        WebElement addBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(notesAddButton)
        );

        // 5) Scroll Add into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);
        pause(500);

        // 6) Try normal click first, then JS click as fallback
        try {
            addBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", addBtn);
        }

        pause(1500);
    }

    public void clickNotesSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement notesHeader = wait.until(
                ExpectedConditions.elementToBeClickable(notesSectionHeader)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", notesHeader);

        notesHeader.click();

        pause(1500);   // small pause as per your pattern
    }
}