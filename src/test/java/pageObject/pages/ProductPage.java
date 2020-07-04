package pageObject.pages;

import io.qameta.allure.Step;
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

    @Step("Проверка открытия страницы Product")
    public boolean isPageOpened() {
        return driver.findElement(PRODUCTSELECTOR).isDisplayed();
    }

    @Step("Добавление товара в корзину на странице Product")
    public void addToCart(String name) {
        String tmpItemButtonidentificator = itemButtonidentificator.replace("replace", name);
        WebElement addCartButton = driver.findElement(By.xpath(tmpItemButtonidentificator));
        addCartButton.click();
    }

    @Step("Получение цены товара на странице Product")
    public String getPrice(String name) {
        String tmpItemPriceidentificator = itemPriceidentificator.replace("replace", name);
        WebElement price = driver.findElement(By.xpath(tmpItemPriceidentificator));
        return price.getText();
    }

    @Step("Проверка количества добавленных товаров в корзину на странице Product")
    public String getCartSelectedCount() {
        WebElement cartCount = driver.findElement(CARTCOUNTICONSELECTOR);
        return cartCount.getText();
    }

    @Step("Переход на страницу Cart на странице Product")
    public void goToBucket() {
        WebElement bucketIcon = driver.findElement(BUCKETICONSELECTOR);
        bucketIcon.click();
    }

    @Step("Получение описания товара на странице Product")
    public String getDescription(String name) {
        String tmpitemDescriptionidentificator = itemDescriptionidentificator.replace("replace", name);
        WebElement description = driver.findElement(By.xpath(tmpitemDescriptionidentificator));
        return description.getText();
    }

    @Step("Нажатие кнопки 'Add to cart' на странице Product")
    public String addButtonIsDisplayed(String name) {
        String tmpItemButtonidentificator = itemButtonidentificator.replace("replace", name);
        WebElement addCartButton = driver.findElement(By.xpath(tmpItemButtonidentificator));
        return addCartButton.getText();
    }
}
