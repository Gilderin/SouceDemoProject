package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private By CARTPAGESELECTOR = By.className("subheader");
    private By CHECKOUTBUTTONSELECTOR = By.cssSelector(".checkout_button");
    private By CONTINUESHOPPINGBUTTONSELECTOR = By.cssSelector(".cart_footer .btn_secondary");

    private String itemIdentificator = "//div[text()='replace']/ancestor::div[@class='cart_item']";
    private String itemPriceidentificator = itemIdentificator + "/descendant::div[@class='inventory_item_price']";
    private String itemNameidentificator = itemIdentificator + "/descendant::div[@class='inventory_item_name']";
    private String itemQuantityIdentificator = itemIdentificator + "/descendant::div[@class='cart_quantity']";
    private String itemDescriptionIdentificator = itemIdentificator + "/descendant::div[@class='inventory_item_desc']";
    private String removeItemButtonIsentificator = itemIdentificator + "/descendant::button[contains(@class,'btn_secondary')]";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка открытия страницы Cart")
    public boolean isPageOpened() {
        return driver.findElement(CARTPAGESELECTOR).isDisplayed();
    }

    @Step("Получение цены товара на странице Cart")
    public String getItemPrice(String name) {
        String tmpItemPriceIdentificator = itemPriceidentificator.replace("replace", name);
        WebElement itemPrice = driver.findElement(By.xpath(tmpItemPriceIdentificator));
        return itemPrice.getText();
    }

    @Step("Получение названия товара на странице Cart")
    public String getItemName(String name) {
        String tmpitemNameidentificator = itemNameidentificator.replace("replace", name);
        WebElement itemName = driver.findElement(By.xpath(tmpitemNameidentificator));
        return itemName.getText();
    }

    @Step("Получения описания товара на странице Cart")
    public String getItemDescription(String name) {
        String tmpitemDescriptionIdentificator = itemDescriptionIdentificator.replace("replace", name);
        WebElement itemDescription = driver.findElement(By.xpath(tmpitemDescriptionIdentificator));
        return itemDescription.getText();
    }

    @Step("Получения количества товара на странице Cart")
    public String getItemQuantity(String name) {
        String tmpitemQuantityIdentificator = itemQuantityIdentificator.replace("replace", name);
        WebElement itemQuantity = driver.findElement(By.xpath(tmpitemQuantityIdentificator));
        return itemQuantity.getText();
    }

    @Step("Удаление товара на странице Cart")
    public void removeItem(String name) {
        String tmpremoveItemButtonIsentificator = removeItemButtonIsentificator.replace("replace", name);
        WebElement removeItemButton = driver.findElement(By.xpath(tmpremoveItemButtonIsentificator));
        removeItemButton.click();
    }

    @Step("Нажатие кнопки 'Checkout'")
    public void checkout() {
        WebElement checkOutButton = driver.findElement(CHECKOUTBUTTONSELECTOR);
        checkOutButton.click();
    }

    @Step("Нажатие кнопки 'Continue Shopping'")
    public void continueShopping() {
        WebElement continueShoppingButton = driver.findElement(CONTINUESHOPPINGBUTTONSELECTOR);
        continueShoppingButton.click();
    }

}
