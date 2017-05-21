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

public class Main {

    private WebDriver driver;
    private WebDriverWait wait;
    static String pagetitle = RandomStringUtils.randomAlphabetic(6);
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean acceptNextAlert = true;
    Login objLogin;
    HomePage objHomePage;
    CreatePage objCreatePage;
    PageDetails objPageDetails;
    RestrictionsPopup objRestrictions;

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
        assertEquals(driver.findElement(By.linkText(pagetitle)).getText(),pagetitle);
        System.out.println("Page successfully created");
    }

    @Test(priority = 1)
    public void restrictions()
    {
        objHomePage.openPage(pagetitle);
        objPageDetails = new PageDetails(driver);
        objRestrictions = new RestrictionsPopup(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        objPageDetails.clickRestrictionsIcon();
        objRestrictions.viewingEditingRestricted();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(driver.findElement(By.id("content-metadata-page-restrictions")).getAttribute("original-title"), "Restrictions apply");
        System.out.println("Restrictions were applied.");

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