package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FinishPage extends BasePage {
    private By FINISHSELECTOR = By.className("subheader");
    private By THANKYOUFORYOURORDERSELECTOR = By.className("complete-header");
    private By YOURORDERHASBEENDISPATCHED = By.className("complete-text");

    public FinishPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка открытия страницы Finish")
    public boolean isPageOpened() {
        return driver.findElement(FINISHSELECTOR).isDisplayed();
    }

    @Step("Проверка что надпись 'ThankYouForYourOrder' присутствует на странице Finish")
    public String getThankYouForYourOrder() {
        WebElement thankYou = driver.findElement(THANKYOUFORYOURORDERSELECTOR);
        return thankYou.getText();
    }

    @Step("Проверка что надпись 'YourOrderHasBeenDispatched' присутствует на странице Finish")
    public String getYourOrderHasBeenDispatched() {
        WebElement YourOrderHasBeenDispatched = driver.findElement(YOURORDERHASBEENDISPATCHED);
        return YourOrderHasBeenDispatched.getText();
    }
}
