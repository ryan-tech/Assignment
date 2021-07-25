package com.QA.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected static WebDriver driver;
    private WebDriverWait wait;

    public BasePage() {
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

        }
        wait = new WebDriverWait(driver, 5);
    }

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
