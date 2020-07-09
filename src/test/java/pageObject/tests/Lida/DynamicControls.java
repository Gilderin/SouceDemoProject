package pageObject.tests.Lida;

import driverSettings.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.tests.Lida.utils.WaitersMain;

import java.util.concurrent.TimeUnit;

public class DynamicControls {
    private By BUTTONREMOVE = By.xpath("//*[text() = 'Remove']");
    private By WAITFORIT = By.id("loading");
    private By INPUT = By.xpath("//*[@id='input-example']/input");
    private By ENABLEBUTTON = By.xpath("//*[@id='input-example']/button");
    private By MASSEGITSENABLE = By.id("message");

    @Test
    public void dynamicControlsTest() {
        WebDriver driver = new BrowserService().initBrowser();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(BUTTONREMOVE).click();

        WaitersMain waitersMain = new WaitersMain(driver);
        waitersMain.waitForVisibility(WAITFORIT);
        waitersMain.waitForInVisibility(WAITFORIT, 10);

        WebElement input = driver.findElement(INPUT);
        Assert.assertFalse(input.isEnabled());
        driver.findElement(ENABLEBUTTON).click();

        waitersMain.waitForVisibility(MASSEGITSENABLE);
        Assert.assertTrue(input.isEnabled());

        driver.quit();
    }
}
