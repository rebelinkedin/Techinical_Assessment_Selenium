package eu.nuvolar.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

    @FindBy(className = "s-search-results")
    private WebElement searchResultContainer;

    public SearchResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void chooseProduct(int index){
        searchResultContainer.findElements(By.xpath("//h2//a")).get(index).click();
    }
}
