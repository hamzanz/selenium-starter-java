package com.hamza.qa.selenium.tests;

import com.hamza.qa.selenium.enums.ConfigKeys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClassIT extends AbstractBaseClass {

    protected String baseUrl;


    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "environment"})
    public void setupClassTest(
            @Optional("null") String browser,
            @Optional("null") String environment,
            ITestContext context){

        logger.info("Executing Before class...");
        initializeTestEnvironment(browser, environment, context);
//        initializeTestEnvironment("FF", "qa", context);

        this.baseUrl = this.config.getProperty(ConfigKeys.BASE_URL);
        uiDriver.get(this.baseUrl);
        logger.debug("Launched firefox driver with the baseurl - " + baseUrl );

    }

    @AfterClass(alwaysRun = true)
    public void afterClassTest() {
        if (uiDriver != null) {
            uiDriver.manage().deleteAllCookies();
            uiDriver.quit();
        }
    }

    public WebDriver getUiDriver(){
        return this.uiDriver;
    }


}
