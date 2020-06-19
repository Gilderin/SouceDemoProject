package pageObject.pages;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;
import pageObject.tests.BaseTests;

public class BasePage extends BaseTests {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
