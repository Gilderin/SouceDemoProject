package pageObject.tests.Lida;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.BaseTests;

public class MethodsOfPriority extends BaseTests {
    String username = "standard_user";
    String password = "secret_sauce";
    String firstName = "Lida";
    String lastName = "Belukhina";
    String code = "220094";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String productsName_2 = "Test.allTheThings() T-Shirt (Red)";
    String productsName_3 = "Sauce Labs Backpack";

    @Test(dependsOnMethods = "testLockedOutUser",priority = 1)
    public void checkProductPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(), "Product page has not been opened");
    }

    @Test(priority = 2)
    public void addOneProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertEquals(finishPage.getThankYouForYourOrder(), "THANK YOU FOR YOUR ORDER",
                "Something is wrong!");
        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Something is wrong!");
    }

    @Test(dependsOnMethods = "checkLoginPage", priority = 1)
    public void testLockedOutUser() {
        String anotherUsername = "locked_out_user";
        String error = "Epic sadface: Sorry, this user has been locked out.";

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(anotherUsername, password);
        Assert.assertEquals(loginPage.getError(), error, "There's been a mistake!");
    }

    @Test(priority = 3)
    public void removeProduct() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem(productsName);
        cartPage.continueShopping();
    }

    @Test(priority = 1)
    public void checkLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(username, password);
    }

    @Test(priority = 3)
    public void addProductContinueShopping() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.addToCart(productsName_2);
        productPage.getCartSelectedCount();
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.continueShopping();

        productPage.addToCart(productsName_3);
        productPage.getCartSelectedCount();
        productPage.goToBucket();

        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        Assert.assertEquals(finishPage.getThankYouForYourOrder(),
                "THANK YOU FOR YOUR ORDER", "Something is wrong!");

        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Something is wrong!");
    }

    @Test(dependsOnMethods = "checkProductPage", priority = 1)
    public void checkCartPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
    }
}
