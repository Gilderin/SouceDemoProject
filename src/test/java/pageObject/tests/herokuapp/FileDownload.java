package pageObject.tests.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.tests.anotation_lesson_andrey.BaseTest;
import pageObject.utils.WaiterMain;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileDownload extends BaseTest {
    private By DOWNLIADFILESLOCATOR = By.cssSelector(".example a");

    @Test
    public void testOne() {
        driver.get("http://the-internet.herokuapp.com/download");
        List<WebElement> list = driver.findElements(DOWNLIADFILESLOCATOR);
        WebElement el = list.get(list.size() - 1);
        String nameFile = el.getText();
        el.click();
        Assert.assertTrue(checkFileDownloads(nameFile), "Downloaded file is not found");
    }

    public boolean checkFileDownloads(String nameFile){
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File file = null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.matches(nameFile)) {
                    file = new File(fileName);
                    found = true;
                    file.deleteOnExit();
                }
            }
        }
        return found;
    }
}
