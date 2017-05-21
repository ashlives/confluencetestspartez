package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

public class RestrictionsPopup {
    WebDriver driver;
    WebDriverWait wait;
    By restrictionsPopup = By.className("aui-dialog2-header-main");
    By restrictionPopupHeader = By.cssSelector("h2.aui-dialog2-header-main");
    By applyButton = By.id("page-restrictions-dialog-save-button");
    By cancelButton = By.id("page-restrictions-dialog-close-button");
    By restrictionDropdownAct = By.className("page-restrictions-dialog-selector-container");
    By restrictionDropdown = By.id("page-restrictions-dialog-selector");

    public RestrictionsPopup(WebDriver driver){
        this.driver = driver;
    }

    public void epicPopupjourney(String restrictionType){
        wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(restrictionsPopup));
        driver.switchTo().activeElement();
        assertEquals(driver.findElement(restrictionPopupHeader).getText(), "Restrictions");
        System.out.println("Restriction Pop-up appeared");
        driver.switchTo().activeElement();
        driver.findElement(restrictionDropdownAct).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element = driver.findElement(restrictionDropdown);
        Select se = new Select(element);
        se.selectByValue(restrictionType);
    }

    public void noRestrictions(){
        this.epicPopupjourney("none");
        System.out.println("Editing Restricted selected");
        this.clickApply();
    }

    public void editingRestricted(){
        this.epicPopupjourney("edit");
        System.out.println("Editing Restricted selected");
        this.clickApply();
    }

    public void viewingEditingRestricted(){
        this.epicPopupjourney("viewedit");
        System.out.println("Viewing and Editing Restricted selected");
        this.clickApply();
    }

    public void clickApply()
    {
        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
        System.out.println("Clicked on Apply button");
    }

    public void clickCancel()
    {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        System.out.println("Clicked on Apply button");
    }
}
