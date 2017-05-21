package main;

import org.apache.commons.lang3.RandomStringUtils;
import java.lang.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import pages.*;

/**
 * Created by Ashish on 19-05-2017.
 */

public class Main {

    private WebDriver driver;
    private WebDriverWait wait;
    static String pagetitle = RandomStringUtils.randomAlphabetic(6);
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean acceptNextAlert = true;
    Login objLogin;
    HomePage objHomePage;
    CreatePage objCreatePage;

    @BeforeMethod
     public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.4.0/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 50);
        driver.get("https://ashishtestspartez.atlassian.net/wiki");
        objLogin = new Login(driver);
        objHomePage = new HomePage(driver);
        objLogin.loginToAtlassian("ashish.p.deshmukh@gmail.com");
        objLogin.passToAtlassian("Password123$");
        objHomePage.loginVerify("Ashish Deshmukh");
    }


    @Test
    public void createPage() throws Exception {
        objHomePage = new HomePage(driver);
        objHomePage.createButton();
        objCreatePage = new CreatePage(driver);
        objCreatePage.enterContentTitle(pagetitle);
        objCreatePage.publish();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(pagetitle)));
        System.out.println("5. Entered page title matches with published page title");
    }

    @Test(priority = 1)
    public void restrictions()
    {
        driver.findElement(By.linkText(pagetitle)).click();
        System.out.println("1. Open page - " + pagetitle);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content-metadata-page-restrictions")));
        driver.findElement(By.id("content-metadata-page-restrictions")).click();

        System.out.println("2. Click on Restrictions icon");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("aui-dialog2-header-main")));
        driver.switchTo().activeElement();
        System.out.println("3. Switched to Overlay");
        assertEquals(driver.findElement(By.cssSelector("h2.aui-dialog2-header-main")).getText(), "Restrictions");
        System.out.println("4. Restriction Pop-up appeared");
        driver.switchTo().activeElement();
        driver.findElement(By.className("page-restrictions-dialog-selector-container")).click();
        System.out.println("5. Clicked on dropdown");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.id("page-restrictions-dialog-selector"));
        Select se=new Select(element);
        se.selectByValue("edit");
        System.out.println("6. Editing Restricted selected");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("page-restrictions-dialog-save-button"))).click();
        System.out.println("7. Clicked on Apply");
        //Wait for pop-up to close
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(driver.findElement(By.id("content-metadata-page-restrictions")).getAttribute("original-title"), "Restrictions apply");
       System.out.println("8. Restrictions were applied.");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
    }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}