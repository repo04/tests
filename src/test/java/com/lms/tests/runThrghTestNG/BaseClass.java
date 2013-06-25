/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.testng.Reporter;

@Listeners({SauceOnDemandTestListener.class})
public class BaseClass implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    //Add your username & key here
    private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("someshbansal", "10c353c4-24e9-434c-811d-f3aba9e14213");
    public static XpathValues xpv, ldv;
    public IsPresent ip = new IsPresent();
    public String browser;
    public static String test;
    public String url;
    public String os;
    public String currentURL;
    public static File directory = new File(".");
    private final static String WEBDRIVER = "webdriver";
    private final static String PROGRAM = "program";
    DesiredCapabilities capabilities;

    /**
     * The annotated method will be run before any test method belonging to the
     * classes inside the <test> tag is run. Following parameter values are
     * received through 'Run Target' specified in build.xml. TestNG allows to
     * perform sophisticated groupings of test methods which is called from XML
     * file
     *
     * @param url
     * @param program
     * @param browser
     * @param os
     * @param test
     * @throws Exception
     */
    @BeforeTest(groups = {"prerequisite"})
    @Parameters({"url", "program", "browser", "os", "test", "session"})
    public void setUp(String url, String program, String browser, String os,
                String test, String session) throws Exception {

        RemoteWebDriver driver;
        this.browser = browser;
        this.test = test;
        this.url = url;
        this.os = os;

        xpv = new XpathValues("xPathAccountProperty");
        ldv = new XpathValues("loginDetails");
        System.out.println("url: " + url);
        System.out.println("program: " + program);
        System.out.println("browser: " + this.browser);
        System.out.println("os: " + os);
        System.out.println("test: " + this.test);

        switch (browser) {
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                Reporter.log("Browser: " + browser);
                Reporter.log("OS: " + os);
                break;
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                Reporter.log("Browser: IE");
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
        driver = new RemoteWebDriver(new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        driver.setFileDetector(new LocalFileDetector());
        driver.get(this.url);
        Utility.putDriver(WEBDRIVER, driver);
        Utility.put(PROGRAM, program);
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("loginPageURL"));        
    }

    /*protected static RemoteWebDriver getDriver() {
     return threadLocalDriver.get();
     }*/
    /**
     * The annotated method will be run after all the test methods belonging to
     * the classes inside the <test> tag have run.
     *
     * @throws Exception
     */
    @AfterTest(alwaysRun = true, groups = {"prerequisite"})
    public void tearDown() throws Exception {
        getWebdriver().quit();
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
}
