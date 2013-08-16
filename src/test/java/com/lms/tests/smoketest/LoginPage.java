package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseClass {

    private final static String USERROLE = "userrole";    
    
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

        Utility.put(USERROLE, user);
        WebElement userName = new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath(xpv.getTokenValue("userNameXPATH"))));
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
        return Utility.getString(USERROLE);
    }
}
