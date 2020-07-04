package pageObject.tests.herokuapp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.tests.anotation_lesson_andrey.BaseTest;
import pageObject.utils.ActionMain;
import pageObject.utils.WaiterMain;

import java.util.concurrent.TimeUnit;

public class ContextMenu extends BaseTest {
    private By SQUARELOCATOR=By.id("hot-spot");
    @Test
    public void testOne() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        driver.get("https://the-internet.herokuapp.com/context_menu");
        ActionMain actionMain = new ActionMain(driver);
        WebElement square = driver.findElement(SQUARELOCATOR);
        actionMain.clickRightMouseButton(square);

        WaiterMain waiterMain = new WaiterMain(driver);
        waiterMain.waitFor();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"You selected a context menu","Text  on alert not correct");
        alert.accept();
    }
}