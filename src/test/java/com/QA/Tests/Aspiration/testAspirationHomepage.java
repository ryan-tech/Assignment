package com.QA.Tests.Aspiration;
import com.QA.Pages.Aspiration.Homepage;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class testAspirationHomepage {
    private Homepage homepage;

    @BeforeTest
    public void beforeTest() {
        homepage = new Homepage();

    }
    @AfterTest
    public void afterTest() {
        Homepage.getDriver().quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        String url = "https://aspiration.com/";
        // navigate to https://aspiration.com/
        homepage.navigateTo(url);

        // load elements with page factory
        homepage.pageFactoryInit(homepage);
    }

    @Test
    public void verifyHomepageURL() {
        String expectedUrl = "https://www.aspiration.com/";

        // check that the web driver has navigated to the expected url properly
        Assert.assertEquals(Homepage.getDriver().getCurrentUrl(), expectedUrl);
    }

    @Test
    public void verifySpendAndSaveLink() {
        String expectedUrl = "https://www.aspiration.com/our-products";
        // click on spend and save button
        homepage.click(homepage.getSpendAndSave());

        Assert.assertEquals(Homepage.getDriver().getCurrentUrl(), expectedUrl);
    }

}
