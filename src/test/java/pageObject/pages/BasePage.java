package pageObject.pages;

import org.openqa.selenium.WebDriver;
import pageObject.tests.Lida.BaseTests;

public class BasePage extends BaseTests{
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
