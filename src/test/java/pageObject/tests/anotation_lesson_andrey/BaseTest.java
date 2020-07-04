package pageObject.tests.anotation_lesson_andrey;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(ListenerTest.class)
public class BaseTest {
    public static WebDriver driver;
    BrowserService browserService = new BrowserService();

    @BeforeMethod
    public void setUp() {
        driver = browserService.initBrowser();
        System.out.println("The Thread name is " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
