package pages;
import utilities.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Listeners(MyListener.class)
public class error {
    public static ExtentReports extent;
    private static ExtentTest logger;
    private WebDriver driver;

    @BeforeSuite
    public void before() {
        extent = new ExtentReports();
    }

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver instance
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        //options.setHeadless(true); // Set headless mode
        driver = new EdgeDriver(options);
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
        logger = extent.createTest("testMethod");
        logger.log(Status.INFO, "This is a test method.");
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
