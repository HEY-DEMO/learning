package utilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class MyListener implements ITestListener {
    private static WebDriver driver;
    private ExtentReports extent;
    private ExtentReports extentReports;
    private ExtentTest extentTest;



    public static void setDriver(WebDriver driver) {
    	MyListener.driver = driver;
    }
    @Override
    public void onStart(ITestContext context) {
        extentReports = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
        extentReports.attachReporter(htmlReporter);

        extentTest = extentReports.createTest("Test Environment Details");
        extentTest.log(Status.INFO, "OS: " + System.getProperty("os.name"));
        extentTest.log(Status.INFO, "Java Version: " + System.getProperty("java.version"));
        try {
            extentTest.log(Status.INFO, "Host Name: " + InetAddress.getLocalHost().getHostName());
        } catch (Exception e) {
            extentTest.log(Status.WARNING, "Unable to fetch Host Name");
        }
        extentTest.log(Status.INFO, "Browser: Chrome"); // Assuming browser is fixed to Chrome
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        // Check if WebDriver instance is initialized
        if (driver == null) {
            System.err.println("WebDriver instance is null. Cannot capture screenshot.");
            return;
        }

        shot(result.getName()+".png");

          
    }
    

    private static void shot(String filename) {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File src = sc.getScreenshotAs(OutputType.FILE);
		try {
		FileHandler.copy(src, new File("output\\"+filename));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

    // Implement other methods of the ITestListener interface if needed
}
