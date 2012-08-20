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
    
    public void attemptLogin( String user ) {
        
        WebElement userName = driver.findElement( By.xpath(av.getTokenValue("userNameXPATH")) );
        WebElement passWord = driver.findElement( By.xpath(av.getTokenValue("pswdXPATH")) );
        WebElement loginBtn = driver.findElement( By.xpath(av.getTokenValue("btnLoginXPATH")) );
        
        switch( user ) {
            
            case "student":
                Utility.mySendKeys( userName, av.getTokenValue("stdntUserName") );
                Utility.mySendKeys( passWord, av.getTokenValue("stdntPswd") );
                break;
        }
        
        Utility.myButtonClick( loginBtn );
        
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("homePageTitle") );
            
    }
}

