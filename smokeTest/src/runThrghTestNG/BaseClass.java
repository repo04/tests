/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import smoketest.AccountValues;
import smoketest.IsPresent;
import smoketest.Utility;

public class BaseClass {
    
    public static AccountValues av;
    public static WebDriver driver;
    public IsPresent ip = new IsPresent();
    String chromDrvrPath;

    //The annotated method will be run before any test method belonging to the classes inside the <test> tag is run
    @BeforeTest
    @Parameters({"program", "drvr", "os"})
    public void setUp(String program, String drvr, String os) throws Exception {
        
        av = new AccountValues(program);
        switch (drvr) {
            case "chrome":
                File directory = new File(".");
                chromDrvrPath = directory.getCanonicalPath() + File.separator + "lib" + File.separator;                
                
                os:
                switch (os) {
                    case "linux32":
                    case "linux64":
                    case "mac":
                        System.out.println(os);
                        System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver");
                        break os;
                    case "win":
                        System.out.println(os);
                        System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver.exe");
                        break os;
                    default:
                        Utility.illegalStateException("Invalid OS paramter passed, expected values {linux32||linux64||mac||win}");                    
                }
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
        }
        
        if (os.equalsIgnoreCase("win") || os.equalsIgnoreCase("${antOS}")) {
            driver.manage().window().maximize();
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
