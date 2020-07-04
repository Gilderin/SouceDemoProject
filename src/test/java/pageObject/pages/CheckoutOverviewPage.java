package pageObject.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {

    private By CHECKOUTOVERVIEWSELECTOR = By.className("subheader");
    private By FINISHBUTTONSELECTOR = By.cssSelector(".btn_action.cart_button");
    private By CANCELBUTTONSELECTOR = By.className("cart_cancel_link btn_secondary");
    private By PAYMENTINFORMATIONSELECTOR = By.xpath("//div[text()='SauceCard #31337']");
    private By SHIPPINGINFORMATIONSELECTOR = By.xpath("//div[text()='FREE PONY EXPRESS DELIVERY!']");
    private By ITEMTOTALSELECTOR = By.className("summary_subtotal_label");
    private By TAXSELECTOR = By.className("summary_tax_label");
    private By TOTALSELECTOR = By.className("summary_total_label");


    private String itemIdentificator = "//div[text()='replace']/ancestor::div[@class='cart_item']";
    private String itemNameIdentificator = itemIdentificator + "/descendant::div[@class='inventory_item_name']";
    private String itemPriceIdentificator = itemIdentificator + "/descendant::div[@class='inventory_item_price']";
    private String itemDescriptionIdentificator = itemIdentificator + "/descendant::div[@class='inventory_item_desc']";

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка открытия страницы Checkout: Overview")
    public boolean isPageOpened() {
        return driver.findElement(CHECKOUTOVERVIEWSELECTOR).isDisplayed();
    }

    @Step("Получение имени товара на странице Checkout: Overview")
    public String getName(String name) {
        String tmpItemNameIdenficator = itemNameIdentificator.replace("replace", name);
        WebElement itemName = driver.findElement(By.xpath(tmpItemNameIdenficator));
        return itemName.getText();
    }

    @Step("Получение цены товара на странице Checkout: Overview")
    public String getPrice(String name) {
        String tmpItemPriceIdentificator = itemPriceIdentificator.replace("replace", name);
        WebElement price = driver.findElement(By.xpath(tmpItemPriceIdentificator));
        return price.getText().replace("$", "");
    }

    @Step("Получение описание товара на странице Checkout: Overview")
    public String getDescription(String name) {
        String tmpItemDescriptionIdentificator = itemDescriptionIdentificator.replace("replace", name);
        WebElement description = driver.findElement(By.xpath(tmpItemDescriptionIdentificator));
        return description.getText();
    }

    @Step("Нажатие кнопки 'Finish' на странице Checkout: Overview")
    public void finish() {
        WebElement finishButton = driver.findElement(FINISHBUTTONSELECTOR);
        finishButton.click();
    }

    @Step("Нажатие кнопки 'Cancel' на странице Checkout: Overview")
    public void cancel() {
        WebElement cancelButton = driver.findElement(CANCELBUTTONSELECTOR);
        cancelButton.click();
    }

    @Step("Получение информации об оплате товара на странице Checkout: Overview")
    public String getPaymentInformation() {
        WebElement paymentInformation = driver.findElement(PAYMENTINFORMATIONSELECTOR);
        return paymentInformation.getText();
    }

    @Step("Получение информации о доставке товара на странице Checkout: Overview")
    public String getShippingInformation() {
        WebElement shippingInformation = driver.findElement(SHIPPINGINFORMATIONSELECTOR);
        return shippingInformation.getText();
    }

    @Step("Получение цены заказа на странице Checkout: Overview")
    public String getItemTotal() {
        WebElement itemTotal = driver.findElement(ITEMTOTALSELECTOR);
        return itemTotal.getText().replace("Item total: $", "");
    }

    @Step("Получение стоимости налогов заказа на странице Checkout: Overview")
    public String getTax() {
        WebElement itemTax = driver.findElement(TAXSELECTOR);
        return itemTax.getText().replace("Tax: $", "");
    }

    @Step("Получение общей цены заказа на странице Checkout: Overview")
    public String getTotal() {
        WebElement total = driver.findElement(TOTALSELECTOR);
        return total.getText().replace("Total: $", "");
    }
}
