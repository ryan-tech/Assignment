package com.QA.Pages.Aspiration;
import com.QA.Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

    @FindBy(xpath = "//*[@id=\"__next\"]/div/header/ul[1]/li[1]/a")
    private WebElement spendAndSave;

    public Homepage() {
        super();
    }

    public WebElement getSpendAndSave() {
        return spendAndSave;
    }
}
