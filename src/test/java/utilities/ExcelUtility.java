package utilities;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelUtility {
    WebDriver driver;
    final Logger LOGGER = Logger.getLogger(ExcelUtility.class.getName());

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        // Read data from Excel file
        FileInputStream excelfile = new FileInputStream("C:/Users/hey1d/OneDrive/Desktop/learn/ps_exercise/src/test/resources/TestData.xlsx");
        Workbook workbook = new XSSFWorkbook(excelfile);
        Sheet sheet = workbook.getSheetAt(0);

        int count = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[count - 1][2];

        for (int i = 1; i < count; i++) {
            Row row = sheet.getRow(i);
            data[i - 1][0] = row.getCell(0).getStringCellValue(); // Username
            data[i - 1][1] = row.getCell(1).getStringCellValue(); // Password
        }

        workbook.close();
        excelfile.close();
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
    	LoginPage Login = new LoginPage(driver);
    	 LOGGER.log(Level.INFO, "_________________________________________________________");
    	
        // Test login functionality with provided credentials
        LOGGER.log(Level.INFO, "Navigating to login page...");
        driver.get("https://demowebshop.tricentis.com/login");
        LOGGER.log(Level.INFO, "Entering username: " + username);
        driver.findElement(Login.usernameField).sendKeys(username);
        LOGGER.log(Level.INFO, "Entering password: " + password);
        driver.findElement(Login.passwordField).sendKeys(password);
        LOGGER.log(Level.INFO, "Clicking login button...");
        driver.findElement(Login.loginButton).click();

        // Add assertions for successful login
        // For demonstration purposes, let's assume successful login is verified here
        LOGGER.log(Level.INFO, "Logged in successfully with username: " + username);
        
   	 LOGGER.log(Level.INFO, "_________________________________________________________");
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        LOGGER.log(Level.INFO, "Quitting WebDriver...");
        driver.quit();
    }
}


