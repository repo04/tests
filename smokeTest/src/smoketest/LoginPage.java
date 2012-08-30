package smoketest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

    IsPresent ip = new IsPresent();
    private static String usr;

    public LoginPage(WebDriver driver, AccountValues av) {

        super(driver, av);
        driver.get(av.getTokenValue("programURL"));
    }

    // Attemps to login based on user type and values from property file
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

            default:
                userName.sendKeys(user);
                passWord.sendKeys("Tech@123");
                break;
        }

        loginBtn.click();

        //PES Admin gets Course Page after login
        if (user.equals("pesAdmin")) {
            ip.isTitlePresent(driver, av.getTokenValue("crsPageTitle"));
        } else {
            ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));
        }
    }

    public static String getUser() {
        return usr;
    }
}
