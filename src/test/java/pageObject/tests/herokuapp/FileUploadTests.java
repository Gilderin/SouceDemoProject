package pageObject.tests.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.tests.anotation_lesson_andrey.BaseTest;
import pageObject.utils.WaiterMain;

public class FileUploadTests extends BaseTest {
    private By INPUTUPLOADLOCATOR=By.id("file-upload");
    private By SUBMITBUTTONLOCATOR=By.id("file-submit");
    private String FILEPATH="C:\\Users\\hotab\\Downloads\\Telegram Desktop\\allure.txt";
    private By CONGRATSTEXTLOCATOR=By.xpath("//*[contains(text(),'Uploaded')]");
    private By FILENAMELOCATOR=By.xpath("//*[contains(text(),'allure.txt')]");
    @Test
    public void testOne() {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement inputUploadFile=driver.findElement(INPUTUPLOADLOCATOR);
        inputUploadFile.sendKeys(FILEPATH);
        driver.findElement(SUBMITBUTTONLOCATOR).click();
        Assert.assertTrue(driver.findElement(CONGRATSTEXTLOCATOR).isDisplayed());
        Assert.assertTrue(driver.findElement(FILENAMELOCATOR).isDisplayed());
    }
}