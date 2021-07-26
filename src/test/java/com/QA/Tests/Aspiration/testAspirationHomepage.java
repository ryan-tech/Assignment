package com.QA.Tests.Aspiration;
import com.QA.Pages.Aspiration.Homepage;

import com.QA.Pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class testAspirationHomepage {
    private Homepage homepage;

    @BeforeTest
    public void beforeTest() throws IOException {
        homepage = new Homepage();

    }
    @AfterTest
    public void afterTest() {
        Homepage.getDriver().quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        String url = BasePage.getProperties().getProperty("initialNavigateUrl");
        // navigate to https://aspiration.com/
        homepage.navigateTo(url);

        // load elements with page factory
        homepage.pageFactoryInit(homepage);
    }

    @Test
    public void verifyHomepageURL() {
        String expectedUrl = BasePage.getProperties().getProperty("expectedHomepageUrl");

        // check that the web driver has navigated to the expected url properly
        Assert.assertEquals(Homepage.getDriver().getCurrentUrl(), expectedUrl);
    }

    @Test
    public void verifySpendAndSaveLink() {
        String expectedUrl = BasePage.getProperties().getProperty("expectedProductsUrl");
        // click on spend and save button
        homepage.click(homepage.getSpendAndSave());

        Assert.assertEquals(Homepage.getDriver().getCurrentUrl(), expectedUrl);
    }

}
