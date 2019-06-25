package eu.nuvolar.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(id = "nav-cart")
    private WebElement cartLink;

    @FindBy(id = "quantity")
    private WebElement quantityDropDownBox;

    public ProductPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void addToCart(){
        addToCartButton.click();
    }

    public void goToCart(){
        cartLink.click();
    }

    public void selectQuantity(String value){
        Select select = new Select(quantityDropDownBox);
        select.selectByValue(value);
    }
}
