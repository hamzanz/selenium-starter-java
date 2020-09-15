package com.hamza.qa.selenium.tests;

import com.hamza.qa.selenium.enums.Browser;
import com.hamza.qa.selenium.enums.ConfigKeys;
import com.hamza.qa.selenium.enums.Environment;
import com.hamza.qa.selenium.util.DefaultConfig;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestContext;

import java.util.concurrent.TimeUnit;


public abstract class AbstractBaseClass {

    protected String workingDir;
//    protected String baseUrl;
    protected Logger logger;
    protected Environment environment;
    protected Browser browser;
    protected DefaultConfig config;
    protected WebDriver uiDriver = null;


    public AbstractBaseClass() {
        logger = Logger.getLogger(AbstractBaseClass.class.getName());
    }

    /**
     * Initialize all the environment parameters from the parameter passed and launch the driver.
     * @param browser
     * @param env
     * @param context
     */
    protected void initializeTestEnvironment(String browser, String env, ITestContext context){
        // Test values
        browser = "chrome";


        workingDir = System.getProperty("user.dir");
        setEnvironment(env);
        setBrowser(browser);
        this.config = getConfig(this.environment);
//        this.baseUrl = this.config.getProperty(ConfigKeys.BASE_URL);

        launchBrowser();
    }

    private void launchBrowser() {
        if(this.browser == Browser.FIREFOX) {
            launchFirefoxDriver();
        } else if (this.browser == Browser.CHROME) {
            launchChromeDriver();
        }

        uiDriver.manage().deleteAllCookies();
        uiDriver.manage().window().maximize();
    }

    private void launchFirefoxDriver() {
        String driverLoc = "";
        String os = System.getProperty("os.name").toLowerCase();
        logger.info("os - " + os);
        if(os.contains("win")){
            driverLoc = workingDir + "/src/test/resources/drivers/geckodriver-v0.27.0-win64/geckodriver.exe";

        } else if(os.contains("mac")) {
            driverLoc = workingDir + "/src/test/resources/drivers/geckodriver-v0.27.0-macos/geckodriver";
        }
        else {
            driverLoc = workingDir + "/src/test/resources/drivers/geckodriver-v0.27.0-linux64/geckodriver";

        }

        logger.info("Driverloc - " + driverLoc);

        FirefoxProfile firefoxProfile = setFirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        logger.info("Launching firefox driver.");

        if(uiDriver == null){
            System.setProperty("webdriver.gecko.driver", driverLoc);
            firefoxOptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
            uiDriver = new FirefoxDriver(firefoxOptions);
        }
    }

    private void launchChromeDriver() {
        String driverLoc = "";
        String os = System.getProperty("os.name").toLowerCase();
        logger.info("os - " + os);
        if(os.contains("win")){
            driverLoc = workingDir + "/src/test/resources/drivers/chromedriver_win32/chromedriver.exe";

        } else if(os.contains("mac")) {
            driverLoc = workingDir + "/src/test/resources/drivers/chromedriver_mac64/chromedriver";
        }
        else {
            driverLoc = workingDir + "/src/test/resources/drivers/chromedriver_linux64/chromedriver";

        }
        logger.info("Driverloc - " + driverLoc);

        if(uiDriver == null){
            System.setProperty("webdriver.chrome.driver", driverLoc);
            uiDriver = new ChromeDriver();
        }
    }

    /**
     * Set the browser from parameter passed during test execution. Possible values are 'firefox', 'chrome', 'ie'
     * @param browser
     */
    private void setBrowser(String browser) {
        if(browser == null){
            this.browser = Browser.FIREFOX;
        }
        else if(browser.equalsIgnoreCase("chrome")){
            this.browser = Browser.CHROME;
        }else {
            this.browser = Browser.FIREFOX;
        }

        logger.debug("Setting the browser to - " + this.browser);
    }

    /**
     * Set environment from parameter passed during test execution.
     * @param env
     */
    private void setEnvironment(String env) {
        if(env == null){
            this.environment = Environment.PROD;
        }
        else if(env.equalsIgnoreCase("prod")){
            this.environment = Environment.PROD;
        }else{
            this.environment = Environment.QA;
        }

        logger.debug("Set the environment to - " + this.environment);
    }


    /**
     * The method is used to get the config file.
     *
     * @param env
     * @return config file
     * @author Hamza Nazeer
     */
    private DefaultConfig getConfig(Environment env) {
        String configPath;
        switch (env) {
            case PROD:
                configPath = "config/prod.cfg";
                break;
            default:
                configPath = "config/qa.cfg";
                break;
        }

        return new DefaultConfig(configPath);
    }

    /**
     * Method to set firefox profile.
     *
     * @return FirefoxProfile
     */
    private FirefoxProfile setFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("dom.max_script_run_time", "120");
        profile.setPreference("marionette",true);
        return profile;
    }

}
