package com.hamza.qa.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DomainSearchResultsPage {

    private final String PAGE_TITLE = "GoDaddy Domain Name Search";
    private WebDriver uiDriver;

    @FindAll(@FindBy(xpath = "//div[@class='dpp-results']/div/div"))
    private List<WebElement> searchResults;


    public DomainSearchResultsPage (WebDriver driver) {
        uiDriver = driver;
        PageFactory.initElements(uiDriver, this);
    }

    public boolean verifyOnPage(){
        if (uiDriver.getTitle().contains(PAGE_TITLE)) {
            return true;
        } else {
            return false;
        }
    }

    public int getResultsCount() {
        return searchResults.size();
    }
}