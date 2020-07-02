package pageObject.tests.Lida;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.Lida.BaseTests;

public class LidaTest extends BaseTests {
    String username = "standard_user";
    String password = "secret_sauce";
    String firstName = "Lida";
    String lastName = "Belukhina";
    String code = "220094";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String productsName_2 = "Test.allTheThings() T-Shirt (Red)";
    String productsName_3 = "Sauce Labs Backpack";

    @Description("This test adds one product to the cart and makes a purchase.")
    @Test(description = "Add one product")
    public void addOneProduct() {
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

    @Description("This test adds some product to the cart and makes a purchase.")
    @Test(description = "Add some product")
    public void addSomeProduct() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());

        productPage.addToCart(productsName);
        productPage.addToCart(productsName_2);

        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "2", "Count is not equals 2");

        String priceFirstProductPage = productPage.getPrice(productsName).replace("$", "");
        String priceSecondProductPage = productPage.getPrice(productsName_2).replace("$", "");

        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "2", "Count is not equals 2");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");

        Assert.assertEquals(cartPage.getItemPrice(productsName), priceFirstProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemPrice(productsName), priceSecondProductPage, "Prices do not match");
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(),
                "Checkout information page has not been opened");

        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page has not opened");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(),
                "SauceCard #31337", "Payment information does not match");

        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(),
                "FREE PONY EXPRESS DELIVERY!", "Shipping information doesn't match");
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        Assert.assertEquals(finishPage.getThankYouForYourOrder(),
                "THANK YOU FOR YOUR ORDER", "Something is wrong!");

        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Something is wrong!");
    }

    @Description("This test checks a locked user.")
    @Test(description = "Test locked out user")
    public void testLockedOutUser() {
        String anotherUsername = "locked_out_user";
        String error = "Epic sadface: Sorry, this user has been locked out.";

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(anotherUsername, password);
        Assert.assertEquals(loginPage.getError(), error, "There's been a mistake!");
    }

    @Description("This test checks the button to continue shopping and makes a purchase.")
    @Test(description = "Check button continue shopping")
    public void addProductContinueShopping() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());

        productPage.addToCart(productsName);
        productPage.addToCart(productsName_2);

        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "2", "Count is not equals 2");

        String priceFirstProductPage = productPage.getPrice(productsName).replace("$", "");
        String priceSecondProductPage = productPage.getPrice(productsName_2).replace("$", "");

        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "2", "Count is not equals 2");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");

        Assert.assertEquals(cartPage.getItemPrice(productsName), priceFirstProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemPrice(productsName), priceSecondProductPage, "Prices do not match");
        cartPage.continueShopping();

        productPage.addToCart(productsName_3);

        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "3", "Count is not equals 3");

        String priceThirdlyProductPage = productPage.getPrice(productsName).replace("$", "");
        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "3", "Count is not equals 2");

        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(),
                "Checkout information page has not been opened");

        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page has not opened");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(),
                "SauceCard #31337", "Payment information does not match");

        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(),
                "FREE PONY EXPRESS DELIVERY!", "Shipping information doesn't match");
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        Assert.assertEquals(finishPage.getThankYouForYourOrder(),
                "THANK YOU FOR YOUR ORDER", "Something is wrong!");

        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Something is wrong!");
    }

    @Description("This test checks the button to remove the product and makes a purchase.")
    @Test(description = "Check button remove")
    public void removeProduct() {

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");

        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());

        productPage.addToCart(productsName);
        productPage.addToCart(productsName_2);

        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "2", "Count is not equals 2");

        String priceFirstProductPage = productPage.getPrice(productsName).replace("$", "");
        String priceSecondProductPage = productPage.getPrice(productsName_2).replace("$", "");

        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "2", "Count is not equals 2");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");

        Assert.assertEquals(cartPage.getItemPrice(productsName), priceFirstProductPage, "Prices do not match");
        Assert.assertEquals(cartPage.getItemPrice(productsName), priceSecondProductPage, "Prices do not match");
        cartPage.removeItem(productsName);
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");
        cartPage.checkout();

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(),
                "Checkout information page has not been opened");

        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page has not opened");
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(),
                "SauceCard #31337", "Payment information does not match");

        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(),
                "FREE PONY EXPRESS DELIVERY!", "Shipping information doesn't match");
        checkoutOverviewPage.finish();

        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        Assert.assertEquals(finishPage.getThankYouForYourOrder(),
                "THANK YOU FOR YOUR ORDER", "Something is wrong!");

        Assert.assertEquals(finishPage.getYourOrderHasBeenDispatched(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Something is wrong!");
    }

    @Description("This test checks for errors on the page Checkout Your Information.")
    @Test(description = "Check error in page checkout your information")
    public void errorInPageCheckoutYourInformation() {
        String errorFirthName = "Error: First Name is required";
        String errorLastName = "Error: Last Name is required";
        String errorPostalCode = "Error: Postal Code is required";

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

//        checkOutInformationPage.addPersonalInformation("", lastName, code);
//        checkOutInformationPage.addPersonalInformation(firstName, "", code);
        checkOutInformationPage.addPersonalInformation(firstName, lastName, "");
        checkOutInformationPage.continueOrdering();
//        Assert.assertEquals(checkOutInformationPage.getError(), errorFirthName, "There's been a mistake!" );
//        Assert.assertEquals(checkOutInformationPage.getError(), errorLastName, "There's been a mistake!" );
        Assert.assertEquals(checkOutInformationPage.getError(), errorPostalCode, "There's been a mistake!");
    }
}
