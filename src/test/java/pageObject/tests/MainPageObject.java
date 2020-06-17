package pageObject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.pages.LoginPage;
import pageObject.pages.ProductPage;

import java.util.List;

public class MainPageObject {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();

    }

    @Test
    public void test() {
        String username = "standard_user";
        String password = "secret_sauce";
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page has not been opened");
        loginPage.login(username, password);
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened());
        productPage.addToCart("Sauce Labs Bolt T-Shirt");
        productPage.getCartSelectedCount();
        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");

        //WebElement bucketIcon
        driver.get("https://www.saucedemo.com/cart.html");

        Assert.assertEquals(productPage.getCartSelectedCount(), "1", "Count is not equals 1");
        //
        driver.findElement(By.className("checkout_button")).click();
        driver.findElement(By.id("first-name")).sendKeys("asd");
        driver.findElement(By.id("last-name")).sendKeys("asd");
        driver.findElement(By.id("postal-code")).sendKeys("220059");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 1, "");
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='FREE PONY EXPRESS DELIVERY!']")).isDisplayed(), "Message good");
        driver.findElement(By.cssSelector(".btn_action.cart_button")).click();

        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "THANK YOU FOR YOUR ORDER", "Упс. Чтото пошло не так");
    }

    @Test
    public void test2() {
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement login = driver.findElement(By.className("btn_action"));
        login.click();

        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Page is incorrect.");

        List<WebElement> itemList = driver.findElements(By.cssSelector(".btn_primary.btn_inventory"));
        itemList.get(1).click();
        itemList.get(2).click();

        WebElement icon = driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge"));
        Assert.assertEquals(icon.getText(), "2", "Количество выбранный элементов не верно.");

        driver.get("https://www.saucedemo.com/cart.html");

        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2, "Количетсво добавленных элементов не равно 1");

        driver.findElement(By.className("checkout_button")).click();
        driver.findElement(By.id("first-name")).sendKeys("Alex");
        driver.findElement(By.id("last-name")).sendKeys("Trostyanko");
        driver.findElement(By.id("postal-code")).sendKeys("220000");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2, "Количетсво добавленных элементов не равно 1");
        Assert.assertTrue(driver.findElement(By.xpath("//div[text() = 'FREE PONY EXPRESS DELIVERY!']")).isDisplayed(), "Текст отсутствует.");
        driver.findElement(By.cssSelector(".btn_action.cart_button")).click();

        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(),
                "THANK YOU FOR YOUR ORDER", "Что-то пошло не так!!!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
