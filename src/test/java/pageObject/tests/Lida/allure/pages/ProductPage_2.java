package pageObject.tests.Lida.allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.tests.Lida.allure.pages.BasePage_2;

public class ProductPage_2 extends BasePage_2 {

    private By productLocator = By.className("product_label");
    private String itemLocator = "//div[text()='replace']/ancestor::div[@class='inventory_item']";
    private String itemButtonLocator = itemLocator + "/descendant::button";
    private String itemPriceLocator = itemLocator + "/descendant::div[@class='inventory_item_price']";
    private By cartCountIconLocator = By.cssSelector(".fa-layers-counter.shopping_cart_badge");
    private By BUCKETICONSELECTOR = By.tagName("svg");


    public ProductPage_2(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return driver.findElement(productLocator).isDisplayed();
    }

    public WebElement getAddCartButton(String itemName) {
        return driver.findElement(By.xpath(updateLocator(itemButtonLocator, itemName)));
    }

    public String getPrice(String name) {
        WebElement price = driver.findElement(By.xpath(updateLocator(itemPriceLocator, name)));
        return price.getText();
    }

    public String getCartSelectedCount() {
        WebElement cartCount = driver.findElement(cartCountIconLocator);
        return cartCount.getText();
    }
    public void goToBucket() {
        WebElement bucketIcon = driver.findElement(BUCKETICONSELECTOR);
        bucketIcon.click();
    }
    @Step("Add item {name} to bucket.")
    public void addToCart(String name) {
        getAddCartButton(name).click();

    }
}
