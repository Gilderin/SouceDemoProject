package pageObject.tests.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.tests.anotation_lesson_andrey.BaseTest;
import pageObject.utils.WaiterMain;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class DynamicControls extends BaseTest {
    private By CHECKBOXLOCATOR = By.cssSelector("[type='checkbox']");
    private By REMOVEBUTTONLOCATOR = By.xpath("//button[text()='Remove']");
    private By INPUTLOCATOR = By.cssSelector("#input-example [type='text']");
    private By ENABLEINPUTBUTTONLOCATOR = By.cssSelector("#input-example [type='button']");
    private By MESSAGELOCATOR = By.id("message");

    @Test
    public void testOne() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement checkBox = driver.findElement(CHECKBOXLOCATOR);
        WebElement addButton = driver.findElement(REMOVEBUTTONLOCATOR);
        addButton.click();
        WaiterMain waiterMain = new WaiterMain(driver);
        waiterMain.waitForVisibility(MESSAGELOCATOR);
        waiterMain.waitForInvisibility(CHECKBOXLOCATOR, 10);
        WebElement input = driver.findElement(INPUTLOCATOR);
        Assert.assertFalse(input.isEnabled(),"Input is enable");
        WebElement enableButton= driver.findElement(ENABLEINPUTBUTTONLOCATOR);
        enableButton.click();
        waiterMain.waitForVisibility(MESSAGELOCATOR);
        Assert.assertTrue(input.isEnabled(),"Input is disable");
    }
}
