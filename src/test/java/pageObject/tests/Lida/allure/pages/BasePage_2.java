package pageObject.tests.Lida.allure.pages;

import org.openqa.selenium.WebDriver;
import pageObject.tests.Lida.BaseTests;

public class BasePage_2 extends BaseTests {
    WebDriver driver;

    public BasePage_2(WebDriver driver) {
        this.driver = driver;
    }

    public String updateLocator(String locator, String value) {
        return locator.replace("replace", value);
    }

}
