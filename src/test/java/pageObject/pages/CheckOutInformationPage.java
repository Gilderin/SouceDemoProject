package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutInformationPage extends BasePage {

    private By CHECKOUTPAGESELECTOR = By.className("subheader");
    private By FIRSTNAMESELECTOR = By.id("first-name");
    private By LASTNAMESELECTOR = By.id("last-name");
    private By ZIPCODESELECTOR = By.id("postal-code");
    private By CANCELBUTTONSELECTOR = By.cssSelector(".cart_cancel_link.btn_secondary");
    private By CONTINUEBUTTONSELECTOR = By.xpath("//input[@type='submit']");
    private By ERRORSELECTOR = By.tagName("h3");

    public CheckOutInformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return driver.findElement(CHECKOUTPAGESELECTOR).isDisplayed();
    }

    public void addPersonalInformation(String first, String last, String code) {
        WebElement firstName = driver.findElement(FIRSTNAMESELECTOR);
        firstName.sendKeys(first);
        WebElement lastName = driver.findElement(LASTNAMESELECTOR);
        lastName.sendKeys(last);
        WebElement zipCode = driver.findElement(ZIPCODESELECTOR);
        zipCode.sendKeys(code);
    }

    public void cancelOrdering() {
        WebElement cancelButton = driver.findElement(CANCELBUTTONSELECTOR);
        cancelButton.click();
    }

    public void continueOrdering() {
        WebElement continueButton = driver.findElement(CONTINUEBUTTONSELECTOR);
        continueButton.click();
    }

    public String getError() {
        return driver.findElement(ERRORSELECTOR).getText();
    }
}
