/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.lms.tests.smoketest.IsPresent;
import com.lms.tests.smoketest.Utility;
import com.lms.tests.smoketest.XpathValues;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Reporter;

//@Listeners({TestNGCustomReport.class})
@Listeners({SauceOnDemandTestListener.class})
public class BaseClass implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    //Ben - Add your username & key here
    private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("", "");
    public static XpathValues xpv, ldv;
    public static WebDriver driver;
    public IsPresent ip = new IsPresent();
    public static String program;
    public static String browser;
    public static String test;
    public static String url;
    public static String currentURL;
    public static File directory = new File(".");
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
    @Parameters({"url", "program", "browser", "os", "test"})
    public void setUp(String url, String program, String browser, String os, String test) throws Exception {

        this.program = program;
        this.browser = browser;
        this.test = test;
        this.url = url;

        xpv = new XpathValues("xPathAccountProperty");
        ldv = new XpathValues("loginDetails");
        System.out.println("url: " + url);
        System.out.println("program: " + this.program);
        System.out.println("browser: " + this.browser);
        System.out.println("os: " + os);
        System.out.println("test: " + this.test);

        switch (browser) {
            case "chrome":
                //ChromeOptions feature does not work on 'MAC' OS
                if (os.equalsIgnoreCase("mac")) {
                    /*driver = new ChromeDriver();
                     ((JavascriptExecutor) driver).executeScript("window.open('','chromeBrowser','width=1280,height=800,top=0,left=0')");
                     driver.close();
                     driver.switchTo().window("chromeBrowser");
                     */
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability("platform", Platform.MAC);

                } else {
                    /*ChromeOptions options = new ChromeOptions();
                     options.addArguments("--start-maximized");
                     options.addArguments("--disable-extensions");
                     driver = new ChromeDriver(options);
                     capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability("platform", Platform.LINUX);
                }
                Reporter.log("Browser: " + browser);
                Reporter.log("OS: " + os);
                break;
            case "ie":
                /*capabilities = DesiredCapabilities.internetExplorer();
                 capabilities.setCapability("nativeEvents", false);
                 caps.setCapability("nativeEvents", true);
                 caps.setCapability("ignoreZoomSetting", true);
                 caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                 driver = new InternetExplorerDriver(capabilities);
                 ip.isTitlePresent(driver, "WebDriver");*/
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability("platform", Platform.WIN8);
                Reporter.log("Browser: IE");
                break;
            default:
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("version", "20");
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
                        capabilities.setCapability("platform", Platform.WIN8);
                }
            /*driver = new FirefoxDriver();
             driver.manage().window().maximize();
             Reporter.log("Browser: firefox");*/
        }

        capabilities.setCapability("name", this.test);
        driver = new RemoteWebDriver(new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        driver.get(this.url);
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("loginPageURL"));
    }

    /**
     * The annotated method will be run after all the test methods belonging to
     * the classes inside the <test> tag have run.
     *
     * @throws Exception
     */
    @AfterTest(alwaysRun = true, groups = {"prerequisite"})
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Override
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }
}
