package pageObject.tests.Lida;

import driverSettings.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Frames {
    private By WINDOWWITHTEXT = By.id("mce_0_ifr");
    private By TEXT = By.xpath("//body/p");
    private String insideText = "Your content goes here.";
    private By ELEMENTALSELENIUMLINK = By.linkText("Elemental Selenium");

    @Test
    public void framesTest() {
        WebDriver driver = new BrowserService().initBrowser();

        driver.get("http://the-internet.herokuapp.com/iframe");

        WebElement webElement = driver.findElement(WINDOWWITHTEXT);
        driver.switchTo().frame(webElement);

        WebElement child = driver.findElement(TEXT);
        Assert.assertEquals(child.getText(), insideText);

        driver.switchTo().parentFrame();
        driver.findElement(ELEMENTALSELENIUMLINK).click();

        driver.quit();
    }
}
