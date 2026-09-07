package pages.Home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final WebDriverWait wait;

    // Locators
    By usernameField = By.xpath("//input[@id='input28']");
    By nextButton = By.xpath("//input[@value='Next']");
    By passwordField = By.xpath("//input[@name='credentials.passcode']");
    By verifyButton = By.xpath("//input[@value='Verify']");

    // ---------- SysAdmin login locators ----------
    By businessUserInput   = By.xpath("//input[@name='businessUser']");
    By sysAdminLoginButton = By.xpath("//button[normalize-space()='Login']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Actions
    public void LoginIntoApplication(String username, String password) {
        type(usernameField, username);
        click(nextButton);
        type(passwordField, password);
        click(verifyButton);
        pause(20000);
    }

    //---------- Actions: SysAdmin login (public?sys=sysadmin) ----------

    public void enterSysAdminBusinessUser(String value) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(businessUserInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(value);

        pause(1000);
    }

    public void clickSysAdminLoginButton() {
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(sysAdminLoginButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", loginBtn);

        loginBtn.click();

        pause(1000);
    }
}
