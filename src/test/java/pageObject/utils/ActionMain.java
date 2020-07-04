package pageObject.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionMain {
    private WebDriver driver;

    public ActionMain(WebDriver driver) {
        this.driver = driver;
    }
    public void clickRightMouseButton(WebElement webElement){
        Actions actions=new Actions(driver);
        actions
                .contextClick(webElement)
                .build()
                .perform();
    }
}
