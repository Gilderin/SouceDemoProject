package pageObject.tests.anotation_lesson_andrey;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.Lida.BaseTests;

public class PriorityTests extends BaseTests {
    String username = "standard_user";
    String lockedUsername = "locked_out_user";
    String password = "secret_sauce";
    String firstName = "Andrey";
    String lastName = "Mihaevich";
    String code = "220022";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String priceProductPage = "1";
    String descriptionProductPage = "";
    String errorMessage = "Epic sadface: Sorry, this user has been locked out.";
    double orderTax = 0;
    double orderTotal = 0;

    @Test(priority = 3)
    public void testRemoveProductFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem(productsName);
        cartPage.continueShopping();

        Assert.assertEquals(productPage.addButtonIsDisplayed(productsName), "ADD TO CART", "Button 'ADD TO CART' is not displayed");
    }
    @Test(priority = 1)
    public void testCartPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
    }

    @Test(priority = 1)
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

    @Test(priority = 1)
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

    @Test(priority = 1)
    public void testLoginPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
    }

    @Test(priority = 1)
    public void testOProductPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test(priority = 1)
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

    @Test(priority = 0)
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test(priority = 2)
    public void testLoginLocketUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(lockedUsername, password);

        Assert.assertEquals(loginPage.getError(), errorMessage, "Error message not correct");
    }

    @Test(priority = 1)
    public void testBuyOneProduct() {
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
        Assert.assertEquals(finishPage.getThankYouForYourOrder(), "THANK YOU FOR YOUR ORDER", "Something is wrong!");
        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Something is wrong!");
    }

    @Test(priority = 3)
    public void testInformationInCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        priceProductPage = productPage.getPrice(productsName).replace("$", "");
        descriptionProductPage = productPage.getDescription(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
        Assert.assertEquals(cartPage.getItemPrice(productsName), priceProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemDescription(productsName), descriptionProductPage, "Description do not match");
        Assert.assertEquals(cartPage.getItemName(productsName), productsName, "Product description on Cart and Product pages not equal");
    }

    @Test(priority = 3)
    public void testInformationInOrder() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        priceProductPage = productPage.getPrice(productsName).replace("$", "");
        descriptionProductPage = productPage.getDescription(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(checkoutOverviewPage.getName(productsName), productsName, "Product name does not match");
        Assert.assertEquals(checkoutOverviewPage.getDescription(productsName), descriptionProductPage, "Product description  does not match");
        orderTax = Math.round(((Double.parseDouble(priceProductPage) / 100) * 8) * 100.0) / 100.0;
        orderTotal = Double.parseDouble(checkoutOverviewPage.getItemTotal()) + orderTax;
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(), "SauceCard #31337", "Payment information does not match");
        Assert.assertEquals(checkoutOverviewPage.getItemTotal(), priceProductPage, "Order products total does not match");
        Assert.assertEquals(checkoutOverviewPage.getTax(), String.valueOf(orderTax), "Order tax does not match");
        Assert.assertEquals(checkoutOverviewPage.getTotal(), String.valueOf(orderTotal), "Order total does not match");
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(), "FREE PONY EXPRESS DELIVERY!", "Shipping information doesn't match");
    }


}
