package pageObject.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WaiterMain {
    private WebDriver driver;
    private WebDriverWait wait;
    public WaiterMain(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void waitFor(){
            wait.until(ExpectedConditions.alertIsPresent());
    }
    public void waitForVisibility(By by){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    public void waitForInvisibility(By by,int time){
        wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public void waitForAttributeContains(By by, String attribute,String value, int time){
        wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.attributeContains(by,attribute,value));
    }
    public void waitfluent(By by){
       Wait<WebDriver> fluent=new FluentWait<>(driver)
               .withTimeout(10, TimeUnit.SECONDS)
               .pollingEvery(5,TimeUnit.SECONDS)
               .ignoring(NoSuchElementException.class);
       WebElement foo=fluent.until(driver1 -> driver.findElement(by));
    }
}
