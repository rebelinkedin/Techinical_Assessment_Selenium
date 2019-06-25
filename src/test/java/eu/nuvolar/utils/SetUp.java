package eu.nuvolar.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetUp {

    private static final String FILE_PATH =  "C:\\chromedriver.exe";
    private static final String WEBSITE =  "https://www.amazon.com/";
    private WebDriver driver;

    public SetUp(){
        System.setProperty("webdriver.chrome.driver", FILE_PATH);
        driver = new ChromeDriver();
        driver.get(WEBSITE);
        driver.manage().window().maximize();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
