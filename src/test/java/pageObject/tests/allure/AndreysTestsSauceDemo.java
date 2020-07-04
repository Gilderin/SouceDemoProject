package pageObject.tests.allure;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.BaseTests;


public class AndreysTestsSauceDemo extends BaseTests {
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

    @Attachment("Привет. Это приложение к тесту")
    @Test(description = "Проверка открытия страницы 'Cart'")
    public void testCartPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.goToBucket();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
    }

    @Description("Проверка открытия страницы ''")
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

    @Test(description = "Проверка открытия страницы 'Checkout: Information'")
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

    @Test(description = "Проверка открытия страницы 'Checkout: Information'")
    public void testLoginPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
    }

    @Test(description = "Проверка открытия страницы 'Product'")
    public void testOProductPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test(description = "Проверка открытия страницы 'Finish'")
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

    @Test(description = "Проверка входа на сайт")
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test(description = "Проверка входа на сайт с заблокированными данными")
    public void testLoginLocketUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(lockedUsername, password);

        Assert.assertEquals(loginPage.getError(), errorMessage, "Error message not correct");
    }

    @Description("Проверка покупки  одного товара")
    @Test
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

    @Description("Проверка информации о товаре на странице 'Cart'")
    @Test
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

    @Description("Проверка информации о товаре на странице 'Checkout: Information'")
    @Test
    public void testInformationInOrder() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
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

    @Description("Проверка удаления товара из Корзины'")
    @Test
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
}
