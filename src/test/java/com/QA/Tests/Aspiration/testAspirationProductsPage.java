package com.QA.Tests.Aspiration;

import com.QA.Pages.Aspiration.ProductsPage;
import com.QA.Pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testAspirationProductsPage{
    private ProductsPage productsPage;

    @BeforeTest
    public void beforeClass() {
        productsPage = new ProductsPage();
    }

    @BeforeMethod
    public void beforeMethod() {
        ProductsPage.getDriver().get(BasePage.getProperties().getProperty("expectedProductsUrl"));
        ProductsPage.getDriver().navigate().refresh();
        productsPage.pageFactoryInit(productsPage);
    }

    @Test(priority = 1)
    public void testAspirationProductsAreVisible() {
        // assert visibility of both card products, Aspiration and Aspiration Plus
        Assert.assertTrue(productsPage.isVisible(productsPage.getAspirationPlan()));
        Assert.assertTrue(productsPage.isVisible(productsPage.getAspirationPlus()));
    }

    @Test(priority = 2)
    public void testAspirationBasePlanEmailForm() {
        // wait until element is clickable
        productsPage.waitForElementToBeClickable(productsPage.getGetAspirationPlanButton());

        // click on "get aspiration"
        productsPage.click(productsPage.getGetAspirationPlanButton());

        // check to see that the email prompt has opened
        productsPage.waitForElementToBeVisible(productsPage.getAspirationPlanEmailField());
        Assert.assertTrue(productsPage.isVisible(productsPage.getAspirationPlanEmailField()));

    }

    // Verifies that the monthly and yearly payment plans are visible when the aspiration plus membership button is clicked
    @Test
    public void testAspirationPlusOptions() {
        // show plus options
        productsPage.waitForElementToBeClickable(productsPage.getGetAspirationPlusButton());
        productsPage.click(productsPage.getGetAspirationPlusButton());
        
        // check to see that the monthly and yearly options are visible
        productsPage.waitForElementToBeVisible(productsPage.getAspirationPlusMonthlyOption());
        Assert.assertTrue(productsPage.isVisible(productsPage.getAspirationPlusMonthlyOption()));

        productsPage.waitForElementToBeVisible(productsPage.getAspirationPlusYearlyOption());
        Assert.assertTrue(productsPage.isVisible(productsPage.getAspirationPlusYearlyOption()));

    }


    // Verifies that the costs of the monthly and yearly plans match what is expected.
    @Test
    public void testAspirationPlusPaymentPlans() {
        // show the plus membership prices
        productsPage.waitForElementToBeClickable(productsPage.getGetAspirationPlusButton());
        productsPage.click(productsPage.getGetAspirationPlusButton());

        // expected rates
        Double expectedMonthlyRate = 7.99;
        Double expectedYearlyRate = 71.88;

        // actual rates
        Double actualYearlyRate = productsPage.getaPlusRate("YEARLY");
        Double actualMonthlyRate = productsPage.getaPlusRate("MONTHLY");

        // assertions
        Assert.assertEquals(actualYearlyRate, expectedYearlyRate);
        Assert.assertEquals(actualMonthlyRate, expectedMonthlyRate);
    }
}
