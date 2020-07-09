package pageObject.tests.Lida;

import driverSettings.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FileUploader {
    private By SECTIONFILE = By.id("file-upload");
    private String Foto = "C:\\Users\\DELL\\OneDrive\\Изображения\\Saved Pictures\\4657035f33839aa8cc8a053d78b0e4ed";
    private By UPLOADBUTTON = By.id("file-submit");
    private By NAMEFILE = By.xpath("//*[contains(text(), '4657035f33839aa8cc8a053d78b0e4ed')]");
    private By CHECKTHATFILEUPLOADER = By.xpath("//*[text() = 'File Uploaded!']");

    @Test
    public void fileUploaderTest(){
        WebDriver driver = new BrowserService().initBrowser();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

        driver.get("http://the-internet.herokuapp.com/upload");

        driver.findElement(SECTIONFILE).sendKeys(Foto);
        driver.findElement(UPLOADBUTTON).click();

        Assert.assertTrue(driver.findElement(NAMEFILE).isDisplayed());
        Assert.assertTrue(driver.findElement(CHECKTHATFILEUPLOADER).isDisplayed());

        driver.quit();
    }
}
