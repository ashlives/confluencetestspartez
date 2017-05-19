import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ashish on 19-05-2017.
 */
public class Main {

    private WebDriver driver;
    private WebDriverWait wait;
    public String generateRandomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

    @Before
     public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.4.0/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 50);
        driver.get("https://ashishtestspartez.atlassian.net/wiki");
        System.out.println("Successfully opened the website ashishtestspartez's confluence page");
        //Enter Email and password for login
        driver.findElement(By.id("username")).sendKeys("ashish.p.deshmukh@gmail.com");
        driver.findElement(By.id("login-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        System.out.println("Email ID accepted");
        driver.findElement(By.id("password")).sendKeys("Password123$");
        driver.findElement(By.id("login-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ashish Deshmukh")));
        System.out.println("login was successful");
    }


    @Test
    public void createPage() {
        driver.findElement(By.id("quick-create-page-button")).click();
        System.out.println("Clicked on Create Page button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content-title")));
        System.out.println("Draft page loaded");
        String pagetitle = RandomStringUtils.randomAlphabetic(6);
        driver.findElement(By.id("content-title")).sendKeys(pagetitle);
        System.out.println("Entered Page Title - " + pagetitle);
        driver.findElement(By.id("rte-button-publish")).click();
        System.out.println("Clicked on Publish button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(pagetitle)));
        System.out.println("Entered page title matches with saved page title");
    }

    @Test
    public void restrictions(){

    }

    @After
    public void tearDown(){
    driver.quit();
    }

}