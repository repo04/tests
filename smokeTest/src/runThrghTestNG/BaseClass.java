/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import smoketest.AccountValues;
import smoketest.IsPresent;
import smoketest.Utility;

@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class BaseClass {

    public static AccountValues av;
    public static WebDriver driver;
    public IsPresent ip = new IsPresent();
    public static String program;
    public static String drvr;
    public static String test;
    String chromDrvrPath;

    //The annotated method will be run before any test method belonging to the classes inside the <test> tag is run
    @BeforeTest
    @Parameters({"program", "drvr", "os", "test"})
    public void setUp(String program, String drvr, String os, String test) throws Exception {

        this.program = program;
        this.drvr = drvr;
        this.test = test;
        
        av = new AccountValues(this.program);
        System.out.println("program: " + this.program);
        System.out.println("drvr: " + this.drvr);
        System.out.println("os: " + os);
        switch (drvr) {
            case "chrome":
                File directory = new File(".");
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
                    ((JavascriptExecutor) driver).executeScript("window.open('','chromeBrwsr','width=1280,height=800,top=0,left‌​=0')");
                    driver.close();
                    driver.switchTo().window("chromeBrwsr");
                } else {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                }
                Reporter.log("Browser: " + drvr);
                Reporter.log("OS: " + os);
                break;
            default:
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                Reporter.log("Browser: firefox");
        }
        driver.get(av.getTokenValue("programURL"));
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
    }

    //The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have run 
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
