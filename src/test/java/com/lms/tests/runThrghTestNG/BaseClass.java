
package com.lms.tests.runThrghTestNG;

import java.io.File;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.lms.tests.smoketest.IsPresent;
import com.lms.tests.smoketest.Utility;
import com.lms.tests.smoketest.XpathValues;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

@Listeners({SauceOnDemandTestListener.class})
public class BaseClass implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    public String os;
    public String url;
    public String browser;
    public String currentURL;
    public static String test;
    private RemoteWebDriver driver;
    private final static String WEBDRIVER = "webdriver";
    private final static String PROGRAM = "program";
    public final static File directory = new File(".");
    private SauceOnDemandAuthentication authentication = 
        new SauceOnDemandAuthentication("someshbansal","10c353c4-24e9-434c-811d-f3aba9e14213");    
    public static XpathValues xpv; 
    public static XpathValues ldv;
    public IsPresent ip = new IsPresent();
    DesiredCapabilities capabilities;
   
   /*
    * The method will be run before any test method belonging to the
    * classes inside the <test> tag. Paramaters are passed via build.xml.
    */
    @BeforeTest(groups = {"prerequisite"})
    @Parameters({"url", "program", "browser", "os", "test", "session"})
    public void setUp(String url, String program, String browser, String os,
                String test, String session) throws Exception {
        xpv = new XpathValues("xPathAccountProperty");
        ldv = new XpathValues("loginDetails");
        
        this.os      = os;
        this.url     = url;
        this.test    = test;
        this.browser = browser;


        // Why do we only set version for FF and not Chrome?
        switch (browser) {
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;

            default:
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("version", "20");
        }

        os:
        switch (os) {
            case "linux32":
            case "linux64":
                capabilities.setCapability("platform", Platform.LINUX);
                break os;
            case "mac":
                capabilities.setCapability("platform", Platform.MAC);
                break os;
            default:
                capabilities.setCapability("platform", "WINDOWS 7");
        }
        capabilities.setCapability("name", session);
        capabilities.setCapability("idle-timeout", 180);
        this.driver = new RemoteWebDriver(new URL("http://" + authentication.getUsername() + 
                                             ":" + authentication.getAccessKey() + 
                                             "@ondemand.saucelabs.com:80/wd/hub"),
                                             capabilities);
        driver.setFileDetector(new LocalFileDetector());
        driver.get(this.url);
        Utility.putDriver(WEBDRIVER, driver);
        Utility.put(PROGRAM, program);
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("loginPageURL"));        
    }

    @Override
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver) getWebdriver()).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

    public static RemoteWebDriver getWebdriver() {
        RemoteWebDriver driver = (RemoteWebDriver) Utility.getDriver(WEBDRIVER);
        return driver;
    }

    public static String getProgram() {
        return Utility.getString(PROGRAM);
    }
    
    @AfterTest(alwaysRun = true, groups = {"prerequisite"})
    public void tearDown() throws Exception {
        getWebdriver().quit();
    }
}
