package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import runThrghTestNG.BaseClass;

public class LoginPage extends BaseClass {

    private static String username;

    /**
     * Attemps to login based on user type and values from property file
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
     * @return userName
     */
    public static String getUser() {
        return username;
    }
}
