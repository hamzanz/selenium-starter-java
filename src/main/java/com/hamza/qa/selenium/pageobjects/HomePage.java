package com.hamza.qa.selenium.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final String PAGE_TITLE = "Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy CA";
    private WebDriver uiDriver;

    @FindBy(name = "domainToCheck")
    @CacheLookup
    private WebElement inputSearch;

    @FindBy(xpath = "//form[@name='domainsearchform']//button[@type='Submit']")
    @CacheLookup
    private WebElement btnSearch;

    public HomePage (WebDriver driver){
        uiDriver = driver;
        PageFactory.initElements(uiDriver, this);
    }

    @Step("Verify user on the page")
    public boolean verifyOnPage(){
        if (uiDriver.getTitle().contains(PAGE_TITLE)) {
            return true;
        } else {
            return false;
        }
    }

    @Step("Enter somain search term - {searchDomain}")
    public void enterSearchDomain (String searchDomain) {
        inputSearch.clear();
        inputSearch.sendKeys(searchDomain);
    }

    @Step("Click Domain Search button")
    public void clickDomainSearch () {
        btnSearch.click();
    }
}
