package pageObject.tests.Lida.allure;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


@Listeners(ListenerTest_2.class)

public class BaseTests_2 {
    public static WebDriver driver;
    String URL;
    BrowserService browserService = new BrowserService();

    @Parameters("URL")
    @BeforeClass
    public void setUp(String URL) {
        driver = browserService.initBrowser();
        this.URL = URL;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
