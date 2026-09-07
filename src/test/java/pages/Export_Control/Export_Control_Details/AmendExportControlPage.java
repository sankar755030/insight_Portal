package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AmendExportControlPage extends BasePage {
    private final WebDriverWait wait;

    public AmendExportControlPage(WebDriver driver)
    {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators
    By initiateAmendmentButton = By.xpath("//button[contains(@class,'_amendmentBtn') and normalize-space()='Initiate Amendment']");
    By amendmentOkButton = By.xpath("//button[contains(@class,'_okButton') and normalize-space()='OK']");
    By saveButton = By.xpath("//button[@aria-label='Save' and contains(@class,'-positive')]");
    By submitButton = By.xpath("//button[@aria-label='Submit' and contains(@class,'-positive')]");
    By yourNameInput = By.id("dynamic-form-field-input-74877-TextBox1");
    By genderMaleRadio = By.xpath("//div[@id='dynamic-form-field-input-74878-RadioButtonList2']" + "//input[@type='radio' and @value='Male']");
    By amendmentAMD1Tab = By.xpath("//div[contains(@class,'_transactionsTabItem')]" + "[.//div[contains(@class,'name') and normalize-space()='Amendment (AMD1)']]");
    By saveButton01 = By.xpath("//button[@aria-label='Save' and contains(@class,'-positive')]");
    By checkboxDraftActions = By.xpath("//span[text()='I have carefully reviewed this record and confirm my sign off']/../input");

    //Actions
    public void clickSaveButton() {

        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton01)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", save
        );

        save.click();

        pause(800); // your standard pause
    }

    public void clickAmendmentAMD1Tab() {

        WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(amendmentAMD1Tab)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", tab
        );

        tab.click();
        pause(800);
    }

    public void enterYourName(String name) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(yourNameInput));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        input.clear();
        input.sendKeys(name);
        pause(500);
    }

    public void selectGenderMale() {
        WebElement male = wait.until(ExpectedConditions.elementToBeClickable(genderMaleRadio));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", male);
        male.click();
        pause(500);
    }

    public void clickSubmit() {

        waitForPresence(submitButton);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                submit
        );

        submit.click();
        pause(800);
    }

    public void clickSave() {

        waitForPresence(saveButton);
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                save
        );

        save.click();
        pause(800);
    }

    public void clickAmendmentOkButton() {

        WebElement okBtn = wait.until(
                ExpectedConditions.elementToBeClickable(amendmentOkButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", okBtn
        );

        okBtn.click();

        pause(800); // your standard pause
    }

    public void clickInitiateAmendment() {

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(initiateAmendmentButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                btn
        );

        btn.click();

        pause(1000); // your standard pause
    }

    public void ClickDraftActionsCheckbox()
    {
        waitForPresence(checkboxDraftActions);
        click(checkboxDraftActions);
        pause(2000);
    }
}