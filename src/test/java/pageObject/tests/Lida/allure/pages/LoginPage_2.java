package pageObject.tests.Lida.allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.pages.BasePage;

public class LoginPage_2 extends BasePage_2 {
    static By loginFieldLocator = By.id("user-name");
    static By pswFieldLocator = By.id("password");
    static By loginButtonLocator = By.className("btn_action");

    public LoginPage_2(WebDriver driver, String URL) {
        super(driver);
        driver.get(URL);
    }

    public boolean isPageOpened() {
        return getLoginField().isDisplayed();
    }

    public WebElement getLoginField() {
        return driver.findElement(loginFieldLocator);
    }

    public WebElement getPswField() {
        return driver.findElement(pswFieldLocator);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginButtonLocator);
    }

    @Step("Login Step with username {0}, password {1}, for method: {method} step.")
    public void login(String username, String password) {
        getLoginField().sendKeys(username);
        getPswField().sendKeys(password);
        getLoginButton().submit();
    }
}
