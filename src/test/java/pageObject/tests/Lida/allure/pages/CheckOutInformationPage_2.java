package pageObject.tests.Lida.allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.pages.BasePage;

public class CheckOutInformationPage_2 extends BasePage_2 {

    private By CHECKOUTPAGESELECTOR = By.className("subheader");
    private By FIRSTNAMESELECTOR = By.id("first-name");
    private By LASTNAMESELECTOR = By.id("last-name");
    private By ZIPCODESELECTOR = By.id("postal-code");
    private By CANCELBUTTONSELECTOR = By.cssSelector(".cart_cancel_link.btn_secondary");
    private By CONTINUEBUTTONSELECTOR = By.xpath("//input[@type='submit']");
    private By ERRORSELECTOR = By.tagName("h3");

    public CheckOutInformationPage_2(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return driver.findElement(CHECKOUTPAGESELECTOR).isDisplayed();
    }

    public WebElement getFirstName() {
        return driver.findElement(FIRSTNAMESELECTOR);
    }

    public WebElement getLastName() {
        return driver.findElement(LASTNAMESELECTOR);
    }

    public WebElement getCode() {
        return driver.findElement(ZIPCODESELECTOR);
    }

    public WebElement getContinueButton() {
        return driver.findElement(CONTINUEBUTTONSELECTOR);
    }

    @Step("Checkout Your Information step with firstName {0}, lastName {1}, for method: {method} step.")
    public void yourInformation(String firstName, String lastName, String code) {
        getFirstName().sendKeys(firstName);
        getLastName().sendKeys(lastName);
        getCode().sendKeys(code);
        getContinueButton().submit();
    }
}
