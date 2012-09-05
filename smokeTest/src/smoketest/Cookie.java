/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author somesh.bansal
 */
public class Cookie {

    static IsPresent ip = new IsPresent();

    public static void main(String[] args) throws Exception {

        // And now output all the available cookies for the current URL
        /*Set<Cookie> allCookies = driver.manage().getCookies();
         for (Cookie loadedCookie : allCookies) {
         System.out.println(loadedCookie.getDomain() +":"+ String.format("%s -> %s", loadedCookie.getName(), loadedCookie.getValue()));
         }
        
         Cookie usrnm = new Cookie("Username", "bkang");
         Cookie pswd = new Cookie("Password", "yXK9QQ(GQbJk");
         driver.manage().addCookie(usrnm); 
         driver.manage().addCookie(pswd);*/
        
        //Initiate Firefox Driver
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("capability.policy.strict.Window.confirm", "noAccess");
        profile.setPreference("network.http.phishy-userpass-length", 255);
        profile.setPreference("network.automatic-ntlm-auth.trusteduris", ".2tor.com");
        profile.setPreference("network.automatic-ntlm-auth.trusteduris", "gu-msn-lms-www-20-stg.2tor.com");
        profile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", "true");
        profile.setPreference("network.ntlm.send-lm-response", "true");
        
        WebDriver driver = new FirefoxDriver(profile);
        System.out.println("COOKIE");
        //WebDriver driver = new FirefoxDriver();

        //Maximise the window
        driver.manage().window().maximize();
        AccountValues av = new AccountValues("guAccountProperty");
        //driver.get("https://contentadmin:Moodle1!@gu-msn-lms-www-20-stg.2tor.com/local/index.php");
        driver.get("https:gu-msn-lms-www-20-stg.2tor.com");

        Set<org.openqa.selenium.Cookie> allCookies = driver.manage().getCookies();
        for (org.openqa.selenium.Cookie loadedCookie : allCookies) {
            
            System.out.println(loadedCookie.getDomain() + ":" + String.format("%s -> %s", loadedCookie.getName(), loadedCookie.getValue()));
        }

        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));

        WebElement userName = driver.findElement(By.xpath(av.getTokenValue("userNameXPATH")));
        WebElement passWord = driver.findElement(By.xpath(av.getTokenValue("pswdXPATH")));
        WebElement loginBtn = driver.findElement(By.xpath(av.getTokenValue("btnLoginXPATH")));

        userName.sendKeys(av.getTokenValue("ctntAdminUserName"));
        passWord.sendKeys(av.getTokenValue("ctntAdminPswd"));

        loginBtn.click();
        ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));

        driver.close();
        WebDriver driver2 = new FirefoxDriver(profile);
        driver2.get("https://gu-msn-lms-www-20-stg.2tor.com/local/index.php");
        ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));
    }
}
