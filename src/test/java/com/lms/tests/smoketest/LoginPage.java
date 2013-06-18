package com.lms.tests.smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.lms.tests.runThrghTestNG.BaseClass;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends BaseClass {

    private final static String USERROLE = "userrole";
    private static ThreadLocal<Map<String, String>> 
            sessions = new InheritableThreadLocal<>();
    
//    private EventFiringWebDriver driver;
//
//    public LoginPage(EventFiringWebDriver driver) {
//        this.driver = driver;
//    }

    private RemoteWebDriver driver;
    
    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;        
    }
    /**
     * Attempts to login based on user type and values from property file
     *
     * @param user
     */
    public void attemptLogin(String user) {

        put(USERROLE, user);
        WebElement userName = driver.findElement(By.xpath(xpv.getTokenValue("userNameXPATH")));
        WebElement passWord = driver.findElement(By.xpath(xpv.getTokenValue("pswdXPATH")));
        WebElement loginBtn = driver.findElement(By.xpath(xpv.getTokenValue("btnLoginXPATH")));

        userName.clear();
        passWord.clear();

        switch (user) {
            case "contentAdmin":
                userName.sendKeys(ldv.getTokenValue("ctntAdminUserName"));
                break;
            case "pesAdmin":
                userName.sendKeys(ldv.getTokenValue("pesUserName"));
                break;
            //Teacher/Student
            default:
                userName.sendKeys(user);
                break;
        }
        passWord.sendKeys(ldv.getTokenValue("password"));
        loginBtn.click();

        //PesAdmin navigates to Course page after login
        if (user.equals("pesAdmin")) {
            Utility.verifyCurrentUrl(driver, xpv.getTokenValue("myCourseURL"));
        } else {
            Utility.verifyCurrentUrl(driver, xpv.getTokenValue("homePageURL"));
        }
    }

    /**
     * @return user role
     */
    public static String getUser() {
        return getUserRole(USERROLE);
    }
    
    private static void put(String key, String value) {
        getSession().put(key, value);
    }
    
    private static String getUserRole(String key) {
        return getSession().get(key);
    }
    
    private static Map<String, String> getSession() {
        Map<String, String> res = sessions.get();
        if (res == null) {
            res = new HashMap<>();
            sessions.set(res);
        }
        return res;
    }
}
