package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseClass {

    private static String username;
    Actions a = new Actions();

    /**
     * Attempts to login based on user type and values from property file
     *
     * @param user
     */
    public void attemptLogin(String user) {
        LoginPage.username = user;
        WebElement userName = driver.findElement(By.xpath(xpv.getTokenValue("userNameXPATH")));
        WebElement passWord = driver.findElement(By.xpath(xpv.getTokenValue("pswdXPATH")));
        WebElement loginBtn = driver.findElement(By.xpath(xpv.getTokenValue("btnLoginXPATH")));

        userName.clear();
        passWord.clear();

        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        int i;
        for (i = 1; i < elements.length; i++) {
            if (elements[i - 1].getClassName().equalsIgnoreCase("com.lms.tests.runThrghTestNG.Student_JoinSocialGroup_Post")) {
                break;
            }
        }

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
        if (test.equalsIgnoreCase("regressionTests")
                && elements[i - 1].getClassName().contains("Student_JoinSocialGroup_Post")) {
            passWord.sendKeys("Moodle2!");
        } else {
            passWord.sendKeys(ldv.getTokenValue("password"));        
        }
        loginBtn.click();

        //PesAdmin navigates to Course page after login
        if (user.equals("pesAdmin")) {
            Utility.verifyCurrentUrl(driver, xpv.getTokenValue("myCourseURL"));
        } else {
            if (test.equalsIgnoreCase("regressionTests")
                    && elements[i - 1].getClassName().contains("Student_JoinSocialGroup_Post")) {
                Utility.verifyCurrentUrl(driver, xpv.getTokenValue("settingsPageURL"));
            } else {
                Utility.verifyCurrentUrl(driver, xpv.getTokenValue("homePageURL"));
            }
        }
    }

    /**
     * @return userName
     */
    public static String getUser() {
        return username;
    }
}
