package pageObject.tests.Lida.factoryAnnatation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.Lida.BaseTests;

public class RegressionTest extends BaseTests {
    String username = "standard_user";
    String password = "secret_sauce";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String firstName = "Lida";
    String lastName = "Belukhina";
    String code = "220094";

    @Test
    public void addOneProduct () {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());

        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");
        String priceProductPage = productPage.getPrice(productsName).replace("$", "");

        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");

        Assert.assertEquals(cartPage.getItemPrice(productsName), priceProductPage, "Product price does not match on the shopping cart page!");

        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(), "Checkout information page has not been opened");
        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);

        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page has not opened");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(), "SauceCard #31337", "Payment information does not match");
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(), "FREE PONY EXPRESS DELIVERY!", "Shipping information doesn't match");
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        Assert.assertEquals(finishPage.getThankYouForYourOrder(), "THANK YOU FOR YOUR ORDER", "Something is wrong!");
        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Something is wrong!");
    }
}
