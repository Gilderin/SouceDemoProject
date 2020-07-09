package pageObject.tests.Lida.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WaitersMain {
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitersMain(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForVisibilityFluent(By by) {
        Wait<WebDriver> fluent = new FluentWait<>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = fluent.until(driver1 -> driver.findElement(by));
        return foo;
    }

    public void waitForInVisibility(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForInVisibility(WebElement webElement, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void waitForChangeAttribute(By by, String attribute, String attributeValue, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.attributeContains(by, attribute, attributeValue));
    }

}
