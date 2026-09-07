package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DisplayChecklistFlowPage extends BasePage {

    private final WebDriverWait wait;

    public DisplayChecklistFlowPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators

    By personnelExclusionRadio = By.xpath("//span[normalize-space()='Personnel Exclusion']/preceding-sibling::input[@type='radio']");
    By saveActionButton = By.xpath("//button[normalize-space()='Save']");
    By submitActionButton = By.xpath("//button[normalize-space()='Submit']");
    By nameInput = By.xpath("//div[contains(@class,'dynamic-form-field')]" + "[.//div[contains(@class,'fr-element') and normalize-space()='What is your Name?']]" + "//input[@type='text']");
    By selectPINameDisabledField = By.xpath("//span[normalize-space()='Select PI Name']");
    By personnelExclusionValue = By.xpath("//dd[@title='Personnel Exclusion' and text()='Personnel Exclusion']");

    //Actions
    public boolean isPersonnelExclusionDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(personnelExclusionValue));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPersonnelExclusionRadioDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(personnelExclusionRadio)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectPINameDisabledDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(selectPINameDisabledField)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterName(String nameValue) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(nameInput));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(nameValue);

        pause(500);
    }

    public void clickSubmitAction() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(submitActionButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickSaveAction() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(saveActionButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void selectPersonnelExclusion() {
        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(personnelExclusionRadio)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", radio);

        radio.click();

        pause(1000);
    }
}