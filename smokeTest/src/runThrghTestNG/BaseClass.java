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

public class BaseClass {

    public static AccountValues av;
    public static WebDriver driver;
    public IsPresent ip = new IsPresent();

    //The annotated method will be run before any test method belonging to the classes inside the <test> tag is run
    @BeforeTest
    @Parameters({"program", "drvr", "os"})
    public void setUp(String program, String os, String drvr) throws Exception {

        av = new AccountValues(program);
        File directory = new File(".");
        switch (drvr) {
            case "Chrome":
                os:
                switch (os) {
                    case "Linux32":
                        System.setProperty("webdriver.chrome.driver", directory.getCanonicalPath() + File.separator + "lib" + File.separator + "chromedriver_linux32"
                                + File.separator + "chromedriver.exe");
                        break os;
                    case "Linux64":
                        System.setProperty("webdriver.chrome.driver", directory.getCanonicalPath() + File.separator + "lib" + File.separator + "chromedriver_linux64"
                                + File.separator + "chromedriver.exe");
                        break os;
                    case "Mac":
                        System.setProperty("webdriver.chrome.driver", directory.getCanonicalPath() + File.separator + "lib" + File.separator + "chromedriver_mac"
                                + File.separator + "chromedriver.exe");
                        break os;
                    default:
                        System.setProperty("webdriver.chrome.driver", directory.getCanonicalPath() + File.separator + "lib" + File.separator + "chromedriver_win"
                                + File.separator + "chromedriver.exe");
                }
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(av.getTokenValue("programURL"));
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
    }

    //The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have run 
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
