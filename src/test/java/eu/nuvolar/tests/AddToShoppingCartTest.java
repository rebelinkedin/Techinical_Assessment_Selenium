package eu.nuvolar.tests;

import eu.nuvolar.components.ProductPage;
import eu.nuvolar.utils.SetUp;
import eu.nuvolar.components.ShoppingCartPage;
import eu.nuvolar.components.HomePage;
import eu.nuvolar.components.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddToShoppingCartTest {

    private static final String HATS_FOR_MEN = "hats for men";
    private static final String HATS_FOR_WOMEN = "hats for women";
    private WebDriver driver;
    private SetUp setUp = new SetUp();
    private HomePage homePage;
    private SearchResultPage resultPage;
    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;

    @BeforeTest
    public void setUp() {
        driver = setUp.getDriver();

        homePage = new HomePage(driver);
        resultPage = new SearchResultPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test(description = "Add items to shopping cart in Amazon and verify the quantity and total price are correct")
    public void verityQuantityAndTotalPrice() {

        addItemsToShoppingCart(HATS_FOR_MEN, 2);

        double menHatPrice = shoppingCartPage.getProductPriceWithoutCurrency(0);
        int menHatQuantity = shoppingCartPage.getProductQuantity(0);
        double totalPrice = menHatPrice * menHatQuantity;
        double subTotal = shoppingCartPage.getSubTotal();

        assertThat(menHatQuantity, is(2));
        assertThat(subTotal, is(roundPriceToTwoDecimals(totalPrice)));

        addItemsToShoppingCart(HATS_FOR_WOMEN, 1);
        waitUntilSubTotalChange(subTotal);

        double womenHatPrice = shoppingCartPage.getProductPriceWithoutCurrency(0);
        int womenHatQuantity = shoppingCartPage.getProductQuantity(0);
        int totalQuantity = shoppingCartPage.getTotalQuantity();

        totalPrice = (menHatPrice * menHatQuantity) + (womenHatPrice * womenHatQuantity);
        subTotal = shoppingCartPage.getSubTotal();

        assertThat(menHatQuantity, is(2));
        assertThat(womenHatQuantity, is(1));
        assertThat(totalQuantity, is(menHatQuantity + womenHatQuantity));
        assertThat(subTotal, is(roundPriceToTwoDecimals(totalPrice)));

        shoppingCartPage.modifyQuantity(1, 1);
        waitUntilSubTotalChange(subTotal);

        menHatQuantity = shoppingCartPage.getProductQuantity(1);
        menHatPrice = shoppingCartPage.getProductPriceWithoutCurrency(1);
        totalQuantity = shoppingCartPage.getTotalQuantity();
        totalPrice = (menHatPrice * menHatQuantity) + (womenHatPrice * womenHatQuantity);
        subTotal = shoppingCartPage.getSubTotal();

        assertThat(menHatQuantity, is(1));
        assertThat(womenHatQuantity, is(1));
        assertThat(totalQuantity, is(menHatQuantity + womenHatQuantity));
        assertThat(subTotal, is(roundPriceToTwoDecimals(totalPrice)));
    }

    @AfterTest
    public void quitBrowser() {

        driver.quit();
    }

    private void addItemsToShoppingCart(String searchWord, Integer quantity) {
        homePage.inputSearch(searchWord);
        homePage.clickSearchButton();
        resultPage.chooseProduct(0);
        productPage.selectQuantity(quantity.toString());
        productPage.addToCart();
        productPage.goToCart();
    }

    private void waitUntilSubTotalChange(final double prevSubTotal) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until((v) -> shoppingCartPage.getSubTotal() != (prevSubTotal));
    }

    private double roundPriceToTwoDecimals(double totalPrice) {
        BigDecimal totalPriceRound = BigDecimal.valueOf(totalPrice);
        totalPriceRound = totalPriceRound.setScale(2, RoundingMode.HALF_UP);
        return totalPriceRound.doubleValue();
    }
}
