/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login
{
    protected WebDriver driver = new FirefoxDriver();
    protected AccountValues av;

    //WebDriver driver=new FirefoxDriver();
    //private AccountValues av;
   
    // Constructor that attempts to navigate to login page
    public Login( String university )
    {  
        // Creates AccountValue object so we can access token values in property file
        av = new AccountValues( university );
       
        // Launches browser to login page
        driver.get( av.getTokenValue("programURL") );
    }
    
    // Fills in necessary fields on Login Page and clicks button to attempt login
    public void attemptLogin( String user ) throws Exception
    {
        // LoginPage class verifies the loaded page is correct.  Throws an exception if fails  
        LoginPage lp = new LoginPage( driver, av.getTokenValue("loginPageTitle") );
        
        // Holds the XPATH location for name, password and login button
        WebElement userName = driver.findElement( By.xpath(av.getTokenValue("userNameXPATH")) );
        WebElement passWord = driver.findElement( By.xpath(av.getTokenValue("pswdXPATH")) );
        WebElement btnLogin = driver.findElement( By.xpath(av.getTokenValue("btnLoginXPATH")) );
        
        switch( user )
        {
            case "student":
                Utility.mySendKeys( userName, av.getTokenValue("stdntUserName") );
                Utility.mySendKeys( passWord, av.getTokenValue("stdntPswd") );
                break;
                
            case "teacher":
                Utility.mySendKeys( userName, av.getTokenValue("tchrUserName") );
                Utility.mySendKeys( passWord, av.getTokenValue("tchrPswd") );
                break;
            
            case "pesAdmin":
                Utility.mySendKeys( userName, av.getTokenValue("pesUserName") );
                Utility.mySendKeys( passWord, av.getTokenValue("pesPswd") );
                break;
        }
        
        // Simulates click on Login button
        Utility.myButtonClick( btnLogin, av.getTokenValue("btnLoginXPATH") );
    }

}

