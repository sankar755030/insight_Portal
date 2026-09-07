package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResponseToReviewPage extends BasePage {

    public final WebDriverWait wait;

    public ResponseToReviewPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locator
    By chiefApproverNode = By.xpath("//div[contains(@class,'_nodeHeader')]//p[contains(normalize-space(),'chiefapprover1')]");
    By activitiesTab = By.xpath("//button[normalize-space()='Activities']");
    By responseToReviewLink = By.xpath("//div[@id='left-sidebar']//a[contains(@class,'label')][span[normalize-space()='Response To Review']]");
    By submissionChecklistSection = By.xpath("//span[@class='toggleable-section-title' and text()='Submission Checklist']");

    //Method
    public boolean clickResponseToReviewIfPresent() {
        try {
            // Quick presence check (no wait)
            if (driver.findElements(responseToReviewLink).isEmpty()) {
                return false;
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(responseToReviewLink));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

            link.click();
            pause(1000);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void clickSubmissionChecklist() {
        WebElement section = driver.findElement(submissionChecklistSection);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", section);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submissionChecklistSection));

        section.click();

        pause(1000);
    }

    public void clickActivitiesTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(activitiesTab));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", tab);

        tab.click();

        pause(1000);
    }

    public void clickChiefApprover() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement node = wait.until(
                ExpectedConditions.elementToBeClickable(chiefApproverNode));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", node);

        node.click();

        pause(1000);
    }
}