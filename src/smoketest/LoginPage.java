package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import runThrghTestNG.BaseClass;

public class LoginPage extends BaseClass {

    private static String usr;

    /**
     * Attemps to login based on user type and values from property file
     * @param user 
     */
    public void attemptLogin(String user) {

        LoginPage.usr = user;
        WebElement userName = driver.findElement(By.xpath(xpv.getTokenValue("userNameXPATH")));
        WebElement passWord = driver.findElement(By.xpath(xpv.getTokenValue("pswdXPATH")));
        WebElement loginBtn = driver.findElement(By.xpath(xpv.getTokenValue("btnLoginXPATH")));

        switch (user) {

            case "contentAdmin":
                userName.sendKeys(pv.getTokenValue("ctntAdminUserName"));
                passWord.sendKeys(pv.getTokenValue("ctntAdminPswd"));
                break;

            case "pesAdmin":
                userName.sendKeys(pv.getTokenValue("pesUserName"));
                passWord.sendKeys(pv.getTokenValue("pesPswd"));
                break;

            //Teacher/Student
            default:
                userName.sendKeys(user);
                passWord.sendKeys("Moodle1!");
                break;
        }

        loginBtn.click();

        //PesAdmin navigates to Course page after login
        if (user.equals("pesAdmin")) {
            ip.isTitlePresent(driver, pv.getTokenValue(this.program + this.env + "crsPageTitle"));
        } else {
            ip.isTitlePresent(driver, pv.getTokenValue(this.program + this.env + "homePageTitle"));
        }
    }

    /**
     * @return userName
     */
    public static String getUser() {
        return usr;
    }
}
