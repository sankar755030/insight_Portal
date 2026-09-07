package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddChecklistFlowPage extends BasePage {


    private final WebDriverWait wait;

    public AddChecklistFlowPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators
    By workflowManagementMenu = By.xpath("//div[@id='left-sidebar']//a[@href='/administration/workflow-management']" + "/span[normalize-space()='Workflow Management']");
    By exportControlWorkflowsLink = By.xpath("//button[normalize-space()='Export Control']" + "/ancestor::div[contains(@class,'menu-item-holder')][1]" + "//a[contains(@href,'/administration/workflow-management') and contains(@href,'workflows-export-control') " + " and normalize-space(text())='Workflows']");
    By personnelWorkflowLink = By.xpath("//td[@data-column='name' and @data-value='Test1']//a[normalize-space()='Test1']");
    By addNewWorkflowVersionButton = By.xpath("//div[contains(@class,'top-bar')][.//header[normalize-space()='Versions']]" + "//button[contains(@class,'-primary')][normalize-space()='Add new']");
    By editableVersionDescription = By.xpath("//div[contains(@class,'_versionsList')]" + "//textarea[contains(@class,'_descriptionField') and not(@disabled)]");
    By saveVersionButton = By.xpath("//div[contains(@class,'_versionItemHeader')]" + "//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space()='Save']");
    By latestVersionLink = By.xpath("(//div[contains(@class,'_versionItemHeader')]//a)[1]");
    By draftNodeBox = By.xpath("//p[normalize-space()='Draft (draft1)']/ancestor::div[contains(@class,'_workflowNode')]");
    By actionsTab = By.xpath("//button[normalize-space()='Actions']");
    By submitActionButton = By.xpath("//div[@class='_entityListContainer_1q559_1']//button[normalize-space()='Submit']");
    By checklistsToggle = By.xpath("//button[normalize-space()='Checklists']");
    By updateButton = By.xpath("//button[normalize-space()='Update']");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By workflowsHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(),'Workflows')]");
    By personnelSpan = By.xpath("//span[normalize-space()='Test1']");
    By versionsHeader = By.xpath("//header[contains(@class,'_font-size-medium') and normalize-space()='Versions']");
    By saveButton01 = By.xpath("//button[@type='button' and contains(@class,'-primary') and normalize-space()='Save']");
    By chiefApproverHeader = By.xpath("//p[contains(@class,'_sidebarHeader_') and normalize-space()='Cheif Approval']");
    By draftSidebarHeader = By.xpath("//p[contains(@class,'_sidebarHeader') and text()='Draft']");
    By personnelExclusionLink = By.xpath("//a[normalize-space()='Personnal Exclusion']");
    By personnelExclusionLabel = By.xpath("//span[normalize-space()='Personnal Exclusion']");

    //Actions
    public boolean isPersonnelExclusionLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(personnelExclusionLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPersonnelExclusionWorkflow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(personnelExclusionLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", link);

        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(2000);
    }

    public boolean isDraftSidebarHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(draftSidebarHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChiefApproverHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(chiefApproverHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaveButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton01));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVersionsHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(versionsHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPersonnelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(personnelSpan));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWorkflowsHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(workflowsHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSaveButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickUpdateButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(updateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickChecklistsToggle() {
        WebElement toggle = wait.until(
                ExpectedConditions.elementToBeClickable(checklistsToggle)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", toggle);

        toggle.click();

        pause(1000);
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

    public void clickActionsTab() {
        WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(actionsTab)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", tab);

        tab.click();

        pause(1000);
    }

    public void clickDraftNode() {
        WebElement node = wait.until(ExpectedConditions.elementToBeClickable(draftNodeBox));

        // Scroll to the Draft box
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", node);

        node.click();

        pause(1000);
    }

    public void clickLatestVersionLink() {

        WebElement versionLink = wait.until(
                ExpectedConditions.elementToBeClickable(latestVersionLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", versionLink);

        try {
            versionLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", versionLink);
        }

        pause(1000);
    }

    public void clickSaveVersion() {
        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(saveVersionButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", saveBtn);

        try {
            saveBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        pause(1000);
    }

    public void enterDescriptionForLatestVersion(String descriptionText) {

        WebElement descriptionArea = wait.until(
                ExpectedConditions.visibilityOfElementLocated(editableVersionDescription));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", descriptionArea);

        descriptionArea.clear();
        descriptionArea.sendKeys(descriptionText);

        pause(1000);
    }

    public void clickAddNewWorkflowVersion() {

        WebElement addNewBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addNewWorkflowVersionButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", addNewBtn);

        try {
            addNewBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewBtn);
        }

        pause(1000);
    }

    public void clickPersonnelWorkflow() {

        WebElement personnel = wait.until(
                ExpectedConditions.elementToBeClickable(personnelWorkflowLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", personnel);

        try {
            personnel.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personnel);
        }

        pause(1000);
    }

    public void openExportControlWorkflows() {

        // 1) Click Administration → Workflow Management from sidebar
        WebElement workflowMgmt = wait.until(
                ExpectedConditions.elementToBeClickable(workflowManagementMenu));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", workflowMgmt);
        workflowMgmt.click();
        pause(800);

        WebElement workflows = wait.until(
                ExpectedConditions.visibilityOfElementLocated(exportControlWorkflowsLink));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", workflows);

        try {
            workflows.click();
        } catch (Exception e) {
            // Fallback to JS click if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", workflows);
        }

        pause(1000);
    }
}