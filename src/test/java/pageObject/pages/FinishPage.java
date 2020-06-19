package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FinishPage extends BasePage{
    private By FINISHSELECTOR = By.className("subheader");
    private By THANKYOUFORYOURORDERSELECTOR = By.className("complete-header");
    private By YOURORDERHASBEENDISPATCHED = By.className("complete-text");

    public FinishPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return driver.findElement(FINISHSELECTOR).isDisplayed();
    }

    public String getThankYouForYourOrder() {
        WebElement thankYou = driver.findElement(THANKYOUFORYOURORDERSELECTOR);
        return thankYou.getText();
    }

    public String getYourOrderHasBeenDispatched() {
        WebElement YourOrderHasBeenDispatched = driver.findElement(YOURORDERHASBEENDISPATCHED);
        return YourOrderHasBeenDispatched.getText();
    }
}
