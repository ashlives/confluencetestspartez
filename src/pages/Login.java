package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login{
    WebDriver driver;
    By username = By.id("username");
    By password = By.id("password");
    By login = By.id("login-submit");
    WebDriverWait wait;

    public Login(WebDriver driver){
       this.driver = driver;
    }

    public void setUserName(String strUserName){
        driver.findElement(username).sendKeys(strUserName);
        System.out.println("Username entered");
    }

    public void setPassword(String strPassword){

        driver.findElement(password).sendKeys(strPassword);
        System.out.println("Password entered");
    }

    public void clickLogin(){
        driver.findElement(login).click();
        System.out.println("Clicked Login button");
    }

    public void loginToAtlassian(String strUserName){
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        this.setUserName(strUserName);
        this.clickLogin();
    }

    public void passToAtlassian(String strPassword){
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        this.setPassword(strPassword);
        this.clickLogin();
    }
}