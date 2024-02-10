package utilities;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.fetch.Fetch;
import org.openqa.selenium.edge.EdgeDriver;





public class NetworkMocking {

    public static void main(String[] args) throws InterruptedException {
//Initialize driver  
      System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        
        EdgeDriver driver = new EdgeDriver();
        
//Create DevTools Object and start session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        
//Enable Fetch Domain to work with network requests
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

//Add Listener to pause request
        devTools.addListener(Fetch.requestPaused(), request->
        {
//Filter url with if condition and make changes in URL with replace method
            if(request.getRequest().getUrl().contains("shetty"))
            {
//Mock URL - make change in the url by using replace method and change the authorName
                String mockedUrl =request.getRequest().getUrl().replace("=shetty", "=BadGuy");
                
                System.out.println(mockedUrl);
                
//Continue request with new mock URL                   
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(),Optional.empty()));
            }
            else {
                
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(),Optional.empty()));
                
            }
            
        });
//Perform test and verify the message
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(3000);
        
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
                    
        
    }

}