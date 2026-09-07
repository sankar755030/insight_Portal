package pages.Administration.People_Management;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class PeopleManagement_ExportControlPage extends BasePage {

    // Constructor
    public PeopleManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By linkPeopleManagement = By.xpath("//span[text()='People Management']/..");
    By linkExportControl = By.xpath("(//a[@href='/administration/people-management'])[2]");

    By linkAddPeopleType = By.xpath("//a[text()='Add People Type']");
    By linkAddRole = By.xpath("//a[text()='Add Role']");

    By inputSearchPeopleType = By.xpath("//input[@placeholder='Search by People Type']");
    By buttonSearch = By.xpath("//button[text()='Search']");

    // Add People Type Locators
    By inputModuleName = By.xpath("//input[@id='moduleValue']");
    By selectModuleName = By.xpath("//div[text()='Export Control']");
    By inputPeopleType = By.xpath("//input[@id='name']");
    By buttonCreate = By.xpath("//button[text()='Create']");

    // Add New Role Locators
    By inputSelectPeopleType = By.id("peopleTypeName");
    By inputRoleName = By.id("roleName");

    //Edit People Type Locators
    By buttonEditPeopleType = By.xpath("(//tr)[2]/td[4]//button");
    By inputEditPeopleType = By.xpath("//span[text()='People Type']/../input");
    By dropdownStatus = By.xpath("//input[@role='combobox']");
    By buttonSave = By.xpath("//button[text()='Save']");
    By buttonCancel = By.xpath("//button[text()='Cancel']");

    // Edit Roles Locators
    By linkRoles = By.xpath("(//tr)[2]/td[3]/a");
    By buttonEditRole = By.xpath("//div[text()='Role']/following::tbody/tr[2]/td[3]//button");
    By buttonEditDefaultRole = By.xpath("//div[text()='Role']/following::tbody/tr[1]/td[3]//button");
    By inputEditRoleName = By.xpath("//td[@data-column='roleName']/input");
    By buttonSaveRoleEdit = By.xpath("//button[@aria-label='Save item']");

    // Functions
    public void NavigateToPeopleManagementExportControlPage() {

        //Click on People Management navigation link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkPeopleManagement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();

        //waitForPresence(linkPeopleManagement);
        //click(linkPeopleManagement);
        pause(2000);

        //Click on Export Control under People Management
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }

    public void ClickAddPeopleType() {
        waitForElement(linkAddPeopleType);
        click(linkAddPeopleType);
        pause(2000);
    }

    public boolean AddPeopleTypeAndVerifyInThePeopleManagementList(String typeName) {
        boolean result = false;

        // Click on Add People Type link
        ClickAddPeopleType();

        // Select Module Name
        click(inputModuleName);
        click(selectModuleName);

        // Enter People Type name
        type(inputPeopleType, typeName);

        // Click Create button
        click(buttonCreate);
        pause(2000);

        if(driver.findElement(By.xpath("//div[text()='People Type added successfully.']")).isDisplayed())
        {
            // Search for the created People Type
            type(inputSearchPeopleType, typeName);
            click(buttonSearch);
            pause(2000);

            String name = driver.findElement(By.xpath("//td[@data-column='name']")).getAttribute("data-value");
            String isActive = driver.findElement(By.xpath("//td[@data-column='isActive']")).getText();
            String role = driver.findElement(By.xpath("//td[@data-column='mappedRoles']/a")).getText();

            if(Objects.equals(name, typeName) && Objects.equals(isActive, "Yes") && Objects.equals(role, "General"))
            {
                result = true;
            }
        }

        return result;
    }

    public String GetRoleName() {
        return driver.findElement(By.xpath("//td[@data-column='mappedRoles']/a")).getText();
    }

    public boolean AddNewRoleToPeopleTypeAndVerifyInList(String typeName, String roleName) {
        boolean result = false;

        // Click Add Role link
        pause(5000);
        waitForPresence(linkAddRole);
        click(linkAddRole);
        pause(2000);

        // Select People Type
        type(inputSelectPeopleType, typeName);
        driver.findElement(By.xpath("//div[text()='"+ typeName + "']")).click();
        pause(2000);

        // Enter Role name
        type(inputRoleName, roleName);

        //Click Create button
        click(buttonCreate);
        pause(2000);

        if(driver.findElement(By.xpath("//div[text()='Role added successfully.']")).isDisplayed())
        {
            // Search for the created People Type
            type(inputSearchPeopleType, typeName);
            click(buttonSearch);
            pause(2000);

            String role = driver.findElement(By.xpath("//td[@data-column='mappedRoles']/a")).getText();

            if(Objects.equals(role, "General, " + roleName))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean EditPeopleTypeNameAndVerifyInList(String newTypeName) {
        boolean result = false;

        click(buttonEditPeopleType);
        pause(2000);

        if(driver.findElement(By.xpath("//header[text()='Edit People Type']")).isDisplayed())
        {
            driver.findElement(inputEditPeopleType).clear();
            type(inputEditPeopleType, newTypeName);

            click(buttonSave);
            pause(2000);
        }

        if(driver.findElement(By.xpath("//div[text()='People Type updated successfully']")).isDisplayed())
        {
            // Search for the created People Type
            driver.findElement(inputSearchPeopleType).clear();
            type(inputSearchPeopleType, newTypeName);
            click(buttonSearch);
            pause(2000);

            String name = driver.findElement(By.xpath("//td[@data-column='name']")).getAttribute("data-value");

            if(Objects.equals(name, newTypeName))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean DeactivatePeopleTypeAndVerifyStatusInList() {
        boolean result = false;

        click(buttonEditPeopleType);
        pause(2000);

        if(driver.findElement(By.xpath("//header[text()='Edit People Type']")).isDisplayed())
        {
            click(dropdownStatus);
            driver.findElement(By.xpath("//div[text()='No']")).click();

            click(buttonSave);
            pause(2000);
        }

        if(driver.findElement(By.xpath("//div[text()='People Type updated successfully']")).isDisplayed())
        {
            String isActive = driver.findElement(By.xpath("//td[@data-column='isActive']")).getText();

            if(Objects.equals(isActive, "No"))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean DeactivateAssociatedRoleAndVerifyInList() {
        boolean result = false;

        click(linkRoles);
        pause(2000);

        if(driver.findElement(By.xpath("//header[text()='Edit Roles']")).isDisplayed())
        {
            click(buttonEditRole);
            pause(2000);

            click(dropdownStatus);
            driver.findElement(By.xpath("//div[text()='No']")).click();
            pause(2000);

            click(buttonSaveRoleEdit);
            pause(2000);

            if(driver.findElement(By.xpath("//div[text()='Role updated successfully.']")).isDisplayed())
            {
                result = true;
            }
        }
        return result;
    }

    public boolean TryToDeactivateAllRolesAndVerifyCorrectErrorMessageIsDisplayed(String err) {
        boolean result = false;

        click(buttonEditDefaultRole);
        pause(2000);

        click(dropdownStatus);
        driver.findElement(By.xpath("//div[text()='No']")).click();
        pause(2000);

        click(buttonSaveRoleEdit);
        pause(2000);

        String errMsg = driver.findElement(By.xpath("//div[@role='alert']/div")).getText();
        if(Objects.equals(errMsg, err))
        {
            result = true;
        }
        return result;
    }

    public boolean EditRoleNameAndVerifyInList(String newRoleName) {
        boolean result = false;

        click(linkRoles);
        pause(2000);

        if(driver.findElement(By.xpath("//header[text()='Edit Roles']")).isDisplayed())
        {
            click(buttonEditRole);
            pause(2000);

            driver.findElement(inputEditRoleName).clear();
            type(inputEditRoleName, newRoleName);

            click(buttonSaveRoleEdit);
            pause(2000);
        }

        if(driver.findElement(By.xpath("//div[text()='Role updated successfully.']")).isDisplayed())
        {
            click(buttonCancel);
            pause(2000);

            String role = driver.findElement(By.xpath("//td[@data-column='mappedRoles']/a")).getText();

            if(Objects.equals(role, "General, " + newRoleName))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean VerifyIfExternalPeopleTypeExist(String typeName) {
        boolean result = false;

        // Search for the created People Type
        driver.findElement(inputSearchPeopleType).clear();
        type(inputSearchPeopleType, typeName);
        click(buttonSearch);
        pause(2000);

        String name = driver.findElement(By.xpath("//td[@data-column='name']")).getAttribute("data-value");

        //String role = driver.findElement(By.xpath("//td[@data-column='mappedRoles']/a")).getText();

        if(Objects.equals(name, typeName))
        {
            result = true;
        }
        return result;
    }

    public String GetStatusOfPeopleType() {
        return driver.findElement(By.xpath("//td[@data-column='isActive']")).getText();
    }

    public boolean ActivateRoleAndVerifyInList() {
        boolean result = false;

        click(linkRoles);
        pause(2000);

        if(driver.findElement(By.xpath("//header[text()='Edit Roles']")).isDisplayed())
        {
            click(buttonEditRole);
            pause(2000);

            click(dropdownStatus);
            driver.findElement(By.xpath("//div[text()='Yes']")).click();
            pause(2000);

            click(buttonSaveRoleEdit);
            pause(2000);

            if(driver.findElement(By.xpath("//div[text()='Role updated successfully.']")).isDisplayed())
            {
                result = true;
            }
        }
        return result;
    }
}