package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MenuFlow extends BasePage {

    public MenuFlow(WebDriver driver) {
        super(driver);
    }

    // ************************************** Locators ********************************************************************

    By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");
    By searchLink = By.xpath("//a[@href='/export-control/search' and contains(@class,'label')]");

    // Buttons
    By searchButton = By.xpath("//button[normalize-space()='Search']");

    // Review date
    By recordNumberSearchGridLink = By.xpath("//table[contains(@class,'item-grid')]//tbody/tr[1]//td[@data-column='_exportControlNumber']//a");
    By searchBreadcrumb = By.xpath("//div[contains(@class,'simple-bread-crumbs')]//span[contains(@class,'crumb') and contains(@class,'_font-bold') and normalize-space()='Search']");

    // ************************************** Functions ********************************************************************

    public boolean isSearchBreadcrumbDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement breadcrumb = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchBreadcrumb)
            );
            return breadcrumb.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRecordNumberFromSearchGrid() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(recordNumberSearchGridLink));

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        // Optional highlight
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.outline='3px solid red';", link);
        } catch (StaleElementReferenceException ignored) {}

        pause(800);

        // Click safely
        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        //  DO NOT remove highlight after click → page navigates → element becomes stale
        // So we wrap it in a try-catch and ignore any error.
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.outline='';", link);
        } catch (Exception ignored) {}

        // Small buffer only for stability
        pause(1000);
    }

    public void clickSearchButton() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickSearchLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(searchLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(1000);
    }

    public void dismissAnyOpenReactSelectMenus() {
        List<WebElement> menus = driver.findElements(
                By.cssSelector("ul[id^='react-select-'][id$='-listbox'], div[id^='react-select-'][id$='-listbox']")
        );
        if (!menus.isEmpty()) {
            try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
            try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(d -> d.findElements(By.cssSelector("ul[id^='react-select-'][id$='-listbox'], div[id^='react-select-'][id$='-listbox']")).isEmpty());
        }
    }

    public void clickExportControlLink() {
        dismissAnyOpenReactSelectMenus(); // <— add this

        waitForPresence(exportControlLink);
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(link));
        link.click();
        pause(1000);
    }
}