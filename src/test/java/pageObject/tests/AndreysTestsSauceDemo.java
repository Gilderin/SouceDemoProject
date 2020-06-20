package pageObject.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;

public class AndreysTestsSauceDemo extends BaseTests{
    String username = "standard_user";
    String lockedUsername = "standard_user";
    String password = "secret_sauce";

    @Test
    public void testLogin() {

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
 }
    @Test
    public void testLoginLocketUser() {
        String errorMessage = "Epic sadface: Sorry, this user has been locked out.";

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
        loginPage.login(lockedUsername, password);

        Assert.assertEquals(loginPage.getError(),errorMessage,"Error message not correct");

         }
    @Test
    public void testLoginErro() {
        String firstName = "Andrey";
        String lastName = "Mihaevich";
        String code = "220022";
        String productsName = "Sauce Labs Bolt T-Shirt";
        String priceProductPage="";
        String descriptionProductPage="";


        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());

        productPage.addToCart("Sauce Labs Bolt T-Shirt");
        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");
        priceProductPage = productPage.getPrice(productsName).replace("$","");
        descriptionProductPage=productPage.getDescription(productsName);
        productPage.goToBucket();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(),"Cart page has not been opened");

        Assert.assertEquals(cartPage.getItemPrice(productsName), priceProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemDescription(productsName), descriptionProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemName(productsName), descriptionProductPage, "Product description on Cart and Product pages not equal");
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(), "Checkout information page has not been opened");
        checkOutInformationPage.addPersonalInformation(firstName,lastName,code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page has not opened");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(),"SauceCard #31337", "Payment information does not match");
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(), "FREE PONY EXPRESS DELIVERY!", "Shipping information doesn't match");
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        Assert.assertEquals(finishPage.getThankYouForYourOrder(), "THANK YOU FOR YOUR ORDER", "Something is wrong!");
        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Something is wrong!");
    }

}
