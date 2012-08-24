package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPage extends Page {
    
    public LoginPage( WebDriver driver, AccountValues av ) {        
        
        super( driver, av );
        
        driver.get( av.getTokenValue("programURL") );     
    }
    
    // Attemps to login based on user type and values from property file
    public void attemptLogin( String user ) {
        
        WebElement userName = driver.findElement( By.xpath(av.getTokenValue("userNameXPATH")) );
        WebElement passWord = driver.findElement( By.xpath(av.getTokenValue("pswdXPATH")) );
        WebElement loginBtn = driver.findElement( By.xpath(av.getTokenValue("btnLoginXPATH")) );
        
        switch( user ) {
            
            case "student":
                userName.sendKeys( av.getTokenValue("stdntUserName") );
                passWord.sendKeys( av.getTokenValue("stdntPswd") );
                break;
            
            case "teacher":
                userName.sendKeys( av.getTokenValue("tchrUserName") );
                passWord.sendKeys( av.getTokenValue("tchrPswd") );
                break;
                
            case "pesAdmin":
                userName.sendKeys( av.getTokenValue("pesUserName") );
                passWord.sendKeys( av.getTokenValue("pesPswd") );
                break;
                
            case "contentAdmin":
                userName.sendKeys( av.getTokenValue("ctntAdminUserName") );
                passWord.sendKeys( av.getTokenValue("ctntAdminPswd") );
                break;
        }
        loginBtn.click();
        
        if ( user.equals("pesAdmin") ) {
            Utility.myVerifyCurrentPage( driver, av.getTokenValue("crsPageTitle") );
        }
        else {
            Utility.myVerifyCurrentPage( driver, av.getTokenValue("homePageTitle"));
        }
    }
}

