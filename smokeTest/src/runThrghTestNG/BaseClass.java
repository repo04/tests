/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import smoketest.AccountValues;
import smoketest.IsPresent;

public class BaseClass {

    public static AccountValues av;
    public static WebDriver driver;
    public IsPresent ip = new IsPresent();
    
    //The annotated method will be run before the first test method in the class is invoked
    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        av = new AccountValues("guAccountProperty");
        driver.get(av.getTokenValue("programURL"));
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
    }
    
    //The annotated method will be run after all the test methods in the class have been run. 
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }    
}
