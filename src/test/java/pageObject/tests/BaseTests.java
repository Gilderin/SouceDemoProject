package pageObject.tests;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTests {
    public WebDriver driver;
    BrowserService browserService = new BrowserService();

    @BeforeTest
    public void setUp() {
        driver = browserService.initBrowser();
        System.out.println("The Thread name is " + Thread.currentThread().getId());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
