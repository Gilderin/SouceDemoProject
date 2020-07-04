package pageObject.tests;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.tests.anotation_lesson_andrey.ListenerTest;

@Listeners(ListenerTest.class)
public class BaseTests {
    public WebDriver driver;
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

}
