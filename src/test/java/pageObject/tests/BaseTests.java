package pageObject.tests;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTests {
    WebDriver driver;
    BrowserService browserService = new BrowserService();

    @BeforeMethod
    public void setUp() {
        driver = browserService.initBrowser();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
