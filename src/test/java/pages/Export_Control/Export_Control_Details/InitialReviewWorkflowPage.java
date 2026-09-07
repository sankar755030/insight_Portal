package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InitialReviewWorkflowPage extends BasePage {

    private final WebDriverWait wait;

    public InitialReviewWorkflowPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators
    By initialReviewLink = By.xpath("//div[@class='name' and normalize-space()='Initial Review (IR)']");
    By nameInput = By.xpath("//div[@data-name='TextBox1']//input[@type='text']");
    By enterNameDisabledField = By.xpath("//div[contains(@class,'fr-element') and contains(@class,'fr-disabled') and normalize-space()='Enter Name']");
    By selectGenderDisabledField = By.xpath("//div[contains(@class,'fr-element') and contains(@class,'fr-disabled') and normalize-space()='Select Gender']");
    By personnelExclusionValue = By.xpath("//dd[@title='Personnel Exclusion' and normalize-space()='Personnel Exclusion']");
    By actionRequiredLink = By.xpath("//a[@href='/export-control/action-required']//span[normalize-space()='Action Required']/parent::a");

    //Actions
    public void refreshCurrentPage() {
        driver.navigate().refresh();
        pause(3000); // allow page to reload completely
    }

    public boolean isActionRequiredLinkDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(actionRequiredLink)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPersonnelExclusionValueDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(personnelExclusionValue)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnterNameDisabledDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(enterNameDisabledField)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectGenderDisabledDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(selectGenderDisabledField)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public By genderOption(String genderText) {
        return By.xpath(
                "//div[@id='dynamic-form-field-input-74921-RadioButtonList1']" +
                        "//label[contains(@class,'option-label')]//span[normalize-space()='" + genderText + "']/preceding-sibling::input"
        );
    }

    public void enterName(String name) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(nameInput));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(name);

        pause(1000);
    }

    public void selectGender(String genderText) {
        By genderLocator = genderOption(genderText);
        WebElement radioInput = wait.until(ExpectedConditions.elementToBeClickable(genderLocator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", radioInput);

        try {
            radioInput.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioInput);
        }

        pause(1000);
    }

    public void selectGenderMale() {
        selectGender("Male");
    }

    public void clickInitialReview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement ir = wait.until(
                ExpectedConditions.elementToBeClickable(initialReviewLink)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", ir);

        ir.click();

        pause(800);
    }
}