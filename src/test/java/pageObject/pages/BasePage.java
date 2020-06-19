package pageObject.pages;

import driverSettings.BrowserService;
import org.openqa.selenium.WebDriver;

public class BasePage extends BrowserService {
    WebDriver driver;
    BrowserService browserService = new BrowserService();

    public BasePage(WebDriver driver) {
        this.driver = browserService.initBrowser();
    }

}
