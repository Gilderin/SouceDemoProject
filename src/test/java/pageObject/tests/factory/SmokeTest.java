package pageObject.tests.factory;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.anotation_lesson_andrey.BaseTest;

public class SmokeTest extends BaseTest {
    String username = "standard_user";
    String lockedUsername = "locked_out_user";
    String password = "secret_sauce";
    String firstName = "Andrey";
    String lastName = "Mihaevich";
    String code = "220022";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String errorMessage = "Epic sadface: Sorry, this user has been locked out.";

    @Test
    public void testCartPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
    }

    @Test
    public void testOverviewPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(), "Checkout information page has not been opened");
    }

    @Test
    public void testCheckOutInformatiomPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(), "Checkout information page has not been opened");
    }

    @Test
    public void testLoginPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
    }

    @Test
    public void testOProductPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test
    public void testFinishPageIsOpen() {
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
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test
    public void testLoginLocketUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(lockedUsername, password);

        Assert.assertEquals(loginPage.getError(), errorMessage, "Error message not correct");
    }
}
