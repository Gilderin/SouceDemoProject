package pageObject.tests.Lida;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.tests.Lida.ListenerTest;

@Listeners(ListenerTest.class)

public class BaseTests {
    public WebDriver driver;
    BrowserService browserService = new BrowserService();

    @BeforeTest
    public void setUp() {
        driver = browserService.initBrowser();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
