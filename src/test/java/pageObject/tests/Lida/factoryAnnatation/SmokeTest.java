package pageObject.tests.Lida.factoryAnnatation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.LoginPage;
import pageObject.pages.ProductPage;
import pageObject.tests.Lida.BaseTests;

public class SmokeTest extends BaseTests {
    String username = "standard_user";
    String password = "secret_sauce";

    @Test
    public void checkLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
        System.out.println("Check Login Page: " + Thread.currentThread().getId());
    }

    @Test
    public void enterYourLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(), "Product Page has not been opened");
        System.out.println("Enter Your Login: " + Thread.currentThread().getId());
    }
}
