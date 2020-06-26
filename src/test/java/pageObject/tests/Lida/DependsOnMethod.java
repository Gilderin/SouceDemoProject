package pageObject.tests.Lida;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.CartPage;
import pageObject.pages.LoginPage;
import pageObject.pages.ProductPage;
import pageObject.tests.BaseTests;

public class DependsOnMethod extends BaseTests {
    String username = "standard_user";
    String password = "secret_sauce";
    String productsName = "Sauce Labs Bolt T-Shirt";

    @Test
    public void checkLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
    }

    @Test(dependsOnMethods = "checkLoginPage")
    public void enterYourLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test(dependsOnMethods = "enterYourLogin")
    public void addProductsToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");
        String priceProductPage = productPage.getPrice(productsName).replace("$", "");
    }

    @Test(dependsOnMethods = "addProductsToCart")
    public void goToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
        cartPage.checkout();
    }
}
