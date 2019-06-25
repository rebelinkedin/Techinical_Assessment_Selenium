package eu.nuvolar.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage {

    private static final String NUMERIC = "[^0-9.]";
    private WebDriver driver;

    @FindBy(className = "sc-product-price")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//span[contains(@class, 'a-button-dropdown') and contains(@class, 'quantity')]")
    private List<WebElement> quantityDropDownBox;

    @FindBy(id = "sc-subtotal-amount-activecart")
    private WebElement subTotal;

    @FindBy(className = "quantity")
    private List<WebElement> quantityBox;

    @FindBy(id = "sc-subtotal-label-activecart")
    private WebElement totalQuantity;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getProductPriceWithoutCurrency(int position) {

        String productPrice = productPrices.get(position).getText();
        return Double.parseDouble(productPrice.replaceAll(NUMERIC, ""));
    }

    public int getProductQuantity(int position) {
        String quantity = quantityDropDownBox.get(position).getText();
        return Integer.parseInt(quantity);
    }

    public double getSubTotal() {
        String productPrice = subTotal.getText();
        return Double.parseDouble(productPrice.replaceAll(NUMERIC, ""));
    }

    public int getTotalQuantity() {
        String quantity = totalQuantity.getText().split(":")[0];
        return Integer.parseInt(quantity.replaceAll(NUMERIC, ""));
    }

    public void modifyQuantity(int elementPosition, int newQuantity) {
        WebElement element = quantityBox.get(elementPosition);
        element.click();
        List<WebElement> dropDownElements = driver.findElements(By.className("a-dropdown-item"));
        dropDownElements.get(newQuantity - 1).click();
    }
}