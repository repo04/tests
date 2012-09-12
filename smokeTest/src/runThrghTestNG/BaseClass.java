/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

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
    @Parameters({"program", "drvr"})
    public void setUp(String program, String drvr) throws Exception {

        switch (drvr) {
            case "Chrome":
                driver = new ChromeDriver();
            default:
                driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        av = new AccountValues(program);
        driver.get(av.getTokenValue("programURL"));
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
    }

    //The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have run 
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
