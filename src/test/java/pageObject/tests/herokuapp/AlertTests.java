package pageObject.tests.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObject.tests.anotation_lesson_andrey.BaseTest;
import pageObject.utils.WaiterMain;

public class AlertTests extends BaseTest {

    @Test
    public void alertTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement alerrtButton= driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        alerrtButton.click();
        WaiterMain waiterMain=new WaiterMain(driver);
        waiterMain.waitFor();
    }
}
