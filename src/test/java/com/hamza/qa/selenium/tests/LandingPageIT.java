package com.hamza.qa.selenium.tests;

import com.hamza.qa.selenium.pageobjects.DomainSearchResultsPage;
import com.hamza.qa.selenium.pageobjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LandingPageIT extends BaseClassIT {

    private HomePage homePage;
    private DomainSearchResultsPage domainSearchResultsPage;
    private final String homePageURL = "https://ca.godaddy.com/";

    @Test(dataProvider = "domainSearchTerms", description = "Verify whether the user can search a domain", priority = 1)
    public void verifyDomainSearch(String serchTerm) {
        homePage = new HomePage(uiDriver);

        uiDriver.get(homePageURL);
        Assert.assertTrue(homePage.verifyOnPage());
        homePage.enterSearchDomain(serchTerm);
        homePage.clickDomainSearch();

        domainSearchResultsPage = new DomainSearchResultsPage(uiDriver);
        Assert.assertTrue(domainSearchResultsPage.verifyOnPage());
        int resultsCount = domainSearchResultsPage.getResultsCount();

        logger.info("Search returned results count - " + resultsCount);

    }


    @DataProvider(name = "domainSearchTerms")
    public Object[][] domainSearchTerms() {
        return new Object[][]{
                {"selenium.com"},
                {"automation.com"}
        };
    }

}
