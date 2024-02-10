package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class grid {
	WebDriver driver;
	@Test
    public static void main(String[] args) throws MalformedURLException {
    	DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.WINDOWS);
        cap.setBrowserName("MicrosoftEdge");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

        // Navigate to the registration page
        driver.get("https://demowebshop.tricentis.com/register");

        // Fill in the registration form
        driver.findElement(By.xpath("//*[@id=\"gender-male\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).sendKeys("first name");
        driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys("lastname");
        driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("asd@fgs1.com");
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("asdfg12@123");
        driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]")).sendKeys("asdfg12@123");

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"register-button\"]"))
                .click();

        // Close the browser
        driver.quit();
    }
}
