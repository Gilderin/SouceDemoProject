package pageObject.tests.Lida;

import driverSettings.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObject.tests.Lida.utils.ActionMain;
import pageObject.tests.Lida.utils.WaitersMain;

import java.util.concurrent.TimeUnit;

public class ContextMenu {
    private By QUADRAT = By.id("hot-spot");
    @Test
    public void contextMenuTest() {
        WebDriver driver = new BrowserService().initBrowser();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

        driver.get("http://the-internet.herokuapp.com/context_menu");

        WebElement webElement = driver.findElement(QUADRAT);

        ActionMain actionMain = new ActionMain(driver);
        actionMain.clickRightButton(webElement);

        WaitersMain waitersMain = new WaitersMain(driver);
        waitersMain.waitForAlert();

        driver.quit();
    }
}
