package pageObject.tests.Lida;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.pages.*;
import pageObject.tests.BaseTests;

public class DependsOnMethod extends BaseTests {
    String username = "standard_user";
    String password = "secret_sauce";
    String productsName = "Sauce Labs Bolt T-Shirt";
    String firstName = "Lida";
    String lastName = "Belukhina";
    String code = "220094";

    @Test
    public void checkLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
        System.out.println("Check Login Page: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "checkLoginPage")
    public void enterYourLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(), "Product Page has not been opened");
        System.out.println("Enter Your Login: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "enterYourLogin")
    public void addProductsToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(productsName);
        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");
        String priceProductPage = productPage.getPrice(productsName).replace("$", "");
        System.out.println("Add Products To Cart: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "addProductsToCart")
    public void goToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.goToBucket();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page has not been opened");
        cartPage.checkout();
        System.out.println("Go To Cart: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "goToCart")
    public void checkCheckOutInformationPage() {
        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isPageOpened(), "Check Out Information Page has not opened");
        System.out.println("Check CheckOut Information Page: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "checkCheckOutInformationPage")
    public void enterYourData() {
        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.addPersonalInformation(firstName, lastName, code);
        checkOutInformationPage.continueOrdering();
        System.out.println("Enter Your Data: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "enterYourData")
    public void workWithCheckoutOverviewPage() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page has not opened");
        checkoutOverviewPage.finish();
        System.out.println("Work With Checkout Overview Page: " + Thread.currentThread().getId());
    }

    @Test(dependsOnMethods = "workWithCheckoutOverviewPage")
    public void checkFinishPage() {
        FinishPage finishPage = new FinishPage(driver);
        Assert.assertTrue(finishPage.isPageOpened(), "Finish page has not been opened");
        System.out.println("Check Finish Page: " + Thread.currentThread().getId());
//       Если нужно, чтобы тест упал для ListenerTest
//        Assert.fail();
    }
}
