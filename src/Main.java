import org.apache.commons.lang3.RandomStringUtils;
import java.lang.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by Ashish on 19-05-2017.
 */
public class Main {

    private WebDriver driver;
    private WebDriverWait wait;
    public static String generateRandomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }
    static String pagetitle = RandomStringUtils.randomAlphabetic(6);

    @BeforeMethod
     public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.4.0/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 50);
        driver.get("https://ashishtestspartez.atlassian.net/wiki");
        System.out.println("Successfully opened ashishtestspartez's confluence page");
        //Enter Email and password for login
        driver.findElement(By.id("username")).sendKeys("ashish.p.deshmukh@gmail.com");
        System.out.println("Email entered");
        driver.findElement(By.id("login-submit")).click();
        System.out.println("Clicked on Login button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        System.out.println("Email ID accepted");
        driver.findElement(By.id("password")).sendKeys("Password123$");
        System.out.println("Password entered");
        driver.findElement(By.id("login-submit")).click();
        System.out.println("Clicked on Login button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ashish Deshmukh")));
        System.out.println("Login was successful");
    }


    @Test
    public void createPage() {
        driver.findElement(By.id("quick-create-page-button")).click();
        System.out.println("1. Clicked on Create Page button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content-title")));
        System.out.println("2. Draft page loaded");
        driver.findElement(By.id("content-title")).sendKeys(pagetitle);
        System.out.println("3. Entered Page Title - " + pagetitle);
        driver.findElement(By.id("rte-button-publish")).click();
        System.out.println("4. Clicked on Publish button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(pagetitle)));
        System.out.println("5. Entered page title matches with published page title");
    }

    @Test(priority = 1)
    public void restrictions()
    {
        driver.findElement(By.linkText("XQhIbu")).click();
        System.out.println("Open page - XQhIbu");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content-metadata-page-restrictions")));
        driver.findElement(By.id("content-metadata-page-restrictions")).click();

        System.out.println("Click on Restrictions icon");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("aui-dialog2-header-main")));
        driver.switchTo().activeElement();
        System.out.println("Switched to Overlay");
        assertEquals(driver.findElement(By.cssSelector("h2.aui-dialog2-header-main")).getText(), "Restrictions");
        System.out.println("Restriction Pop-up appeared");
        driver.switchTo().activeElement();
        driver.findElement(By.className("page-restrictions-dialog-selector-container")).click();
        System.out.println("Clicked on dropdown");
        //driver.findElement(By.id("select2-drop-mask")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.id("page-restrictions-dialog-selector"));
        Select se=new Select(element);
        se.selectByValue("edit");
        System.out.println("Editing Restricted selected");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("page-restrictions-dialog-save-button"))).click();
        //driver.findElement(By.id("page-restrictions-dialog-save-button")).click();
        System.out.println("Clicked on Apply");
    }

    @AfterMethod
    public void tearDown(){
    driver.quit();
    }

}