package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePage {
    WebDriver driver;
    WebDriverWait wait;
    By contentTitle = By.id("content-title");
    By publishButton = By.id("rte-button-publish");

    public CreatePage(WebDriver driver){
        this.driver = driver;
    }

    public void enterContentTitle(String title){
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contentTitle)).sendKeys(title);
        System.out.println("Page title entered - " + title);
    }

    public void publish()
    {
        driver.findElement(publishButton).click();
        System.out.println("Clicked on Publish button");
    }

}
