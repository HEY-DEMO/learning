package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
    // Locators for elements on the login page
    public By usernameField = By.id("Email");
    public By passwordField = By.id("Password");
    public By loginButton = By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input");
    By remember = By.id("RememberMe");
    
    public LoginPage(WebDriver driver) {
    	this.driver = driver;
    	}
}
