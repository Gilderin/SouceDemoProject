package driverSettings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class BrowserService {
    WebDriver driver=null;
    String browserName="chrome";
    public WebDriver initBrowser(){
        switch(browserName){
            case "chrome":
                ChromeOptions chromeOptions=new ChromeOptions();
                //chromeOptions.addArguments("--headless");
                //chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1200");
                chromeOptions.addArguments("--ignore-certificate-errors");
                //chromeOptions.addArguments("--silent");
                driver=new ChromeDriver(chromeOptions);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "firefox":
                driver=new FirefoxDriver();
                break;
            case "ie":
                driver=new InternetExplorerDriver();
                break;
            case "edge":
                driver=new EdgeDriver();
                break;
            default:
                System.out.println("This browser is not supported");
        }
        return driver;
    }
}
