package pageObject.tests.Lida;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.tests.Lida.utils.ListenerTest;

@Listeners(ListenerTest.class)

public class BaseTests {
    public WebDriver driver;
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
