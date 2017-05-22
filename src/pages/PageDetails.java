package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageDetails {
    WebDriver driver;
    WebDriverWait wait;
    By restrictionsIcon = By.id("content-metadata-page-restrictions");


    public PageDetails(WebDriver driver){
        this.driver = driver;
    }

    public void clickRestrictionsIcon(){
        wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(restrictionsIcon));
        driver.findElement(restrictionsIcon).click();
        System.out.println("Clicked on Restrictions icon");
    }

    public String pageUrl()
    {
        String url = driver.getCurrentUrl();
        return url;
    }

}
