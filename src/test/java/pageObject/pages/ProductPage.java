package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    private By PRODUCTSELECTOR = By.className("product_label");
    private String itemidentificator = "//div[text()='replace']/ancestor::div[@class='inventory_item']";
    private String itemButtonidentificator = itemidentificator + "/descendant::button";
    private String itemPriceidentificator = itemidentificator + "/descendant::div[@class='inventory_item_price']";
    private String itemDescriptionidentificator = itemidentificator + "/descendant::div[@class='inventory_item_desc']";
    private By CARTCOUNTICONSELECTOR = By.cssSelector(".fa-layers-counter.shopping_cart_badge");
    private By BUCKETICONSELECTOR = By.tagName("svg");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return driver.findElement(PRODUCTSELECTOR).isDisplayed();
    }

    public void addToCart(String name) {
        String tmpItemButtonidentificator = itemButtonidentificator.replace("replace", name);
        WebElement addCartButton = driver.findElement(By.xpath(tmpItemButtonidentificator));
        addCartButton.click();
    }

    public String getPrice(String name) {
        String tmpItemPriceidentificator = itemPriceidentificator.replace("replace", name);
        WebElement price = driver.findElement(By.xpath(tmpItemPriceidentificator));
        return price.getText();
    }

    public String getCartSelectedCount() {
        WebElement cartCount = driver.findElement(CARTCOUNTICONSELECTOR);
        return cartCount.getText();
    }

    public void goToBucket() {
        WebElement bucketIcon = driver.findElement(BUCKETICONSELECTOR);
        bucketIcon.click();
    }

    public String getDescription(String name) {
        String tmpitemDescriptionidentificator = itemDescriptionidentificator.replace("replace", name);
        WebElement description = driver.findElement(By.xpath(tmpitemDescriptionidentificator));
        return description.getText();
    }
}
