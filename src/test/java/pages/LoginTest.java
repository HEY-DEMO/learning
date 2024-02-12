package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Level;


public class LoginTest {
    WebDriver driver;
   

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginSuccess() {
    	LoginPage Login = new LoginPage(driver);
        // Test login functionality with valid credentials
        driver.get("https://demowebshop.tricentis.com/login");
        driver.findElement(Login.usernameField).sendKeys("username");
        driver.findElement(Login.passwordField).sendKeys("password");
        driver.findElement(Login.loginButton).click();

        // Add assertions for successful login
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        driver.quit();
    }
}





