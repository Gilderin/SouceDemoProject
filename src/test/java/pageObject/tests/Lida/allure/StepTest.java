package pageObject.tests.Lida.allure;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.tests.Lida.allure.pages.CartPage_2;
import pageObject.tests.Lida.allure.pages.CheckOutInformationPage_2;
import pageObject.tests.Lida.allure.pages.LoginPage_2;
import pageObject.tests.Lida.allure.pages.ProductPage_2;

public class StepTest extends BaseTests_2 {
    String username = "standard_user";
    String password = "secret_sauce";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String firstName = "Lida";
    String lastName = "Belukhina";
    String code = "220094";

    @Test(description = "Login Page")
    public void loginPage() {
        LoginPage_2 loginPage = new LoginPage_2(getDriver(), URL);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page hasn't opened.");
        loginPage.login(username, password);
    }

    @Test(dependsOnMethods = "loginPage", description = "Add product to cart")
    public void addProductsToCart() {
        ProductPage_2 productPage = new ProductPage_2(getDriver());
        Assert.assertTrue(productPage.isPageOpened(), "Product page hasn't opened");
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        productPage.goToBucket();
    }

    @Test(dependsOnMethods = "addProductsToCart", description = "Check cart page")
    public void checkCartPage() {
        CartPage_2 cartPage = new CartPage_2(getDriver());
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page hasn't been opened");
        cartPage.checkout();
    }

    @Test(dependsOnMethods = "checkCartPage", description = "Enter your data")
    public void enterYourData() {
        CheckOutInformationPage_2 checkOutInformationPage = new CheckOutInformationPage_2(getDriver());
        Assert.assertTrue(checkOutInformationPage.isPageOpened(), "Checkout information page hasn't opened");
        checkOutInformationPage.yourInformation(firstName, lastName, code);
    }
}
