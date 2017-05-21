package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    By CreatePage = By.id("quick-create-page-button");
    By profile = By.id("user-menu-link");

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void createButton (){
        driver.findElement(CreatePage).click();
        System.out.println("Create Button clicked");
    }

    public void profileClick(){
        driver.findElement(profile).click();
        System.out.print("Clicked on Profile");
    }

    public void loginVerify(String loginName)
    {
        wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertEquals(driver.findElement(profile).getAttribute("title"),loginName);
        System.out.println("Logged in by " + loginName);
    }

    public void openPage (String pagetitle){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(pagetitle))).click();
        //driver.findElement(By.linkText(pagetitle)).click();
        System.out.println("Clicked on page " + pagetitle);
    }
}
