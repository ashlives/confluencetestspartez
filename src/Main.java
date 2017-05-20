import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void restrictions(){
        System.out.println("Page to click -" + pagetitle);
    }

    @AfterMethod
    public void tearDown(){
    driver.quit();
    }

}