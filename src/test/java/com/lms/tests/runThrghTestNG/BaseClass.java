/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.lms.tests.smoketest.IsPresent;
import com.lms.tests.smoketest.Utility;
import com.lms.tests.smoketest.XpathValues;

@Listeners({com.lms.tests.runThrghTestNG.TestNGCustomReport.class})
public class BaseClass {

    public static XpathValues xpv, ldv;
    public static WebDriver driver;
    public IsPresent ip = new IsPresent();
    public static String program;
    public static String environment;
    public static String browser;
    public static String test;
    public static String url;
    public static String currentURL;
    public static File directory = new File(".");

    /**
     * The annotated method will be run before any test method belonging to the
     * classes inside the <test> tag is run. Following parameter values are
     * received through 'Run Target' specified in build.xml. TestNG allows to
     * perform sophisticated groupings of test methods which is called from XML
     * file
     *
     * @param url
     * @param program
     * @param environment
     * @param browser
     * @param os
     * @param test
     * @throws Exception
     */
    @BeforeTest(groups = {"prerequisite"})
    @Parameters({"url", "program", "environment", "browser", "os", "test"})
    public void setUp(String url, String program, String environment, String browser, String os, String test) throws Exception {

        this.program = program;
        this.environment = environment;
        this.browser = browser;
        this.test = test;
        this.url = url;

        xpv = new XpathValues("xPathAccountProperty");
        ldv = new XpathValues("loginDetails");
        System.out.println("url: " + url);
        System.out.println("program: " + this.program);
        System.out.println("environment: " + this.environment);
        System.out.println("browser: " + this.browser);
        System.out.println("os: " + os);
        System.out.println("test: " + this.test);

        switch (browser) {
            case "chrome":
                String chromDrvrPath;
                chromDrvrPath = directory.getCanonicalPath() + File.separator + "lib" + File.separator;

                os:
                switch (os) {
                    case "linux32":
                    case "linux64":
                    case "mac":
                        System.out.println("initialize OS: " + os);
                        System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver");
                        break os;
                    case "win":
                        System.out.println("initialize  OS: " + os);
                        System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver.exe");
                        break os;
                    default:
                        Utility.illegalStateException("Invalid OS paramter, expected values 'linux32||linux64||mac||win'");
                }

                //ChromeOptions feature does not work on 'MAC' OS
                if (os.equalsIgnoreCase("mac")) {
                    driver = new ChromeDriver();
                    ((JavascriptExecutor) driver).executeScript("window.open('','chromeBrowser','width=1280,height=800,top=0,left=0')");
                    driver.close();
                    driver.switchTo().window("chromeBrowser");
                } else {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-extensions");
                    driver = new ChromeDriver(options);
                }
                Reporter.log("Browser: " + browser);
                Reporter.log("OS: " + os);
                break;
            case "ie":
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability("nativeEvents", false);
                //caps.setCapability("nativeEvents", true);
                //caps.setCapability("ignoreZoomSetting", true);
                //caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(caps);
                ip.isTitlePresent(driver, "WebDriver");
                Reporter.log("Browser: IE");
                break;
            default:
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                Reporter.log("Browser: firefox");                
        }

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
}
