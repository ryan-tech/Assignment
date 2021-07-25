package com.QA.Pages.Aspiration;

import com.QA.Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"content-area\"]/div/spend-save-plans/div/div/div[1]")
    private WebElement aspirationPlan;

    @FindBy(xpath = "//*[@id=\"content-area\"]/div/spend-save-plans/div/div/div[2]")
    private WebElement aspirationPlus;

    @FindBy(xpath = "//*[@id=\"content-area\"]/div/spend-save-plans/div/div/div[1]/div[2]/button")
    private WebElement getAspirationPlanButton;

    @FindBy(xpath = "//*[@id=\"content-area\"]/div/spend-save-plans/div/div/div[2]/div[2]/button")
    private WebElement getAspirationPlusButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div[1]")
    private WebElement aspirationPlusYearlyOption;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div[2]")
    private WebElement aspirationPlusMonthlyOption;

    @FindBy(id = "join-waitlist-input")
    private WebElement aspirationPlanEmailField;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div[1]/p/b")
    private WebElement aPlusYearlyRate;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div[2]/p/b")
    private WebElement aPlusMonthlyRate;

    public ProductsPage() {
        super();
        super.pageFactoryInit(this);
    }

    public WebElement getAspirationPlan() {
        return aspirationPlan;
    }
    public WebElement getAspirationPlus() {
        return aspirationPlus;
    }
    public WebElement getGetAspirationPlanButton() {
        return getAspirationPlanButton;
    }
    public WebElement getGetAspirationPlusButton() {
        return getAspirationPlusButton;
    }
    public WebElement getAspirationPlusYearlyOption() {
        return aspirationPlusYearlyOption;
    }
    public WebElement getAspirationPlusMonthlyOption() {
        return aspirationPlusMonthlyOption;
    }
    public WebElement getAspirationPlanEmailField() {
        return aspirationPlanEmailField;
    }

    public Double getaPlusRate(String option) {
        String rateText = "";
        if(option.equals("YEARLY")) {
            waitForElementToBeVisible(aPlusYearlyRate);
            rateText = aPlusYearlyRate.getText();
        }
        else if(option.equals("MONTHLY")) {
            waitForElementToBeVisible(aPlusMonthlyRate);
            rateText = aPlusMonthlyRate.getText();
        }
        else {
            return 0.0;
        }

        if(rateText.equals("")) {
            return 0.0;
        }


        // remove dollar sign
        return Double.valueOf(rateText.substring(1));
    }

}
