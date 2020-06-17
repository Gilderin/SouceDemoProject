package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage {
    private WebDriver driver;
    private By PRODUCTSELECTOR = By.className("product_label");
    private String itemidentificator = "//div[text()='i']/ancestor::div[@class='inventory_item']";
    private String itemButtonidentificator = itemidentificator+"/descendant::button";
    private String itemPriceidentificator = itemidentificator+"/descendant::div[@class='inventory_item_price']";
private By CARTCOUNTICONSELECTOR=By.cssSelector(".fa-layers-counter.shopping_cart_badge");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageOpened() {
        return driver.findElement(PRODUCTSELECTOR).isDisplayed();
    }

    public void addToCart(String name) {
        itemButtonidentificator = itemButtonidentificator.replace("i", name);
        WebElement addCartButton = driver.findElement(By.xpath(itemButtonidentificator));
        addCartButton.click();
    }

    public String getProve(String name){
        itemPriceidentificator=itemPriceidentificator.replace("i",name);
        WebElement price=driver.findElement(By.xpath(itemPriceidentificator));
        return price.getText();
    }
    public String getCartSelectedCount(){
        WebElement cartCount=driver.findElement(CARTCOUNTICONSELECTOR);
        return cartCount.getText();
    }
}
