package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTest {
    WebDriver driver;
    final Logger LOGGER = Logger.getLogger(LoginTest.class.getName());

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
        LOGGER.log(Level.INFO, "Navigating to login page...");
        driver.get("https://demowebshop.tricentis.com/login");
        LOGGER.log(Level.INFO, "Entering username...");
        driver.findElement(Login.usernameField).sendKeys("username");
        LOGGER.log(Level.INFO, "Entering password...");
        driver.findElement(Login.passwordField).sendKeys("password");
        LOGGER.log(Level.INFO, "Clicking login button...");
        driver.findElement(Login.loginButton).click();

        // Add assertions for successful login
        LOGGER.log(Level.INFO, "Logged in successfully.");
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        LOGGER.log(Level.INFO, "Quitting WebDriver...");
        driver.quit();
    }
}





