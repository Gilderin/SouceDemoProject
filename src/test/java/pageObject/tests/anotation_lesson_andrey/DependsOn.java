package pageObject.tests.anotation_lesson_andrey;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.BaseTests;

public class DependsOn extends BaseTest {
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
    @Test
    public void testLoginPageIsOpen() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
    }

    @Test(dependsOnMethods = "testLoginPageIsOpen")
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
    }

    @Test(dependsOnMethods = "testLogin")
    public void testAddProduct(){
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        priceProductPage = productPage.getPrice(productsName).replace("$", "");
        descriptionProductPage = productPage.getDescription(productsName);
    }
    @Test(dependsOnMethods = "testAddProduct")
    public void checkCart(){
        ProductPage productPage = new ProductPage(driver);
        productPage.goToBucket();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
        Assert.assertEquals(cartPage.getItemPrice(productsName), priceProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemDescription(productsName), descriptionProductPage, "Description do not match");
        Assert.assertEquals(cartPage.getItemName(productsName), productsName, "Product description on Cart and Product pages not equal");
    }
    @Test(dependsOnMethods = "checkCart")
    public void addPersonalInformation(){
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();
    }
    @Test(dependsOnMethods = "addPersonalInformation")
    public void checkOrderInformation(){
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
