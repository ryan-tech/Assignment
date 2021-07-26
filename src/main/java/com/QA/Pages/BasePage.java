package com.QA.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BasePage {
    protected static WebDriver driver;
    private WebDriverWait wait;
    protected static Properties prop;

    public BasePage() throws IOException {
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

        }
        wait = new WebDriverWait(driver, 5);

        prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/java/config.properties");
        prop.load(ip);
    }

    public static Properties getProperties() {return prop; }

    public static WebDriver getDriver() {
        return driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void click(WebElement w) {
        w.click();
    }

    public void sendKeys(WebElement w, String keys) {
        w.sendKeys(keys);
    }

    public Boolean isVisible(WebElement w) {
        return w.isDisplayed();
    }

    public void pageFactoryInit(Object c) {
        PageFactory.initElements(driver, c);
    }

    public void waitForElementToBeVisible(WebElement we) {
        wait.until(ExpectedConditions.visibilityOf(we));
    }

    public void waitForElementToBeClickable(WebElement we) {
        wait.until(ExpectedConditions.elementToBeClickable(we));
    }
}
