package pages;
import utilities.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;

@Listeners(MyListener.class)
public class error {
    public static ExtentReports extent;

    private WebDriver driver;

    @BeforeSuite
    public void before() {
        extent = new ExtentReports();
    }

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver instance
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        //options.setHeadless(true); // Set headless mode
        driver = new FirefoxDriver();
        MyListener.setDriver(driver);
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver instance
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test1() {
        LoginPage Login = new LoginPage(driver);
        // Test login functionality with valid credentials
        driver.get("https://demowebshop.tricentis.com/login");
        driver.findElement(Login.usernameField).sendKeys("username@121.com");
        driver.findElement(Login.passwordField).sendKeys("password");
        driver.findElement(Login.loginButton).click();
        // Add your assertions or further test steps here
    }

    // Your test methods go here
}
