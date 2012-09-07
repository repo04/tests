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
        WebElement userName = driver.findElement(By.xpath(av.getTokenValue("userNameXPATH")));
        WebElement passWord = driver.findElement(By.xpath(av.getTokenValue("pswdXPATH")));
        WebElement loginBtn = driver.findElement(By.xpath(av.getTokenValue("btnLoginXPATH")));

        switch (user) {

            case "contentAdmin":
                userName.sendKeys(av.getTokenValue("ctntAdminUserName"));
                passWord.sendKeys(av.getTokenValue("ctntAdminPswd"));
                break;

            case "pesAdmin":
                userName.sendKeys(av.getTokenValue("pesUserName"));
                passWord.sendKeys(av.getTokenValue("pesPswd"));
                break;

            //Teacher/Student
            default:
                userName.sendKeys(user);
                passWord.sendKeys("Tech@123");
                break;
        }

        loginBtn.click();

        //PesAdmin navigates to Course page after login
        if (user.equals("pesAdmin")) {
            ip.isTitlePresent(driver, av.getTokenValue("crsPageTitle"));
        } else {
            ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));
        }
    }

    /**
     * @return userName
     */
    public static String getUser() {
        return usr;
    }
}
