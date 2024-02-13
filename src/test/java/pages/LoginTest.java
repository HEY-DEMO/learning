package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class LoginTest {
    WebDriver driver;
   

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
    	 System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
  
    }

    @Test
    public void testLoginSuccess() {
 
    	  driver = new FirefoxDriver();
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
    	if (driver != null) {
            driver.quit();
    }
}
}




